package com.example.comp_assignment1;

public class FoodModel
{
    private String restName;
    private int image;
    private String foodName;
    private String information;
    private double price;
    private int quantity;

    /* constructor for a FoodModel object **/
    public FoodModel (String restName, int foodImage, String foodName, String information, double price)
    {
        this.restName = restName;
        this.image = foodImage;
        this.foodName = foodName;
        this.information = information;
        this.price = price;
        this.quantity = 0;
    }

    /* getter methods **/
    public String getRestName() {return this.restName;}
    public int getImage() {return this.image;}
    public String getFoodName() {return this.foodName;}
    public double getPrice() {return this.price;}
    public int getQuantity() {return this.quantity;}
    public String getInformation() { return information; }

    /* setter methods **/
    public void setQuantity(int num) {this.quantity=num;}
    public void addQuantity(){ this.quantity += 1;}
    public void minusQuantity()
    {
        if (this.quantity > 0)
        {
            this.quantity -= 1;
        }
    }
}
