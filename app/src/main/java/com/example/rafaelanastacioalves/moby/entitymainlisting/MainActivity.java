package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailsFragment;
import com.example.rafaelanastacioalves.moby.entitydetailing.EntityDetailActivity;
import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.vo.Fruit;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;

import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private final RecyclerViewClickListener mClickListener = this;
    private MainEntityAdapter mTripPackageListAdapter;
    private int tripPackageListLoaderId = 10;
    private RecyclerView mRecyclerView;
    private LiveDataMainEntityListViewModel mLiveDataMainEntityListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        setupRecyclerView();
        subscribe();
        loadData();

    }

    private void loadData() {
        mLiveDataMainEntityListViewModel.loadData();
    }

    private void subscribe() {
        mLiveDataMainEntityListViewModel = ViewModelProviders.of(this).get(LiveDataMainEntityListViewModel.class);
        mLiveDataMainEntityListViewModel.getMainEntityList().observe(this, new Observer<List<Fruit>>() {
            @Override
            public void onChanged(@Nullable List<Fruit> mainEntities) {
                Timber.d("On Changed");
                populateRecyclerView(mainEntities);
            }
        });
    }

    private void setupViews() {
        setContentView(R.layout.activity_main);
        Timber.tag("LifeCycles");
        Timber.i("onCreate Activity");
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.trip_package_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        if (mTripPackageListAdapter == null) {
            mTripPackageListAdapter = new MainEntityAdapter(this);
        }
        mTripPackageListAdapter.setRecyclerViewClickListener(mClickListener);
        mRecyclerView.setAdapter(mTripPackageListAdapter);
    }


    private void populateRecyclerView(List<Fruit> data) {
        if (data == null) {
            mTripPackageListAdapter.setItems(null);
            //TODO add any error managing
            Timber.w("Nothing returned from Trip Package List API");

        } else {
            mTripPackageListAdapter.setItems(data);
        }

    }


    @Override
    public void onClick(View view, int position) {
        Fruit Fruit = (Fruit) mTripPackageListAdapter.getItems().get(position);

        AppCompatImageView transitionImageView = view.findViewById(R.id.fruit_imageview);
        startActivityByVersion(Fruit, transitionImageView);


    }

    private void startActivityByVersion(Fruit fruit, AppCompatImageView transitionImageView) {
        Intent i = new Intent(this, EntityDetailActivity.class);
        i.putExtra(EntityDetailsFragment.ARG_FRUIT_OBJECT, fruit);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = null;
            bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                    transitionImageView, transitionImageView.getTransitionName()).toBundle();
            startActivity(i, bundle);

        } else {
            startActivity(i);
        }
    }
}
