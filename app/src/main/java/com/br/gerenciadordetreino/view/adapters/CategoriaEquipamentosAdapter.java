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

public class CategoriaEquipamentosAdapter extends  RecyclerView.Adapter<CategoriaEquipamentosAdapter.EquipamentosPadroesViewHolder>  {

    ArrayList<Equipamento> equipamentos;
    Context context;
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    EquipamentoAdapter categoriaEquipamentosAdapter;
    private String[] categorias;

    public CategoriaEquipamentosAdapter(ArrayList<Equipamento> equipamentos, Context context) {
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

        holder.tvNome.setText(categorias[position]);
        holder.expandableLayout.collapse();

        recyclerView = holder.recyclerViewEquipamentos;


        setListEquipamentos(recyclerView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expandableLayout.toggle(true);
            }
        });
    }

    private void setListEquipamentos(RecyclerView recyclerView) {
        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        categoriaEquipamentosAdapter = new EquipamentoAdapter(equipamentos, context );
        recyclerView.setAdapter(categoriaEquipamentosAdapter);
    }

    @Override
    public int getItemCount() {
       return categorias.length;
    }


    //HOLDER

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
