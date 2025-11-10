package model;

public class Meat extends Food {

    public Meat(Integer amount, Double price) {
        super(amount, price);
        this.isVegetarian = false;
    }
}
