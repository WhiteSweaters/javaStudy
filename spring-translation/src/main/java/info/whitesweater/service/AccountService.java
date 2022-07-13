package info.whitesweater.service;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountService {
    @Transactional
    void transfer(String from, String to, BigDecimal money);
}
