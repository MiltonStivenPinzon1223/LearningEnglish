package com.edu.ue.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText EtMail, EtPass, EtCpass, EtName;
    private Button btnRegister, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.EtName = findViewById(R.id.EtName);
        this.EtMail = findViewById(R.id.EtMail);
        this.EtPass = findViewById(R.id.EtPass);
        this.EtCpass = findViewById(R.id.EtCpass);
        this.btnRegister = findViewById(R.id.btnRegister);
        this.btnLogin = findViewById(R.id.btnLogin);

    }
    public void Register(View view){
        String name = EtName.getText().toString();
        String mail = EtMail.getText().toString();
        String pass = EtPass.getText().toString();
        String cpass = EtCpass.getText().toString();

        if(name.equals("")||pass.equals("")||cpass.equals("")||mail.equals(""))
            Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else{
            /*if(pass.equals(cpass)){
                Boolean checkuser = DB.checkusername(user);
                if(checkuser==false){
                    Boolean insert = DB.insertData(user, pass);
                    if(insert==true){
                        Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
            }*/
        } }
    public void Login(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
    }