package com.edu.ue.myproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.ue.myproject.R;
import com.edu.ue.myproject.model.Results;

import java.util.List;

public class AdapterResults extends RecyclerView.Adapter<AdapterResults.vh>{
    List<Results> resultsList;
    private Context context;

    public AdapterResults(List<Results> resultsList, Context context) {
        this.resultsList = resultsList;
        this.context = context;
    }


    @Override
    public AdapterResults.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_results,parent,false);
        return new AdapterResults.vh(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AdapterResults.vh holder, int position) {
        holder.tvDate.setText(resultsList.get(position).getWor_english()+" - "+resultsList.get(position).getCreated_at());
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class vh extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvEnglish;
        TextView tvSpanish;
        public vh(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvEnglish = itemView.findViewById(R.id.tvEnglish);
            tvSpanish = itemView.findViewById(R.id.tvSpanish);
        }
    }
}
