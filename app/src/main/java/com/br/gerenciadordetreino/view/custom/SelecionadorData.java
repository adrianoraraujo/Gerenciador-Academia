package com.br.gerenciadordetreino.view.custom;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.br.gerenciadordetreino.utils.DateUtils;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by joaov on 29/08/2017.
 */

public class SelecionadorDate extends LinearLayout {
    public SelecionadorDate(Context context) {
        super(context);
    }

    public SelecionadorDate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelecionadorDate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SelecionadorDate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private  String convertDateToText(DateTime dateTime){
    }


    void goThisDate(){

    }

    void goNext(DateTime date){

    }
    void goBack(DateTime date){

    }


}
