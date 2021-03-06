package com.br.gerenciadordetreino.view.custom;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.utils.DateUtils;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by joaov on 29/08/2017.
 */

public class SelecionadorData extends LinearLayout {
    ImageView tvSetaEsquerda;
    ImageView tvSetaDireita;
    TextView tvTextData;
    private Date data;
    private Context context;
    private int mes;
    private int ano;
    private Date dateSelected;

    public SelecionadorData(Context context) {
        super(context);
        init(context);
    }

    public SelecionadorData(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelecionadorData(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SelecionadorData(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        View view = inflate(context, R.layout.selecionador_data, this);
        tvSetaDireita = (ImageView) view.findViewById(R.id.ic_direita);
        tvSetaEsquerda = (ImageView) view.findViewById(R.id.ic_esquerda);
        tvTextData = (TextView) view.findViewById(R.id.tv_mes_ano);

    }

    private  String getDataFormatada(){
        Resources res = context.getResources();
        String meses[] = res.getStringArray(R.array.meses);
        String mes = meses[this.mes-1];
        return mes+"/"+ano;
    }

    public void goToday(){
        dateSelected = DateUtils.today();
        mes = DateUtils.getMonth(dateSelected);
        ano = DateUtils.getYear(dateSelected);
        String mesAno = getDataFormatada();

        tvTextData.setText(mesAno);
    }

    public void setDate(Date date){
        dateSelected = date;
        mes = DateUtils.getMonth(dateSelected);
        ano = DateUtils.getYear(dateSelected);
        String mesAno = getDataFormatada();

        tvTextData.setText(mesAno);
    }

   public  void goNext(){
       if(mes == 12){
           ano++;
           mes = 1;
       }else{
           mes++;
       }
       String mesAno = getDataFormatada();
       tvTextData.setText(mesAno);
       refreshDate();

   }

    public void goBack(){
        if(mes == 1){
            mes = 12;
            ano--;
        }else{
            mes--;
        }
        String mesAno = getDataFormatada();
        tvTextData.setText(mesAno);
        refreshDate();
    }

    private void refreshDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateSelected);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);

    }

    public DateTime getDateSelected() {
        DateTime dateTime = new DateTime(dateSelected);
        return dateTime;
    }
}
