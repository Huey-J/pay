package com.example.demo.web;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserRepository;
import com.example.demo.web.dto.UserRequestDto;
import com.example.demo.web.dto.UserResponseDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanup() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입() throws Exception {
        String url = "http://localhost:" + port + "/api/v1/user/signup";
        String email = "dsflm@mmmm.com";
        String password = "Password";

        UserRequestDto requestDto = new UserRequestDto(email, password);

        ResponseEntity<UserResponseDto> responseEntity = restTemplate.postForEntity(url, requestDto, UserResponseDto.class);

        // 응답 검사
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getEmail()).isEqualTo(email);

        // DB 검사
        Optional<User> entity = userRepository.findByEmail(email);
        if (entity.isEmpty()) {
            throw new Exception("DB 추가 실패");
        }
        assertThat(entity.get().getEmail()).isEqualTo(email);
    }

}
