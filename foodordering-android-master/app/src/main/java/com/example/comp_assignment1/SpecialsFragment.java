package com.example.comp_assignment1;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

public class SpecialsFragment extends Fragment
{
    private CartOrder order;
    public List<FoodModel> specialList;

    /* to format currency values into amount in USD **/
    private NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public SpecialsFragment()
    {
        order = CartOrder.startOrder();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_specials, container, false);
        RecyclerView rv = view.findViewById(R.id.homeView);
        SpecialsAdapter specialsAdapter = new SpecialsAdapter(specialList);
        rv.setAdapter(specialsAdapter);
        return view;
    }

    /* nested view holder class for recycler view **/
    private class SpecialHolder extends RecyclerView.ViewHolder
    {
        ImageView foodImage; TextView foodInfo; TextView foodName; TextView foodPrice;
        TextView foodAmt; Button addButton; Button removeButton; FoodModel model;

        public SpecialHolder(@NonNull LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.specialslist, parent, false));

            /* attaching UI elements **/
            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodInfo = itemView.findViewById(R.id.foodInformation);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            foodAmt = itemView.findViewById(R.id.foodQuantity);
            addButton = itemView.findViewById(R.id.addButton);
            removeButton = itemView.findViewById(R.id.removeButton);

            /* when user clicks on the + button **/
            addButton.setOnClickListener(new View.OnClickListener()
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
            removeButton.setOnClickListener(new View.OnClickListener()
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

        public void bind(@NonNull FoodModel specialItem)
        {
            model = specialItem;

            foodName.setText(specialItem.getFoodName());
            foodInfo.setText(specialItem.getRestName());
            foodImage.setImageResource(specialItem.getImage());

            /* format price as currency instance (USD) **/
            String price = specialItem.getPrice() + "VND";
            foodPrice.setText(price);

            if(specialItem.getQuantity() > 0)
            {
               foodAmt.setText(Integer.toString(specialItem.getQuantity()));
            }
        }
    }

    /* nested adapter class for recycler view **/
    private class SpecialsAdapter extends RecyclerView.Adapter<SpecialHolder>
    {
        private List<FoodModel> data;
        private SpecialHolder viewHolder;

        public SpecialsAdapter(List<FoodModel> data)
        {
            this.data = data;
        }

        @NonNull
        @Override
        public SpecialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            viewHolder = new SpecialHolder(layoutInflater,parent);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SpecialHolder holder, int position)
        {
            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount()
        {
            return data.size();
        }
    }
}