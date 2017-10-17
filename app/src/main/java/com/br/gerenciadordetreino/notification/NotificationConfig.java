package com.br.gerenciadordetreino.notification;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.br.gerenciadordetreino.model.Equipamento;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by joaov on 29/09/2017.
 */

public class NotificationConfig {
    private Context context;
    private Equipamento equipamento;

    public NotificationConfig(Context context, Equipamento equipamento) {
        this.context = context;
        this.equipamento = equipamento;
    }

    public void scheduleNotificatin(NotificationModel notificationModel){
        Intent intent = new Intent(context, NotificationService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(NotificationService.EQUIPAMENTO, equipamento);
        bundle.putSerializable(NotificationService.NOTIFICATION, notificationModel);
        intent.putExtra("bundle",bundle);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,equipamento.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC,  getTimeEquipamento(), pendingIntent);
    }

    public void cancelNotification(){
        Intent intent = new Intent(context, NotificationService.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, equipamento.getId(), intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }


    public void cancelAndScheduleNotification(NotificationModel notificationModel){
        cancelNotification();
        scheduleNotificatin(notificationModel);
    }

    private long getTimeEquipamento() {
        Date  dataMalhada = equipamento.getUltimaDataMalhada();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataMalhada);
        calendar.add(Calendar.DATE, 10);
        return  calendar.getTime().getTime();
    }
}

