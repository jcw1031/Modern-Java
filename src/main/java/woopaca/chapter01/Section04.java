package woopaca.chapter01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Section04 {

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        initializeTransactions(transactions);

        Map<Currency, List<Transaction>> nonStream =
                StreamTest.groupExpensiveTransactionIntoCurrencies(transactions);
        Map<Currency, List<Transaction>> stream =
                StreamTest.groupExpensiveTransactionIntoCurrenciesStream(transactions);

        for (Currency currency : stream.keySet()) {
            System.out.println("stream.get(currency) = " + stream.get(currency));
        }
    }

    private static void initializeTransactions(List<Transaction> transactions) {
        Currency currencyA = new Currency();
        Currency currencyB = new Currency();
        Currency currencyC = new Currency();
        transactions.add(new Transaction(100, currencyA));
        transactions.add(new Transaction(200, currencyC));
        transactions.add(new Transaction(300, currencyA));
        transactions.add(new Transaction(150, currencyB));
        transactions.add(new Transaction(80, currencyA));
    }
}

class StreamTest {

    public static Map<Currency, List<Transaction>> groupExpensiveTransactionIntoCurrencies(List<Transaction> transactions) {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getPrice() > 1_000) {
                Currency currency = transaction.getCurrency();
                List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
                if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                    transactionsByCurrencies.put(currency, transactionsForCurrency);
                }

                transactionsForCurrency.add(transaction);
            }
        }

        return transactionsByCurrencies;
    }

    public static Map<Currency, List<Transaction>> groupExpensiveTransactionIntoCurrenciesStream(List<Transaction> transactions) {
        Map<Currency, List<Transaction>> transactionsByCurrencies =
                transactions.stream()
                        // 고가의 트랜잭션 필터링
                        .filter(transaction -> transaction.getPrice() > 1_000)
                        // 통화로 그룹화
                        .collect(groupingBy(Transaction::getCurrency));

        return transactionsByCurrencies;
    }
}

class Transaction {

    private int price;
    private Currency currency;

    public Transaction() {
    }

    public Transaction(int price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }
}

class Currency {

}
