package com.transaction.response;

import com.model.CommonApi;

public class TransactOutcome {
    private CommonApi commonApi;
    private String outcome;

    public TransactOutcome(CommonApi commonApi, String outcome) {
        this.commonApi = commonApi;
        this.outcome = outcome;
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
}
