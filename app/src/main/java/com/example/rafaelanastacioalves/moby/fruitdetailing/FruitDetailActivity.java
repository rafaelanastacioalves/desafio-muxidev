package com.example.rafaelanastacioalves.moby.fruitdetailing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.vo.Fruit;

import static com.example.rafaelanastacioalves.moby.fruitdetailing.FruitDetailFragment.ARG_FRUIT_OBJECT;


public class FruitDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fruit fruit = (Fruit) getIntent().getSerializableExtra(ARG_FRUIT_OBJECT);
        setContentView(R.layout.activity_package_detail);
        setupActionBar();


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();

            arguments.putSerializable(ARG_FRUIT_OBJECT, fruit);
            FruitDetailFragment fragment = new FruitDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.package_detail_fragment_container, fragment)
                    .commit();


            supportPostponeEnterTransition();
        }
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

    }

}
