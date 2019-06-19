package com.rynkbit.smartcoffee.alarmList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rynkbit.smartcoffee.R;
import com.rynkbit.smartcoffee.entity.Alarm;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.AlarmListViewHolder> {
    private List<Alarm> alarmList;
    private AlarmListListener alarmListListener;

    public AlarmListAdapter(List<Alarm> alarmList){
        this.alarmList = alarmList;
    }

    public AlarmListAdapter() {
        this(new LinkedList<Alarm>());
    }

    public void setListener(AlarmListListener listener) {
        this.alarmListListener = listener;
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
        final Alarm alarm = alarmList.get(position);
        holder.txtAlarmName.setText(alarm.getName());
        holder.txtAlarmTime.setText(
                String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        alarm.getHour(),
                        alarm.getMinute())
        );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alarmListListener != null){
                    alarmListListener.onClick(alarm);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(alarmListListener != null){
                    alarmListListener.onLongClick(alarm);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public void setAlarmList(List<Alarm> alarmList) {
        this.alarmList = alarmList;
        this.notifyDataSetChanged();
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
