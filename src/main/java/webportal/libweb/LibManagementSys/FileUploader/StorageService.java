package webportal.libweb.LibManagementSys.FileUploader;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    public void init();
    public void upload(MultipartFile file);
    public void deleteByName(String fileName);
}
