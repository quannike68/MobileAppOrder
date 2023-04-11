package com.example.comp_assignment1;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment
{
    TextView total;
    private boolean logged;
    private CartOrder order;
    public List<FoodModel> cartList;
    protected FragmentActivity mActivity;
    private DBModel database; private String email;
    private final Calendar calendar = Calendar.getInstance();
    private final DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    /* to format currency values into amount in USD **/
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    public CartFragment()
    {
        order = CartOrder.startOrder();
        cartList = order.getCart();
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView rv = view.findViewById(R.id.cartView);
        Button checkout = view.findViewById(R.id.checkout);

        /* displaying the total bill **/
        total = view.findViewById(R.id.totalPrice);
        String price = (order.calculateTotal() + "VND");
        total.setText(price);

        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        CartAdapter cartAdapter = new CartAdapter(cartList);
        rv.setAdapter(cartAdapter);

        LoginData viewModel = new ViewModelProvider(requireActivity()).get(LoginData.class);
        viewModel.isLogged.observe(getViewLifecycleOwner(), new Observer<Boolean>()
        {
            @Override
            public void onChanged(@Nullable Boolean status)
            {
                logged = viewModel.getIsLogged();
            }
        });

        viewModel.userEmail.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String pEmail)
            {
                email = viewModel.getEmail();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(order.getLogged())
                {
                    /* load the database and add an order **/
                    database = new DBModel();
                    database.load(mActivity);
                    cartList = order.getCart();

                    if(!cartList.isEmpty())
                    {
                        successfulCheckout(cartList);
                        cartList = order.getCart();
                        Toast toast = Toast.makeText(getContext(),"Order Placed!",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }else{

                    Toast toast = Toast.makeText(getContext(),"Login to your account to place the order",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        return view;
    }

    /* nested view holder class for recycler view **/
    private class CartHolder extends RecyclerView.ViewHolder
    {
        TextView orderName; TextView orderRestName; TextView orderQuantity;
        TextView orderPrice; Button remove; FoodModel model;

        public CartHolder(@NonNull LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.cartlist, parent, false));

            /* attaching UI elements **/
            remove = itemView.findViewById(R.id.cartButton);
            orderName = itemView.findViewById(R.id.cartFood);
            orderRestName = itemView.findViewById(R.id.cartRestName);
            orderQuantity = itemView.findViewById(R.id.cartQuantity);
            orderPrice = itemView.findViewById(R.id.cartPrice);

            /* when user clicks on the remove button, remove order from cart **/
            remove.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    model.minusQuantity();
                    if(model.getQuantity() == 0)
                    {
                        order.remove(model);

                    }else{

                        String amount = Integer.toString(model.getQuantity());
                        orderQuantity.setText(amount);

                        /* get the cost for all units of the specific food item ordered **/
                        double total = ((double)model.getQuantity()) * model.getPrice();

                        /* format price as currency instance (USD) **/
                        String unitPrice = model.getPrice() + "VND";
                        String price = (total + "VND");

                        String output = String.format("%s x %s = %s", unitPrice, amount, price);
                        orderPrice.setText(output);
                    }

                    String price = (order.calculateTotal() + "VND");
                    total.setText(price);
                }
            });
        }

        public void bind(@NonNull FoodModel foodItem)
        {
            model = foodItem;
            orderName.setText(foodItem.getFoodName());
            orderRestName.setText(foodItem.getRestName());

            String amount = Integer.toString(foodItem.getQuantity());
            orderQuantity.setText(amount);

            /* get the cost for all units of the specific food item ordered **/
            double total = ((double)foodItem.getQuantity()) * foodItem.getPrice();

            /* format price as currency instance (USD) **/
            String unitPrice = foodItem.getPrice() + "VND";
            String price = (total + "VND");

            String output = String.format("%s x %s = %s", unitPrice, amount, price);
            orderPrice.setText(output);
        }
    }

    /* nested adapter class for recycler view **/
    private class CartAdapter extends RecyclerView.Adapter<CartHolder>
    {
        private List<FoodModel> data;
        private CartHolder viewHolder;

        public CartAdapter(List<FoodModel> data)
        {
            this.data = data;
        }

        @NonNull
        @Override
        public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            viewHolder = new CartHolder(layoutInflater,parent);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CartHolder holder, int position)
        {
            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount()
        {
            return data.size();
        }
    }

    /* method to add successful orders into the database **/
    private void successfulCheckout (List<FoodModel> list)
    {
        String date = dateFormatter.format(calendar.getTime());

        for(int i = 0; i < list.size(); i++)
        {
            FoodModel foodItem = cartList.get(i);
            double quantity = (double)foodItem.getQuantity();
            double cost = foodItem.getPrice() * quantity;
            database.addOrder(date,foodItem.getRestName(), foodItem.getFoodName(), cost, foodItem.getQuantity(), order.getEmail());
        }

        /* clear the cart **/
        order.clearCart();
    }
}