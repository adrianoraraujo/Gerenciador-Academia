package com.br.gerenciadordetreino.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.popup_fragment_treino)
public class CadastroTreinoActivity extends SuperActivity {

    @AfterViews
    void init() {

    }

    @Click(R.id.tv_fechar)
    void fechar() {
        closePopup();
    }


    @Override
    public void onBackPressed() {
        closePopup();
    }
}
