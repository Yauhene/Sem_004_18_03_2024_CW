package market;

import exceptions.*;

import java.util.*;

public class Market {

    private static List<User> users = new ArrayList<>();
    private static List<Product> products;
    private static List<Order> orders;


    public Market() {

// инициализация магазина (первичные списки пользователей и товаров
        new User("Tom", 45, "11111", "male");
        new User("Bob", 26, "22222", "male");
        new User("Jim", 53, "33333", "male");
        new User("John", 40, "44444", "male");
        new User("Emma", 24, "77777", "female");
        new User("Anna", 42, "88888", "female");

        products = new ArrayList<>(List.of(
                new Product("Milk", 89),
                new Product("Bread", 26),
                new Product("Cheese", 125)
        ));
        orders = new ArrayList<>();
    }

    /**
     * Функция создания заказа
     * @param userId - id пользователя
     * @param day - особенности дня покупки
     * @param productId - id товара
     * @param quantity - количество товара
     * @return - id заказа
     * @throws UserNotFoudException
     * @throws ProductNotFoundException
     * @throws QuantityIsNegativeException
     */
    public int createOrder(int userId, String day, int productId, int quantity)
            throws UserNotFoudException, ProductNotFoundException, QuantityIsNegativeException {
        boolean userFound = false;
        boolean productFound = false;
        boolean quantityPositive = false;
        int position = -1;
        Order order = null;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                userFound = true;
                position = i;
            }
        }
        if (!userFound) throw new UserNotFoudException("User not found: " + userId);

        long count = products.stream().filter(o -> o.getId() == productId).count();
        if (count == 0) {
            throw new ProductNotFoundException("product with id " + productId + " not found");
        } else {
            productFound = true;
        }
        if (quantity > 0) quantityPositive = true;
        if(userFound && productFound && quantityPositive) {
            order = new Order(users.get(position), day, productId, quantity);
            orders.add(order);
            return order.getId();
        } else {
            return -1;
        }
    }

    public static void addUserToList (User user) {
        users.add(user);
    }
    public void addProductToOrder(int orderId, int productId, int quantity) throws ProductNotFoundException, QuantityIsNegativeException {
        long count = products.stream().filter(o -> o.getId() == productId).count();
        try {
            if (count == 0) throw new ProductNotFoundException("product with id " + productId + " not found");
//        if (!products.contains(productId)) throw new ProductNotFoundException("product with id " + productId + " not found");
            if (quantity <= 0) throw new QuantityIsNegativeException("quantity of product id " + productId + " is negative");
            Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().get();
            order.add(productId, quantity);
        } catch (ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }
//        return order;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Product> getProducts() {
        return products;
    }
    public static Product getProduct(int productId) {
        Product result = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                result = products.get(i);
            }
        }
        return result;
    }

    public static List<Order> getOrders() {
        return orders;
    }
    public static void addUser(String name, int age, String phone, String sex) {
        users.add(new User(name, age, phone, sex));
    }
}