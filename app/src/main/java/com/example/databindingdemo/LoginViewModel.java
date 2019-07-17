package com.example.databindingdemo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> Email = new MutableLiveData<>();

    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<User> userMutableLiveData;

    public MutableLiveData<User> getUser() {

        if (userMutableLiveData == null)
            userMutableLiveData = new MutableLiveData<>();

        return userMutableLiveData;
    }

    public void onClick(View view) {

        User user = new User(Email.getValue(), Password.getValue());

        userMutableLiveData.setValue(user);

    }

}
