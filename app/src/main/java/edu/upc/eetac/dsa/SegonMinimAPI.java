package edu.upc.eetac.dsa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.Call;

import retrofit2.http.Path;

public interface SegonMinimAPI {

    String BASE_URL = "https://do.diba.cat/api/dataset/municipis/format/json/pag-ini/1/pag-fi/11";
    List<Cities> Cities = new ArrayList<>();


    @GET("municipi_nom")
    Call<List<Cities>> cities();





}
