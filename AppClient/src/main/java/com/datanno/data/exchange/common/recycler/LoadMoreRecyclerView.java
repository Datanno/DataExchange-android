package com.datanno.data.exchange.common.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;


public class LoadMoreRecyclerView extends RecyclerView {

    private OnLoadMoreListener mListener;


    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setListener(OnLoadMoreListener mListener) {
        this.mListener = mListener;
    }
    private void init() {
        this.addOnScrollListener(onScrollListener);
    }

    private OnScrollListener onScrollListener=new OnScrollListener() {
        @Override
        public void onScrolled(@NonNull  RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
            int totalItemCount = getLayoutManager().getItemCount();
            if (lastVisibleItem >= totalItemCount - 1 && dy > 0 && mListener != null) {
                mListener.loadMore();
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case SCROLL_STATE_IDLE:
                    if (getContext() != null)
                        Glide.with(getContext()).resumeRequests();
                    break;
                case SCROLL_STATE_DRAGGING:
                    if (getContext() != null)
                        Glide.with(getContext()).pauseRequests();
                    break;
                case SCROLL_STATE_SETTLING:
                    if (getContext() != null)
                        Glide.with(getContext()).pauseRequests();
                    break;

            }
        }
    };



    public interface OnLoadMoreListener {
        void loadMore();
    }
}
