package com.example.aula_json;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter {

    List<Filme> listaFilmes;
    AppCompatActivity activity;

    public FilmeAdapter(List<Filme> listaFilmes, AppCompatActivity activity) {
        this.listaFilmes = listaFilmes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        FilmeViewHolder viewHolder = new FilmeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FilmeViewHolder viewHolder = (FilmeViewHolder) holder;
        viewHolder.idTextView.setText(String.valueOf(listaFilmes.get(position).getId()));
        viewHolder.tituloTextView.setText(listaFilmes.get(position).getTitulo());
        viewHolder.generoTextView.setText(listaFilmes.get(position).getGenero());
        viewHolder.anoTextView.setText(String.valueOf(listaFilmes.get(position).getAno()));

        viewHolder.elenco1NomeTextView.setText(listaFilmes.get(position).getElenco().get(0).getNome());
        viewHolder.elenco2NomeTextView.setText(listaFilmes.get(position).getElenco().get(1).getNome());
        viewHolder.elenco3NomeTextView.setText(listaFilmes.get(position).getElenco().get(2).getNome());

        viewHolder.elenco1PersonagemTextView.setText(listaFilmes.get(position).getElenco().get(0).getPersonagem());
        viewHolder.elenco2PersonagemTextView.setText(listaFilmes.get(position).getElenco().get(1).getPersonagem());
        viewHolder.elenco3PersonagemTextView.setText(listaFilmes.get(position).getElenco().get(2).getPersonagem());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getBaseContext(), InserirFilmeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("filme",listaFilmes.get(holder.getAdapterPosition()));
                bundle.putInt("position",holder.getAdapterPosition());
                bundle.putInt("request_code",MainActivity.REQUEST_EDITAR_FILME);
                intent.putExtras(bundle);
                activity.startActivityForResult(intent, MainActivity.REQUEST_EDITAR_FILME);
            }
        });
    }

    @Override
    public int getItemCount() {
            return listaFilmes.size();
    }

    public void insertFilme(Filme filme) {
        listaFilmes.add(filme);
        notifyItemInserted(getItemCount());
    }

    public void update(Filme filme, int position){
        listaFilmes.get(position).setId(filme.getId());
        listaFilmes.get(position).setTitulo(filme.getTitulo());
        listaFilmes.get(position).setGenero(filme.getGenero());
        listaFilmes.get(position).setAno(filme.getAno());
        listaFilmes.get(position).setElenco(filme.getElenco());
        notifyItemChanged(position);
    }

    public void remover(int position){
        listaFilmes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,this.getItemCount());
    }

    public static class FilmeViewHolder extends RecyclerView.ViewHolder{

        TextView idTextView;
        TextView tituloTextView;
        TextView generoTextView;
        TextView anoTextView;
        TextView elenco1NomeTextView;
        TextView elenco2NomeTextView;
        TextView elenco3NomeTextView;
        TextView elenco1PersonagemTextView;
        TextView elenco2PersonagemTextView;
        TextView elenco3PersonagemTextView;


        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            idTextView = (TextView) itemView.findViewById(R.id.idTextView);
            tituloTextView = (TextView) itemView.findViewById(R.id.tituloTextView);
            generoTextView = (TextView) itemView.findViewById(R.id.generoTextView);
            anoTextView = (TextView) itemView.findViewById(R.id.anoTextView);
            elenco1NomeTextView = (TextView) itemView.findViewById(R.id.elenco1NomeTextView);
            elenco2NomeTextView = (TextView) itemView.findViewById(R.id.elenco2NomeTextView);
            elenco3NomeTextView = (TextView) itemView.findViewById(R.id.elenco3NomeTextView);
            elenco1PersonagemTextView = (TextView) itemView.findViewById(R.id.elenco1PersonagemTextView);
            elenco2PersonagemTextView = (TextView) itemView.findViewById(R.id.elenco2PersonagemTextView);
            elenco3PersonagemTextView = (TextView) itemView.findViewById(R.id.elenco3PersonagemTextView);
        }
    }

}
