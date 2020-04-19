package com.example.newapplication.Adapter;


import android.content.Context;
import android.widget.TextView;

import com.example.newapplication.R;
import com.example.newapplication.entity.EventBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class Eventdapter extends BaseRecyclerViewAdapter<EventBean, RecyclerViewHolder> {
    public Eventdapter(Context context) {
        super(context);
    }
    @Override
    protected void convert(RecyclerViewHolder holder, EventBean data, int position, int viewType) {

        TextView event_date = holder.getView(R.id.da_date);
        event_date.setText(data.getEvent_date());

        TextView event_title = holder.getView(R.id.da_event_title);
        event_title.setText(data.getEvevt_title());

        TextView event_contant = holder.getView(R.id.da_event_contant);
        event_contant.setText(data.getEvent_content());

        TextView event_startTime= holder.getView(R.id.da_evevt_startTime);
        event_startTime.setText(data.getEvent_start_time());

        TextView event_endTime = holder.getView(R.id.da_evevt_endTime);
        event_endTime.setText(data.getEvent_finish_time());

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.date_item;
    }

    @Override
    protected int getViewType(int position, EventBean data) {
        return 0;
    }
}
