package ru.edu.pr01;

public class OrderCalculator {

    // ===== Границы (вариант 7: VAT_RATE = 40%) =====
    public static final int    MIN_QUANTITY   = 1;
    public static final int    MAX_QUANTITY   = 10_000;
    public static final double MIN_PRICE      = 0.01;
    public static final double MAX_PRICE      = 5_000_000.00;
    public static final double MIN_DISCOUNT   = 0.0;
    public static final double MAX_DISCOUNT   = 30.0;
    public static final double VAT_RATE       = 40.0; // % (вариант 7)

    // ===== Проверки (возвращают null, если всё ок) =====
    public static String validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
            return "количество должно быть от " + MIN_QUANTITY
                    + " до " + MAX_QUANTITY;
        }
        return null;
    }

    public static String validatePrice(double price) {
        if (price < MIN_PRICE || price > MAX_PRICE) {
            return "цена должна быть от " + MIN_PRICE
                    + " до " + MAX_PRICE;
        }
        return null;
    }

    public static String validateDiscount(double discount) {
        if (discount < MIN_DISCOUNT || discount > MAX_DISCOUNT) {
            return "скидка должна быть от " + MIN_DISCOUNT
                    + " до " + MAX_DISCOUNT + "%";
        }
        return null;
    }

    /** Общая проверка: возвращает текст первой ошибки или null. */
    public static String validate(int quantity, double price, double discount) {
        String e = validateQuantity(quantity);
        if (e != null) return e;
        e = validatePrice(price);
        if (e != null) return e;
        return validateDiscount(discount);
    }

    public static boolean isValid(int quantity, double price, double discount) {
        return validate(quantity, price, discount) == null;
    }

    // ===== Расчёты =====
    /** Базовая сумма = количество * цена. */
    public static double calculateBase(int quantity, double price) {
        return quantity * price;
    }

    /** Скидка в процентах: возвращает сумму ПОСЛЕ скидки. */
    public static double applyDiscount(double baseSum, double discountPercent) {
        return baseSum - (baseSum * discountPercent / 100.0);
    }

    /** НДС: возвращает сумму С НДС (ставка в процентах). */
    public static double calculateVat(double sum, double vatRate) {
        return sum + (sum * vatRate / 100.0);
    }

    /** Итог: база -> скидка -> НДС. Единая точка расчёта. */
    public static double calculateTotal(int quantity, double price,
                                        double discountPercent, double vatRate) {
        double base = calculateBase(quantity, price);
        double afterDiscount = applyDiscount(base, discountPercent);
        return calculateVat(afterDiscount, vatRate);
    }
}
