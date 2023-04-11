package com.example.comp_assignment1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* create a fragment manager to work with fragments -- open login fragment **/
        FragmentManager fm = getSupportFragmentManager();
        LoginFragment login = (LoginFragment) fm.findFragmentById(R.id.fragmentContainer);

        if(login == null)
        {
            login = new LoginFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, login).commit();
        }
    }
}