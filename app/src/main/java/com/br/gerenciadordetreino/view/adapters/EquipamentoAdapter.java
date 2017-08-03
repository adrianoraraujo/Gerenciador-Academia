package com.br.gerenciadordetreino.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Equipamento;
import com.br.gerenciadordetreino.view.CadastroEquipamentoActivity_;
import com.br.gerenciadordetreino.view.CadastroTreinoActivity_;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.meetic.marypopup.MaryPopup;

import java.util.List;

/**
 * Created by joaov on 15/07/2017.
 */

public class EquipamentoAdapter extends RecyclerView.Adapter<EquipamentoAdapter.EquipamentosViewHolder> {
    List<Equipamento> equipamentos;
    Context context;

    public EquipamentoAdapter(List<Equipamento> equipamentos, Context context) {
        this.equipamentos = equipamentos;
        this.context = context;
    }

    @Override
    public EquipamentosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipamento, parent, false);
        return new EquipamentosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EquipamentosViewHolder holder, int position) {
        Equipamento item = equipamentos.get(position);

        holder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CadastroEquipamentoActivity_.class));
                ((AppCompatActivity)context).overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);

            }
        });

        holder.circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.popup_imagem, null, false);
                MaryPopup marypopup = MaryPopup.with((Activity) context)
                        .cancellable(true)
                        .blackOverlayColor(Color.parseColor("#DD444444"))
                        .backgroundColor(Color.parseColor("#EFF4F5"))
                        .center(true)
                        .draggable(true)
                        .content(view)
                        .from(holder.circularImageView);
                marypopup.show();
            }
        });

        holder.cardBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CadastroTreinoActivity_.class));
                ((AppCompatActivity)context).overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);

            }
        });
    }

    @Override
    public int getItemCount() {
        return equipamentos.size();
    }

    public class EquipamentosViewHolder extends RecyclerView.ViewHolder {
        CircularImageView circularImageView;
        TextView tvNome;
        CardView cardBody;
        ImageView imgEditar;

        public EquipamentosViewHolder(View itemView) {
            super(itemView);

            cardBody = (CardView) itemView.findViewById(R.id.body_card);
            circularImageView = (CircularImageView) itemView.findViewById(R.id.circle_image);
            imgEditar = (ImageView) itemView.findViewById(R.id.img_editar);
            //tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
        }
    }
}
