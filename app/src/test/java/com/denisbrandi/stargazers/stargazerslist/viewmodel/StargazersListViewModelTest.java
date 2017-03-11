package com.denisbrandi.stargazers.stargazerslist.viewmodel;

import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.pagination.Paginator;
import com.denisbrandi.stargazers.webservice.StargazersApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by denis on 11/03/17.
 */
public class StargazersListViewModelTest {

    private Paginator paginator;
    private StargazersListViewModel viewModel;

    private String owner = "owner";
    private String repository = "repository";

    @Mock
    private Paginator.PaginatorListener paginatorListener;
    @Mock
    private StargazersListViewModel.StargazersListViewModelListener viewModelListener;
    @Mock
    private StargazersApi stargazersApi;

    @Mock
    private List<Stargazer> dummyList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        paginator = new Paginator(paginatorListener);
        viewModel = new StargazersListViewModel(stargazersApi, paginator, viewModelListener);
    }

    @After
    public void tearDown() {
        viewModel.cleanup();
    }

    @Test
    public void checkDefaultStatus() {
        verify(paginatorListener, never()).onPaginationProgress();
        verify(stargazersApi, never()).getStargazers(owner, repository, paginator.getPage());

        assertTrue(viewModel.showPlaceholder.get());
        assertFalse(viewModel.showProgress.get());
        assertFalse(viewModel.showEmptyView.get());
    }

    @Test
    public void searchWithResults() {

        when(dummyList.size()).thenReturn(30);
        assertTrue(viewModel.showPlaceholder.get());
        viewModel.showNewData(dummyList);
        assertFalse(viewModel.showPlaceholder.get());
        viewModel.setDataCount(dummyList.size());
        assertFalse(viewModel.showEmptyView.get());
    }

    @Test
    public void searchWithNoResults() {

        when(dummyList.size()).thenReturn(0);
        assertTrue(viewModel.showPlaceholder.get());
        viewModel.showNewData(dummyList);
        assertFalse(viewModel.showPlaceholder.get());
        viewModel.setDataCount(dummyList.size());
        assertTrue(viewModel.showEmptyView.get());
    }

}