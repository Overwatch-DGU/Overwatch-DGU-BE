package com.example.overwatchbe.domain.user.service;

import com.example.overwatchbe.domain.user.dto.UserLoginRequest;
import com.example.overwatchbe.domain.user.dto.UserResponse;
import com.example.overwatchbe.domain.user.entity.User;
import com.example.overwatchbe.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse login(UserLoginRequest loginRequest) {
        User user = userRepository.findByEmailAndPassword(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ).orElseThrow(() -> new RuntimeException("Invalid email or password"));

        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getLevel(),
                user.getCoin()
        );
    }

    public void logout(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        // 로그아웃 로직 추가 필요시 구현
    }
}
