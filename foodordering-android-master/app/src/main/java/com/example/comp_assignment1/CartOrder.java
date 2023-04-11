package com.example.comp_assignment1;
import java.util.ArrayList;
import java.util.List;

public class CartOrder
{
    private String email;
    private boolean isLogged;
    private List<FoodModel> list;
    private static CartOrder order;

    /* this is a singleton class **/
    private CartOrder()
    {
        list = new ArrayList<>();
        isLogged = false;
    }

    public static CartOrder startOrder()
    {
        if(order == null) { order = new CartOrder(); }
        return order;
    }

    /* getters **/
    public boolean getLogged() { return  isLogged; }
    public String getEmail() { return email; }

    /* setters **/
    public void setEmail(String pEmail) { email = pEmail; }
    public void setLogged(boolean value) { isLogged = value; }

    public void add(FoodModel food)
    {
        boolean isFound = false;

        /* iterate through the list **/
        for(int i = 0; i < list.size(); i++)
        {
            /* if the food item is already in the cart, just set the quantity **/
            if(list.get(i).getFoodName().equals(food.getFoodName())  && list.get(i).getRestName().equals(food.getRestName()))
            {
                list.get(i).setQuantity(food.getQuantity());
                isFound = true;
                break;
            }
        }

        /* if it's a new food item, add it to the cart **/
        if(!isFound) { list.add(food); }
    }

    public void remove(FoodModel food)
    {
       /* iterate through the list **/
       for(int i = 0; i < list.size(); i++)
       {
           if(list.get(i).getFoodName().equals(food.getFoodName())  && list.get(i).getRestName().equals(food.getRestName()))
           {
               list.get(i).setQuantity(food.getQuantity());

               /* if user decides to remove the food item entirely off of their cart, remove the food model object **/
               if(list.get(i).getQuantity() == 0)
               {
                   list.remove(i);
               }
           }
       }
    }

    public List<FoodModel> getCart()
    {
        /* returns a list of food items added by a user to their cart **/
        return list;
    }

    /* remove all food items from the cart **/
    public void clearCart()
    {
        list.clear();
    }

    /* add up the cost of each food item in the cart **/
    public double calculateTotal()
    {
        double sum = 0;
        for(int i = 0; i < list.size(); i++)
        {
            double price = list.get(i).getPrice();
            double quantity = (double) list.get(i).getQuantity();

            if(quantity > 0)
            {
                sum = sum + (price * quantity);
            }
        }
        return sum;
    }
}