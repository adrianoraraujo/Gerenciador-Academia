package com.br.gerenciadordetreino.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.br.gerenciadordetreino.persistence.EquipamentoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.utils.PhotoUtils;
import com.br.gerenciadordetreino.view.CadastroEquipamentoActivity;
import com.br.gerenciadordetreino.view.CadastroEquipamentoActivity_;
import com.br.gerenciadordetreino.view.CadastroTreinoActivity;
import com.br.gerenciadordetreino.view.CadastroTreinoActivity_;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.meetic.marypopup.MaryPopup;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

/**
 * Created by joaov on 15/07/2017.
 */

public class EquipamentoAdapter extends RecyclerView.Adapter<EquipamentoAdapter.EquipamentosViewHolder> {
    private static final int COLOR_OTIMO = 3;
    private static final int COLOR_BOM = 7;
    private static final int COLOR_MEDIO = 15;
    private static final int COLOR_RUIM = 30;


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

        setColorBackground(holder, item);
        setClicks(holder, item, position);
        setPhotoInView(item, holder.circularImageView);

        holder.tvTitle.setText(item.getNome());
        holder.tvRepeticao.setText(String.valueOf(item.getRepeticoesDefault()));
        holder.tvPeso.setText(String.valueOf(item.getPesoDefault()));
        holder.tvSerie.setText(String.valueOf(item.getSeriesDefault()));
    }

    private void setColorBackground(EquipamentosViewHolder holder, Equipamento item) {
        Date dataAtual = new Date();
        Date dataUlitmaMalhada = item.getUltimaDataMalhada();
        long diasPassados = DateUtils.diferenceDays(dataAtual, dataUlitmaMalhada);

        if(diasPassados <= COLOR_OTIMO) {
            holder.cardBody.setBackgroundColor(ContextCompat.getColor(context, R.color.otimo));
        }else if(diasPassados <= COLOR_BOM){
            holder.cardBody.setBackgroundColor(ContextCompat.getColor(context, R.color.bom));
        }else if(diasPassados <= COLOR_MEDIO){
            holder.cardBody.setBackgroundColor(ContextCompat.getColor(context, R.color.medio));
        }else if(diasPassados <= COLOR_RUIM){
            holder.cardBody.setBackgroundColor(ContextCompat.getColor(context, R.color.ruim));
        }else if(diasPassados > COLOR_RUIM)
            holder.cardBody.setBackgroundColor(ContextCompat.getColor(context, R.color.pessimo));

    }

    private void setClicks(final EquipamentosViewHolder holder, final Equipamento item, final int position) {
        holder.imgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CadastroEquipamentoActivity_.class);
                intent.putExtra(CadastroEquipamentoActivity.EQUIPAMENTO, item);
                intent.putExtra(CadastroTreinoActivity.CODE_ACAO, CadastroTreinoActivity.EDITAR_VALORES_PADROES);
                ((AppCompatActivity) context).startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
            }
        });

        holder.imgExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AppDialogStyle);
                alertDialog.setTitle("Alerta !");
                alertDialog.setMessage("Tem certeza que deseja excluir \n"+item.getNome()+" ?");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EquipamentoDAO.deleteEquipamento(context, item);
                        equipamentos.remove(position);
                        notifyDataSetChanged();
                    }
                });

                alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                alertDialog.show();
            }
        });

        holder.circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.popup_imagem, null, false);
                ImageView imgCentral = (ImageView) view.findViewById(R.id.image_view);
                BitmapDrawable drawable = (BitmapDrawable) holder.circularImageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap bitmapEscaled = Bitmap.createScaledBitmap(bitmap, 600, 600, true);
                imgCentral.setImageBitmap(bitmapEscaled);

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
                Intent intent = new Intent(context, CadastroTreinoActivity_.class);
                intent.putExtra(CadastroTreinoActivity.TITULO, "Malhar Agora !");
                intent.putExtra(CadastroTreinoActivity.EQUIPAMENTO_ITEM, item);
                intent.putExtra(CadastroTreinoActivity.CODE_ACAO, CadastroTreinoActivity.MALHAR);
                ((AppCompatActivity)context).startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);

            }
        });
    }

    @Override
    public int getItemCount() {
        return equipamentos.size();
    }

    public class EquipamentosViewHolder extends RecyclerView.ViewHolder {
        CircularImageView circularImageView;
        TextView tvTitle;
        TextView tvPeso;
        TextView tvRepeticao;
        TextView tvSerie;
        CardView cardBody;
        ImageView imgEditar;
        ImageView imgExcluir;

        public EquipamentosViewHolder(View itemView) {
            super(itemView);

            cardBody = (CardView) itemView.findViewById(R.id.body_card);
            circularImageView = (CircularImageView) itemView.findViewById(R.id.circle_image);
            imgEditar = (ImageView) itemView.findViewById(R.id.img_editar);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvPeso = (TextView) itemView.findViewById(R.id.tv_peso);
            tvRepeticao = (TextView) itemView.findViewById(R.id.tv_repeticao);
            tvSerie = (TextView) itemView.findViewById(R.id.tv_series);
            imgExcluir = (ImageView) itemView.findViewById(R.id.img_excluir);

        }
    }

    private void setPhotoInView(Equipamento equipamento, CircularImageView circularImageView) {
        Bitmap bitmap = null;
        if (equipamento.getFoto() != null) {
            try {
                bitmap = PhotoUtils.getImage(context, equipamento.getFoto());
                if (bitmap != null) {
                    circularImageView.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
