package com.example.exampap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class TimetableItemAdapter extends RecyclerView.Adapter<TimetableItemAdapter.TimetableItemViewHolder> {

    private List<TimetableItem> timetableItems;
    private final Context context;

    public TimetableItemAdapter(List<TimetableItem> timetableItems, Context context) {
        this.timetableItems = timetableItems;
        this.context = context;
    }

    @NonNull
    @Override
    public TimetableItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TimetableItemAdapter.TimetableItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timetable_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableItemViewHolder timetableItemViewHolder, int i) {
        timetableItemViewHolder.bindItem(timetableItems.get(i));
    }

    @Override
    public int getItemCount() {
        return timetableItems.size();
    }

    public class TimetableItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGroup;
        private TextView tvDay;
        private TextView tvTime;
        private TextView tvVenue;
        private Button button;

        public TimetableItemViewHolder(View itemView) {
            super(itemView);
           tvGroup = itemView.findViewById(R.id.group);
            tvDay = itemView.findViewById(R.id.day);
            tvTime = itemView.findViewById(R.id.time2);
            tvVenue = itemView.findViewById(R.id.venue);
            button = itemView.findViewById(R.id.button);
        }

        public void bindItem(TimetableItem timetableItem) {
            tvGroup.setText("GROUP:" + timetableItem.getGroup());
            tvDay.setText("DAY:" + timetableItem.getDay());
            tvTime.setText("TIME:" +timetableItem.getTime());
            tvVenue.setText("ROOM:" +timetableItem.getVenue());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://10.9.41.27/" + ""));
                    context.startActivity(intent);
                }
            });

        }
    }
}
