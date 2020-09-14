package com.example.aula_json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("mbegnini/jsonServer2020_/filmes")
    Call<List<Filme>> getFilmes();

    @POST("mbegnini/jsonServer2020_/filmes")
    Call<Filme> insertFilme(@Body Filme filme);

    @PUT("mbegnini/jsonServer2020_/filmes/{id}") //substitui
    Call<Filme> putFilme(@Path("id") int id, @Body Filme filme);

    @PUT("mbegnini/jsonServer2020_/filmes/{id}") //altera diferenças
    Call<Filme> patchFilme(@Path("id") int id, @Body Filme filme);

    @DELETE("mbegnini/jsonServer2020_/filmes/{id}")
    Call<Void> removeFilme(@Path("id") int id);

}
