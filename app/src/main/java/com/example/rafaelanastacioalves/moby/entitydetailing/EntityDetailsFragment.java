package com.example.rafaelanastacioalves.moby.entitydetailing;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.vo.Fruit;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntityDetailsFragment extends Fragment implements View.OnClickListener {

    public static final String ARG_FRUIT_OBJECT = "fruit_object";

    private LiveDataEntityDetailsViewModel mLiveDataEntityDetailsViewModel;

    @BindView(R.id.fruit_original_price_text_view)
    TextView fruitOriginalPriceTextView;
    @BindView(R.id.fruit_converted_price_text_view)
    TextView fruitConvertedPriceTextView;
    @BindView(R.id.fruit_name_text_view)
    TextView fruitNameTextView;

    @BindView(R.id.trip_package_detail_imageview)
    ImageView tripPackageDetailImageview;


    public EntityDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fruit fruit = (Fruit) getArguments().getSerializable(ARG_FRUIT_OBJECT);
        subscribe();
        loadData(fruit);
    }

    private void loadData(Fruit fruit) {
        mLiveDataEntityDetailsViewModel.loadData(fruit.getPrice());
    }

    private void subscribe() {
        mLiveDataEntityDetailsViewModel = ViewModelProviders.of(this).get(LiveDataEntityDetailsViewModel.class);
        mLiveDataEntityDetailsViewModel.getEntityDetails().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(@Nullable Float entityDetails) {
                setViewsWith(entityDetails);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflateViews(inflater, container);
    }


    private View inflateViews(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_detail_entity_detail_view, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private void setupActionBarWithTitle(String title) {
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);


        }
    }

    private void setViewsWith(Float entityDetails) {

        fruitOriginalPriceTextView.setText("---");
        fruitNameTextView.setText("Banana");
        fruitConvertedPriceTextView.setText(String.valueOf(entityDetails));

        setupActionBarWithTitle("---");
        getActivity().supportStartPostponedEnterTransition();

//        Picasso.get()
//                .load(entityDetails.getImage_url())
//                .into(tripPackageDetailImageview, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        getActivity().supportStartPostponedEnterTransition();
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                });
//

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "Comprado!", Toast.LENGTH_SHORT).show();
    }


}
