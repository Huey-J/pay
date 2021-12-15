package com.example.demo.web.service;

import com.example.demo.model.ledger.Ledger;
import com.example.demo.model.ledger.LedgerRepository;
import com.example.demo.web.dto.*;
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

    @Transactional
    public SaveResponseDto save(LedgerSaveRequestDto requestDto, String token) throws Exception {
        Long userId = Long.parseLong(jwtTokenProvider.getUserIdFromJwt(token));

        if (userId != requestDto.getUserId()) {
            throw new Exception("No permission for this request");
        }
        return new SaveResponseDto(ledgerRepository.save(requestDto.toEntity()).getId());
    }

    @Transactional(readOnly = true)
    public LedgerDetailResponseDto findById(Long id, String token) throws Exception {
        Optional<Ledger> entity = ledgerRepository.findById(id);
        Long userId = Long.parseLong(jwtTokenProvider.getUserIdFromJwt(token));

        if (entity.isPresent()) {
            if (userId != entity.get().getUserId()) {
                throw new Exception("No permission for this request");
            }
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
    public LedgerResponseDto update(Long id, LedgerSaveRequestDto requestDto, String token) throws Exception {
        Optional<Ledger> entity = ledgerRepository.findById(id);
        Long userId = Long.parseLong(jwtTokenProvider.getUserIdFromJwt(token));

        if (entity.isPresent()) {
            Ledger updatedEntity = entity.get();

            if (userId != updatedEntity.getUserId()) {
                throw new Exception("No permission for this request");
            }

            updatedEntity.setPrice(requestDto.getPrice());
            updatedEntity.setTitle(requestDto.getTitle());
            updatedEntity.setMemo(requestDto.getMemo());
            updatedEntity.setPaidDate(requestDto.getPaidDate());
            updatedEntity.setModifiedDate(LocalDateTime.now());

            return new LedgerResponseDto(updatedEntity);
        } else {
            throw new Exception("can't find ledger");
        }
    }

    @Transactional
    public LedgerResponseDto delete(Long id, String token) throws Exception {
        Optional<Ledger> entity = ledgerRepository.findById(id);
        Long userId = Long.parseLong(jwtTokenProvider.getUserIdFromJwt(token));

        if (entity.isPresent()) {
            Ledger updatedEntity = entity.get();

            if (userId != updatedEntity.getUserId()) {
                throw new Exception("No permission for this request");
            }

            updatedEntity.setIsDeleted(Boolean.TRUE);

            return new LedgerResponseDto(updatedEntity);
        } else {
            throw new Exception("can't find ledger");
        }
    }
}
