package webportal.libweb.LibManagementSys.FileUploader;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	private String uploadDir = "E://bookCovers/";

	public String getDir() {
		return uploadDir;
	}

	public void setDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

}
