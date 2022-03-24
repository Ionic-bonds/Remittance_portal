package com.transaction.request;

import com.model.CommonApi;

public class SendTransaction {
    private String access_token;
    private String api_name;
    private CommonApi payload;

    public SendTransaction(String access_token, String api_name, CommonApi payload) {
        this.access_token = access_token;
        this.api_name = api_name;
        this.payload = payload;
    }
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getApi_name() {
        return api_name;
    }
    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }
    public CommonApi getPayload() {
        return payload;
    }
    public void setPayload(CommonApi payload) {
        this.payload = payload;
    }

    
}
