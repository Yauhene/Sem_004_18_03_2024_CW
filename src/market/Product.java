package market;

import java.util.*;

public class Product {
    private static int index = 0;
    private String title;
    private int price;
    private int id;
    private static Map<Integer, Product> prodMap;

    public int getId() {
        return id;
    }

    public Product(String title, int price) {
        this.id = ++index;
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Product: " +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}