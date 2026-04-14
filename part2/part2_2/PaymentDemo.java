package part2.part2_2;

sealed interface PaymentMethod permits CreditCard, BankTransfer, CryptoWallet {
    String process(double amount);
    double fee(double amount);
}

record CreditCard(String cardNumber, String holder) implements PaymentMethod {
    @Override public String process(double amount) {
        return "Оплата картой *" + cardNumber.substring(cardNumber.length() - 4) + ": " + amount + " руб.";
    }
    @Override public double fee(double amount) { return amount * 0.02; }
}

record BankTransfer(String bankName, String iban) implements PaymentMethod {
    @Override public String process(double amount) { return "Перевод через " + bankName + ": " + amount + " руб."; }
    @Override public double fee(double amount) { return 50.0; }
}

record CryptoWallet(String address, String currency) implements PaymentMethod {
    @Override public String process(double amount) { return "Криптоплатёж (" + currency + "): " + amount + " руб."; }
    @Override public double fee(double amount) { return amount * 0.015; }
}

class PaymentProcessor {
    public static void describe(PaymentMethod pm) {
        switch (pm) {
            case CreditCard cc -> System.out.println("  Тип: Кредитная карта владельца " + cc.holder());
            case BankTransfer bt -> System.out.println("  Тип: Банковский перевод (IBAN: " + bt.iban() + ")");
            case CryptoWallet cw -> System.out.println("  Тип: Криптокошелек (" + cw.address() + ")");
        }
    }
}

public class PaymentDemo {
    public static void main(String[] args) {
        PaymentMethod[] payments = {
            new CreditCard("4111222233334444", "Иван Петров"),
            new BankTransfer("Сбербанк", "RU1234567890"),
            new CryptoWallet("0xABC123", "BTC")
        };
        double amount = 10000;
        for (PaymentMethod pm : payments) {
            System.out.println(pm.process(amount));
            System.out.printf("  Комиссия: %.2f руб.%n", pm.fee(amount));
            PaymentProcessor.describe(pm);
            System.out.println();
        }
    }
}
