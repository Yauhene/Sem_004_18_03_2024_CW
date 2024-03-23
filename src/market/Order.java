package market;

import java.util.*;

import static market.User.Gender.female;
import static market.User.Gender.male;

public class Order {

    private static int count = 1;
    private int id;
    private User user;
    private Map<Integer,Integer> products;
    private int quantity;
    public enum Holiday {notHoliday, NewYear, March_8, February_23};
    private Holiday day;
    private double discount = 0;
    private int productId;

    public Order(User user, String day, int productId, int quantity) {
        this.id = count;
        this.day = Holiday.valueOf(day);
        count++;
        this.user = user;
        this.productId = productId;
        this.quantity = quantity;
        switch (Holiday.valueOf(day)) {
            case NewYear : {
                this.discount = 20;
                break;
            }
            case March_8 : {
                if (user.getSex().equals(female)) {
                    this.discount = 15;
                    break;
                }
            }
            case February_23 : {
                if (user.getSex().equals(male)) {
                    this.discount = 15;
                    break;
                }
            }
            default : {
                this.discount = 0;
            }
        }
        products = new HashMap<Integer, Integer>();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void add (int productId, int quantity) {
        products.put(productId, quantity);
    }

    /**
     * Метод вывода списка заказов
     * @param order
     */
    public static void printOrder(Order order) {
        String offset = " ".repeat(5);

        String strOrder = "Order " + order.getId() + ": ";
        String strUser = offset + "User " + order.getUser().getId() +  ": " + order.getUser().getName() +
                ", " + order.getUser().getAge() + " age, gender " + order.getUser().getSex() + ",  phone " + order.getUser().getPhone();
        String strProduct = offset + "Product " + order.productId + ": " + "title " + Market.getProduct(order.productId).getTitle() +
                ", price " + Market.getProduct(order.productId).getPrice() + ", quantity " + order.quantity;
        String strCalculations = offset + "Calculate of order: day " + order.day + ", " + " discount " + order.discount + "%, FINAL PRICE IS: " +
                Market.getProduct(order.productId).getPrice() + " * " + order.quantity + " * " + (1 - order.discount/100) + " = " +
                Market.getProduct(order.productId).getPrice() * order.quantity * (1 - order.discount/100);

        System.out.println(strOrder);
        System.out.println(strUser);
        System.out.println(strProduct);
        System.out.println(strCalculations);
        System.out.println("-".repeat(100));
    }
    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", products=" + products +
                '}';
    }
}