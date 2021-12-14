package com.example.demo.web.service;

import com.example.demo.model.ledger.Ledger;
import com.example.demo.model.ledger.LedgerRepository;
import com.example.demo.web.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LedgerService {
    private final LedgerRepository ledgerRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // TODO
    //  API추가: 수정
    //  API추가: 삭제
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

    @Transactional(readOnly = true)
    public LedgerListResponseDto findAll(String token) {
        Long userId = Long.parseLong(jwtTokenProvider.getUserIdFromJwt(token));

        return new LedgerListResponseDto(ledgerRepository.findAllByUserId(userId)
                .stream()
                .map(LedgerResponseDto::new)
                .collect(Collectors.toList()));
    }

    @Transactional
    public LedgerResponseDto update(Long id, LedgerSaveRequestDto requestDto) {
        Optional<Ledger> entity = ledgerRepository.findById(id);

        if (entity.isPresent()) {
            Ledger updatedEntity = entity.get();

            updatedEntity.setPrice(requestDto.getPrice());
            updatedEntity.setTitle(requestDto.getTitle());
            updatedEntity.setMemo(requestDto.getMemo());
            updatedEntity.setPaidDate(requestDto.getPaidDate());
            updatedEntity.setModifiedDate(LocalDateTime.now());

            return new LedgerResponseDto(updatedEntity);
        }
        return null;
    }


}
