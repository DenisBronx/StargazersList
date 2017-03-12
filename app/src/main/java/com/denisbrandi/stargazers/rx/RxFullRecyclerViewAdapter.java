package com.denisbrandi.stargazers.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import rx.Observable;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Created by denis on 19/12/16.
 */

public final class RxFullRecyclerViewAdapter<T> {

    /**
     * Create an observable of data change events for {@code RecyclerView.adapter}.
     * <p>
     * <em>Note:</em> A value will be emitted immediately on subscribe.
     */
    @CheckResult
    @NonNull
    public static <T extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder>> Observable<T> dataChanges(
            @NonNull T adapter) {
        checkNotNull(adapter, "adapter == null");
        return Observable.create(new RecyclerAdapterFullDataChangeOnSubscribe<T>(adapter));
    }

    private RxFullRecyclerViewAdapter() {
        throw new AssertionError("No instances.");
    }

}
