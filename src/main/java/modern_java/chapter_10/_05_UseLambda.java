package modern_java.chapter_10;

import static modern_java.chapter_10.LambdaOrderBuilder.*;

public class _05_UseLambda {
    public static void main(String[] args) {
        order(lambdaOrderBuilder -> {
            lambdaOrderBuilder.forCustomer("BigBank");
            lambdaOrderBuilder.buy(tradeBuilder -> {
                tradeBuilder.quantity(80);
                tradeBuilder.price(125.00);
                tradeBuilder.stock(stockBuilder -> {
                    stockBuilder.symbol("IBM");
                    stockBuilder.market("NYSE");
                });
            });

            lambdaOrderBuilder.sell(tradeBuilder -> {
                tradeBuilder.quantity(50);
                tradeBuilder.price(375.00);
                tradeBuilder.stock(stockBuilder -> {
                    stockBuilder.symbol("GOOGLE");
                    stockBuilder.market("NASDAQ");
                });
            });
        });
    }
}
