package com.br.gerenciadordetreino.persistence.converters;

import android.arch.persistence.room.TypeConverter;

import com.br.gerenciadordetreino.model.Foto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}