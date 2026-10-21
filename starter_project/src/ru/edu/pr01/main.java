package ru.edu.pr01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        run(new Scanner(System.in));
    }

    public static void run(Scanner scanner) {
        System.out.print("Количество: ");
        int quantity = scanner.nextInt();

        System.out.print("Цена за единицу: ");
        double price = scanner.nextDouble();

        System.out.print("Скидка (%): ");
        double discount = scanner.nextDouble();

        // A1: конкретная проверка диапазонов с причиной ошибки
        String error = OrderCalculator.validate(quantity, price, discount);
        if (error != null) {
            System.err.println("Ошибка: " + error);
            return;
        }

        // A2: единый расчёт — без дублирования формул
        double total = OrderCalculator.calculateTotal(
                quantity, price, discount, OrderCalculator.VAT_RATE);

        System.out.printf("Итоговая стоимость заказа: %.2f ₽%n", total);
    }
}
