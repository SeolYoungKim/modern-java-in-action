package modern_java.chapter_10;

import java.util.function.Consumer;
import modern_java.chapter_10.MethodChainingOrderBuilder.TradeBuilder;
import modern_java.chapter_10.Trade.Type;

public class LambdaOrderBuilder {
    private Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept(builder);

        return builder.order;
    }

    public void forCustomer(String customer) {
        order.setCustomer(customer);
    }

    public void buy(Consumer<TradeBuilder> consumer) {
        trade(consumer, Type.BUY);
    }

    public void sell(Consumer<TradeBuilder> consumer) {
        trade(consumer, Type.SELL);
    }

    private void trade(Consumer<TradeBuilder> consumer, Type type) {
        TradeBuilder tradeBuilder = new TradeBuilder();
        tradeBuilder.trade.setType(type);
        consumer.accept(tradeBuilder);
        order.addTrade(tradeBuilder.trade);
    }
}
