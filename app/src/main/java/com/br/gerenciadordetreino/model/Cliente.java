package com.br.gerenciadordetreino.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by joaov on 22/10/2017.
 */

public class Cliente implements Serializable {
    long id;
    String nome;
    String numero;
    Date dataCadastro;
    Float latitude;
    Float longitude;
    String fotoPath;

    public Cliente() {
    }

    public Cliente(long id, String nome, String numero, Date dataCadastro, Float latitude, Float longitude, String fotoPath) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.dataCadastro = dataCadastro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fotoPath = fotoPath;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
