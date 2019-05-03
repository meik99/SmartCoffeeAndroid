package com.rynkbit.smartcoffee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rynkbit.smartcoffee.alarm.AlarmListAdapter;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnMakeCoffee = Objects.requireNonNull(getView()).findViewById(R.id.btnMakeCoffee);
        RecyclerView listAlarm = Objects.requireNonNull(getView()).findViewById(R.id.listAlarms);

        listAlarm.setLayoutManager(new LinearLayoutManager(getContext()));
        listAlarm.setAdapter(new AlarmListAdapter(new ArrayList<Alarm>(){{
            add(new Alarm(1, 6, 5, "Alarm 1", new Date()));
            add(new Alarm(2, 7, 15, "Alarm 2", new Date()));
            add(new Alarm(3, 8, 25, "Alarm 3", new Date()));
            add(new Alarm(4, 9, 35, "Alarm 4", new Date()));
            add(new Alarm(1, 6, 5, "Alarm 1", new Date()));
            add(new Alarm(2, 7, 15, "Alarm 2", new Date()));
            add(new Alarm(3, 8, 25, "Alarm 3", new Date()));
            add(new Alarm(4, 9, 35, "Alarm 4", new Date()));
        }}));

        btnMakeCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.sendCoffeeRequest(getContext());
            }
        });
    }
}
