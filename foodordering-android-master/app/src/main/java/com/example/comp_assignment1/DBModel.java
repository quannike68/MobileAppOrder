package com.example.comp_assignment1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.comp_assignment1.DBSchema.RestaurantTable;
import com.example.comp_assignment1.DBSchema.FoodItemTable;
import com.example.comp_assignment1.DBSchema.UserTable;
import com.example.comp_assignment1.DBSchema.OrderTable;
import java.util.ArrayList;

public class DBModel
{
        public SQLiteDatabase db;

        public void load(Context context)
        {
            this.db = new DBHelper(context.getApplicationContext()).getWritableDatabase();
        }

        /* add a restaurant into the restaurant table in the database */
        private void addRestaurant(SQLiteDatabase db, String restName, int restLogo, int specialImage)
        {
            ContentValues cv = new ContentValues();
            cv.put(RestaurantTable.Cols.NAME, restName);
            cv.put(RestaurantTable.Cols.IMAGE, restLogo);
            cv.put(RestaurantTable.Cols.SPECIAL, specialImage);
            db.insert(RestaurantTable.TABLE_NAME, null, cv);
        }

        /* add a food item into the foodItem table in the database */
        private void addFoodItem(SQLiteDatabase db, String restName, int foodImage, String foodName, String information, double price)
        {
            ContentValues cv = new ContentValues();
            cv.put(FoodItemTable.Cols.RESTNAME, restName);
            cv.put(FoodItemTable.Cols.IMAGE, foodImage);
            cv.put(FoodItemTable.Cols.FOODNAME, foodName);
            cv.put(FoodItemTable.Cols.INFORMATION, information);
            cv.put(FoodItemTable.Cols.PRICE, price);
            db.insert(FoodItemTable.TABLE_NAME, null, cv);
        }

        /* add all restaurants into the database and update restaurant list  */
        public void updateRestaurantTable()
        {
            addRestaurant(db,"Java Lounge", R.drawable.javalounge_logo, R.drawable.javalounge_fruit_salad);
            addRestaurant(db,"Cafe by Raffles", R.drawable.rafflescafe_logo, R.drawable.raffles_vanilla_cake_slice);
            addRestaurant(db,"Starbucks", R.drawable.starbucks_logo, R.drawable.starbucks_crepe_cake);
            addRestaurant(db,"Butter Boutique", R.drawable.butterboutique_logo, R.drawable.butterboutique_gooey_brownies);
            addRestaurant(db,"The English Cake Company", R.drawable.englishcakecompany_logo, R.drawable.englishcakecompany_choco_berry_cake);
            addRestaurant(db,"Krispy Kreme", R.drawable.krispykreme_logo, R.drawable.krispykreme_gift_set);
            addRestaurant(db,"Chocoholics!", R.drawable.chocoholics_logo,R.drawable.chocoholics_chocolate_lava);
            addRestaurant(db,"Dunkin Donuts", R.drawable.dunkindonuts_logo, R.drawable.dunkindonuts_cake_pops);
            addRestaurant(db,"Caramel Pumpkin", R.drawable.caramelpumpkin_logo, R.drawable.caramelpumpkin_blueberry_cake);
            addRestaurant(db,"Divine Cakes", R.drawable.divinecakes_logo, R.drawable.divinecakes_pumpkin_cakes);
            addRestaurant(db,"Bakes By Bella", R.drawable.bakesbybella_logo, R.drawable.bakesbybella_eclairs);
            addRestaurant(db,"Bubble Me", R.drawable.bubbleme_logo, R.drawable.bubbleme_thai_tea);
        }


        public void updateFoodItemTable()
        {
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_berry_cake, "Berry Bite", "Được làm bằng quả việt quất tươi và quả mâm xôi", 30000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_berry_cheesecake, "Berry Delight", "Vụ nổ hương vị thú vị của quả mâm xôi!", 35000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_crepe_cake, "Vanilla crepe cake", "Handmade and Authentic",30000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_cake_pops, "Sugary Pops!", "Bánh vụn trộn với kem sô cô la và sô cô la trắng", 40000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_fruit_tart, "Fruity Fun", "Đầy quả mâm xôi, quả mọng xanh và dâu tây",45000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_choco_cookies, "Chocolate cookies", "Bánh quy tươi, giòn với sô cô la chip", 35000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_churros, "Churros", "Phục vụ với một bên sô cô la béo ngậy", 30000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_berry_tart, "Blueberry Tart", "Một ngụm ngon lành của quả mọng xanh", 40000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_fruit_salad, "Tra sua toco", "Duoc lam tu nguyen chat tra",30000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_iced_tea, "Strawberry Iced Tea", "Trà mát lạnh sảng khoái để nhâm nhi", 35000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_lemon_juice, "Lemon juice", "500ml", 35000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_macaroons, "Macaroons", "Bộ bốn bánh hạnh nhân", 25000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_strawberry_milkshake, "Strawberry Milkshake", "1000ml", 45000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_oj, "Fresh OJ", "300ml", 40000);
            addFoodItem(db,"Java Lounge", R.drawable.javalounge_red_cupcake, "Red-velvet cupcake", "Phủ kem phô mai đỏ", 35000);

            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_strawberry_iced_tea, "Strawberry Shake", "500ml", 40000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_cinnamon_rolls, "Cinnamon rolls", "2 cinnamon rolls served with a side of cream cheese icing", 30000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_vanilla_cake_slice, "Vanilla cake", "Vanilla cake topped with buttercream", 35000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_coffee_mousse, "Coffee mousse", "Cappuccino flavoured mousse", 50000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_strawberry_mousse, "Strawberry mousse", "Topped with freshly cut strawberries", 25000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_choco_overload_shake, "Choco-Overload!", "Blast of white chocolate, milk chocolate and yummy marshmallows", 40000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_fruit_salad, "Fruit salad bowl", "No cashews included",  50000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_cheese_cake, "Lime cheesecake", "Perfectly tangy, Perfectly sweet",  60000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_macchiato, "Caramel Macchiato", "500ml", 55000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_choco_milkshake, "Chocolate Milkshake", "800ml",  45000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_nutella_waffle, "Nutty Waffles", "Crunchy waffles downed in Nutella", 30000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_pink_cake, "Neon Pink Blast", "Tangy cake with a lemon base", 45000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_waffles, "Creamy Waffle Party", "Served with a hearty side of whipped cream and berries",  25000);
            addFoodItem(db,"Cafe by Raffles", R.drawable.raffles_donut, "Donuts Gonuts", "Soft donuts glazed with yummy chocolate sauce", 35000);

            addFoodItem(db, "Starbucks", R.drawable.starbucks_brownies, "Brownies", "One portion contains four gooey treats", 1.69);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_crepe_cake, "Crepe Cake", "Keeps you coming back for more", 2.98);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_latte, "Iced latte", "750ml", 1.79);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_bubbletea, "Choco Bubble tea", "750ml", 1.98);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_churros, "Churros", "Served with chocolate sauce", 0.99);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_macchiato, "Caramel macchiato", "800ml", 2.05);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_strawberry_shake, "Strawberry Shake", "800ml", 1.79);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_cinnamon_rolls, "Cinnamon rolls", "Two cinnamon rolls topped with whipped cream ", 1.39);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_iced_coffee, "Iced coffee", "800ml", 3.95);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_tiramisu_mousse, "Tiramisu mousse", "A blend of espresso and cocoa", 1.69);
            addFoodItem(db, "Starbucks", R.drawable.starbucks_strawberry_cake, "Strawberry cake", "A slice of cake with fresh strawberry bits inside", 1.99);

            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_oreo_shake, "Oreo Flurry", "Combination of creamy vanilla soft serve with crunchy pieces of OREO cookies!",  2.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_thai_tea, "Thai bubble tea", "With flavoured tapioca boba", 4.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_strawberry_boba, "Strawberry Boba", "With clear boba", 4.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_taiwan_milk, "Taiwan milk bubble tea", "With black boba", 4.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_matcha_boba, "Matcha boba",  "With mini boba", 5.29);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_macarons, "Macaroon set", "A set of 12 macaroons", 17.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_soft_serve, "Ice cream meetups", "Soft serve ice cream served with berries", 3.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_orange_juice, "Fresh Orange juice", "500ml", 1.69);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_three_cone, "Tri-scoop ice cream", "Classic flavour - Vanilla/Chocolate/Strawberry", 9.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_caramel_icecream, "Caramel ice cream", "Topped with honey and crunchy butterscotch", 3.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_icecream_set, "Iced treats deluxe", "A special treat box with all our flavours",9.99);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_choco_icecream, "Chocolate ice cream", "Milky and Soft", 3.95);
            addFoodItem(db, "Bubble Me", R.drawable.bubbleme_choco_milkshake, "Choco-shake", "800ml", 4.55);

            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_sweet_blast, "Sweet Tooth", "Sugary donut topped with strawberry icing and sprinkles", 3.99);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_gift_set, "Donut Treats!", "A gift box of 4 donuts of your choice", 12.99);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_ily_set, "Drive me (do)nuts!", "Sweet gift for your loved ones", 11.09);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_strawberry_donut, "Strawberry Donut", "Topped with strawberry buttercream", 3.99);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_monster_donuts, "Rawr-nuts", "Can be purchased in mint and vanilla flavours", 5.59);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_churros, "Sweet Churro Bites", "With chocolate sauce", 1.39);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_zebra_donut, "Striped Check", "Buttercream donut with strawberry glaze", 2.99);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_sprinkle_donuts, "Sprinkle donuts", "Topped with rainbow sprinkles", 2.99);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_strawberry_shake, "Strawberry Shake", "900ml", 4.99);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_cookies, "Chip Cookies", "A set of 6 chocolate chip cookies", 3.95);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_cake_pops, "Sweet cake pops", "Cake crumbs mixed with dark chocolate icing", 1.69);
            addFoodItem(db, "Krispy Kreme", R.drawable.krispykreme_glazed_donut, "Choco Blast", "Chocolate sauce glazed donut", 3.49);

            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_chip_cookies, "Cookies & Milk", "Cookies with a glass of milk", 4.99);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_chocolate_macaroons, "Chocolate macaroons", "A set of 3 chocolatey macaroons", 7.99);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_chocolate_lava, "Chocolate Lovers", "Gooey chocolate cake with raspberry icing", 3.49);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_cookies, "Chip cookies", "Freshly baked. Six oer serving", 1.99);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_choco_cake_slice, "CHOC-bite", "Sweet chocolate cake with buttercream frosting", 4.49);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_chocolate_cake, "Chocoholics Special", "1kg cake - rich and filled with fun chocolate treats", 10.99);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_hot_chocolate, "Hot Chocolate drink", "800ml", 3.59);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_choco_cake, "Mud cake", "Blend of dark chocolate and black berries", 2.49);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_choco_donut, "Double Choco-light", "Sweet donut topped with chocolate sauce", 3.49);
            addFoodItem(db, "Chocoholics!", R.drawable.chocoholics_waffles, "Waffle Treats", "Served with a hearty portion of nutella", 4.79);

            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_donut_family, "Donut Treat box", "A box of 4 donuts", 12.49);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_pancakes, "Breakfast pancakes", "Served with honey syrup and berries", 3.99);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_cake_pops, "Cinnamon cake pops", "3 per serving", 3.79);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_donut_set, "Donut Box", "A box of 6 donuts with your chosen flavours", 10.79);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_croissants, "Chocolate Croissants", "Melt in the mouth pastry filled with chocolate", 4.99);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_chocolate_donuts, "Chocolate Kreme donut", "Dark chocolate flavoured", 7.99);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_pudding, "Caramel Pudding", "Sugar-free", 1.99);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_coffee, "Dunkin Coffee", "Made with our own blend of cocoa", 2.49);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_oreo_donut, "Oreo Crumble", "Vanilla soft serve with crunchy bits of Oreo", 5.79);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_waffles, "Waffle Trouble", "Topped with chocolate sauce and mangoes", 7.79);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_strawberry_delight, "Frosted Strawberry donut", "Strawberry frosting and cream cheese inside", 4.99);
            addFoodItem(db, "Dunkin Donuts", R.drawable.dunkindonuts_strawberry_donut, "Strawberry-Kreme donut", "Strawberry frosting with buttercream inside", 6.49);

            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_blueberry_cake, "Blueberry Magic", "Available in buttercream frosting", 3.79);
            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_latte, "Iced latte", "700ml", 3.99);
            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_macaroons, "Macaroon set", "A box of 12 unique macaroons", 10.99);
            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_vanilla_cupcake, "Vanilla Cupcake", "Topped with a creamy buttercream icing", 2.49);
            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_fruit_salad, "Fruit Salad bowl", "Pineapples/Mangoes/Bananas/Raspberries", 3.69);
            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_vanillasoft_icecream, "Vanilla soft serve", "Available in waffle cone/ waffle cup",1.99);
            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_donut_set, "Caramel pumpkin donut box", "A box of 6 unique flavoured donuts", 14.79);
            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_choco_milkshake, "Choco Shake", "800ml", 4.98);
            addFoodItem(db, "Caramel Pumpkin", R.drawable.caramelpumpkin_waffles, "Waffle Quaffle", "A flavour bomb of nutella, chocolate sauce and honey", 4.09);;

            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_vanilla_cake_slice, "Caramel Carnival", "Cream cheese topping with caramel sauce swirls", 5.49);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_cheese_cake, "Cheese Cake", "Cheesecake with a tangy lemon zest", 3.79);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_strawberry_cake_large, "Strawberry Season", "1kg",10.59);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_choco_berry_cake, "Very Berry", "Blueberry sauce and cream cheese topped sweet treat", 5.98);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_graham_cake, "Graham Crumble", "Perfect crunch, Perfect bite", 6.99);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_lemon_macaroons, "Macaroons", "Lemon flavoured set of macaroons - contains 4 ",  10.99);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_choco_crepe_cake,"Crepe cake", "Handmade crepe cake", 3.99);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_fruit_cake, "Berry fruit tart", "Made with fresh blueberries",5.49);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_fruitcake,"Fruit Mart", "Buttercream cake served with fresh bits of fruits", 6.79);
            addFoodItem(db, "The English Cake Company", R.drawable.englishcakecompany_blueberry_cake, "Berry Lovers", "Combination of blueberries, strawberries and raspberries", 5.99);

            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_waffles, "Supreme Waffle", "", 9.99);
            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_whipped_cake, "Vanilla Cake", "", 5.49);
            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_berry_cake_large, "Merry Berry", "", 3.99);
            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_mudcake, "Guilty Pleasures", "", 3.79);
            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_berry_tarts, "Berry Tart", "", 4.49);
            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_choco_cake, "Chocolate Cake", "", 8.05);
            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_pumpkin_cakes, "Bumble Bee!", "", 11.99);
            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_choco_treat, "Choco Treats", "", 11.99);
            addFoodItem(db, "Divine Cakes", R.drawable.divinecakes_vanilla_cupcake, "Vanilla cupcake", "", 2.99);

            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_macaroons, "Macaroon Set", "A set of 4 lemon flavoured macaroons", 11.99);
            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_crepe_cake, "Crepe Cake", "Melt in the mouth, handmade crepe cake", 5.99);
            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_fruit_cake, "Fruity Delight", "Butter cake with buttercream icing and fruit topping", 2.98);
            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_brownies, "Choco Brownies", "Heavenly, crunchy brownies to your sweet tooth",6.98);
            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_vanilla_cake,"Vanilla Softie", "White chocolate topped vanilla cake", 8.99);
            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_strawberry_cake_large,"Berry Cake", "",11.05);
            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_eclairs,"Buttercream Eclairs", "Set of 2 eclairs - Available in white/milk chocolate", 11.05);
            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_strawberry_milkshake, "Berry Shake", "1000ml", 7.98);
            addFoodItem(db, "Bakes By Bella", R.drawable.bakesbybella_berry_slice, "Berry Bite", "Filled with a whipped cream and fresh berry filling", 5.98);

            addFoodItem(db, "Butter Boutique", R.drawable.butterboutique_fruit_salad, "Fruit bowl", "Mix of fresh pineapples, blue berries, raspberries and strawberries", 1.59);
            addFoodItem(db, "Butter Boutique", R.drawable.butterboutique_gooey_brownies, "Brownie", "Soft fudgy centre and gooey in all the right places!", 2.49);
            addFoodItem(db, "Butter Boutique", R.drawable.butterboutique_cinnamon_rolls, "Cinnamon Swirls", "Set of 2 rolls served with a white chocolate sauce", 6.49);
            addFoodItem(db, "Butter Boutique", R.drawable.butterboutique_vanilla_cake_large, "Vanilla Whipped cake", "1kg", 8.99);
            addFoodItem(db, "Butter Boutique", R.drawable.butterboutique_pink_cake, "Neon Light", "Fun, colorful, tangy treat", 2.89);
            addFoodItem(db, "Butter Boutique", R.drawable.butterboutique_macaroons, "Macaroons", "A set of 4 macaroons", 10.99);
            addFoodItem(db, "Butter Boutique", R.drawable.butterboutique_banana_cake, "Banana? Banana!", "Sweet treat of mashed bananas served with a cream cheese topping",3.98);
            addFoodItem(db, "Butter Boutique", R.drawable.butterboutique_choco_cake_largey, "Chocolate Cake", "1kg", 8.99);
        }

        /* Adds a user into the user table in the database */
        public void addUser(String email, String password)
        {
            ContentValues cv = new ContentValues();
            cv.put(UserTable.Cols.EMAIL, email);
            cv.put(UserTable.Cols.PASSWORD, password);
            db.insert(UserTable.TABLE_NAME, null, cv);
        }

        /* Adds an order history to the Order table in the database */
        public void addOrder(String date, String restName, String foodName, double total_cost, int quantity, String email)
        {
            ContentValues cv = new ContentValues();
            cv.put(OrderTable.Cols.DATE, date);
            cv.put(OrderTable.Cols.RESTAURANT, restName);
            cv.put(OrderTable.Cols.FOOD_ITEM, foodName);
            cv.put(OrderTable.Cols.TOTAL_COST, total_cost);
            cv.put(OrderTable.Cols.QUANTITY, quantity);
            cv.put(OrderTable.Cols.EMAIL, email);
            db.insert(OrderTable.TABLE_NAME, null, cv);
        }

        public Boolean checkEmail(String email)
        {
            Cursor cursor = db.rawQuery("select * from user where email = ?", new String[]{email});
            int count = cursor.getCount();
            cursor.close();
            return count > 0;
        }

        public boolean checkEmailPassword(String email, String password)
        {
            Cursor cursor = db.rawQuery("select * from user where email = ? and password = ?", new String[]{email, password});
            int count = cursor.getCount();
            cursor.close();
            return count > 0;
        }

        /* Return an ArrayList of FoodModel objects based on the special food item from the database */
        public ArrayList<FoodModel> getAllSpecialItems()
        {
            ArrayList<FoodModel> specialItemList = new ArrayList<>();
            ArrayList<RestaurantModel> restItemList = new ArrayList<>();

            /* move through the restaurant database and make a list of restaurant name and its special food item **/
            try (Cursor cursor = db.query(RestaurantTable.TABLE_NAME, null,
                    null, null, null, null,
                    null))
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    restItemList.add(new RestaurantModel(cursor.getString(0), cursor.getInt(2)));
                    cursor.moveToNext();
                }
            }

            for(int i = 0; i < restItemList.size(); i++)
            {
                RestaurantModel model = restItemList.get(i);
                String str = Integer.toString(model.getRestaurantImage());

                /* move through the rows and add a food item list **/
                try (Cursor cursor = db.rawQuery("select * from foodItem where restName = ? and image = ?", new String[]{model.getRestaurantName(), str}))
                {
                    cursor.moveToFirst();
                    while(!cursor.isAfterLast())
                    {
                       specialItemList.add(new FoodModel(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
                       cursor.moveToNext();
                    }
                }
            }
            return specialItemList;
        }

        /* Return an ArrayList of FoodModel objects based on the restaurant that sells them from the database */
        public ArrayList<FoodModel> getAllFoodItems(RestaurantModel restaurant)
        {
            ArrayList<FoodModel> foodItemList = new ArrayList<>();

            /* move through the rows and add a food item list **/
            try (Cursor cursor = db.rawQuery("select * from foodItem where restName = ?", new String[]{restaurant.getRestaurantName()}))
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    foodItemList.add(new FoodModel(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
                    cursor.moveToNext();
                }
            }
            return foodItemList;
        }

        /* Access the database to return an array list of restaurant objects */
        public ArrayList<RestaurantModel> getRestaurantList()
        {
            ArrayList<RestaurantModel> restaurantList = new ArrayList<>();

            /* moves through the rows and add a restaurant list as an object **/
            try (Cursor cursor = db.query(RestaurantTable.TABLE_NAME, null,
                    null, null, null, null, null))
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    restaurantList.add(new RestaurantModel(cursor.getString(0), cursor.getInt(1)));
                    cursor.moveToNext();
                }
            }
            return restaurantList;
        }

    public ArrayList<HistoryModel> getOrderItems(String email)
    {
        ArrayList<HistoryModel> historyList = new ArrayList<>();

        /* moves through the rows and add an order to the list as an object **/
        try (Cursor cursor = db.rawQuery("select * from orderHistory where email = ?", new String[]{email}))
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                historyList.add(new HistoryModel(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getDouble(3), cursor.getInt(4)));
                cursor.moveToNext();
            }
        }
        return historyList;
    }
}