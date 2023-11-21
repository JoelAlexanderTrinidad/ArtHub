package com.mindhub.merchshop.servicios;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
    String uploadFile(File file, String fileName) throws IOException;
    File convertToFile(MultipartFile multipartFile, String fileName) throws Exception;
    String getExtension(String fileName);
    String upload(MultipartFile multipartFile) throws Exception;
}