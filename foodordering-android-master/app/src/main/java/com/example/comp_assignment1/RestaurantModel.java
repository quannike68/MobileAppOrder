package com.example.comp_assignment1;

public class RestaurantModel
{
    private String name;
    private int image;

    /* constructor method for a RestaurantModel object **/
    public RestaurantModel(String name, int image)
    {
        this.name = name;
        this.image = image;
    }

    /* getter methods **/
    public String getRestaurantName()
    {
        return this.name;
    }
    public int getRestaurantImage()
    {
        return this.image;
    }
}
