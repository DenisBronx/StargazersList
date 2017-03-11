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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stargazer, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getBinding().setViewModel(getItem(position));
        holder.getBinding().executePendingBindings();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemStargazerBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemStargazerBinding getBinding() {
            return binding;
        }
    }

}
