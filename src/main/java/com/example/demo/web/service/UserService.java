package com.example.demo.web.service;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserRepository;
import com.example.demo.web.dto.UserResponseDto;
import com.example.demo.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserResponseDto save(UserRequestDto requestDto) {
        // 중복된 email인지 확인
        Optional<User> findedEntity = userRepository.findByEmail(requestDto.getEmail());

        if (findedEntity.isEmpty()) {
            // 이메일 유효성 검사
            // password 암호화

            User entity = userRepository.save(requestDto.toEntity());       // DB 저장

            String token = jwtTokenProvider.createToken(entity);            // 토큰 생성
            return new UserResponseDto(entity.getEmail(), token);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public UserResponseDto findByEmailAndPassword(UserRequestDto requestDto) {
        Optional<User> findedEntity = userRepository.findByEmail(requestDto.getEmail());

        if (findedEntity.isPresent()) {
            if (findedEntity.get().getPassword().equals(requestDto.getPassword())) {
                String token = jwtTokenProvider.createToken(findedEntity.get());

                return new UserResponseDto(findedEntity.get().getEmail(), token);
            }
        }
        return null;
    }

}
