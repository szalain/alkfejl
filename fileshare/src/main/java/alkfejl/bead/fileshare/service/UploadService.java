package alkfejl.bead.fileshare.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import alkfejl.bead.fileshare.api.UserApiController;
import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.repository.FileRepository;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.annotations.Role;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import static alkfejl.bead.fileshare.model.User.Role.*;


@Service
public class UploadService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("upload-dir");

    @Autowired
    private UserService userService;

    @Autowired
    private UserApiController userApiController;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UploadService storageService;

    public void store(MultipartFile file, String path) throws Exception {

            User user = userService.getUser();
            if (user==null || !userService.isValid(user) || userService.isBanned(user)) {
                throw new UserNotValidException();
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            File virtualFile = new File();
            if(!this.isDirPresent(path)) {
                throw new FileNotFoundException("No such directory!");
            }
            virtualFile.setPath(path);
            virtualFile.setFileName(file.getOriginalFilename());
            virtualFile.setEditLevel(0);
            virtualFile.setOwner(user);
            virtualFile.setViewLevel(0);
            if(fileRepository.findByFullPath(path+file.getOriginalFilename()).isPresent()) {
                throw new FileAlreadyExistsException("The file is already present!");
            }
            virtualFile.setFullPath(path+file.getOriginalFilename());
            virtualFile.setDir(false);
            fileRepository.save(virtualFile);
            String newName = fileRepository.findByFullPath(path+file.getOriginalFilename()).get().getId().toString();
            Files.move(this.rootLocation.resolve(file.getOriginalFilename()), this.rootLocation.resolve(file.getOriginalFilename()).resolveSibling(newName));

    }

    public void store(String location, String name) throws Exception {
            User user = userService.getUser();
            if (user==null || !userService.isValid(user) || userService.isBanned(user)) {
                throw new UserNotValidException();
            }
            File virtualFile = new File();
            virtualFile.setFileName(name+"/");
            virtualFile.setPath(location);
            if(!this.isDirPresent(location)) {
                throw new FileNotFoundException("No such directory!");
            }
            virtualFile.setEditLevel(0);
            virtualFile.setOwner(user);
            virtualFile.setViewLevel(0);
            String fullPath = location+name+"/";
            if(fileRepository.findByFullPath(fullPath).isPresent()) {
                throw new FileAlreadyExistsException("The directory is already present!");
            }
            virtualFile.setFullPath(fullPath);
            virtualFile.setDir(true);
            fileRepository.save(virtualFile);
    }

    public Resource loadFile(String restOfTheUrl) throws Exception {
        try {
            if(!fileRepository.findByFullPath(restOfTheUrl).isPresent()) {
                throw new FileNotFoundException("File not found!");
            }
            String filename = storageService.findID(restOfTheUrl);
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public Iterable<File> findAll() {
        return fileRepository.findAll();
    }

    public boolean isDirPresent(String fullPath) {
        return fileRepository.findByFullPath(fullPath).isPresent();
    }

    public String findID(String fullPath) {
        String s = fileRepository.findByFullPath(fullPath).get().getId().toString();
        return s;
    }

    public Iterable<File> findAllByPath(String path) throws Exception {
        if(!this.isDirPresent(path)) {
            throw new FileNotFoundException("No such directory!");
        }
        return fileRepository.findByPath(path);
    }

    public File showFile(String restOfTheUrl) throws Exception {
        if (!fileRepository.findByFullPath(restOfTheUrl).isPresent()) {
            throw new FileNotFoundException("File not found!");
        }
        File file = fileRepository.findByFullPath(restOfTheUrl).get();
        return file;

        }

}