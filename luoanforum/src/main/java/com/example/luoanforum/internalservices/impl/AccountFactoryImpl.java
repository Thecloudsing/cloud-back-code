package com.example.luoanforum.internalservices.impl;


import com.example.luoanforum.internalservices.AccountFactory;
import com.example.luoanforum.mapper.RegisterMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author 落扶苏
 * @version 1.1
 */
@Service
public class AccountFactoryImpl implements AccountFactory {

    private String first;
    private Integer accountCount = 4;
    private final Integer RANDOM_VALUE = 10;
    private final StringBuffer STR = new StringBuffer();

    @Autowired
    private RandomFactory random;
    @Autowired
    private RegisterMapper registerMapper;

    @PostConstruct
    private void init() {
        int i;
        do {
            i = random.getRandom().nextInt(RANDOM_VALUE);
        } while (i == 0);
        first = String.valueOf(i);
    }

    @Override
    public String accountFactory() {
        int registerCount = 0;
        STR.delete(0, STR.length());
        STR.append(first);
        boolean b;
        String temp;
        do {
            for (int i = 0; i < accountCount; i++) {
                STR.append(random.getRandom().nextInt(RANDOM_VALUE));
            }
            if (registerCount > 20) accountCount++; else if (registerCount > 10) this.init();
            b = registerMapper.queryWhetherTheAccountIsRegistered(temp = STR.toString()) != null;
            registerCount++;
        } while (b);
        return temp;
    }
}
