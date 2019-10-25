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
    private OnItemClickListener onItemClickListener;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView thumbnail;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbnail = view.findViewById(R.id.thumbnail);

        }

        public void bind(final Section section, final OnItemClickListener onItemClickListener){
            title.setText(section.getName());
            Glide.with(mContext).load(section.getThumbNail()).into(thumbnail);
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(section);
                }
            });
        }
    }

    SectionAdapter(Context mContext, List<Section> sectionList, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.sectionList = sectionList;
        this.onItemClickListener = onItemClickListener;
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
       holder.bind(section,onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

}
