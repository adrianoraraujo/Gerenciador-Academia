package com.br.gerenciadordetreino.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.view.CadastroTreinoActivity;
import com.br.gerenciadordetreino.view.CadastroTreinoActivity_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaov on 23/07/2017.
 */

public class TreinoCategoriaAdapter extends RecyclerView.Adapter<TreinoCategoriaAdapter.TreinoHolder> {
    List<Treino> treinos = new ArrayList<>();
    private Context context;
     private String categoria;

    public TreinoCategoriaAdapter(List<Treino> treinos, Context context, String categoria) {
        this.treinos = treinos;
        this.context = context;
        this.categoria = categoria;
    }

    @Override
    public TreinoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_treino_categoria, parent, false);
        return new TreinoCategoriaAdapter.TreinoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TreinoHolder holder, int position) {
        final Treino item = treinos.get(position);
        if(item.getCategoria().equals(categoria)) {
            holder.cvBody.setVisibility(View.VISIBLE);
            holder.tvNome.setText(DateUtils.toString("dd/MM/yyyy",item.getData())+" - "+ item.getNome());
        }else{
            holder.cvBody.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return treinos.size();
    }

    public class TreinoHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        CardView cvBody;
        public TreinoHolder(View itemView) {
            super(itemView);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
            cvBody = (CardView) itemView.findViewById(R.id.cv_body);
        }
    }
}
