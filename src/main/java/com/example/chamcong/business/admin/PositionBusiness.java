package com.example.chamcong.business.admin;

import com.example.chamcong.business.BaseBusiness;
import com.example.chamcong.entity.Position;
import com.example.chamcong.entity.User;
import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.PageResponse;
import com.example.chamcong.model.request.CreatePositionRequest;
import com.example.chamcong.model.request.PositionRequest;
import com.example.chamcong.model.response.IdPositionResponse;
import com.example.chamcong.model.response.PositionResponse;
import com.example.chamcong.repository.PositionRepository;
import com.example.chamcong.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionBusiness extends BaseBusiness {

    private final PositionRepository positionRepository;

    private final UserRepository userRepository;

    public PositionBusiness(PositionRepository positionRepository, UserRepository userRepository) {
        this.positionRepository = positionRepository;
        this.userRepository = userRepository;
    }

    public PageResponse<PositionResponse> listPosition(PositionRequest input) {
        List<Position> positionList = positionRepository.findByAllPosition(input);
        int totalElements = positionRepository.getAllPosition(input);
        int totalPages = Math.max(1, (int) Math.ceil((double) totalElements / input.getSize()));
        List<PositionResponse> responses = positionList.stream().map(position -> {
            PositionResponse positionResponse = new PositionResponse();
            positionResponse.setId(position.getId());
            positionResponse.setPosition(position.getPosition());
            position.setAllowance(position.getAllowance());
            position.setSalary(position.getSalary());
            position.setWage(position.getWage());
            return positionResponse;
        }).collect(Collectors.toList());
        return PageResponse.create(totalElements,totalPages,responses);
    }

    public IdPositionResponse createPosition(CreatePositionRequest input) {
        Optional<Position> optPosition = positionRepository.getByName(input.getPosition());
        if (optPosition.isPresent()){
            throw  new DataNotFoundException("Tên chức vụ này đã tồn tại");
        }
        Position  position = new Position();
        position.setAllowance(input.getAllowance());
        position.setPosition(input.getPosition());
        position.setSalary(input.getSalary());
        position.setWage(input.getWage());
        positionRepository.save(position);
        return new IdPositionResponse(position.getId());
    }

    public IdPositionResponse updatePosition(int id, CreatePositionRequest input) {
        Position position = positionRepository.getById(id);
        if (position == null){
            throw new DataNotFoundException("Chức vụ này không tồn tại");
        }
        position.setAllowance(input.getAllowance());
        position.setPosition(input.getPosition());
        position.setSalary(input.getSalary());
        position.setWage(input.getWage());
        positionRepository.save(position);
        return new IdPositionResponse(position.getId());
    }

    public IdPositionResponse deletePosition(int id) {
        Position position =positionRepository.getById(id);
        if (position == null){
            throw new DataNotFoundException("Chức vụ này không tồn tại");
        }
        List<User> users = userRepository.getAllUserPosition(id);
        if(users.size() > 0){
            throw new DataNotFoundException("Cần loại bỏ các nhân viên có chức vụ này trước");
        }
        positionRepository.delete(position);
        return new IdPositionResponse(position.getId());
    }
}
