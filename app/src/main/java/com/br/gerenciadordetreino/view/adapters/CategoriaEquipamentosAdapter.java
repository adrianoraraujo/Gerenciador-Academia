package com.br.gerenciadordetreino.view.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.github.siyamed.shapeimageview.CircularImageView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaov on 15/07/2017.
 */

public class CategoriaEquipamentosAdapter extends RecyclerView.Adapter<CategoriaEquipamentosAdapter.EquipamentosPadroesViewHolder> {

    List<Equipamento> equipamentos;
    Context context;
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    EquipamentoAdapter categoriaEquipamentosAdapter;
    private String[] categorias;

    public CategoriaEquipamentosAdapter(List<Equipamento> equipamentos, Context context) {
        this.equipamentos = equipamentos;
        this.context = context;

        Resources res = context.getResources();
        categorias = res.getStringArray(R.array.categorias);
    }

    @Override
    public EquipamentosPadroesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria_equipamento, parent, false);
        return new EquipamentosPadroesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EquipamentosPadroesViewHolder holder, int position) {
        String categoriaAtual = categorias[position];

        holder.tvNome.setText(categoriaAtual);
        holder.expandableLayout.collapse();
        recyclerView = holder.recyclerViewEquipamentos;
        setListEquipamentos(recyclerView, categoriaAtual);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expandableLayout.toggle(true);
            }
        });
    }

    private void setListEquipamentos(RecyclerView recyclerView, String categoriaAtual) {
        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        List<Equipamento> listaEquipamentosCategoria = getEquipamentosForCategoria(categoriaAtual);
        categoriaEquipamentosAdapter = new EquipamentoAdapter(listaEquipamentosCategoria, context);
        recyclerView.setAdapter(categoriaEquipamentosAdapter);
    }

    private List<Equipamento> getEquipamentosForCategoria(String categoriaAtual) {
        List<Equipamento> listaCategoriaAtual = new ArrayList<>();
        for(Equipamento e: equipamentos){
            if(e.getCategoria().equals(categoriaAtual)){
                listaCategoriaAtual.add(e);
            }
        }
        return listaCategoriaAtual;
    }

    @Override
    public int getItemCount() {
        return categorias.length;
    }

    protected class EquipamentosPadroesViewHolder extends RecyclerView.ViewHolder {
        ExpandableLayout expandableLayout;
        CircularImageView circularImageView;
        TextView tvNome;
        CardView cardView;
        Button btnCadastrar;
        RecyclerView recyclerViewEquipamentos;

        public EquipamentosPadroesViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.body_card);
            expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandableLayout);
            circularImageView = (CircularImageView) itemView.findViewById(R.id.img_circular_equipamento);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
            btnCadastrar = (Button) itemView.findViewById(R.id.btn_cadastrar);
            recyclerViewEquipamentos = (RecyclerView) itemView.findViewById(R.id.recycler_equipamentos);

        }
    }
}
