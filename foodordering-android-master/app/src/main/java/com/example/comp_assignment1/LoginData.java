package com.example.comp_assignment1;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* this is a ViewModel class used to communicate between LoginFragment and CartFragment **/
public class LoginData extends ViewModel
{
    public MutableLiveData<Boolean> isLogged;
    public MutableLiveData<String> userEmail;

    public LoginData()
    {
        isLogged = new MutableLiveData<>();
        isLogged.setValue(false);
        userEmail = new MutableLiveData<>();
        userEmail.setValue("");
    }

    public boolean getIsLogged() { return isLogged.getValue(); }
    public void setIsLogged(boolean value) { isLogged.setValue(value); }
    public String getEmail() { return userEmail.getValue();}
    public void setEmail(String pEmail) { userEmail.setValue(pEmail); }
}
