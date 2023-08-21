package woopaca.chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static woopaca.chapter10.MixedBuilder.buy;
import static woopaca.chapter10.MixedBuilder.forCustomer;
import static woopaca.chapter10.MixedBuilder.sell;

public class DSLExample {

    public static void main(String[] args) {
        /*Order order = new Order();
        order.setCustomer("BigBank");

        Trade trade1 = new Trade();
        trade1.setType(Type.BUY);

        Stock stock1 = new Stock();
        stock1.setSymbol("IBM");
        stock1.setMarket("NYSE");

        trade1.setStock(stock1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);
        order.addTrade(trade1);

        Trade trade2 = new Trade();
        trade2.setType(Type.SELL);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);
        order.addTrade(trade2);*/

        /*Order order = OrderBuilder.forCustomer("BigBank")
                .buy(80)
                .stock("IBM")
                .on("NYSE")
                .at(125.00)
                .sell(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .end();*/

        /*Order order = order(
                "BigBank",
                buy(80,
                        stock("IBM", on("NYSE")), at(125.00)),
                sell(50,
                        stock("GOOGLE", on("NASDAQ")), at(375.00))
        );*/

        /*Order madeOrder = order(order -> {
            order.forCustomer("BigBank");
            order.buy(trade -> {
                trade.quantity(80);
                trade.price(125.00);
                trade.stock(stock -> {
                    stock.symbol("IBM");
                    stock.market("NYSE");
                });
            });
            order.sell(trade -> {
                trade.quantity(50);
                trade.price(375.00);
                trade.stock(stock -> {
                    stock.symbol("GOOGLE");
                    stock.market("NASDAQ");
                });
            });
        });*/

        Order order = forCustomer("BigBank",
                buy(trade -> trade.quantity(80)
                        .stock("IBM")
                        .on("NYSE")
                        .at(125.00)),
                sell(trade -> trade.quantity(50)
                        .stock("GOOGLE")
                        .on("NASDAQ")
                        .at(375.00)));
    }
}

class Order {

    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getValue() {
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer='" + customer + '\'' +
                ", trades=" + trades +
                '}';
    }
}

class Trade {

    private Type type;
    private Stock stock;
    private int quantity;
    private double price;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "type=" + type +
                ", stock=" + stock +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

class Stock {

    private String symbol;
    private String market;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", market='" + market + '\'' +
                '}';
    }
}

enum Type {
    BUY, SELL;
}

class Tax {

    public static double regional(double value) {
        return value * 1.1;
    }

    public static double general(double value) {
        return value * 1.3;
    }

    public static double surcharge(double value) {
        return value * 1.05;
    }
}

/*class OrderBuilder {

    private final Order order = new Order();

    private OrderBuilder(String customer) {
        order.setCustomer(customer);
    }

    public static OrderBuilder forCustomer(String customer) {
        return new OrderBuilder(customer);
    }

    public TradeBuilder buy(int quantity) {
        return new TradeBuilder(this, Type.BUY, quantity);
    }

    public TradeBuilder sell(int quantity) {
        return new TradeBuilder(this, Type.SELL, quantity);
    }

    public OrderBuilder addTrade(Trade trade) {
        order.addTrade(trade);
        return this;
    }

    public Order end() {
        return order;
    }


}*/

/*class TradeBuilder {

    private final OrderBuilder orderBuilder;
    private final Trade trade = new Trade();


    public TradeBuilder(OrderBuilder orderBuilder, Type type, int quantity) {
        this.orderBuilder = orderBuilder;
        trade.setType(type);
        trade.setQuantity(quantity);
    }

    public StockBuilder stock(String stock) {
        return new StockBuilder(orderBuilder, trade, stock);
    }
}*/

/*class StockBuilder {

    private final OrderBuilder orderBuilder;
    private final Trade trade;
    private final Stock stock = new Stock();


    public StockBuilder(OrderBuilder orderBuilder, Trade trade, String stock) {
        this.orderBuilder = orderBuilder;
        this.trade = trade;
        this.stock.setSymbol(stock);
    }

    public TradeBuilderWithStock on(String market) {
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStock(orderBuilder, trade);
    }
}*/

/*class TradeBuilderWithStock {

    private final OrderBuilder orderBuilder;
    private final Trade trade;

    public TradeBuilderWithStock(OrderBuilder orderBuilder, Trade trade) {
        this.orderBuilder = orderBuilder;
        this.trade = trade;
    }

    public OrderBuilder at(double price) {
        trade.setPrice(price);
        return orderBuilder.addTrade(trade);
    }
}*/

/*class NestedFunctionOrderBuilder {

    public static Order order(String customer, Trade... trades) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Type.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Type.SELL);
    }

    private static Trade buildTrade(int quantity, Stock stock, double price, Type type) {
        Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setType(type);
        trade.setStock(stock);
        trade.setPrice(price);
        return trade;
    }

    public static double at(double price) {
        return price;
    }

    public static Stock stock(String symbol, String market) {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static String on(String market) {
        return market;
    }
}*/

/*class LambdaOrderBuilder {

    private final Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        LambdaOrderBuilder lambdaOrderBuilder = new LambdaOrderBuilder();
        consumer.accept(lambdaOrderBuilder);
        return lambdaOrderBuilder.order;
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
        tradeBuilder.getTrade().setType(type);
        consumer.accept(tradeBuilder);
        order.addTrade(tradeBuilder.getTrade());
    }
}*/

/*class TradeBuilder {

    private final Trade trade = new Trade();

    public Trade getTrade() {
        return trade;
    }

    public void quantity(int quantity) {
        trade.setQuantity(quantity);
    }

    public void price(double price) {
        trade.setPrice(price);
    }

    public void stock(Consumer<StockBuilder> consumer) {
        StockBuilder stockBuilder = new StockBuilder();
        consumer.accept(stockBuilder);
        trade.setStock(stockBuilder.getStock());
    }
}*/

/*class StockBuilder {

    private final Stock stock = new Stock();

    public Stock getStock() {
        return stock;
    }

    public void symbol(String symbol) {
        stock.setSymbol(symbol);
    }

    public void market(String market) {
        stock.setMarket(market);
    }
}*/

class MixedBuilder {

    public static Order forCustomer(String customer, TradeBuilder... builders) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(builders).forEach(b -> order.addTrade(b.getTrade()));
        return order;
    }

    public static TradeBuilder buy(Consumer<TradeBuilder> consumer) {
        return buildTrade(consumer, Type.BUY);
    }

    public static TradeBuilder sell(Consumer<TradeBuilder> consumer) {
        return buildTrade(consumer, Type.SELL);
    }

    private static TradeBuilder buildTrade(Consumer<TradeBuilder> consumer, Type buy) {
        TradeBuilder builder = new TradeBuilder();
        builder.getTrade().setType(buy);
        consumer.accept(builder);
        return builder;
    }
}

class StockBuilder {

    private final TradeBuilder builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder(TradeBuilder builder, Trade trade, String symbol) {
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilder on(String market) {
        stock.setMarket(market);
        trade.setStock(stock);
        return builder;
    }
}

class TradeBuilder {

    private final Trade trade = new Trade();

    public Trade getTrade() {
        return trade;
    }

    public TradeBuilder quantity(int quantity) {
        trade.setQuantity(quantity);
        return this;
    }

    public TradeBuilder at(double price) {
        trade.setPrice(price);
        return this;
    }

    public StockBuilder stock(String symbol) {
        return new StockBuilder(this, trade, symbol);
    }

}
