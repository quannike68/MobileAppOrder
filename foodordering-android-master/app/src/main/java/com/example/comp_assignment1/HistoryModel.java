package com.example.comp_assignment1;

public class HistoryModel
{
    private String foodName;
    private String restaurantName;
    private double costValue;
    private int quantityAmount;
    private String datePurchased;

    /* constructor for the HistoryModel class **/
    public HistoryModel (String date, String restaurant, String food, double price, int quantity)
    {
        foodName = food;
        restaurantName = restaurant;
        costValue = price;
        quantityAmount = quantity;
        datePurchased = date;
    }

    /* getters and setters for the HistoryModel class **/
    public String getRestaurantName() {
        return restaurantName;
    }
    public double getCostValue() {
        return costValue;
    }
    public int getQuantityAmount() {
        return quantityAmount;
    }
    public String getDatePurchased() {
        return datePurchased;
    }
    public String getFoodName() {
        return foodName;
    }
}
