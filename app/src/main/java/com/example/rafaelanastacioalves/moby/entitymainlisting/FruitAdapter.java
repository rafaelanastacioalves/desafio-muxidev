package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;
import com.example.rafaelanastacioalves.moby.vo.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitViewHolder> {
    private RecyclerViewClickListener recyclerViewClickListener;
    private List<Fruit> items = new ArrayList<>();

    private Context mContext;

    public FruitAdapter(Context context) {
        mContext = context;
    }


    public void setRecyclerViewClickListener(RecyclerViewClickListener aRVC) {
        this.recyclerViewClickListener = aRVC;
    }

    public List<Fruit> getItems() {
        return this.items;
    }

    public void setItems(List<Fruit> items) {
        this.items = items;
        notifyDataSetChanged();


    }

    @Override
    public FruitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FruitViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_viewholder, parent, false), recyclerViewClickListener);
    }


    @Override
    public void onBindViewHolder(FruitViewHolder holder, int position) {
        Fruit aRepoW = getItems().get(position);
        ((FruitViewHolder) holder).bind(aRepoW, mContext);
    }

    @Override
    public int getItemCount() {
        if (getItems() != null) {
            return getItems().size();
        } else {
            return 0;
        }
    }
}

