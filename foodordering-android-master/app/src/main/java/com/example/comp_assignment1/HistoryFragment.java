package com.example.comp_assignment1;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class HistoryFragment extends Fragment
{
    public List<HistoryModel> historyList;
    protected FragmentActivity mActivity;

    /* to format currency values into amount in USD **/
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public HistoryFragment() {}

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        RecyclerView rv = view.findViewById(R.id.orderView);
        Button logout = view.findViewById(R.id.logout);

        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        HistoryAdapter restAdapter = new HistoryAdapter(historyList);
        rv.setAdapter(restAdapter);

        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /* logs out of the current user profile **/
                CartOrder order = CartOrder.startOrder();
                order.clearCart();
                order.setLogged(false);

                /* load the database **/
                DBModel database = new DBModel();
                database.load(mActivity);

                /* open up the landing page **/
                FragmentManager fm = getParentFragmentManager();
                SpecialsFragment specialsFragment = new SpecialsFragment();
                specialsFragment.specialList = database.getAllSpecialItems();
                fm.beginTransaction().replace(R.id.fragmentSpace, specialsFragment).commit();
            }
        });

        return view;
    }

    /* nested view holder class for recycler view **/
    private class HistoryHolder extends RecyclerView.ViewHolder
    {
        TextView foodName; TextView date; TextView price; TextView quantity; HistoryModel model;

        public HistoryHolder(@NonNull LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.orderlist, parent, false));

            /* attaching UI elements **/
            foodName = itemView.findViewById(R.id.orderFood);
            date = itemView.findViewById(R.id.orderDate);
            price = itemView.findViewById(R.id.orderPrice);
            quantity = itemView.findViewById(R.id.orderQuantity);
        }

        public void bind(@NonNull HistoryModel orderItem)
        {
            model = orderItem;
            foodName.setText(orderItem.getFoodName());
            date.setText(orderItem.getDatePurchased());
            quantity.setText(Integer.toString(orderItem.getQuantityAmount()));

            /* format price as currency instance (USD) **/
            String orderPrice = (orderItem.getCostValue() + "VND");
            price.setText(orderPrice);
        }
    }

    /* nested adapter class for recycler view **/
    private class HistoryAdapter extends RecyclerView.Adapter<HistoryHolder>
    {
        private List<HistoryModel> data;
        private HistoryHolder viewHolder;

        public HistoryAdapter(List<HistoryModel> data)
        {
            this.data = data;
        }

        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            viewHolder = new HistoryHolder(layoutInflater,parent);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder holder, int position)
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