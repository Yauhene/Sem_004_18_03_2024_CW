package market;

import exceptions.*;

import java.util.*;

public class Market {

    private static List<User> users;
    private List<Product> products;
    private static List<Order> orders;


    public Market() {
        users = new ArrayList<>(List.of(
                new User("Tom", 45, "11111"),
                new User("Bob", 26, "22222"),
                new User("Jim", 53, "33333"),
                new User("John", 40, "44444")
        ));
        products = new ArrayList<>(List.of(
                new Product("Milk", 89),
                new Product("Bread", 26),
                new Product("Cheese", 125)
        ));
        orders = new ArrayList<>();
    }

    public int createOrder(User user) throws UserNotFoudException {
        if (!users.contains(user)) throw new UserNotFoudException("user not found, " + user);
        Order order = new Order(user);
        orders.add(order);
        return order.getId();
    }

    public Order addProductToOrder(int orderId, Product product, int quantity)
            throws ProductNotFoundException, QuantityIsNegativeException {
        if (!products.contains(product)) throw new ProductNotFoundException("product not found");
        if (quantity <= 0) throw new QuantityIsNegativeException("quantity of product is negative");
        Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().get();
        order.add(product, quantity);
        return order;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public static List<Order> getOrders() {
        return orders;
    }
    public static void addUser(String name, int age, String phone) {
        users.add(new User(name,age,phone));
    }
}