package com.example.exampap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.MyViewHolder> {

    private Context mContext;
    private List<Section> sectionList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView thumbnail;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbnail = view.findViewById(R.id.thumbnail);

        }
    }

    SectionAdapter(Context mContext, List<Section> sectionList) {
        this.mContext = mContext;
        this.sectionList = sectionList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.section_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
       Section section= sectionList.get(position);
        holder.title.setText(section.getName());


        // loading album cover using Glide library
        //TODO Old implementation
        //Glide.with(mContext).load(sectionList).into(holder.thumbnail);

        Glide.with(mContext).load(sectionList.get(position).getThumbNail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

}
