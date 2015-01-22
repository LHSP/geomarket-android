package com.geomarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.geomarket.domain.PreferenceManager;
import com.geomarket.infrastructure.RespostaCallback;
import com.geomarket.model.Usuario;
import com.geomarket.webRequests.WebRequests;
import com.geomarket.webRequests.json.JsonResult;
import com.geomarket.webRequests.json.LoginJsonResult;
import com.geomarket.webRequests.json.RegisterJsonResult;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        PreferenceManager.context = getApplicationContext();

        Usuario usuario = new Usuario();
        usuario.carregar();
        if(usuario.getUid() == null || usuario.getUid() == ""){
            usuario.gerarNovaSenha();
            WebRequests.Register(usuario.getSenha(), CallbackRegistro(usuario));
        }else if(usuario.getToken() == null || usuario.getToken() == ""){
            WebRequests.Login(usuario.getUid(), usuario.getSenha(), CallbackLogin(usuario));
        } else {
            GoToMain();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private RespostaCallback CallbackLogin(final Usuario usuario){
        return new RespostaCallback<JsonResult>() {
            @Override
            public void call() {
                this.setRespostaSuccess(this.getRespostaObject().errors == null
                        || this.getRespostaObject().errors.get(0).equals(""));
                if(this.getSuccess()){
                    String token = ((LoginJsonResult)this.getRespostaObject()).user_token;
                    usuario.setToken(token);
                    usuario.salvar();
                    GoToMain();
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.FalhaAoLogar), Toast.LENGTH_LONG).show();
                }
            }
        };

    }

    private RespostaCallback CallbackRegistro(final Usuario usuario){
        return new RespostaCallback<JsonResult>() {
            @Override
            public void call() {
                this.setRespostaSuccess(this.getRespostaObject().errors == null
                        || this.getRespostaObject().errors.get(0).equals(""));
                Toast.makeText(getApplicationContext(), this.getSuccess() ? "Registrado!" : "Não registrou", Toast.LENGTH_LONG).show();
                if(this.getSuccess()){
                    String uid = ((RegisterJsonResult)this.getRespostaObject()).user_uid;
                    usuario.setUid(uid);
                    usuario.salvar();

                    RespostaCallback<JsonResult> respostaLoginCallback = CallbackLogin(usuario);

                    WebRequests.Login(usuario.getUid(), usuario.getSenha(), respostaLoginCallback);
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.FalhaAoCadastrar), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public void GoToMain(){
        final Intent goToMain = new Intent(this, Main.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(goToMain);
            }
        }).start();
    }
}
