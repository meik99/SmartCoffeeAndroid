package com.rynkbit.smartcoffee;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.rynkbit.smartcoffee.communication.GetAlarmRequestListener;
import com.rynkbit.smartcoffee.communication.GetAlarmsRequest;
import com.rynkbit.smartcoffee.communication.MakeCoffeeRequest;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Alarm>> mAlarms = new MutableLiveData<>();

    public LiveData<List<Alarm>> getAlarms() {
        return mAlarms;
    }

    public void sendCoffeeRequest(Context context) {
        MakeCoffeeRequest coffeeRequest = new MakeCoffeeRequest();
        coffeeRequest.sendMakeCoffeeRequest(context);
    }

    public void sendGetAlarmsRequest(Context context){
        GetAlarmsRequest request = new GetAlarmsRequest();
        request.setListener(new GetAlarmRequestListener() {
            @Override
            public void onResponse(List<Alarm> alarms) {
                mAlarms.postValue(alarms);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
        request.sendGetAlarmsRequest(context);
    }
}
