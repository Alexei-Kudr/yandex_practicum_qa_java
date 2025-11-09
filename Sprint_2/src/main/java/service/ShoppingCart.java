package service;

import model.Food;

public class ShoppingCart {
    private Food[] foods;

    public ShoppingCart(Food[] foods) {
        this.foods = foods;
    }

    public Double getTotalWithoutDiscount() {
        double countPrice = 0;
        for (Food food : foods) {
            countPrice += food.getPrice() * food.getAmount();
        }
        return countPrice;
    }

    public Double getTotalWithDiscount() {
        double countPrice = 0;
        for (Food food : foods) {
            countPrice += (food.getPrice() - discountSum(food)) * food.getAmount();
        }
        return countPrice;
    }

    public Double getTotalVegetariansWithoutDiscount() {
        double countPrice = 0;
        for (Food food : foods) {
            if (food.getVegetarian()) {
                countPrice += food.getPrice() * food.getAmount();
            }
        }
        return countPrice;
    }

    private double discountSum(Food food) {
        return food.getPrice() / 100 * food.getDiscount();
    }
}
