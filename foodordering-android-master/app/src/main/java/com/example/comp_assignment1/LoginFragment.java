package com.example.comp_assignment1;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment
{
    protected FragmentActivity mActivity;

    public LoginFragment() {}

    @Override
    public void onAttach(Context context)
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        /* identifying the UI elements **/
        Button login = view.findViewById(R.id.loginButton);
        Button register = view.findViewById(R.id.registerButton);
        EditText emailAddress = view.findViewById(R.id.email);
        EditText userPassword = view.findViewById(R.id.password);

        DBModel database = new DBModel();
        database.load(mActivity);

        CartOrder order = CartOrder.startOrder();

        LoginData viewModel = new ViewModelProvider(requireActivity()).get(LoginData.class);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = emailAddress.getText().toString();
                String password = userPassword.getText().toString();

                /* checks if the entered email address is valid **/
                boolean validEmail = isEmailValid(email);

                if(validEmail)
                {
                    if(!email.equals("") && !password.equals(""))
                    {
                        if(database.checkEmailPassword(email,password))
                        {
                            viewModel.setIsLogged(true);
                            order.setLogged(true);
                            viewModel.setEmail(email);
                            order.setEmail(email);

                            FragmentManager fm = getParentFragmentManager();
                            SuccessFragment successFragment = new SuccessFragment();
                            fm.beginTransaction().replace(R.id.fragmentContainer, successFragment).commit();

                        }else{

                            viewModel.setIsLogged(false);

                            /* user credentials do not match **/
                            Toast toast = Toast.makeText(getContext(),"Login failed. Try again",Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }else{

                        viewModel.setIsLogged(false);
                        Toast toast = Toast.makeText(getContext(),"User name/Password cannot be empty",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }else{

                    Toast toast = Toast.makeText(getContext(),"Please enter a valid email address",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email = emailAddress.getText().toString();
                String password = userPassword.getText().toString();

                boolean validEmail = isEmailValid(email);

                if(validEmail)
                {
                    if(!email.equals("") && !password.equals("") && validEmail)
                    {
                        /* an account from this email already exists **/
                        if(database.checkEmail(email))
                        {
                            viewModel.setIsLogged(false);
                            Toast toast = Toast.makeText(getContext(),"An account already exists",Toast.LENGTH_SHORT);
                            toast.show();

                        }else{

                            viewModel.setIsLogged(true);
                            order.setLogged(true);
                            viewModel.setEmail(email);
                            order.setEmail(email);

                            database.addUser(email,password);

                            FragmentManager fm = getParentFragmentManager();
                            SuccessFragment successFragment = new SuccessFragment();
                            fm.beginTransaction().replace(R.id.fragmentContainer, successFragment).commit();
                        }

                    }else{

                        Toast toast = Toast.makeText(getContext(),"User name/Password cannot be empty",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }else{

                    Toast toast = Toast.makeText(getContext(),"Please enter a valid email address",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return view;
    }

    boolean isEmailValid(CharSequence email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}