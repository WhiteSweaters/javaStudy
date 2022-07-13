package info.whitesweater.service.impl;

import info.whitesweater.mapper.AccountMapper;
import info.whitesweater.mapper.LogMapper;
import info.whitesweater.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private LogMapper logMapper;

    public void transfer(String from, String to, BigDecimal money) {
        try {
            // 转出钱
            accountMapper.transferFrom(from, money);
            int i = 1 / 0;
            // 转入钱
            accountMapper.transferTo(to, money);
        }finally {
            logMapper.logTransfer(from,to,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),money);
        }
    }
}
