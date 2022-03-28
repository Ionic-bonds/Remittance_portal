package com.response;

import java.util.List;

public class TransactResponse {
    private List<TransactOutcome> transactOutcomeList;
    private List<String> errorList;

    public TransactResponse(List<TransactOutcome> transactOutcomeList, List<String> errorList) {
        this.transactOutcomeList = transactOutcomeList;
        this.errorList = errorList;
    }

    public List<TransactOutcome> getTransactOutcomeList() {
        return transactOutcomeList;
    }

    public void setTransactOutcomeList(List<TransactOutcome> transactOutcomeList) {
        this.transactOutcomeList = transactOutcomeList;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
