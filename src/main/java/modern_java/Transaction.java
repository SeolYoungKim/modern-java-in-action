package modern_java;

import java.util.List;

public class Transaction {

    public static final Trader raoul = new Trader("Raoul", "Cambridge");
    public static final Trader mario = new Trader("Mario", "Milan");
    public static final Trader alan = new Trader("Alan", "Cambridge");
    public static final Trader brian = new Trader("Brian", "Cambridge");

    public static final List<Transaction> transactions = List.of(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader trader() {
        return trader;
    }

    public int year() {
        return year;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "modern_java.Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
