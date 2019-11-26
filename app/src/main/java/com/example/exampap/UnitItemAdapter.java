package com.example.exampap;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UnitItemAdapter extends RecyclerView.Adapter<UnitItemAdapter.UnitItemViewHolder> {

    private List<UnitItem> unitItems;
    private final Context context;

    public UnitItemAdapter(List<UnitItem> unitItemList, Context context){
        this.unitItems = unitItemList;

        this.context = context;
    }
    @NonNull
    @Override
    public UnitItemAdapter.UnitItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UnitItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.units_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UnitItemAdapter.UnitItemViewHolder unitItemViewHolder, int i) {
        unitItemViewHolder.bindItem(unitItems.get(i));
    }

    @Override
    public int getItemCount() {
        return unitItems.size();
    }

    public class UnitItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUnitName;
        private TextView tvUnitCode;
        private Button button;
        //private ProgressBar progressBar;
        public UnitItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUnitName = itemView.findViewById(R.id.unit_name);
            tvUnitCode = itemView.findViewById(R.id.unit_code);
            button = itemView.findViewById(R.id.button);
            //progressBar = itemView.findViewById(R.id.progressBar);
        }
        public  void bindItem(final UnitItem unitItem){
            tvUnitName.setText(unitItem.getUnit_name());
            tvUnitCode.setText(unitItem.getUnit_code());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,TimetableActivity.class);
                    intent.putExtra("Unit Code", unitItem.getUnit_code().trim());
                    context.startActivity(intent);
                }
            });
//            progressBar.setProgress(unitItem.getProgress());
//
//            if(unitItem.getStatus() == UnitItem.Status.PENDING){
//                button.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.GONE);
//            }else{
//                button.setVisibility(View.GONE);
//                progressBar.setVisibility(View.VISIBLE);
//            }
        }
    }
}
