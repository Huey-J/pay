package com.example.demo.web;

import com.example.demo.web.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
@RestController
public class LedgerController {
    private final LedgerService ledgerService;



}
