package com.denisbrandi.stargazers.rx;

import android.support.v7.widget.RecyclerView;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static rx.android.MainThreadSubscription.verifyMainThread;

/**
 * Created by denis on 19/12/16.
 */

public class RecyclerAdapterFullDataChangeOnSubscribe<T extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder>>
        implements Observable.OnSubscribe<T> {
    final T adapter;

    RecyclerAdapterFullDataChangeOnSubscribe(T adapter) {
        this.adapter = adapter;
    }

    @Override
    public void call(final Subscriber<? super T> subscriber) {
        verifyMainThread();

        final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                goToNext();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                goToNext();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                goToNext();
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                goToNext();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                goToNext();
            }

            @Override
            public void onChanged() {
                goToNext();
            }

            void goToNext() {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(adapter);
                }
            }
        };

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                adapter.unregisterAdapterDataObserver(observer);
            }
        });

        adapter.registerAdapterDataObserver(observer);

        // Emit initial value.
        subscriber.onNext(adapter);
    }
}
