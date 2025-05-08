package com.menus.backend.service;

import com.menus.backend.domain.enums.AnalyticEventKey;

public interface AnalyticsAdapter {
    void log(AnalyticEventKey eventKey, String info, Object ...more);
}
