package com.br.gerenciadordetreino.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.popup_fragment_treino)
public class CadastroTreinoActivity extends AppCompatActivity {

    @AfterViews
    void init() {

    }

    @Click(R.id.tv_fechar)
    void fechar() {
        finish();
        overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }
}
