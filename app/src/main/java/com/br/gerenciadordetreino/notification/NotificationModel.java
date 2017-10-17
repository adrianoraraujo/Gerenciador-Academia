package com.br.gerenciadordetreino.notification;

import java.io.Serializable;

/**
 * Created by joaov on 29/09/2017.
 */

public class NotificationModel implements Serializable {
    String title;
    String text;
    String contentInfo;

    public NotificationModel() {
    }

    public NotificationModel(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public NotificationModel(String title, String text, String contentInfo) {
        this.title = title;
        this.text = text;
        this.contentInfo = contentInfo;
    }

    public String getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
