package com.github.mukhlisov.fileSystemImageStorage;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileSystemStorageService implements StorageService{

	private final String noCover;
    private final Path rootLocation;

	@Autowired
    public FileSystemStorageService(StorageProperties prop, @Value("${void-file}") String noCover){

        if(prop.getDir().trim().isEmpty()){
            throw new StorageException("File upload location can not be Empty."); 
        }
		this.noCover = noCover;
        this.rootLocation = Paths.get(prop.getDir());
    }

    @Override
    public void upload(MultipartFile file, String fileName) {
        try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			Path destinationFile = this.rootLocation
					.resolve(Paths.get(fileName))
					.normalize().toAbsolutePath();
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

			Path destinationFile = this.rootLocation
					.resolve(Paths.get(noCover))
					.normalize().toAbsolutePath();
			try (InputStream inputStream = new ClassPathResource("static/" + noCover).getInputStream()) {
				Files.copy(inputStream, destinationFile,
						StandardCopyOption.REPLACE_EXISTING);
			}

		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
    }

	@Override
	public void deleteByName(String fileName) throws IOException {
		Path fileToDelete = rootLocation.resolve(fileName).normalize().toAbsolutePath();
		Files.deleteIfExists(fileToDelete);
	}

	@Override
	public byte[] getByName(String fileName) throws IOException {
		if (fileName.isEmpty()) {
			fileName = noCover;
		}
		Path fileToGet = rootLocation.resolve(fileName).normalize().toAbsolutePath();
		return Files.readAllBytes(fileToGet);
	}
}
