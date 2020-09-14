package com.example.aula_json;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InserirFilmeActivity extends AppCompatActivity {

    TextView idTextView;
    EditText tituloEditText;
    EditText generoEditText;
    EditText anoEditText;
    EditText nome1EditText;
    EditText personagem1EditText;
    EditText nome2EditText;
    EditText personagem2EditText;
    EditText nome3EditText;
    EditText personagem3EditText;

    Button deletar;

    Filme filme;
    int position;
    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_filme);



        idTextView = (TextView) findViewById(R.id.idTextView);
        tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        generoEditText = (EditText) findViewById(R.id.generoEditText);
        anoEditText = (EditText) findViewById(R.id.anoEditText);
        nome1EditText = (EditText) findViewById(R.id.elenco1NomeEditText);
        nome2EditText = (EditText) findViewById(R.id.elenco2NomeEditText);
        nome3EditText = (EditText) findViewById(R.id.elenco3NomeEditText);
        personagem1EditText = (EditText) findViewById(R.id.elenco1PersonagemEditText);
        personagem2EditText = (EditText) findViewById(R.id.elenco2PersonagemEditText);
        personagem3EditText = (EditText) findViewById(R.id.elenco3PersonagemEditText);

        deletar = (Button) findViewById(R.id.buttonDelete);

        Bundle bundle = getIntent().getExtras();
        requestCode = bundle.getInt("request_code");

        if (requestCode==MainActivity.REQUEST_EDITAR_FILME){
            filme = (Filme) bundle.getSerializable("filme");
            position = bundle.getInt("position");

            idTextView.setText(String.valueOf(filme.getId()));
            tituloEditText.setText(filme.getTitulo());
            generoEditText.setText(filme.getGenero());
            anoEditText.setText(String.valueOf(filme.getAno()));
            nome1EditText.setText(filme.getElenco().get(0).getNome());
            nome2EditText.setText(filme.getElenco().get(1).getNome());
            nome3EditText.setText(filme.getElenco().get(2).getNome());
            personagem1EditText.setText(filme.getElenco().get(0).getPersonagem());
            personagem2EditText.setText(filme.getElenco().get(1).getPersonagem());
            personagem3EditText.setText(filme.getElenco().get(2).getPersonagem());

        }else{
            filme = new Filme();
            deletar.setVisibility(View.INVISIBLE);
            deletar.setClickable(false);
        }
    }

    public void concluir(View view){
        Bundle bundle = new Bundle();
        if (requestCode==MainActivity.REQUEST_EDITAR_FILME)
            bundle.putInt("position",position);
        filme.setTitulo(tituloEditText.getText().toString());
        filme.setGenero(generoEditText.getText().toString());
        filme.setAno(Integer.valueOf(anoEditText.getText().toString()));
        List<Cast> elenco = new ArrayList<Cast>();
        String nome = nome1EditText.getText().toString();
        String personagem = personagem1EditText.getText().toString();
        elenco.add(new Cast(nome,personagem));
        nome = nome2EditText.getText().toString();
        personagem = personagem2EditText.getText().toString();
        elenco.add(new Cast(nome,personagem));

        nome = nome3EditText.getText().toString();
        personagem = personagem3EditText.getText().toString();
        elenco.add(new Cast(nome,personagem));

        filme.setElenco(elenco);

        bundle.putSerializable("filme", filme);
        bundle.putString("operacao","inserir/editar");

        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void deletar(View view){
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("operacao","deletar");
        bundle.putSerializable("filme", filme);
        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}