package com.example.quotes;

import androidx.transition.Slide;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomAPI {
    @GET("random")
    Call<DataModel> getRandom();
}
