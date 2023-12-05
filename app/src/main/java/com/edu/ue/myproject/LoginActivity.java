package com.edu.ue.myproject;
import static com.edu.ue.myproject.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.ue.myproject.api.ServiceLogin;
import com.edu.ue.myproject.model.Auth;
import com.edu.ue.myproject.model.ResponseCredentials;
import com.edu.ue.myproject.remote.ClienteRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Retrofit retrofit;
    EditText EtMail, EtPass;
    Button btnLogin , btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EtMail = findViewById(R.id.EtMail);
        EtPass = findViewById(R.id.EtPass);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this::Login);
    }
    public void Login(View view){
        String user = EtMail.getText().toString();
        String pass = EtPass.getText().toString();

        if(user.equals("")||pass.equals("")) {
            Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }else{
            Auth auth = new Auth();
            auth.setUse_email(user);
            auth.setUse_password(pass);
            retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
            Call<ResponseCredentials> call = serviceLogin.accessLogin(auth);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    if(response.isSuccessful()){
                        ResponseCredentials body = response.body();
                        String msg = body.getMsg();
                        Toast.makeText(LoginActivity.this,"Ingresando...", Toast.LENGTH_SHORT).show();
                        if (msg.equals("User logged success")){
                            SharedPreferences shared = getSharedPreferences("credeniales", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString("token",body.getToken());
                            editor.commit();
                            goTo();
                        }
                    }else{
                        alertView("Error de credenciales");
                    }
                }

                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    Log.i("respoonse",t.getMessage());
                    alertView("Error de peticion");
                }
            });
            //goTo();
            /*
            Boolean checkuserpass = .checkusernamepassword(user, pass);
            if(checkuserpass==true){
                Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        */}
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
public void Register(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
}