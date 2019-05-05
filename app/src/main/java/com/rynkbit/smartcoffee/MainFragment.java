package com.rynkbit.smartcoffee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rynkbit.smartcoffee.alarm.AlarmListAdapter;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private AlarmListAdapter mAlarmListAdapter;

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

        Button btnMakeCoffee = Objects.requireNonNull(getView()).findViewById(R.id.btnMakeCoffee);
        RecyclerView listAlarm = Objects.requireNonNull(getView()).findViewById(R.id.listAlarms);
        FloatingActionButton fabAddAlarm = Objects.requireNonNull(
                getView()).findViewById(R.id.fabAddAlarm);

        fabAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_mainFragment_to_editAlarmFragment);
            }
        });

        mAlarmListAdapter = new AlarmListAdapter();

        listAlarm.setLayoutManager(new LinearLayoutManager(getContext()));
        listAlarm.setAdapter(mAlarmListAdapter);

        mViewModel.getAlarms().observe(
                this,
                new Observer<List<Alarm>>() {
                    @Override
                    public void onChanged(List<Alarm> alarmList) {
                        mAlarmListAdapter.setAlarmList(alarmList);
                    }
                }
        );

        btnMakeCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.sendCoffeeRequest(getContext());
            }
        });

        mViewModel.sendGetAlarmsRequest(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
