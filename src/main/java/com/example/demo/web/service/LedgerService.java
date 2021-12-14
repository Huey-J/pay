package com.example.demo.web.service;

import com.example.demo.model.ledger.Ledger;
import com.example.demo.model.ledger.LedgerRepository;
import com.example.demo.web.dto.LedgerDetailResponseDto;
import com.example.demo.web.dto.SaveResponseDto;
import com.example.demo.web.dto.LedgerSaveRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LedgerService {
    private final LedgerRepository ledgerRepository;

    // TODO
    //  API추가: 수정
    //  API추가: 삭제
    //  API추가: 조회 (리스트)
    //  EXCEPTION 처리
    //  jwt-user_id 검증

    @Transactional
    public SaveResponseDto save(LedgerSaveRequestDto requestDto) {
        return new SaveResponseDto(ledgerRepository.save(requestDto.toEntity()).getId());
    }

    @Transactional(readOnly = true)
    public LedgerDetailResponseDto findById(Long id) {
        Optional<Ledger> entity = ledgerRepository.findById(id);

        if (entity.isPresent()) {
            return new LedgerDetailResponseDto(entity.get());
        }
        return null;
    }



}
