package com.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.model.CommonApi;

public class SendTransaction {
    private String accessToken;
    private String apiName;
    private CommonApi payload;

    public SendTransaction(String accessToken, String apiName, CommonApi payload) {
        this.accessToken = accessToken;
        this.apiName = apiName;
        this.payload = payload;
    }
    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    @JsonProperty("api_name")
    public String getApiName() {
        return apiName;
    }
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
    public CommonApi getPayload() {
        return payload;
    }
    public void setPayload(CommonApi payload) {
        this.payload = payload;
    }

    
}
