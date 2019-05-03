package com.rynkbit.smartcoffee;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.rynkbit.smartcoffee.communication.MakeCoffeeRequest;

public class MainViewModel extends ViewModel {
    public void sendCoffeeRequest(Context context) {
        MakeCoffeeRequest coffeeRequest = new MakeCoffeeRequest();
        coffeeRequest.sendMakeCoffeeRequest(context);
    }
}
