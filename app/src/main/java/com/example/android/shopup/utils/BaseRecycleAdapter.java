package com.example.android.shopup.utils;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycleAdapter<T>
        extends RecyclerView.Adapter<BaseRecycleAdapter.ViewHolder> implements BaseAdapterI<T> {

    private List<T> items;
    private int selectedItemPosition;

    public BaseRecycleAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(createViewItem(parent.getContext(), viewType).getView());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.itemView instanceof BaseItemView) {
            ((BaseItemView<T>) holder.itemView).setData(position, getItem(position));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        if (position == items.size()) {
            return null;
        }

        return items.get(position);
    }

    public void removeAndNotify(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(@NonNull List<T> items) {
        if (items == null) {
            return;
        }

        this.items = items;
        notifyDataSetChanged();
    }

    public void addItem(T t, int location) {
        items.add(location, t);
        notifyItemInserted(location);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void setItem(int position, T t) {
        items.set(position, t);
        notifyItemChanged(position, t);
    }

    public void updateAdapter(List<T> items) {
        clearItems();
        addItems(items);
    }

    public void toggleSelection(int pos) {
        notifyItemChanged(pos);
        selectedItemPosition = pos;
        notifyItemChanged(pos);
    }

    public boolean isItemSelected(int pos) {
        return pos == selectedItemPosition;
    }

    public int indexOf(T item) {
        return items.indexOf(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
