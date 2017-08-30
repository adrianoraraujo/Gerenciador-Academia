package com.br.gerenciadordetreino.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Treino;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.view.CadastroEquipamentoActivity_;
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
    private String title;

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
        setTitulo(holder, item);
        setValuesInViews(holder, item);


        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CadastroTreinoActivity_.class));
                Intent intent = new Intent(context, CadastroEquipamentoActivity_.class);
                intent.putExtra(CadastroTreinoActivity.CODE_ACAO, CadastroTreinoActivity.EDITAR_VALORES_PADROES);
                context.startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);

            }
        });

    }

    private void setValuesInViews(TreinoHolder holder, Treino item) {
        Resources res = context.getResources();
        String semanas[] = res.getStringArray(R.array.diasSemana);

        int diaMes = DateUtils.getMonth(item.getData());

        holder.tvNome.setText(item.getNome());
        holder.tvDiaSemana.setText(semanas[diaMes-1]);
        holder.tvDiaMes.setText(String.valueOf(DateUtils.getDayOfMonth(item.getData())));
        holder.tvCategoria.setText(item.getCategoria());
        holder.tvKilos.setText(String.valueOf(item.getPeso()));
        holder.tvRepeticoes.setText(String.valueOf(item.getRepeticoes()));
        holder.tvSeries.setText(String.valueOf(item.getSerie()));
    }

    private void setTitulo(TreinoHolder holder, Treino item) {
        if(item.getCategoria().equalsIgnoreCase(title)){
            holder.tvCategoria.setVisibility(View.GONE);
        }else{
            holder.tvCategoria.setVisibility(View.VISIBLE);
        }
        title = item.getCategoria();
    }

    @Override
    public int getItemCount() {
        return treinos.size();
    }

    public class TreinoHolder extends RecyclerView.ViewHolder {
        TextView tvDiaMes;
        TextView tvDiaSemana;
        TextView tvNome;
        TextView tvKilos;
        TextView tvRepeticoes;
        TextView tvSeries;
        TextView tvCategoria;
        LinearLayout body;

        public TreinoHolder(View itemView) {
            super(itemView);
            tvDiaMes = (TextView) itemView.findViewById(R.id.tv_dia_mes);
            tvDiaSemana = (TextView) itemView.findViewById(R.id.tv_dia_semana);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
            tvKilos = (TextView) itemView.findViewById(R.id.tv_kilos);
            tvRepeticoes = (TextView) itemView.findViewById(R.id.tv_repeticoes);
            tvSeries = (TextView) itemView.findViewById(R.id.tv_series);
            body = (LinearLayout) itemView.findViewById(R.id.ll_body_treino);
            ;
            tvCategoria = (TextView) itemView.findViewById(R.id.tv_categoria);
        }
    }
}
