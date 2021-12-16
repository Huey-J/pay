package com.example.demo.model.ledger;

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
public class LedgerRepositoryTest {

    @Autowired
    LedgerRepository ledgerRepository;

    @After
    public void cleanup() {
        ledgerRepository.deleteAll();
    }

    @Test
    public void 가계부_저장_불러오기() {
        String title = "ledger title";
        Integer price = 1000;

        ledgerRepository.save(Ledger.builder()
                .userId(1L)
                .price(price)
                .title(title)
                .memo("memo")
                .isDeleted(Boolean.FALSE)
                .paidDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build());

        List<Ledger> ledgerList = ledgerRepository.findAll();

        Ledger ledger = ledgerList.get(0);

        assertThat(ledger.getTitle()).isEqualTo(title);
        assertThat(ledger.getPrice()).isEqualTo(price);
    }

}
