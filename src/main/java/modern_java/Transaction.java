package modern_java;

public class Transaction {
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
