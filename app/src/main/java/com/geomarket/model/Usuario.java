package com.geomarket.model;

import com.geomarket.domain.PreferenceManager;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Luiz on 08/01/2015.
 */
public class Usuario {

    private static String USUARIO_PREF_UID = "USUARIO_uid";
    private static String USUARIO_PREF_SENHA = "USAURIO_SENHA";
    private static String USUARIO_PREF_TOKEN = "USUARIO_TOKEN";

    public Usuario(){
    }

    public Usuario(String uid, String senha, String token) {
        this.uid = uid;
        this.senha = senha;
        this.token = token;
    }

    private String uid;
    private String senha;
    private String token;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean salvar(){
        return
                PreferenceManager.SaveString(USUARIO_PREF_UID, uid) &&
                PreferenceManager.SaveString(USUARIO_PREF_SENHA, senha) &&
                PreferenceManager.SaveString(USUARIO_PREF_TOKEN, token);
    }

    public void carregar(){
        this.setUid(PreferenceManager.LoadString(USUARIO_PREF_UID));
        this.setSenha(PreferenceManager.LoadString(USUARIO_PREF_SENHA));
        this.setToken(PreferenceManager.LoadString(USUARIO_PREF_TOKEN));
    }

    public String gerarNovaSenha(){
        SecureRandom random = new SecureRandom();

        this.senha = Long.toString(random.nextLong());
        return this.senha;
    }
}
