package modern_java.chapter_10;

public class _03_BuilderPattern {
    public static void main(String[] args) {
        Order order = MethodChainingOrderBuilder.forCustomer("BigBank")
                .buy(80)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .sell(50)
                .stock("IBM")
                .on("NYSE")
                .at(125.00)
                .end();
    }
}
