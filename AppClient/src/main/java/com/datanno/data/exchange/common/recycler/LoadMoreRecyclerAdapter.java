package com.datanno.data.exchange.common.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datanno.data.exchange.R;



public class LoadMoreRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int LOAD_MORE_ITEM_TYPE = 99;

    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private boolean isNeedLoadMore;
    private Context mContext;

    public LoadMoreRecyclerAdapter(Context context, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, boolean isNeedLoadMore) {
        mAdapter = adapter;
        mContext = context;
        this.isNeedLoadMore = isNeedLoadMore;
    }

    public boolean isNeedLoadMore() {
        return isNeedLoadMore;
    }

    public void setIsNeedLoadMore(boolean isNeedLoadMore) {
        this.isNeedLoadMore = isNeedLoadMore;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        if (viewType == LOAD_MORE_ITEM_TYPE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_recyclerview_bottom_more, parent, false);
            return new RViewHolder(view);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        if (position == mAdapter.getItemCount()) return;
        mAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if (isNeedLoadMore) {
            return mAdapter.getItemCount() + 1;
        }
        return mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mAdapter.getItemCount())
            return LOAD_MORE_ITEM_TYPE;
        return mAdapter.getItemViewType(position);
    }

    public RecyclerView.Adapter<RecyclerView.ViewHolder> getWrappedAdapter() {
        return mAdapter;
    }

    private  class RViewHolder extends RecyclerView.ViewHolder{
        private RViewHolder(View itemView) {
            super(itemView);
        }
    }
}
