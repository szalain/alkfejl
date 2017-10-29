package hu.elte.alkfelj.dataman.dataman.Controller;

import hu.elte.alkfelj.dataman.dataman.Entity.Request.AddFileRequest;
import hu.elte.alkfelj.dataman.dataman.Entity.File;
import hu.elte.alkfelj.dataman.dataman.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/files")
public class FileController {
    private FileRepository fileRepository;

    @Autowired
    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<File> findAllFiles() {
        return fileRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addFile(@RequestBody AddFileRequest addFileRequest) {
        File file = new File();
        file.setPath(addFileRequest.getPath());
        file.setEditLevel(addFileRequest.getEditLevel());
        file.setViewLevel(addFileRequest.getViewLevel());
        file.setOwner(addFileRequest.getOwner());
        fileRepository.save(file);
    }
}
