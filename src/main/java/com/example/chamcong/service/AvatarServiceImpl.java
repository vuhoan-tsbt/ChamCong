package com.example.chamcong.service;

import com.example.chamcong.entity.User;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AvatarServiceImpl implements AvatarService{

    private final UserRepository userRepository;

    public AvatarServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    private final Path rootPath = Path.of(("Avatar"));

    @Override
    public void saveFileFolder(MultipartFile file, String fileName) throws IOException {
        try {
            Files.copy(file.getInputStream(), this.rootPath.resolve(fileName));
        } catch (Exception e) {
            throw new DataNotFoundException("Could not store the file. Error:" + e.getMessage());
        }
    }


}
