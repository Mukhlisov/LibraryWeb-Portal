package webportal.libweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import webportal.libweb.LibManagementSys.FileUploader.StorageProperties;
import webportal.libweb.LibManagementSys.FileUploader.StorageService;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class LibwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibwebApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
