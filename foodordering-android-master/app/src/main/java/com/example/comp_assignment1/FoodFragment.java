package com.example.comp_assignment1;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FoodFragment extends Fragment
{
    private CartOrder order;
    private DBModel database;
    private List<FoodModel> foodList;
    public RestaurantModel restaurant;
    private FragmentActivity mActivity;

    /* to format currency values into amount in USD **/
    private NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public FoodFragment()
    {
        order = CartOrder.startOrder();
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        if (context instanceof Activity)
        {
            mActivity = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        RecyclerView rv = view.findViewById(R.id.foodView);

        /* load the database and get the list of food items **/
        database = new DBModel();
        database.load(mActivity);
        foodList = database.getAllFoodItems(restaurant);

        FoodAdapter foodAdapter = new FoodAdapter(foodList);
        rv.setAdapter(foodAdapter);
        return view;
    }

    /* nested view holder class for recycler view **/
    private class FoodHolder extends RecyclerView.ViewHolder
    {
        ImageView foodImage; TextView foodInfo; TextView foodName; TextView foodPrice;
        TextView foodAmt; Button add; Button remove; FoodModel model;

        public FoodHolder(@NonNull LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.foodlist, parent, false));

            /* attaching UI elements **/
            foodImage = itemView.findViewById(R.id.image);
            foodName = itemView.findViewById(R.id.name);
            foodInfo = itemView.findViewById(R.id.information);
            foodPrice = itemView.findViewById(R.id.price);
            foodAmt = itemView.findViewById(R.id.quantity);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);

            /* when user clicks on the + button **/
            add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    model.addQuantity();
                    order.add(model);
                    foodAmt.setText(Integer.toString(model.getQuantity()));
                }
            });

            /* when user clicks on the - button **/
            remove.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    model.minusQuantity();
                    order.remove(model);
                    foodAmt.setText(Integer.toString(model.getQuantity()));
                }
            });
        }

        public void bind(@NonNull FoodModel foodItem)
        {
            model = foodItem;

            foodName.setText(foodItem.getFoodName());
            foodInfo.setText(foodItem.getInformation());
            foodImage.setImageResource(foodItem.getImage());

            if(foodItem.getQuantity() > 0)
            {
                foodAmt.setText(Integer.toString(foodItem.getQuantity()));
            }

            /* format price as currency instance (USD) **/
            String price = (foodItem.getPrice() + "VND");
            foodPrice.setText(price);
        }
    }

    /* nested adapter class for recycler view **/
    private class FoodAdapter extends RecyclerView.Adapter<FoodHolder>
    {
        private List<FoodModel> data;
        private FoodHolder viewHolder;

        public FoodAdapter(List<FoodModel> data)
        {
            this.data = data;
        }

        @NonNull
        @Override
        public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            viewHolder = new FoodHolder(layoutInflater,parent);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull FoodHolder holder, int position)
        {
            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount()
        {
            return data.size();
        }
    }

    public void setRestaurant(RestaurantModel pRestaurant)
    {
        restaurant = pRestaurant;
    }
}