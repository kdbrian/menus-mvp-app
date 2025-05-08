package com.menus.backend.domain.repo.impl;

import com.menus.backend.domain.enums.AnalyticEventKey;
import com.menus.backend.service.AnalyticsAdapter;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsAdapterImpl implements AnalyticsAdapter {

    @Override
    public void log(AnalyticEventKey eventKey, String info, Object... more) {

    }

}
