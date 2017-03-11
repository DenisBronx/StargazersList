package com.denisbrandi.stargazers.pagination;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by denis on 11/03/17.
 */
public class PaginatorTest {

    private Paginator paginator;

    @Mock
    private Paginator.PaginatorListener listener;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        paginator = new Paginator(listener);
    }

    @Test
    public void checkPaginationStart() {

        paginator.calculatePagination(1, 1, 1, false);

        verify(listener).onPaginationProgress();

    }

    @Test
    public void checkPaginationStartEmpty() {

        paginator.calculatePagination(0, 0, 0, false);

        verify(listener, never()).onPaginationProgress();

    }

    @Test
    public void checkPaginationAlreadyInProgress() {
        paginator.calculatePagination(1, 1, 1, true);

        verify(listener, never()).onPaginationProgress();
    }

    @Test
    public void checkPaginationLimitReached() {
        paginator.setLimitReached(true);
        paginator.calculatePagination(1, 1, 1, false);

        verify(listener, never()).onPaginationProgress();
    }


}