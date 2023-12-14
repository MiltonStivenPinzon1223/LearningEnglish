package com.edu.ue.myproject;

import static com.edu.ue.myproject.api.ValuesApi.BASE_URL;
import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.ue.myproject.adapter.AdapterCategories;
import com.edu.ue.myproject.adapter.AdapterResults;
import com.edu.ue.myproject.api.ServiceCategories;
import com.edu.ue.myproject.api.ServiceResults;
import com.edu.ue.myproject.model.Categories;
import com.edu.ue.myproject.model.ResponseCategories;
import com.edu.ue.myproject.model.ResponseResults;
import com.edu.ue.myproject.model.Results;
import com.edu.ue.myproject.remote.ClienteRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProgressFragment extends Fragment {

    public Retrofit retrofit;
    public List<Categories> categoriesList;
    public List<Results> ResultsList;
    public RecyclerView rv_categories;
    public RecyclerView rv_results;
    public ResponseCategories responseCategories;
    public ResponseResults responseResults;
    private  AdapterCategories adapterCategories;
    private  AdapterResults adapterResults;

    public ProgressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        rv_categories = (RecyclerView) view.findViewById(R.id.rv_categories);
        rv_results = (RecyclerView) view.findViewById(R.id.rv_results);
        rv_categories.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rv_results.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        showCategories();
        showResults();
        return view;
    }

    public void showCategories(){
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceCategories serviceCategories = retrofit.create(ServiceCategories.class);
        Call<ResponseCategories> call = serviceCategories.categories();
        call.enqueue(new Callback<ResponseCategories>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseCategories> call, Response<ResponseCategories> response) {
                if (response.isSuccessful()){
                    responseCategories = response.body();
                    categoriesList = responseCategories.getCategories();
                    adapterCategories = new AdapterCategories(categoriesList,getContext());
                    rv_categories.setAdapter(adapterCategories);
                }
            }

            @Override
            public void onFailure(Call<ResponseCategories> call, Throwable t) {
                Toast.makeText(getContext(), "Error de Peticion"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showResults(){
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceResults serviceResults = retrofit.create(ServiceResults.class);
        Call<ResponseResults> call = serviceResults.results();
        call.enqueue(new Callback<ResponseResults>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseResults> call, Response<ResponseResults> response) {
                if (response.isSuccessful()){
                    responseResults = response.body();
                    ResultsList = responseResults.getResults();
                    adapterResults = new AdapterResults(ResultsList,getContext());
                    rv_results.setAdapter(adapterResults);
                }else {
                    Toast.makeText(getContext(), "Error de mostrar datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseResults> call, Throwable t) {
                Toast.makeText(getContext(), "Error de Peticion"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void alertView(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Progreso");
        builder.setMessage(msg);
        builder.setPositiveButton("ACEPTAR",null);
        builder.create();
        builder.show();
    }
}