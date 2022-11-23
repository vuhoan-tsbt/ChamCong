package com.example.chamcong.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    public void saveFileFolder(MultipartFile file, String fileName) throws IOException;


}
