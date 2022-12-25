package com.example.chamcong.business;

import com.example.chamcong.dto.*;
import com.example.chamcong.entity.Department;
import com.example.chamcong.entity.News;
import com.example.chamcong.entity.User;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.request.ChainPasswordRequest;
import com.example.chamcong.model.request.UpdateInformationRequest;
import com.example.chamcong.model.response.ProfileUserResponse;
import com.example.chamcong.model.response.IdResponse;
import com.example.chamcong.repository.DepartmentRepository;
import com.example.chamcong.repository.NewsRepository;
import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.service.AvatarService;
import com.example.chamcong.utils.HashUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class
UserBusiness extends BaseBusiness {

    private final UserRepository userRepository;

    private final HashUtils hashUtils;

    private final DepartmentRepository departmentRepository;

    private final NewsRepository newsRepository;

    private final AvatarService avatarService;


    public UserBusiness(UserRepository userRepository, HashUtils hashUtils, DepartmentRepository departmentRepository, NewsRepository newsRepository, AvatarService avatarService) {
        this.userRepository = userRepository;
        this.hashUtils = hashUtils;
        this.departmentRepository = departmentRepository;
        this.newsRepository = newsRepository;
        this.avatarService = avatarService;
    }


    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    private final String root = "Avatar\\";

    private final Path rootPath = Path.of(("Avatar"));

    public ProfileUserResponse profileEmployee(){
        User user = getCurrentUser();
        ProfileUserResponse response = new ProfileUserResponse();
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setStaffCode(user.getStaffCode());
        response.setAddress(user.getAddress());
        response.setAvatar(String.format("http://localhost:8081/%s",user.getNameAvatar()));
        response.setDateOfBirth(user.getDateOfBirth());
        response.setPhone(user.getPhone());
        response.setDepartment(user.getDepartment().getDepartment());
        response.setPosition((user.getPosition().getPosition()));
        return response;
    }

    public IdResponse staffUpdateInformation(UpdateInformationRequest input) {
        User user = getCurrentUser();
        user.setFullName(input.getFullName());
        user.setDateOfBirth(input.getDateOfBirth());
        user.setAddress(input.getAddress());
        user.setPhone(input.getPhone());
        user.setAvatar(input.getAvatar());
        userRepository.save(user);
        return new IdResponse(user.getId());
    }
    public IdResponse chainPassword(ChainPasswordRequest input) {
        User userLogin = getCurrentUser();
        Optional<User> optUser = userRepository.findByIdPassword(userLogin.getId());
        if (optUser.isEmpty()) {
            throw new DataNotFoundException("Đặt lại mật khẩu không thành công");
        }
        if (!hashUtils.check(input.getOldPassword(), optUser.get().getPassword())) {
            throw new DataNotFoundException("Mật khẩu cũ đúng");
        }
        userRepository.save(userLogin.setPassword(hashUtils.hash(input.getNewPassword())));
        return new IdResponse(userLogin.getId());

    }

    public List<DepartmentDTO> department() {
        List<Department> department = departmentRepository.getAllByDepartment();
        List<DepartmentDTO> dto = department.stream().map(department1 -> {
            DepartmentDTO dto1 = new DepartmentDTO();
            dto1.setId(department1.getId());
            dto1.setDepartment(department1.getDepartment());
            return dto1;
        }).collect(Collectors.toList());

        return  dto;
    }

    public List<NewsDTO> news() {
        List<News> news = newsRepository.getAllByNews();
        List<NewsDTO> newsDTO = news.stream().map(news1 -> {
            NewsDTO dto = new NewsDTO();
            dto.setNameNews(news1.getTypeOfNews());
            dto.setContent(news1.getContent());
            return dto;
        }).collect(Collectors.toList());
        return newsDTO;
    }

    public List<UserDTO>  listStaff() {
        List<User> users  = userRepository.findUser();
        List<UserDTO> list = users.stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setFullName(user.getFullName());
            dto.setDepartment(user.getDepartment().getDepartment());
            dto.setPosition(user.getPosition().getPosition());
            dto.setDateOfBirth(user.getDateOfBirth());
            dto.setAvatar(user.getAvatar());
            dto.setAddress(user.getAddress());
            return dto;
        }).collect(Collectors.toList());
        return list;
    }

    public String uploadImage(MultipartFile file) throws IOException {
         User user = getCurrentUser();
        String name = file.getOriginalFilename().split("[.]")[0] + UUID.randomUUID();
        String type = file.getOriginalFilename().split("[.]")[1];
        String fileName = name + "." + type;
        String filePath = root + fileName;
        avatarService.saveFileFolder(file,fileName);
         user.setAvatar(filePath);
         user.setNameAvatar(fileName);
         userRepository.save(user);
        return filePath;
    }

    public byte[] downloadImage(String fileName) throws IOException {
        User fileData = getCurrentUser();
        String filePath = fileData.getAvatar();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }


    public UserDTOId getUserId(Integer id) {
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isEmpty()){
            throw new DataNotFoundException("tài khoản không tồn tại");
        }
        UserDTOId dtoId = new UserDTOId();
        dtoId.setFullName(optUser.get().getFullName());
        dtoId.setRole(optUser.get().getRole().getId());
        dtoId.setAvatar(optUser.get().getAvatar());
        dtoId.setDepartment(optUser.get().getDepartment().getId());
        dtoId.setPosition(optUser.get().getPosition().getId());
        dtoId.setAddress(optUser.get().getAddress());
        dtoId.setDateOfBirth(optUser.get().getDateOfBirth());
        return dtoId;
    }
}
