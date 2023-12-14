package com.edu.ue.myproject;

import static com.edu.ue.myproject.api.ValuesApi.BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.edu.ue.myproject.api.ServiceLogin;
import com.edu.ue.myproject.model.Auth;
import com.edu.ue.myproject.model.ResponseCredentials;
import com.edu.ue.myproject.remote.ClienteRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    private EditText EtMail, EtPass, EtCpass, EtName;
    Retrofit retrofit;
    private Button btnRegister, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.EtName = findViewById(R.id.EtName);
        this.EtMail = findViewById(R.id.EtMail);
        this.EtPass = findViewById(R.id.EtPass);
        this.btnRegister = findViewById(R.id.btnRegister);
        this.btnLogin = findViewById(R.id.btnLogin);
        btnRegister.setOnClickListener(this::Register);
    }
    public void Register(View view){
        if(EtName.getText().toString() == ""||EtMail.getText().toString() == ""||EtPass.getText().toString() == "")
            Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else{
            Auth auth = new Auth();
            auth.setUse_name(EtName.getText().toString());
            auth.setUse_email(EtMail.getText().toString());
            auth.setUse_password(EtPass.getText().toString());
            retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
            Call<ResponseCredentials> call = serviceLogin.register(auth);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    if(response.isSuccessful()){
                        ResponseCredentials body = response.body();
                        String msg = body.getMsg();
                        Toast.makeText(getApplicationContext(),"Registrando...", Toast.LENGTH_SHORT).show();
                        if (msg.equals("Registration successful")){
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            goTo();
                        }
                    }else{
                        alertView("Error de credenciales"+response.body());
                    }
                }
                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    Log.i("respoonse",t.getMessage());
                    alertView("Error de peticion");
                }
            });
        } 
    }

    private void alertView(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");
        builder.setMessage(msg);
        builder.setPositiveButton("ACEPTAR",null);
        builder.create();
        builder.show();
    }

    private void goTo(){
        try {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void Login(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
    }