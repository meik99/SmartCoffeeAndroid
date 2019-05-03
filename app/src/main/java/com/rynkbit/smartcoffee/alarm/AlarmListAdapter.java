package com.rynkbit.smartcoffee.alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rynkbit.smartcoffee.R;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.AlarmListViewHolder> {
    private List<Alarm> mAlarmList;

    public AlarmListAdapter(List<Alarm> alarmList){
        this.mAlarmList = alarmList;
    }

    public AlarmListAdapter() {
        this(new LinkedList<Alarm>());
    }

    @NonNull
    @Override
    public AlarmListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlarmListViewHolder(
                    LayoutInflater
                            .from(parent.getContext())
                            .inflate(R.layout.alarm_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmListViewHolder holder, int position) {
        Alarm alarm = mAlarmList.get(position);
        holder.txtAlarmName.setText(alarm.getName());
        holder.txtAlarmTime.setText(
                String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        alarm.getHour(),
                        alarm.getMinute())
        );
    }

    @Override
    public int getItemCount() {
        return mAlarmList.size();
    }

    public class AlarmListViewHolder extends RecyclerView.ViewHolder{
        TextView txtAlarmName;
        TextView txtAlarmTime;

        public AlarmListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAlarmName = itemView.findViewById(R.id.txtAlarmName);
            txtAlarmTime = itemView.findViewById(R.id.txtAlarmTime);
        }
    }
}