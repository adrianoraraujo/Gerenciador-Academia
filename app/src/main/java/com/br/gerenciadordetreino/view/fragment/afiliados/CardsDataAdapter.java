package com.br.gerenciadordetreino.view.fragment.afiliados;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Cliente;
import com.br.gerenciadordetreino.utils.DateUtils;

public class CardsDataAdapter extends ArrayAdapter<Cliente> {

    public CardsDataAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        TextView tvNome = (TextView)(contentView.findViewById(R.id.tv_nome));
        TextView tvNum = (TextView)(contentView.findViewById(R.id.tv_num));
        TextView tvData = (TextView)(contentView.findViewById(R.id.tv_data));
        ImageView imgAcademia = (ImageView)(contentView.findViewById(R.id.img_academia));
        Cliente cliente = getItem(position);
        tvData.setText(DateUtils.toString("dd/MM/yyyy", cliente.getDataCadastro()));
        tvNome.setText(cliente.getNome());
        tvNum.setText(cliente.getNumero());
        setImageInField(imgAcademia);
        return contentView;
    }

    private void setImageInField(ImageView imgAcademia) {

    }

}