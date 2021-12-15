package com.example.demo.web;

import com.example.demo.web.dto.ErrorResponseDto;
import com.example.demo.web.dto.LedgerDetailResponseDto;
import com.example.demo.web.dto.LedgerSaveRequestDto;
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
    public ResponseEntity add(HttpServletRequest request,
                              @RequestBody LedgerSaveRequestDto requestDto) throws Exception {
        try {
            String token = request.getHeader("Authorization");
            return new ResponseEntity(ledgerService.save(requestDto, token), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @ApiOperation(value = "가계부 세부 조회")
    @GetMapping("/{id}")
    public ResponseEntity findById(HttpServletRequest request,
                                   @PathVariable(name="id") Long id) throws Exception {
        String token = request.getHeader("Authorization");
        LedgerDetailResponseDto responseDto = ledgerService.findById(id, token);

        if (responseDto == null) {
            return new ResponseEntity(new ErrorResponseDto("error", "can't find ledger's id " + id), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "가계부 리스트 조회")
    @GetMapping
    public ResponseEntity findAll(HttpServletRequest request) throws Exception {
        try {
            String token = request.getHeader("Authorization");
            return new ResponseEntity(ledgerService.findAll(token), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @ApiOperation(value = "가계부 수정")
    @PatchMapping("/{id}")
    public ResponseEntity updateById(HttpServletRequest request,
                                     @PathVariable(name="id") Long id,
                                     @RequestBody LedgerSaveRequestDto requestDto) throws Exception {
        try {
            String token = request.getHeader("Authorization");
            return new ResponseEntity(ledgerService.update(id, requestDto, token), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @ApiOperation(value = "가계부 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(HttpServletRequest request,
                                     @PathVariable(name="id") Long id) throws Exception {
        try {
            String token = request.getHeader("Authorization");
            return new ResponseEntity(ledgerService.delete(id, token), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @ExceptionHandler
    public ResponseEntity userExHandle(Exception e) {
        return new ResponseEntity<>(new ErrorResponseDto("error", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
