package com.itm.ecosurprise.domain.port.in;

public interface FileStorageService {
    String storeFile(byte[] file, String fileName, String subfolder);
}
