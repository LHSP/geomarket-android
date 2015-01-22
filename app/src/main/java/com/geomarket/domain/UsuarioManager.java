package com.geomarket.domain;

import android.content.Context;
import android.widget.Toast;

import com.geomarket.R;
import com.geomarket.infrastructure.Callback;
import com.geomarket.infrastructure.RespostaCallback;
import com.geomarket.model.Propaganda;
import com.geomarket.model.Usuario;
import com.geomarket.webRequests.WebRequests;
import com.geomarket.webRequests.json.GetPropagandasJsonResult;
import com.geomarket.webRequests.json.JsonResult;
import com.geomarket.webRequests.json.MessageJsonResult;

import java.util.ArrayList;

/**
 * Created by Luiz on 12/01/2015.
 */
public class UsuarioManager {
    private Context context;
    private Usuario usuario;
    private static UsuarioManager ourInstance = new UsuarioManager();

    public static UsuarioManager getInstance(Context context) {
        ourInstance.context = context;
        PreferenceManager.context = ourInstance.context;
        ourInstance.usuario = new Usuario();
        ourInstance.usuario.carregar();
        return ourInstance;
    }

    private UsuarioManager() {}

    public void buscaNovasPropagandas(final Callback<ArrayList<Propaganda>> callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RespostaCallback<JsonResult> respostaCallback = new RespostaCallback<JsonResult>() {
                    @Override
                    public void call() {
                        this.setRespostaSuccess(this.getRespostaObject().errors == null
                                || this.getRespostaObject().errors.size() > 0);
                        ArrayList<Propaganda> propagandas = new ArrayList<Propaganda>();
                        if(this.getSuccess()){
                            propagandas = ((GetPropagandasJsonResult)this.getRespostaObject()).propagandas;
                        }
                        callback.setResult(propagandas);
                        callback.call();
                    }
                };

                WebRequests.GetPropagandas(ourInstance.usuario.getToken(), respostaCallback);
            }
        }).start();
    }

    public void cadastraEstabelecimento(final Callback<String> callback, final String tokenEstabelecimento) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RespostaCallback<JsonResult> respostaCallback = new RespostaCallback<JsonResult>() {
                    @Override
                    public void call() {
                        this.setRespostaSuccess(this.getRespostaObject().errors == null
                                || this.getRespostaObject().errors.get(0).equals(""));
                        String resposta = "";
                        if(this.getSuccess()) {
                            resposta = ((MessageJsonResult) this.getRespostaObject()).message;
                        } else {
                            if(this.getRespostaObject().errors.size() >= 1)
                                resposta = this.getRespostaObject().errors.get(0);
                        }
                        callback.setResult(resposta);
                        callback.call();
                    }
                };

                WebRequests.assinaEstabelecimento(tokenEstabelecimento, ourInstance.usuario.getToken(), respostaCallback);
            }
        }).start();
    }
}
