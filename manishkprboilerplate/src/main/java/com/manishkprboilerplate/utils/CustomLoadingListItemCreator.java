package com.manishkprboilerplate.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manishkprboilerplate.R;
import com.paginate.recycler.LoadingListItemCreator;

/**
 * Created by Munish Kapoor on 15/2/17.
 */

public class CustomLoadingListItemCreator<T extends android.support.v7.widget.RecyclerView.ViewHolder> implements LoadingListItemCreator {

    RecyclerView recyclerView;
    RecyclerView.Adapter<T> adapter;

    public CustomLoadingListItemCreator(RecyclerView recyclerView, RecyclerView.Adapter<T> adapter){
        this.recyclerView = recyclerView;
        this.adapter    = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_loading_list_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        vh.tvLoading.setText(String.format("Total items loaded: %d.\nLoading more...", adapter.getItemCount()));

        // This is how you can make full span if you are using StaggeredGridLayoutManager
        if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) vh.itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView tvLoading;

        public VH(View itemView) {
            super(itemView);
            tvLoading = (TextView) itemView.findViewById(R.id.tv_loading_text);
        }
    }

}


