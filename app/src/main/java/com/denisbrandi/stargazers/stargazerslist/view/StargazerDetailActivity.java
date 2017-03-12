package com.denisbrandi.stargazers.stargazerslist.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.denisbrandi.stargazers.R;
import com.denisbrandi.stargazers.StargazersApp;
import com.denisbrandi.stargazers.base.BaseActivity;
import com.denisbrandi.stargazers.dagger.component.DaggerStargazerDetailComponent;
import com.denisbrandi.stargazers.dagger.module.StargazerDetailModule;
import com.denisbrandi.stargazers.databinding.ActivityStargazerDetailBinding;
import com.denisbrandi.stargazers.model.Stargazer;
import com.denisbrandi.stargazers.stargazerslist.viewmodel.StargazerDetailViewModel;

import org.parceler.Parcels;

import javax.inject.Inject;

/**
 * Created by denis on 12/03/17.
 */

public class StargazerDetailActivity extends BaseActivity {

    public static final String STARGAZER = "stargazer";

    @Inject
    StargazerDetailViewModel viewModel;

    private ActivityStargazerDetailBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stargazer_detail);

        setBackButton(binding.toolbar);

        Stargazer stargazer = Parcels.unwrap(getIntent().getParcelableExtra(STARGAZER));

        DaggerStargazerDetailComponent.builder()
                .netComponent(((StargazersApp) getApplication()).getNetComponent())
                .stargazerDetailModule(new StargazerDetailModule(stargazer))
                .build().inject(this);

        binding.setViewModel(viewModel);

    }
}
