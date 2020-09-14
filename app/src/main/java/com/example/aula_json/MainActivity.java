package com.example.aula_json;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_EDITAR_FILME = 2;
    private static final int REQUEST_INSERIR_FILME = 1;
    RecyclerView recyclerView;
    FilmeAdapter adapter;

    JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*String filmesJson = LoadData();
        Gson gson = new GsonBuilder().create();
        ListaFilmes listaFilmes = gson.fromJson(filmesJson, ListaFilmes.class);

        adapter = new FilmeAdapter(listaFilmes.filmes);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);*/

        adapter =  new FilmeAdapter(new ArrayList<Filme>(), this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        /*List elenco = new ArrayList<Cast>();
        elenco.add(new Cast("Daniel Craig","Benoit Blanc"));
        elenco.add(new Cast("Ana de Armas","Marta Cabrera"));
        elenco.add(new Cast("Chris Evans","Benoit Blanc"));
        Filme f = new Filme("Knives Out","Crime",2019, elenco);

        insertFilme(f);*/

        getFilmes();
    }

    public void getFilmes(){
        Call<List<Filme>> call = jsonPlaceHolderApi.getFilmes();

        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                }else{
                    List<Filme> filmes = response.body();
                    for(Filme filme : filmes)
                        adapter.insertFilme(filme);
                }
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void insertFilme(Filme filme){
        Call<Filme> call = jsonPlaceHolderApi.insertFilme(filme);
        call.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),response.code()+"",Toast.LENGTH_LONG).show();
                    Filme fResponse = response.body();
                    adapter.insertFilme(fResponse);
                }
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateFilme(Filme filme, int position){
        Call<Filme> call = jsonPlaceHolderApi.putFilme(filme.getId(), filme);
        call.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),response.code()+"",Toast.LENGTH_LONG).show();
                    Filme fResponse = response.body();
                    adapter.update(filme, position);
                }
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void removeFilme(Filme filme, int position){
        Call<Void> call = jsonPlaceHolderApi.removeFilme(filme.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),response.code()+"",Toast.LENGTH_LONG).show();
                    adapter.remover(position);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

/*    public String LoadData() {
        String tContents = "";

        try {
            InputStream stream = getAssets().open("Filmes.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }
        return tContents;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.inserirMenu){
            Bundle bundle = new Bundle();
            bundle.putInt("request_code",REQUEST_INSERIR_FILME);
            Intent intent = new Intent(this, InserirFilmeActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_INSERIR_FILME);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_INSERIR_FILME){
            if (resultCode == Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                Filme filme = (Filme) bundle.getSerializable("filme");
                insertFilme(filme);
            }
        }
        if (requestCode == REQUEST_EDITAR_FILME){
            if (resultCode == Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                int position = bundle.getInt("position");
                Filme filme = (Filme) bundle.getSerializable("filme");
                String op = bundle.getString("operacao");
                if(op.equals("deletar")){
                    removeFilme(filme, position);
                }else {
                    filme = (Filme) bundle.getSerializable("filme");
                    updateFilme(filme, position);
                }
            }
        }

    }
}