package com.edu.ue.myproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.ue.myproject.model.Categories;
import com.edu.ue.myproject.R;

import java.util.List;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.vh> {

    List<Categories> categoriesList;
    private Context context;

    public AdapterCategories(List<Categories> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterCategories.vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_categories,parent,false);
        return new vh(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AdapterCategories.vh holder, int position) {
        holder.textView.setText(categoriesList.get(position).getCat_category() +" "+ categoriesList.get(position).getCat_progress() +"%");
        holder.textView.setBackgroundColor(R.color.btnPrimary);
        holder.progressBar.setProgress(categoriesList.get(position).getCat_progress());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
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
