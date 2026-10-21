package ru.edu.pr01;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = true;

        // Типовой расчёт
        ok &= Math.abs(OrderCalculator.calculateBase(2, 100.0) - 200.0) < 1e-9;
        ok &= Math.abs(OrderCalculator.applyDiscount(200.0, 10.0) - 180.0) < 1e-9;
        ok &= Math.abs(OrderCalculator.calculateVat(180.0, 20.0) - 216.0) < 1e-9;
        ok &= Math.abs(OrderCalculator.calculateTotal(2, 100.0, 10.0, 20.0) - 216.0) < 1e-9;
        
        // Вариант 7: ставка НДС = 40 %
        // base = 1 × 100 = 100; discount = 0; VAT = 40 %  ->  100 × 1.4 = 140
        ok &= Math.abs(OrderCalculator.calculateTotal(1, 100.0, 0.0, OrderCalculator.VAT_RATE) - 140.0) < 1e-9;

        // Нижние границы — включены
        ok &= OrderCalculator.isValid(1, 0.01, 0.0);
        // Верхние границы — включены
        ok &= OrderCalculator.isValid(10_000, 5_000_000.0, 30.0);
        // Выход за границы
        ok &= !OrderCalculator.isValid(0, 100.0, 10.0);
        ok &= !OrderCalculator.isValid(10_001, 100.0, 10.0);
        ok &= !OrderCalculator.isValid(10, 0.0, 10.0);
        ok &= !OrderCalculator.isValid(10, 5_000_000.01, 10.0);
        ok &= !OrderCalculator.isValid(10, 100.0, -0.01);
        ok &= !OrderCalculator.isValid(10, 100.0, 30.01);

        System.out.println(ok ? "PASS" : "FAIL: complete TODO methods");
    }
}
