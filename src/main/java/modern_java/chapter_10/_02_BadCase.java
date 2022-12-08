package modern_java.chapter_10;

import modern_java.chapter_10.Trade.Type;

public class _02_BadCase {
    public static void main(String[] args) {
        Order order = new Order();
        order.setCustomer("BigBank");

        Trade trade = new Trade();
        trade.setType(Type.BUY);

        Stock stock = new Stock();
        stock.setSymbol("IBM");
        stock.setMarket("NYSE");

        trade.setStock(stock);
        trade.setPrice(125.00);
        trade.setQuantity(80);
        order.addTrade(trade);


        Trade trade2 = new Trade();
        trade2.setType(Type.BUY);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);
        order.addTrade(trade2);
    }
}
