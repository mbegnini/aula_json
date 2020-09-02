package com.example.aula_json;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter {

    List<Filme> listaFilmes;

    public FilmeAdapter(List<Filme> listaFilmes) {
        this.listaFilmes = listaFilmes;
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
        viewHolder.tituloTextView.setText(listaFilmes.get(position).getTitulo());
        viewHolder.generoTextView.setText(listaFilmes.get(position).getGenero());
        viewHolder.anoTextView.setText(String.valueOf(listaFilmes.get(position).getAno()));

        viewHolder.elenco1NomeTextView.setText(listaFilmes.get(position).getElenco().get(0).getNome());
        viewHolder.elenco2NomeTextView.setText(listaFilmes.get(position).getElenco().get(1).getNome());
        viewHolder.elenco3NomeTextView.setText(listaFilmes.get(position).getElenco().get(2).getNome());

        viewHolder.elenco1PersonagemTextView.setText(listaFilmes.get(position).getElenco().get(0).getPersonagem());
        viewHolder.elenco2PersonagemTextView.setText(listaFilmes.get(position).getElenco().get(1).getPersonagem());
        viewHolder.elenco3PersonagemTextView.setText(listaFilmes.get(position).getElenco().get(2).getPersonagem());
    }

    @Override
    public int getItemCount() {
            return listaFilmes.size();
    }

    public void insertFilme(Filme filme) {
        listaFilmes.add(filme);
        notifyItemInserted(getItemCount());
    }

    public static class FilmeViewHolder extends RecyclerView.ViewHolder{

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
