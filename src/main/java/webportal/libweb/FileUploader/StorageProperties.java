package webportal.libweb.FileUploader;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	private String uploadDir = "D://bookCovers/";

	public String getDir() {
		return uploadDir;
	}

	public void setDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

}
