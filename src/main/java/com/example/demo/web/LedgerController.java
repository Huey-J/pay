package com.example.demo.web;

import com.example.demo.web.dto.ErrorResponseDto;
import com.example.demo.web.dto.LedgerDetailResponseDto;
import com.example.demo.web.dto.LedgerSaveRequestDto;
import com.example.demo.web.dto.UserRequestDto;
import com.example.demo.web.service.JwtTokenProvider;
import com.example.demo.web.service.LedgerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
@RestController
public class LedgerController {
    private final LedgerService ledgerService;

    @ApiOperation(value = "가계부 추가")
    @PostMapping
    public ResponseEntity add(@RequestBody LedgerSaveRequestDto requestDto) {
        return new ResponseEntity(ledgerService.save(requestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "가계부 세부 조회")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(name="id") Long id) {
        LedgerDetailResponseDto responseDto = ledgerService.findById(id);

        if (responseDto == null) {
            return new ResponseEntity(new ErrorResponseDto("no data", "can't find ledger's id " + id), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "가계부 리스트 조회")
    @GetMapping
    public ResponseEntity findAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return new ResponseEntity(ledgerService.findAll(token), HttpStatus.OK);
    }


}
