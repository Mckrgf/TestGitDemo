package com.god.yb.testgitdemo.viewSearch.adapter;

import android.content.Context;

import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.viewSearch.model.Bean;
import com.god.yb.testgitdemo.viewSearch.util.CommonAdapter;
import com.god.yb.testgitdemo.viewSearch.util.ViewHolder;

import java.util.List;


public class SearchAdapter extends CommonAdapter<Bean> {

    public SearchAdapter(Context context, List<Bean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        holder.setImageResource(R.id.item_search_iv_icon,mData.get(position).getIconId())
                .setText(R.id.item_search_tv_title,mData.get(position).getTitle())
                .setText(R.id.item_search_tv_content,mData.get(position).getContent())
                .setText(R.id.item_search_tv_comments,mData.get(position).getComments());
    }
}
