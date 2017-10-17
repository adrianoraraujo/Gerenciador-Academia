package com.br.gerenciadordetreino.view.adapters;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Foto;
import com.br.gerenciadordetreino.persistence.FotoDAO;
import com.br.gerenciadordetreino.utils.DateUtils;
import com.br.gerenciadordetreino.utils.PhotoUtils;
import com.br.gerenciadordetreino.view.fragment.EvolucaoFragment;
import com.br.gerenciadordetreino.view.fragment.EvolucaoFragment_;
import com.br.gerenciadordetreino.view.listener.OnCameraEvolucaoCamera;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by joaov on 08/10/2017.
 */

public class EvolucaoAdapter extends RecyclerView.Adapter<EvolucaoAdapter.EvolucaoHolder> {
    public static final int CODE_CAMERA_EVOLUCAO = 30;
    private List<Foto> fotos;
    private AppCompatActivity context;
    public static OnCameraEvolucaoCamera onCameraEvolucaoCamera;

    public EvolucaoAdapter(List<Foto> fotos, AppCompatActivity context) {
        this.fotos = fotos;
        this.context = context;
    }

    @Override
    public EvolucaoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evolucao_foto, parent, false);
        return new EvolucaoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EvolucaoHolder holder, final int position) {
        final Foto foto = fotos.get(position);

        setEventCamera();

        holder.imgExcluir.setVisibility(View.INVISIBLE);
        holder.imgEditar.setVisibility(View.INVISIBLE);

        //ULTIMO ITEM
        if(foto.getPath().isEmpty()){
            holder.imgFoto.setVisibility(View.GONE);
            holder.tvEmpty.setVisibility(View.VISIBLE);
            holder.tvDataAtual.setVisibility(View.INVISIBLE);


            holder.tvEmpty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goCamera(holder);

                }
            });
        }else{
            //OUTROS ITENS
            holder.imgFoto.setVisibility(View.VISIBLE);
            holder.tvEmpty.setVisibility(View.GONE);
            holder.tvDataAtual.setVisibility(View.VISIBLE);
            holder.tvDataAtual.setText(DateUtils.toString("MM/yyyy", foto.getData()));
            holder.llBackgroundFoto.setBackgroundResource(0);
            try {
                holder.imgFoto.setImageDrawable(new BitmapDrawable(context.getResources(), PhotoUtils.getImage(context, foto.getPath())));
                RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(context.getResources(), PhotoUtils.getImage(context, foto.getPath()));
                dr.setCornerRadius(21f);
                holder.imgFoto.setImageDrawable(dr);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            holder.imgFoto.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    holder.imgEditar.setVisibility(View.GONE);
                    if(holder.imgExcluir.getVisibility() == View.INVISIBLE){
                        holder.imgExcluir.setVisibility(View.VISIBLE);
                    }else{
                        holder.imgExcluir.setVisibility(View.INVISIBLE);
                    }

                    return false;
                }
            });

            holder.imgExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FotoDAO.deleteFoto(context, foto);
                    refreshActivity();
                }
            });

        }
    }

    private void setEventCamera() {
        onCameraEvolucaoCamera = new OnCameraEvolucaoCamera() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                String path = getNomePath();
                try {
                    PhotoUtils.savePhotoInFile(context, bitmap, path);
                    FotoDAO.add(context, new Foto(path, new Date()));
                    refreshActivity();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError() {

            }

            @Override
            public void onCancel() {

            }
        };
    }

    private void goCamera(final EvolucaoHolder evolucaoHolder) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivityForResult(i, CODE_CAMERA_EVOLUCAO);
    }


    @Override
    public int getItemCount() {
        return fotos.size();
    }

    class EvolucaoHolder extends RecyclerView.ViewHolder implements Serializable{
        TextView tvDataAtual;
        ImageView imgFoto;
        ImageView imgEditar;
        ImageView imgExcluir;
        LinearLayout llBodyEvolucao;
        LinearLayout llBackgroundFoto;
        TextView tvEmpty;


        public EvolucaoHolder(View itemView) {
            super(itemView);
            tvDataAtual = (TextView) itemView.findViewById(R.id.tv_data_foto);
            imgEditar = (ImageView) itemView.findViewById(R.id.img_editar);
            imgExcluir = (ImageView) itemView.findViewById(R.id.img_excluir);
            imgFoto = (ImageView) itemView.findViewById(R.id.img_foto_atual);
            tvEmpty = (TextView) itemView.findViewById(R.id.tv_empty);
            llBodyEvolucao = (LinearLayout) itemView.findViewById(R.id.ll_body_evolucao);
            llBackgroundFoto = (LinearLayout) itemView.findViewById(R.id.ll_background_foto);

        }
    }

    private String getNomePath() {
        Date date = DateUtils.today();
        String dataFormatada = DateUtils.toString("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", date);
        return  dataFormatada;
    }

    private void refreshActivity(){
        EvolucaoFragment evolucaoFragment = EvolucaoFragment_.newInstance();
        startFragment(evolucaoFragment);
    }

    private void startFragment(Fragment fragment) {
        FragmentManager fm = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.body_home_empty, fragment);
        fragmentTransaction.commit();
    }
}
