package com.br.gerenciadordetreino.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.br.gerenciadordetreino.R;

/**
 * Created by joaov on 13/07/2017.
 */

class SuperActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.i("CICLO_DE_VIDA","onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CICLO_DE_VIDA","onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLO_DE_VIDA","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CICLO_DE_VIDA","onPause");
    }


    protected  void goPopup(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }

    protected void closePopup(){
        finish();
        overridePendingTransition(R.anim.popup_fragment_enter_anim, R.anim.popup_fragment_exit_anim);
    }

}
