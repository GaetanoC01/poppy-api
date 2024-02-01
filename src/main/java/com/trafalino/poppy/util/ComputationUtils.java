package com.trafalino.poppy.util;

public class ComputationUtils {
    public static double computeSale(
            double price,
            double discount,
            int quantity
    ) {
        double result;
        double totalDiscount;
        double total;

        totalDiscount = price * quantity * discount;
        total = price * quantity;
        result = total - totalDiscount;

        return result;
    }
}
