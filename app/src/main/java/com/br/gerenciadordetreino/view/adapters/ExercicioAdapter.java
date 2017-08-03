package com.br.gerenciadordetreino.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.view.CadastroTreinoActivity;
import com.br.gerenciadordetreino.view.CadastroTreinoActivity_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaov on 23/07/2017.
 */

public class ExercicioAdapter extends RecyclerView.Adapter<ExercicioAdapter.TreinoHolder> {
    List<Treino> treinos = new ArrayList<>();
    private Context context;

    public ExercicioAdapter(List<Treino> treinos, Context context) {
        this.treinos = treinos;
        this.context = context;
    }

    @Override
    public TreinoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercicio, parent, false);
        return new ExercicioAdapter.TreinoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TreinoHolder holder, int position) {
        Treino item = treinos.get(position);

        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CadastroTreinoActivity_.class));
                ((AppCompatActivity)context).overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);

            }
        });

    }

    @Override
    public int getItemCount() {
        return treinos.size();
    }

    public class TreinoHolder extends RecyclerView.ViewHolder{
        TextView tvDiaMes;
        TextView tvDiaSemana;
        TextView tvNome;
        TextView tvKilos;
        TextView tvRepeticoes;
        TextView tvSeries;
        TextView tvTitle;
        LinearLayout body;

        public TreinoHolder(View itemView) {
            super(itemView);
            tvDiaMes = (TextView) itemView.findViewById(R.id.tv_dia_mes);
            tvDiaSemana = (TextView) itemView.findViewById(R.id.tv_dia_semana);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
            tvKilos = (TextView) itemView.findViewById(R.id.tv_kilos);
            tvRepeticoes = (TextView) itemView.findViewById(R.id.tv_repeticoes);
            tvSeries = (TextView) itemView.findViewById(R.id.tv_series);
            body = (LinearLayout) itemView.findViewById(R.id.ll_body_treino);;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_titulo);
        }
    }
}
