package alkfejl.bead.fileshare;

import alkfejl.bead.fileshare.service.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileshareApplication  implements CommandLineRunner{

	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(FileshareApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}