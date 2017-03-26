package com.denisbrandi.stargazers.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by denis on 11/03/17.
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private ArrayList<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addItems(Collection<T> itemsToAdd) {
        int startPosition = items.size();
        items.addAll(itemsToAdd);
        notifyItemRangeInserted(startPosition, itemsToAdd.size());
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
