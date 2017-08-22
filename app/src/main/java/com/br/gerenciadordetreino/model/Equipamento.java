package com.br.gerenciadordetreino.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joaov on 15/07/2017.
 */

@Entity(tableName = "Equipamento")
public class Equipamento implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "foto")
    private String foto;
    @ColumnInfo(name = "pesoDefault")
    private int pesoDefault;
    @ColumnInfo(name = "repeticoesDefault")
    private int repeticoesDefault;
    @ColumnInfo(name = "seriesDefault")
    private int seriesDefault;
    @ColumnInfo(name = "observacoes")
    private String observacoes;
    @ColumnInfo(name = "categoria")
    private String categoria;

    public String getObservacoes() {
        return observacoes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getPesoDefault() {
        return pesoDefault;
    }

    public void setPesoDefault(int pesoDefault) {
        this.pesoDefault = pesoDefault;
    }

    public int getRepeticoesDefault() {
        return repeticoesDefault;
    }

    public void setRepeticoesDefault(int repeticoesDefault) {
        this.repeticoesDefault = repeticoesDefault;
    }

    public int getSeriesDefault() {
        return seriesDefault;
    }

    public void setSeriesDefault(int seriesDefault) {
        this.seriesDefault = seriesDefault;
    }
}
