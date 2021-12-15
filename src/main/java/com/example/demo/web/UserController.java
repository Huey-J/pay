package com.example.demo.web;

import com.example.demo.web.dto.ErrorResponseDto;
import com.example.demo.web.dto.UserResponseDto;
import com.example.demo.web.dto.UserRequestDto;
import com.example.demo.web.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.save(requestDto);
        if (responseDto == null) {
            return new ResponseEntity(new ErrorResponseDto("can't sign-up", "This email already exists."), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(responseDto, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity logIn(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.findByEmailAndPassword(requestDto);
        if (responseDto == null) {
            return new ResponseEntity(new ErrorResponseDto("can't login", "wrong email or password"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(responseDto, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity logOut() {
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
