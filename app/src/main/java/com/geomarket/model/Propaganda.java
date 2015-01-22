package com.geomarket.model;

import java.util.Date;

/**
 * Created by Luiz on 11/11/2014.
 */
public class Propaganda {
    private int id;
    private String titulo;
    private String corpo;
    private String link;
    private int tipoId;
    private int estabId;
    private Date dataInicio;
    private Date dataFim;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public int getEstabId() {
        return estabId;
    }

    public void setEstabId(int estabId) {
        this.estabId = estabId;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
