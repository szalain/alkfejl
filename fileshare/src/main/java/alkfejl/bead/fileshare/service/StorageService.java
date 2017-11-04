package alkfejl.bead.fileshare.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("upload-dir");

    @Autowired
    private FileRepository fileRepository;

    public void store(MultipartFile file, String path){
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            File virtualFile = new File();
            virtualFile.setPath(path);
            virtualFile.setFileName(file.getOriginalFilename());
            virtualFile.setEditLevel(0);
            virtualFile.setOwner(0);
            virtualFile.setViewLevel(0);
            virtualFile.setFullPath(path+file.getOriginalFilename());
            fileRepository.save(virtualFile);
            Files.move(this.rootLocation.resolve(file.getOriginalFilename()), this.rootLocation.resolve(file.getOriginalFilename()).resolveSibling("10"));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
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

    public String findID(String fullPath) {
        return fileRepository.findByFullPath(fullPath).get().getId().toString();
    }
}