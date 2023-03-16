package com.wa.last.l78z;

import java.util.Currency;
import java.util.Locale;

public class T1 {

    public static void main(String[] args) {

        String symbol = Currency.getInstance(Locale.CHINA).getSymbol();
        System.out.printf(symbol);

    }
}
