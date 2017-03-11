package com.denisbrandi.stargazers.pagination;

import javax.inject.Inject;

/**
 * Created by denis on 11/03/17.
 */

public class Paginator {

    private int page = 1;

    public interface PaginatorListener {
        void onPaginationProgress();
    }

    private int previousTotal = 0;
    private boolean loading = true;
    private boolean limitReached = false;

    private PaginatorListener listener;

    @Inject
    public Paginator(PaginatorListener listener) {
        this.listener = listener;
    }

    public void calculatePagination(int totalItemCount, int visibleItemCount, int firstVisibleItem, boolean isPaginationProgressVisible) {
        if (limitReached)
            return;

        if (loading) {

            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem)) {

            if (totalItemCount > 0 && !isPaginationProgressVisible) {
                page++;
                listener.onPaginationProgress();
            }

            loading = true;
        }

    }

    public int getPage() {
        return page;
    }

    public void setLimitReached(boolean limitReached) {
        this.limitReached = limitReached;
    }

    public void cleanup() {
        previousTotal = 0;
        loading = true;
        limitReached = false;
        page = 1;
    }
}
