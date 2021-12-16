package com.example.demo.model.user;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저_저장_불러오기() {
        String email = "test@test.com";
        String password = "test password";

        userRepository.save(User.builder()
                .email(email)
                .password(password)
                .createdDate(LocalDateTime.now())
                .build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);

        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
    }

}
