package modern_java.chapter_10;

import java.util.function.Consumer;
import modern_java.chapter_10.Trade.Type;

public class MethodChainingOrderBuilder {
    public final Order order = new Order();

    private MethodChainingOrderBuilder(String customer) {
        order.setCustomer(customer);
    }

    public static MethodChainingOrderBuilder forCustomer(String customer) {
        return new MethodChainingOrderBuilder(customer);
    }

    public TradeBuilder buy(int quantity) {
        return new TradeBuilder(this, Type.BUY, quantity);
    }

    public TradeBuilder sell(int quantity) {
        return new TradeBuilder(this, Type.SELL, quantity);
    }

    public MethodChainingOrderBuilder addTrade(Trade trade) {
        order.addTrade(trade);
        return this;
    }

    public Order end() {
        return order;
    }

    public static class TradeBuilder {
        private MethodChainingOrderBuilder builder;
        public final Trade trade = new Trade();

        public TradeBuilder() {
        }

        TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
            this.builder = builder;
            trade.setType(type);
            trade.setQuantity(quantity);
        }

        public StockBuilder stock(String symbol) {
            return new StockBuilder(builder, trade, symbol);
        }

        public void stock(Consumer<StockBuilder> consumer) {
            StockBuilder stockBuilder = new StockBuilder();
            consumer.accept(stockBuilder);
            trade.setStock(stockBuilder.stock);
        }

        public void quantity(int quantity) {
            trade.setQuantity(quantity);
        }

        public void price(double price) {
            trade.setPrice(price);
        }
    }

    public static class StockBuilder {
        private MethodChainingOrderBuilder builder;
        private Trade trade;
        private final Stock stock = new Stock();

        public StockBuilder() {
        }

        public void symbol(String symbol) {
            stock.setSymbol(symbol);
        }

        public void market(String market) {
            stock.setMarket(market);
        }

        public StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol(symbol);
        }

        public TradeBuilderWithStock on(String market) {
            stock.setMarket(market);
            trade.setStock(stock);
            return new TradeBuilderWithStock(builder, trade);
        }
    }

    public static class TradeBuilderWithStock {
        private final MethodChainingOrderBuilder builder;
        private final Trade trade;

        public TradeBuilderWithStock(MethodChainingOrderBuilder builder, Trade trade) {
            this.builder = builder;
            this.trade = trade;
        }

        public MethodChainingOrderBuilder at(double price) {
            trade.setPrice(price);
            return builder.addTrade(trade);
        }
    }

}
