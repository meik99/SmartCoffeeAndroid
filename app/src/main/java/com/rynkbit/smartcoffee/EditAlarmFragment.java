package com.rynkbit.smartcoffee;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.rynkbit.smartcoffee.communication.PostAlarmRequestListener;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import java.util.Locale;
import java.util.Objects;

public class EditAlarmFragment extends Fragment {

    private EditAlarmViewModel mViewModel;
    private Button btnSave;
    private ProgressBar progressBar;

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

        btnSave = Objects.requireNonNull(getView()).findViewById(R.id.btnSave);
        progressBar = Objects.requireNonNull(getView()).findViewById(R.id.progressBar);

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
                btnSave.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                mViewModel.setName(txtAlarmName.getText().toString());
                mViewModel.saveAlarm(getContext(), new PostAlarmRequestListener() {
                    @Override
                    public void onResponse(Alarm alarm) {
                        exitWithSnackbarMessage(R.string.alarm_saved);
                    }

                    @Override
                    public void onError(VolleyError error) {
                        exitWithSnackbarMessage(R.string.no_connection);
                    }
                });
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

    private void exitWithSnackbarMessage(@StringRes int message){
        btnSave.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        Snackbar.make(
                Objects.requireNonNull(getView()),
                message,
                Snackbar.LENGTH_SHORT)
                .show();
        NavHostFragment
                .findNavController(EditAlarmFragment.this)
                .popBackStack();
    }

}
