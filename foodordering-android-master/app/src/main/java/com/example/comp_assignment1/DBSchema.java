package com.example.comp_assignment1;

public class DBSchema
{
    public static class RestaurantTable{
        public static final String TABLE_NAME = "restaurant";
        public static class Cols{
            public static final String NAME = "name";
            public static final String IMAGE = "image";
            public static final String SPECIAL = "specialMenu";
        }
    }

    public static class FoodItemTable{
        public static final String TABLE_NAME = "foodItem";
        public static class Cols{
            public static final String RESTNAME = "restName";
            public static final String IMAGE = "image";
            public static final String FOODNAME = "foodName";
            public static final String INFORMATION = "information";
            public static final String PRICE = "price";
        }
    }

    public static class UserTable{
        public static final String TABLE_NAME = "user";
        public static class Cols{
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
        }
    }

    public static class OrderTable{
        public static final String TABLE_NAME = "orderHistory";
        public static class Cols
        {
            public static final String DATE = "date";
            public static final String RESTAURANT = "restName";
            public static final String FOOD_ITEM = "foodName";
            public static final String TOTAL_COST = "totalCost";
            public static final String QUANTITY = "quantity";
            public static final String EMAIL = "email";
        }
    }
}
