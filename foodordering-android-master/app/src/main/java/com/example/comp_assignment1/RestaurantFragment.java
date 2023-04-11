package com.example.comp_assignment1;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class RestaurantFragment extends Fragment {

    public List<RestaurantModel> restList;

    public RestaurantFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        RecyclerView rv = view.findViewById(R.id.restaurantView);
        RestaurantAdapter restAdapter = new RestaurantAdapter(restList);
        rv.setAdapter(restAdapter);
        return view;
    }

    /* nested view holder class for recycler view **/
    private class RestaurantHolder extends RecyclerView.ViewHolder
    {
        ImageView restImage; TextView restName; RestaurantModel model;

        public RestaurantHolder(@NonNull LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.restaurantlist, parent, false));

            /* attaching UI elements **/
            restImage = itemView.findViewById(R.id.restLogo);
            restName = itemView.findViewById(R.id.restName);

            /* when user clicks on a restaurant, takes them to a scrollable list of all available food items **/
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    FragmentManager fm = getParentFragmentManager();
                    FoodFragment foodFragment = new FoodFragment();
                    foodFragment.setRestaurant(model);
                    fm.beginTransaction().replace(R.id.fragmentSpace, foodFragment).commit();
                }
            });
        }

        public void bind(@NonNull RestaurantModel restItem)
        {
            model = restItem;

            restName.setText(restItem.getRestaurantName());
            restImage.setImageResource(restItem.getRestaurantImage());
        }
    }

    /* nested adapter class for recycler view **/
    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder>
    {
        private List<RestaurantModel> data;
        private RestaurantHolder viewHolder;

        public RestaurantAdapter(List<RestaurantModel> data) { this.data = data; }

        @NonNull
        @Override
        public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            viewHolder = new RestaurantHolder(layoutInflater,parent);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RestaurantHolder holder, int position)
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