package com.edu.ue.myproject;

import static com.edu.ue.myproject.api.ValuesApi.BASE_URL;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.ue.myproject.api.ServiceResults;
import com.edu.ue.myproject.api.ServiceWords;
import com.edu.ue.myproject.model.Registration;
import com.edu.ue.myproject.model.ResponseResponse;
import com.edu.ue.myproject.model.ResponseResults;
import com.edu.ue.myproject.model.ResponseWords;
import com.edu.ue.myproject.model.Results;
import com.edu.ue.myproject.model.Words;
import com.edu.ue.myproject.remote.ClienteRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PracticeFragment extends Fragment {
    public TextView tvWord;
    public Button btnOp1;
    public Button btnOp2;
    public Button btnOp3;
    public ImageButton imageButton;
    public Retrofit retrofit;
    public ArrayList<Words> WordsList;
    public ArrayList<Results> ResultsList;
    public ResponseWords responseWords;
    public ResponseResults responseResults;
    public Words word;

    public PracticeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_practice, container, false);
        tvWord = (TextView) view.findViewById(R.id.tvWord);
        btnOp1 = (Button) view.findViewById(R.id.BtnOp1);
        btnOp2 = (Button) view.findViewById(R.id.BtnOp2);
        btnOp3 = (Button) view.findViewById(R.id.BtnOp3);
        getWord();
        btnOp1.setOnClickListener(this::validate1);
        btnOp2.setOnClickListener(this::validate2);
        btnOp3.setOnClickListener(this::validate3);

        return view;
    }


    private void getWord() {
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceWords serviceWords = retrofit.create(ServiceWords.class);
        Call<ResponseWords> call = serviceWords.word();
        call.enqueue(new Callback<ResponseWords>() {
            @Override
            public void onResponse(Call<ResponseWords> call, Response<ResponseWords> response) {
                if (response.isSuccessful()){
                    responseWords = response.body();
                    WordsList = responseWords.getWords();
                    tvWord.setText(WordsList.get(0).getWor_spanish());
                    btnOp1.setText(WordsList.get(0).getWord1());
                    btnOp2.setText(WordsList.get(0).getWor_english());
                    btnOp3.setText(WordsList.get(0).getWord2());
                    word = new Words(WordsList);
                }else{
                    Toast.makeText(getContext(), "Error al mostrar datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseWords> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validate1(View view) {
        String opc = btnOp1.getText().toString();
        validate(opc);
    }

    private void validate2(View view) {
        String opc = btnOp2.getText().toString();
        validate(opc);
    }
    private void validate3(View view) {
        String opc = btnOp3.getText().toString();
        validate(opc);
    }
    private void validate(String opc) {
        if (word.getWor_english() == opc){
            Toast.makeText(getContext(), "respuesta correcta", Toast.LENGTH_SHORT).show();
            registration(word.getWor_id(),"11");
            Intent intent = new Intent(getContext(), Done.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getContext(), Wrong.class);
            startActivity(intent);
            Toast.makeText(getContext(), "respuesta incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

    private void registration(String wor_id, String use_id) {
        Registration registration = new Registration();
        registration.setUse_id(use_id);
        registration.setWor_id(wor_id);
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceResults serviceResults = retrofit.create(ServiceResults.class);
        Call<ResponseResponse> call = serviceResults.registration(registration);
        call.enqueue(new Callback<ResponseResponse>() {
            @Override
            public void onResponse(Call<ResponseResponse> call, Response<ResponseResponse> response) {
                alertView(""+response.body());
            }

            @Override
            public void onFailure(Call<ResponseResponse> call, Throwable t) {
                Log.i("response","asd");
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