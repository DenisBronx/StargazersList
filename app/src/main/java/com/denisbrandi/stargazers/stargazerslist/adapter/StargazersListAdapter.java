package com.denisbrandi.stargazers.stargazerslist.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denisbrandi.stargazers.R;
import com.denisbrandi.stargazers.base.BaseAdapter;
import com.denisbrandi.stargazers.databinding.ItemStargazerBinding;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.ItemListStargazersViewModel;

/**
 * Created by denis on 11/03/17.
 */

public class StargazersListAdapter extends BaseAdapter<ItemListStargazersViewModel, StargazersListAdapter.ViewHolder> {

    private class ViewType {
        static final int ITEM = 1;
        static final int PROGRESS = 2;
    }

    @Override
    public int getItemViewType(int position) {

        ItemListStargazersViewModel viewModel = getItem(position);

        if (viewModel.isProgress()) {
            return ViewType.PROGRESS;
        } else {
            return ViewType.ITEM;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rowView;
        boolean dataBinding;

        if (viewType == ViewType.ITEM) {
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stargazer, parent, false);
            dataBinding = true;
        } else {
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
            dataBinding = false;
        }

        return new ViewHolder(rowView, dataBinding);
    }

    @Override
    public void addItem(ItemListStargazersViewModel item) {
        removeProgress();
        super.addItem(item);
    }

    public void removeProgress() {
        if (getItemCount() > 0 && getItem(getItemCount() - 1).isProgress()) {
            removeItem(getItemCount() - 1);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ItemListStargazersViewModel viewModel = getItem(position);

        if (viewModel.isProgress())
            return;

        holder.getBinding().setViewModel(viewModel);
        holder.getBinding().executePendingBindings();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemStargazerBinding binding;

        public ViewHolder(View itemView, boolean dataBinding) {
            super(itemView);
            if (dataBinding)
                binding = DataBindingUtil.bind(itemView);
        }

        public ItemStargazerBinding getBinding() {
            return binding;
        }
    }

}
