package com.response;

import com.model.CommonApi;

public class TransactOutcome {
    private CommonApi commonApi;
    private String outcome;
    private String apiName;
    
    public TransactOutcome(CommonApi commonApi, String outcome, String apiName) {
        this.commonApi = commonApi;
        this.outcome = outcome;
        this.apiName = apiName;
    }

    public CommonApi getCommonApi() {
        return commonApi;
    }

    public void setCommonApi(CommonApi commonApi) {
        this.commonApi = commonApi;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    
}
