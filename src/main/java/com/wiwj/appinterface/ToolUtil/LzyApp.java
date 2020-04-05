package com.wiwj.appinterface.ToolUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LzyApp {
    @Value("${lzy.app_id}")
    private String appId;
    @Value("${lzy.app_secret}")
    private String appSecret;
    @Value("${feishu.jobpost_key}")
    public String jobpost_key;

    public String getJobpost_key() {
        return jobpost_key;
    }

    public void setJobpost_key(String jobpost_key) {
        this.jobpost_key = jobpost_key;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
