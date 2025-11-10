package model;

import static model.constants.Discount.DISCOUNT_FOR_ALL;

public abstract class Food implements Discountable {
    protected Integer amount;
    protected Double price;
    protected Boolean isVegetarian;

    protected Food(Integer amount, Double price) {
        this.amount = amount;
        this.price = price;
    }

    @Override
    public Double getDiscount() {
        return DISCOUNT_FOR_ALL;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getVegetarian() {
        return isVegetarian;
    }
}
