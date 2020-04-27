package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.newapplication.R;
import com.example.newapplication.entity.AddressBean;
import com.example.newapplication.entity.MssageBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class MessageAdapter extends BaseRecyclerViewAdapter<MssageBean, RecyclerViewHolder> {
    public MessageAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, MssageBean data, int position, int viewType) {
        TextView time = holder.getView(R.id.message_time);
        time.setText(data.getMessage_publish_time());

        TextView title = holder.getView(R.id.message_title);
        title.setText(data.getMessage_title());

        TextView contant = holder.getView(R.id.message_contant);
        contant.setText(data.getMessage_context());

    }
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.message_item;
    }

    @Override
    protected int getViewType(int position, MssageBean data) {
        return 0;
    }

}
