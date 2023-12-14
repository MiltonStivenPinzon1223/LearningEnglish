package com.edu.ue.myproject;

import static android.app.Activity.RESULT_OK;
import static android.speech.RecognizerIntent.EXTRA_PROMPT;

import static com.edu.ue.myproject.api.ValuesApi.BASE_URL;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.ue.myproject.api.ServiceResults;
import com.edu.ue.myproject.api.ServiceWords;
import com.edu.ue.myproject.model.Registration;
import com.edu.ue.myproject.model.ResponseResponse;
import com.edu.ue.myproject.model.ResponseWords;
import com.edu.ue.myproject.model.Words;
import com.edu.ue.myproject.remote.ClienteRetrofit;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MicroFragment extends Fragment {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView grabar;
    private TextView tvword;
    private ImageButton btnV;
    public Retrofit retrofit;
    public ResponseWords responseWords;
    public ArrayList<Words> WordsList;
    ArrayList<String> result;
    public Words word;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento aquí
        View view = inflater.inflate(R.layout.fragment_micro, container, false);
        grabar = view.findViewById(R.id.tvResult);
        tvword = view.findViewById(R.id.tvWordMicro);
        btnV = view.findViewById(R.id.btnV);
        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ieVOZ();
            }
        });
        getWord();
        return view;
    }

    private void ieVOZ() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(EXTRA_PROMPT, "Responde");
        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode == RESULT_OK && null != data){
                    result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String prueba = result.get(0).toString();
                    grabar.setText(prueba);
                    validate(prueba);
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }



    //REGISTRO DE RESULTADO

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
                    tvword.setText(WordsList.get(0).getWor_english());
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
    private void validate(String opc) {
        if (word.getWor_english().toLowerCase() == result.get(0).toString().toLowerCase()){
            Toast.makeText(getContext(), "respuesta correcta", Toast.LENGTH_SHORT).show();
            registration(word.getWor_id(),"11");
            Intent intent = new Intent(getContext(), Done.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getContext(), Wrong.class);
            startActivity(intent);
            Toast.makeText(getContext(), ""+word.getWor_english().toLowerCase(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), ""+opc.toLowerCase(), Toast.LENGTH_SHORT).show();
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