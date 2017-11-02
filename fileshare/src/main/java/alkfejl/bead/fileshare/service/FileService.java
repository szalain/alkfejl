package alkfejl.bead.fileshare.service;

import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public void uploadFile(File file) {
        fileRepository.save(file);
    }
}
