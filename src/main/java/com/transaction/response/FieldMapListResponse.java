package com.transaction.response;

import java.util.List;

import com.model.EverywhereRemit;
import com.model.FinanceNow;
import com.model.PaymentGo;

public class FieldMapListResponse {
    private List<FinanceNow> financeNowList;
    private List<EverywhereRemit> everywhereRemitList;
    private List<PaymentGo> paymentGoList;

    public FieldMapListResponse(List<FinanceNow> financeNowList, List<EverywhereRemit> everywhereRemitList,
            List<PaymentGo> paymentGoList) {
        this.financeNowList = financeNowList;
        this.everywhereRemitList = everywhereRemitList;
        this.paymentGoList = paymentGoList;
    }
    public List<FinanceNow> getFinanceNowList() {
        return financeNowList;
    }
    public void setFinanceNowList(List<FinanceNow> financeNowList) {
        this.financeNowList = financeNowList;
    }
    public List<EverywhereRemit> getEverywhereRemitList() {
        return everywhereRemitList;
    }
    public void setEverywhereRemitList(List<EverywhereRemit> everywhereRemitList) {
        this.everywhereRemitList = everywhereRemitList;
    }
    public List<PaymentGo> getPaymentGoList() {
        return paymentGoList;
    }
    public void setPaymentGoList(List<PaymentGo> paymentGoList) {
        this.paymentGoList = paymentGoList;
    }
}
