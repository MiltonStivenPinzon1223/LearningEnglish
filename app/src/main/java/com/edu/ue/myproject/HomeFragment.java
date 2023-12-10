package com.edu.ue.myproject;

import static com.edu.ue.myproject.api.ValuesApi.BASE_URL;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.ue.myproject.api.ServiceUsers;
import com.edu.ue.myproject.model.Users;
import com.edu.ue.myproject.model.ResponseUsers;
import com.edu.ue.myproject.remote.ClienteRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.content.Intent;
public class HomeFragment extends Fragment {
    public Retrofit retrofit;
    public List<Users> UsersList;
    public ResponseUsers responseUsers;
    public TextView tvWelcome;
    public Button btnStart;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvWelcome = (TextView) view.findViewById(R.id.tvWelcome);
        btnStart = (Button) view.findViewById(R.id.btnStart);
        getUser();
        return view;
    }


    public void getUser(){
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceUsers serviceUsers = retrofit.create(ServiceUsers.class);
        Call<ResponseUsers> call = serviceUsers.users();
        call.enqueue(new Callback<ResponseUsers>() {
            @Override
            public void onResponse(Call<ResponseUsers> call, Response<ResponseUsers> response) {
                if (response.isSuccessful()){
                    responseUsers = response.body();
                    UsersList = responseUsers.getUsers();
                    tvWelcome.setText("Bienvenido "+UsersList.get(0).getUse_name()+". ¿Estás listo para aprender inglés?");
                }
            }

            @Override
            public void onFailure(Call<ResponseUsers> call, Throwable t) {
                Toast.makeText(getContext(), "Error de peticion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}