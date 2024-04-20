package webportal.libweb.FileUploader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService{

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties prop){

        if(prop.getLocation().trim().length() == 0){
            throw new StorageException("File upload location can not be Empty."); 
        }

        this.rootLocation = Paths.get(prop.getLocation());
    }

    @Override
    public void upload(MultipartFile file) {
        try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			Path destinationFile = this.rootLocation.resolve(
					Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();
            System.out.println(destinationFile.toString());
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
    }

    @Override
    public void init() {
        try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
    }

}