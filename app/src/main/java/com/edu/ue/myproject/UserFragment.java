package com.edu.ue.myproject;

import static com.edu.ue.myproject.api.ValuesApi.BASE_URL;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.ue.myproject.api.ServiceUsers;
import com.edu.ue.myproject.model.ResponseUsers;
import com.edu.ue.myproject.model.Users;
import com.edu.ue.myproject.remote.ClienteRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserFragment extends Fragment {
    public Retrofit retrofit;
    public List<Users> UsersList;
    public RecyclerView recyclerView;
    public ResponseUsers responseUsers;
    public EditText etName;
    public EditText etMail;
    public Button btnUpdate;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        etName = (EditText) view.findViewById(R.id.etName);
        etMail = (EditText) view.findViewById(R.id.etMail);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this::updateUser);
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
                    etName.setText(UsersList.get(0).getUse_name());
                    etMail.setText(UsersList.get(0).getUse_email());
                }
            }

            @Override
            public void onFailure(Call<ResponseUsers> call, Throwable t) {
                Toast.makeText(getContext(), "Error de peticion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateUser(View view) {
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceUsers serviceUsers = retrofit.create(ServiceUsers.class);
        Call<ResponseUsers> call = serviceUsers.users();
        call.enqueue(new Callback<ResponseUsers>() {
            @Override
            public void onResponse(Call<ResponseUsers> call, Response<ResponseUsers> response) {
                if (response.isSuccessful()) {
                    responseUsers = response.body();
                    UsersList = responseUsers.getUsers();
                    Users user = new Users(UsersList);
                    user.setUse_name(etName.getText().toString());
                    user.setUse_email(etMail.getText().toString());
                    user.setUse_password(user.getUse_password());
                    Call<ResponseUsers> call2 = serviceUsers.usersUpdate(user);
                    call2.enqueue(new Callback<ResponseUsers>() {
                        @Override
                        public void onResponse(Call<ResponseUsers> call, Response<ResponseUsers> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUsers> call, Throwable t) {
                            Toast.makeText(getContext(), "Error de peticion de actualizacion", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseUsers> call, Throwable t) {
                Toast.makeText(getContext(), "Error de peticion", Toast.LENGTH_SHORT).show();
            }

        });
    }
}