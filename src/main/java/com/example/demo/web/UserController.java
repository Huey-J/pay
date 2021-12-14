package com.example.demo.web;

import com.example.demo.web.dto.ErrorResponseDto;
import com.example.demo.web.dto.UserResponseDto;
import com.example.demo.web.dto.UserSaveRequestDto;
import com.example.demo.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserSaveRequestDto requestDto) {
        UserResponseDto responseDto = userService.save(requestDto);
        if (responseDto == null) {
            return new ResponseEntity(new ErrorResponseDto("can't sign-up", "This email already exists."), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(responseDto, HttpStatus.OK);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity logIn() {
//        return new ResponseEntity(null, HttpStatus.OK);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity logOut() {
//        return new ResponseEntity(null, HttpStatus.OK);
//    }

}
