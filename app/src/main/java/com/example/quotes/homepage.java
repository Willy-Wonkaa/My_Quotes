package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homepage extends AppCompatActivity {
    Button button;
    TextView rand1,rand2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        button = findViewById(R.id.button);
        rand1=findViewById(R.id.rand1);
        rand2=findViewById(R.id.rand2);
        //Retrofit builder
        Retrofit retrofitrand=new Retrofit.Builder()
                .baseUrl("https://api.quotable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RandomAPI RandomAPI=retrofitrand.create(RandomAPI.class);
        Call<DataModel> call1=RandomAPI.getRandom();
        call1.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if(response.code()!=200)
                {
                    Toast.makeText(getApplicationContext(), "Check the connection!",Toast.LENGTH_LONG).show();
                    return;
                }
                String quote=""+response.body().getContent();
                String author="- "+response.body().getAuthor();
                rand1.setText(quote);
                rand2.setText(author);
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homepage.this, MainActivity.class);
                startActivity(i);
            }
        });
        




    }
}