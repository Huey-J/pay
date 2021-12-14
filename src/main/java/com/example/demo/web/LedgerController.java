package com.example.demo.web;

import com.example.demo.web.dto.ErrorResponseDto;
import com.example.demo.web.dto.LedgerDetailResponseDto;
import com.example.demo.web.dto.LedgerSaveRequestDto;
import com.example.demo.web.dto.UserRequestDto;
import com.example.demo.web.service.LedgerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity(ledgerService.findById(id), HttpStatus.OK);
    }




}
