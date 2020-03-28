package com.slotvinskiy;

import java.util.ArrayList;
import java.util.List;

public class ClassForParseResponseJson {

    private List<Currency> exchangeRate = new ArrayList<>();

    class Currency {

        private String baseCurrency;
        private String currency;
        private String saleRate;
        private String purchaseRate;

        public boolean isParsed() {
            if (baseCurrency != null && currency != null && saleRate != null && purchaseRate != null) {
                return true;
            }
            return false;
        }

        public String getBaseCurrency() {
            return baseCurrency;
        }

        public String getCurrency() {
            return currency;
        }

        public String getSaleRate() {
            return saleRate;
        }

        public String getPurchaseRate() {
            return purchaseRate;
        }
    }

    public String get(String string) {
        for (Currency currency : exchangeRate) {
            if (string.equals(currency.getCurrency()) && currency.isParsed()) {
                return "Base Currency:" + currency.getBaseCurrency() + ";\n" +
                        "Currency:" + currency.getCurrency() + ";\n" +
                        "Purchase Rate:" + currency.getPurchaseRate() + ";\n" +
                        "Sale Rate:" + currency.getSaleRate();
            }
        }
        return "Could'n parse response...";
    }
}
