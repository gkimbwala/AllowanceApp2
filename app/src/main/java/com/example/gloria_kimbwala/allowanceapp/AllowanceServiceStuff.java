package com.example.gloria_kimbwala.allowanceapp;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * This test calls the real server! Maybe with Retrofit.
 */
public class AllowanceServiceStuff {
    public AllowanceRate getAllowanceRate(String name) throws Exception {

        // TODO(gkimbwala): we're creating a new REtrofit every time. Instead we should create one
        // once and reuse it for all network calls
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://localhost:8080/")
//                .baseUrl("http://10.0.2.2:8080/")
                .baseUrl("https://allowance-182916.appspot.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        AllowanceService allowanceService = retrofit.create(AllowanceService.class);

        Call<AllowanceRate> call = allowanceService.getAllowanceRate(name);

        Response<AllowanceRate> response = call.execute();

        return response.body();
    }

    interface AllowanceService {
        @GET("allowance/{name}")
        Call<AllowanceRate> getAllowanceRate(@Path("name") String name);
    }

}