package Traders;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
       // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).

        List<Transaction> trans2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .collect(Collectors.toList());
        System.out.println(trans2011);

        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.

        List<String> uniqueCities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCities);

        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        List<Trader> tradersInCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(tradersInCambridge);

        // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        String traderNames = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(traderNames);
        //5. Выяснить, существует ли хоть один трейдер из Милана.
        boolean anyTraderFromMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(anyTraderFromMilan);

       // 6. Вывести суммы всех транзакций трейдеров из Кембриджа.

        int sumOfCambridgeTransactions = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println(sumOfCambridgeTransactions);

        //7.Какова максимальная сумма среди всех транзакций?


        OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println("Максимальная сумма среди всех транзакций" + max);

        //8. Какова минимальная сумма среди всех транзакций?
        OptionalInt min = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min();
        System.out.println("Максимальная сумма среди всех транзакций" + min);



       












    }
}