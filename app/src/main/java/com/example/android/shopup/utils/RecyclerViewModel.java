package com.example.android.shopup.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewModel<T>
        extends BaseAndroidViewModel {

    public static final int COUNT_ITEMS_BEFORE_END_TO_LOAD_DATA = 3;
    public static final String RECYCLER_VIEW_STATE_KEY = "Fragment_recyclerVIew";

    private final WeakReference<RecyclerViewModel> weakReference;
    private View.OnClickListener onErrorClickedListenr;

    public ObservableList<T> items;

    public ObservableField<CharSequence> footerTextMessage;
    public ObservableField<CharSequence> infoText;
    public ObservableInt loadingState;
    public ObservableInt columns;
    public ObservableBoolean refreshingEnabled;
    public ObservableBoolean refreshing;
    public ObservableInt footerState;
    public ObservableBoolean noMoreItems;
    public ObservableInt image;
    public ObservableInt title;
    public ObservableField<String> actionText;
    public ObservableBoolean showProgressbarOnErrorClicked;

    public ObservableField<View.OnClickListener> onErrorClicked;
    public ObservableField<View.OnClickListener> onAdditionalErrorClicked;

    public EndlessAdapter<T> adapter = new EndlessAdapter<T>(COUNT_ITEMS_BEFORE_END_TO_LOAD_DATA) {
        @Override
        protected void onStartLoad(int page) {
            RecyclerViewModel.this.loadData(true, page);
        }

        @Override
        public void onStopLoad() {

        }

        @Override
        public BaseItemView<T> createViewItem(Context context, int viewType) {
            return createItemView(context, viewType);
        }

        @Override
        public T getItem(int position) {
            return RecyclerViewModel.this.getItem(position);
        }

        @Override
        public int getItemViewType(int position) {
            return RecyclerViewModel.this.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return RecyclerViewModel.this.getItemCount();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            onSetData(position, getItem(position));
        }
    };

    public RecyclerViewModel() {
        super(ApplicationController.getInstance());
        weakReference = new WeakReference<>(this);
        init();
    }

    private static void saveAdapterInstanceState(Bundle outState, EndlessAdapter<? extends Parcelable> adapter) {
    }

    public static void saveRecyclerInstanceState(Bundle outState, RecyclerView recyclerView) {
        Parcelable value = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(
                RECYCLER_VIEW_STATE_KEY,
                value
        );
    }

    public void init() {
        showProgressbarOnErrorClicked = new ObservableBoolean(true);
        onRefreshListener = () -> {
            RecyclerViewModel recyclerViewModel = weakReference.get();
            if (recyclerViewModel != null) {
                recyclerViewModel.onRefresh();
            }
        };

        actionText = new ObservableField<>();
        refreshingEnabled = new ObservableBoolean(true);
        footerTextMessage = new ObservableField<>();
        footerTextMessage.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (adapter.getFooterView() != null) {
                    adapter.getFooterView().setInfoText(footerTextMessage.get());
                }
            }
        });
        infoText = new ObservableField<>();
        loadingState = new ObservableInt();

        items = new ObservableArrayList<>();
        footerState = new ObservableInt();
        columns = new ObservableInt(1);
        noMoreItems = new ObservableBoolean(false);
        noMoreItems.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                adapter.setMoreItems(!noMoreItems.get());
                adapter.notifyDataSetChanged();
            }
        });

        image = new ObservableInt();
        title = new ObservableInt();
        refreshing = new ObservableBoolean(false);
        onErrorClickedListenr = v -> {
            RecyclerViewModel recyclerViewModel = weakReference.get();
            if (recyclerViewModel != null) {
                recyclerViewModel.onRefresh();
            }
        };

        onErrorClicked = new ObservableField<>(onErrorClickedListenr);
        onAdditionalErrorClicked = new ObservableField<>();
    }

    public T getItem(int position) {
        return adapter.isEmpty() ? null : adapter.isShowFooterView() && position == getItemCount() - 1 ?
                null : adapter.getItems().get(position);
    }

    public int getItemViewType(int position) {
        return adapter.isShowFooterView() && position == adapter.getItemCount() - 1 ?
                EndlessAdapter.FOOTER_VIEW_TYPE : 0;
    }

    public int getItemCount() {
        return adapter.isShowFooterView() ? adapter.getItems().size() + 1 : adapter.getItems().size();
    }

    public void onRefresh() {
        onRefresh(false);
    }

    public void onRefresh(boolean showProgress) {
        adapter.resetStates();
    }

    public SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    public void onError(String errorMessage, int page) {
        showProgressbarOnErrorClicked.set(true);
    }

    public static <T> void handleResponse(
            RecyclerViewModel<T> viewModel,
            ArrayList<T> response) {

        viewModel.addPage(response);
    }

    public void handleAddItem(RecyclerViewModel<T> viewModel, T item, int position, String endOfListText) {
        viewModel.footerTextMessage.set(endOfListText);

        viewModel.loadingState.notifyChange();
        viewModel.footerState.notifyChange();
        viewModel.footerTextMessage.notifyChange();

        adapter.addItem(item, position);
    }

    public void onSetData(int position, T item) {

    }

    @NonNull
    public BaseItemView<T> createItemView(Context context, int ViewType) {
        throw new RuntimeException("Override createItemView.");
    }

    public void loadData(boolean showProgress, int page) {

    }

    protected void addPage(List<T> page) {
        adapter.addItems(page);
        items.addAll(page);
        adapter.notifyDataSetChanged();
    }

    private void clearItems() {
        adapter.clearItems();
        items.clear();
    }

    private void setEmptyText(CharSequence emptyText) {
        infoText.set(emptyText);
    }

    //    @Bindable
    public EndlessAdapter getAdapter() {
        return adapter;
    }

}
