package com.rynkbit.smartcoffee;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.rynkbit.smartcoffee.entitiy.Alarm;

import java.util.Locale;
import java.util.Objects;

public class EditAlarmFragment extends Fragment {

    private EditAlarmViewModel mViewModel;

    public static EditAlarmFragment newInstance() {
        return new EditAlarmFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_alarm_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditAlarmViewModel.class);
        final SharedAlarmViewModel sharedAlarmViewModel =
                ViewModelProviders.of(getActivity()).get(SharedAlarmViewModel.class);

        mViewModel.setAlarm(sharedAlarmViewModel.getSharedAlarm());

        final EditText txtAlarmName = Objects.requireNonNull(getView()).findViewById(R.id.txtAlarmName);
        final EditText txtAlarmTime = Objects.requireNonNull(getView()).findViewById(R.id.txtAlarmTime);
        Button btnSave = Objects.requireNonNull(getView()).findViewById(R.id.btnSave);

        txtAlarmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialogFragment timeDialogFragment = new TimeDialogFragment(getContext());
                timeDialogFragment.setTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       setTime(txtAlarmTime, hourOfDay, minute);
                    }
                });
                timeDialogFragment.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setName(txtAlarmName.getText().toString());
                mViewModel.saveAlarm(getContext());
                NavHostFragment
                        .findNavController(EditAlarmFragment.this)
                        .popBackStack();
            }
        });

        if(mViewModel.getAlarm().getId() > 0){
            Alarm alarm = mViewModel.getAlarm();

            txtAlarmName.setText(alarm.getName());
            setTime(txtAlarmTime, alarm.getHour(), alarm.getMinute());
        }
    }

    private void setTime(EditText txtAlarmTime, int hourOfDay, int minute){
        txtAlarmTime.setText(String.format(Locale.getDefault(),
                "%02d:%02d", hourOfDay, minute));
        mViewModel.setTime(hourOfDay, minute);
    }

}
