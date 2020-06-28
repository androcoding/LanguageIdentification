package com.example.languageidentification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    EditText editText;
    Button buttonIdentity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult=findViewById(R.id.res);
        editText=findViewById(R.id.edit);
        buttonIdentity=findViewById(R.id.identity);


        buttonIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseLanguageIdentification languageIdentifier =
                        FirebaseNaturalLanguage.getInstance().getLanguageIdentification();
                languageIdentifier.identifyLanguage(editText.getText().toString())
                        .addOnSuccessListener(
                                new OnSuccessListener<String>() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void onSuccess(@Nullable String languageCode) {
                                        if (languageCode!= "und") {
                                            textViewResult.setText(languageCode);

                                        } else {
                                            Toast.makeText(MainActivity.this, "Some Error!", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        
                                    }
                                });
            }});
    }
}