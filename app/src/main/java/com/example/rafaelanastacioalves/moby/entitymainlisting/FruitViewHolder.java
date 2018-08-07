package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;
import com.example.rafaelanastacioalves.moby.vo.Fruit;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FruitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.fruit_name_container)
    View tripPackageContainer;
    @BindView(R.id.fruit_imageview)
    ImageView fruitImageView;
    @BindView(R.id.fruit_name_text_view)
    TextView fruitNameTextView;
    @BindView(R.id.fruit_price)
    TextView fruitPrice;
    private RecyclerViewClickListener aRecyclerViewListener;


    public FruitViewHolder(View itemView, RecyclerViewClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.aRecyclerViewListener = clickListener;
        tripPackageContainer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    public void bind(Fruit aFruit, Context context) {

        fruitNameTextView.setText(aFruit.getName());
        fruitPrice.setText(String.valueOf(aFruit.getPrice()));
        final StateListDrawable placeholderList = (StateListDrawable) context.getResources().getDrawable(R.drawable.ic_placeholder_map_selector);
        Picasso.get()
                .load(aFruit.getImage())
                .placeholder(placeholderList)
                .into(fruitImageView);

    }
}
