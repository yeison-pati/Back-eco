package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.port.in.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageApplicationService implements FileStorageService {

    private final HttpServletRequest request;

    @Autowired
    public FileStorageApplicationService(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String storeFile(byte[] file, String fileName, String subfolder) {
        try {
            if (file != null && file.length > 0) {
                String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
                String folder = "src/main/resources/static/" + subfolder + "/";
                File directory = new File(folder);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                Path path = Paths.get(folder + uniqueFileName);
                Files.copy(new java.io.ByteArrayInputStream(file), path, StandardCopyOption.REPLACE_EXISTING);

                String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                return baseUrl + "/" + subfolder + "/" + uniqueFileName;
            } else {
                throw new RuntimeException("File is empty");
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
