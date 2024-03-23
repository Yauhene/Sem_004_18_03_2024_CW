package market;
import exceptions.*;

import java.util.*;


public class TaskTwo {

    public static void main(String[] args) throws UserNotFoudException, QuantityIsNegativeException, ProductNotFoundException {
        Market market = new Market();
        List<User> users = market.getUsers();
        int orderId1 = 0;
        int orderId2 = 0;
        List<Product> products = market.getProducts();

        System.out.println("users------------------------------"); // вывод списка пользователей
        for (int i = 0; i < users.size(); i++) {
            System.out.println("User id: " + users.get(i).getId() + " ===> " + users.get(i));
        }
        System.out.println("products------------------------------"); // вывод списка продуктов
        for (int j = 0; j < products.size(); j++) {
            System.out.println("Product id " + products.get(j).getId() + " ===> " + products.get(j).getId() + " " + products.get(j).getTitle() + " " + products.get(j).getPrice());
        }
        // создание заказов
        buy(market, users.get(2), 1, 15, "February_23");
        buy(market, users.get(5), 15, 15, "February_23"); // некорректный id продукта
        buy(market, users.get(5), 2, 15, "February_23");

        buy(market, users.get(2), 1, -2, "NewYear"); // некорректное количество в заказе
        buy(market, users.get(2), 1, 2, "NewYear");
        buy(market, users.get(5), 1, 15, "NewYear");

        // альтернативное создание заказов одновременно с созданием пользователей
        System.out.println("===========================================");
        try {
            market.createOrder((new User("Alex", 22, "666666", "male")).getId(), "notHoliday", 1,4);
            market.createOrder((new User("Sarah", 22, "88888", "female")).getId(), "March_8", 2,14);

        } catch (UserNotFoudException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }

        // вывод заказов
        System.out.println("orders-----------------------------------------------------------------------------------------");
        List<Order> ordersList = Market.getOrders();
        String strOut = "";
        for (int m = 0; m < ordersList.size(); m++) {
            Order.printOrder(ordersList.get(m));
        }
    }

    /**
     * Метод создания заказа
     * @param market - экземпляр класса Market
     * @param user - экземпляр класса User
     * @param productId - номер продукта
     * @param quantity - количество продукта
     * @param day - особенности дня формирования заказа
     * @throws UserNotFoudException - исключение на несуществующего юзера
     * @throws ProductNotFoundException - исключение на несуществующий продукт
     * @throws QuantityIsNegativeException - исключение на попытку ввода отрицательного числа продукта в заказ
     */
    public static void buy(Market market, User user, int productId, int quantity, String day)
            throws UserNotFoudException, ProductNotFoundException, QuantityIsNegativeException {
        try {
            int orderId = market.createOrder(user.getId(), day, productId, quantity);
            market.addProductToOrder(orderId, productId, quantity);
        } catch (UserNotFoudException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }
    }

}
