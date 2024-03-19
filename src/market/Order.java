package market;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private static int count = 1;
    private int id;
    private User user;
    private Map<Product, Integer> products;

    public Order(User user) {
        this.id = count;
        count++;
        this.user = user;
        products = new HashMap<Product, Integer>();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void add (Product product, int quantity) {
        products.put(product, quantity);
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", products=" + products +
                '}';
    }
}