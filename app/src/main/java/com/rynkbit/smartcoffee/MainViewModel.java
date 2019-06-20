package com.rynkbit.smartcoffee;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.rynkbit.smartcoffee.communication.listener.MakeCoffeeListener;
import com.rynkbit.smartcoffee.communication.request.DeleteAlarmRequest;
import com.rynkbit.smartcoffee.communication.listener.DeleteAlarmRequestListener;
import com.rynkbit.smartcoffee.communication.listener.GetAlarmRequestListener;
import com.rynkbit.smartcoffee.communication.request.GetAlarmsRequest;
import com.rynkbit.smartcoffee.communication.request.MakeCoffeeRequest;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Alarm>> mAlarms = new MutableLiveData<>();

    public LiveData<List<Alarm>> getAlarms() {
        return mAlarms;
    }

    public void sendCoffeeRequest(Context context, MakeCoffeeListener listener) {
        MakeCoffeeRequest coffeeRequest = new MakeCoffeeRequest(context);
        coffeeRequest.setListener(listener);
        coffeeRequest.sendMakeCoffeeRequest(context);
    }

    public void sendGetAlarmsRequest(Context context){
        GetAlarmsRequest request = new GetAlarmsRequest(context);
        request.setListener(new GetAlarmRequestListener() {
            @Override
            public void onResponse(List<Alarm> alarms) {
                mAlarms.postValue(alarms);
            }

            @Override
            public void onError(VolleyError error) {
                error.printStackTrace();
            }
        });
        request.sendGetAlarmsRequest(context);
    }

    public void sendDeleteAlarmRequest(final Context context, Alarm alarm) {
        DeleteAlarmRequest request = new DeleteAlarmRequest(context);
        request.setListener(new DeleteAlarmRequestListener() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, R.string.alarm_deleted, Toast.LENGTH_SHORT)
                        .show();
                sendGetAlarmsRequest(context);
            }

            @Override
            public void onError(VolleyError error) {
                error.printStackTrace();

                Toast.makeText(context, R.string.alarm_error_deleted, Toast.LENGTH_SHORT)
                        .show();
                sendGetAlarmsRequest(context);
            }
        });
        request.sendDeleteAlarmsRequest(context, alarm);
    }
}
