package com.edu.ue.myproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.ue.myproject.R;

public class AdapterWords extends RecyclerView.Adapter<AdapterWords.vh> {
    @NonNull
    @Override
    public AdapterWords.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWords.vh holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class vh extends RecyclerView.ViewHolder {
        TextView textView;
        ProgressBar progressBar;
        public vh(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvCategory);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
