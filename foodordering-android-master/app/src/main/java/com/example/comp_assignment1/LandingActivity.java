package com.example.comp_assignment1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LandingActivity extends AppCompatActivity
{
    ImageView homePage;
    ImageView searchPage;
    ImageView cartPage;
    ImageView userPage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        /* start an instance for an order **/
        CartOrder order = CartOrder.startOrder();

        /* loading the database **/
        DBModel database = new DBModel();
        database.load(getApplicationContext());
        database.updateRestaurantTable();
        database.updateFoodItemTable();

        /* icons at the bottom of the screen **/
        homePage = (ImageView) findViewById(R.id.home);
        searchPage = (ImageView) findViewById(R.id.search);
        cartPage = (ImageView) findViewById(R.id.cart);
        userPage = (ImageView) findViewById(R.id.profile);

        /* create a fragment manager to work with fragments **/
        FragmentManager fm = getSupportFragmentManager();
        SpecialsFragment specials = (SpecialsFragment) fm.findFragmentById(R.id.fragmentSpace);

        if(specials == null)
        {
            specials = new SpecialsFragment();
            specials.specialList = database.getAllSpecialItems();
            fm.beginTransaction().add(R.id.fragmentSpace, specials).commit();
        }

        /* on-click listeners for all icons - fragments are switched here **/
        homePage.setOnClickListener(new View.OnClickListener()
        {
            /* displays a scrollable list of special menu items offered by registered merchants **/
            @Override
            public void onClick(View v)
            {
                SpecialsFragment specialsFragment = new SpecialsFragment();
                specialsFragment.specialList = database.getAllSpecialItems();
                fm.beginTransaction().replace(R.id.fragmentSpace, specialsFragment).commit();
            }
        });

        searchPage.setOnClickListener(new View.OnClickListener()
        {
            /* displays a scrollable list of all merchants partnered with the app **/
            @Override
            public void onClick(View v)
            {
                RestaurantFragment restFragment = new RestaurantFragment();
                restFragment.restList = database.getRestaurantList();
                fm.beginTransaction().replace(R.id.fragmentSpace, restFragment).commit();
            }
        });

        cartPage.setOnClickListener(new View.OnClickListener()
        {
            /* displays all food items selected by the user for purchase **/
            @Override
            public void onClick(View v)
            {
                CartFragment cartFragment = new CartFragment();
                fm.beginTransaction().replace(R.id.fragmentSpace, cartFragment).commit();
            }
        });

        userPage.setOnClickListener(new View.OnClickListener()
        {
            /* displays login/register page for users - when successfully logged-in/registered, will display order history **/
            @Override
            public void onClick(View v)
            {
                if(!order.getLogged())
                {
                    /* move to login activity **/
                    startActivity(new Intent(LandingActivity.this, LoginActivity.class));

                }else{

                    HistoryFragment historyFragment = new HistoryFragment();
                    historyFragment.historyList = database.getOrderItems(order.getEmail());
                    fm.beginTransaction().replace(R.id.fragmentSpace, historyFragment).commit();
                }
            }
        });
    }
}