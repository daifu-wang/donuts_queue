package com.wdf.service.impl;

import com.wdf.service.MockPremiumClientService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class MockPremiumClientServiceImpl implements MockPremiumClientService {
    @Override
    public boolean isPremium(Integer clientId) {

        if (clientId > 0 && clientId < 1000) {
            return true;
        } else {
            return false;
        }
    }
}
