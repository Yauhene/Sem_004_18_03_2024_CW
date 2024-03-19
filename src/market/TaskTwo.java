package market;
import exceptions.*;

import java.util.*;

public class TaskTwo {

    public static void main(String[] args) {
        Market market = new Market();
        List<User> users = market.getUsers();
        int orderId1 = 0;
        int orderId2 = 0;
        List<Product> products = market.getProducts();

        System.out.println("users------------------------------");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + " ===> " + users.get(i));
        }
        System.out.println("products------------------------------");
        for (int j = 0; j < products.size(); j++) {
            System.out.println(j + " ===> " + products.get(j));
        }

        buy(market, users.get(2), products.get(1), 15);
        buy(market, users.get(1), products.get(0), 2);

//        Market.addUser("Alex", 22, "666666");

        System.out.println("===========================================");
        try {
            orderId2 = market.createOrder(new User("Alex", 22, "666666"));
            System.out.println("orderId2 = " + orderId2);
//            orderId2 = market.createOrder(new User("John", 40, "44444"));
        } catch (UserNotFoudException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("users more ------------------------------");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i + " ===> " + users.get(i));
        }

        System.out.println("orders------------------------------");
        List<Order> ordersList = Market.getOrders();
        for (int m = 0; m < ordersList.size(); m++) {
            System.out.println(m + " ===> " + ordersList.get(m));
        }
    }

    public static void buy(Market market, User user, Product product, int quantity) {
        try {
            int orderId = market.createOrder(user);
            market.addProductToOrder(orderId, product, quantity);
            System.out.println(market.getOrders());
        } catch (UserNotFoudException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }
    }
}