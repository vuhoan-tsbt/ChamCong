package com.example.chamcong.controller;

import com.example.chamcong.business.UserBusiness;
import com.example.chamcong.model.RootResponse;
import com.example.chamcong.model.response.IdAvatarResponse;
import com.example.chamcong.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequestMapping(value = "/avatar/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UploadAvatarController {

    private final AvatarService avatarService;
    private final UserBusiness userBusiness;

    public UploadAvatarController(AvatarService avatarService, UserBusiness userBusiness) {
        this.avatarService = avatarService;
        this.userBusiness = userBusiness;
    }

    @PostMapping("/upload")
    public RootResponse<IdAvatarResponse> createImages(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String name = file.getOriginalFilename().split("[.]")[0] + UUID.randomUUID();
            String type = file.getOriginalFilename().split("[.]")[1];
            String fileName = name + "." + type;
            avatarService.saveFileFolder(file,fileName);
            message = "upload the file success:" + fileName;
            return RootResponse.success("", new IdAvatarResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file" + file.getOriginalFilename() + "!";
        }
        return RootResponse.success("", new IdAvatarResponse(message));
    }
    @PostMapping("/upload_images")
    public RootResponse<Object> uploadImage(@RequestBody MultipartFile file) throws IOException {
        return RootResponse.success("upload images success", userBusiness.uploadImage(file));
    }

    @GetMapping("/images/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = userBusiness.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
