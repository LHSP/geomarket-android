package com.geomarket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.geomarket.domain.UsuarioManager;
import com.geomarket.infrastructure.Callback;
import com.geomarket.infrastructure.OnSwipeTouchListener;
import com.geomarket.infrastructure.PropagandaListAdapter;
import com.geomarket.infrastructure.SwipeDetector;
import com.geomarket.model.Propaganda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main extends Activity {

    private static UsuarioManager usuarioManager;

    private static ImageButton ivSync;
    private static ListView lvOportunidades;
    private static Button btnMaisLoja;
    private static RelativeLayout rlDimScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioManager = UsuarioManager.getInstance(getApplicationContext());
        ivSync = (ImageButton)findViewById(R.id.ivSync);
        lvOportunidades = (ListView)findViewById(R.id.lvOportunidades);
        btnMaisLoja = (Button)findViewById(R.id.btnMaisLoja);
        rlDimScreen = (RelativeLayout)findViewById(R.id.rlDimScreen);


        ivSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivSync.setImageResource(android.R.drawable.ic_popup_sync);

                final Callback buscarPropagandasCallback = new Callback() {
                    @Override
                    public void call() {
                        lvOportunidades.setAdapter(new PropagandaListAdapter(getApplicationContext(), R.layout.propaganda_list_item, (ArrayList<Propaganda>) this.getResult()));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ivSync.setImageResource(android.R.drawable.stat_notify_sync_noanim);
                            }
                        });
                    }
                };

                usuarioManager.buscaNovasPropagandas(buscarPropagandasCallback);
            }
        });

        List<Propaganda> propagandas = new ArrayList<Propaganda>();
        for (int i = 0; i < 10; i++){
            Propaganda propaganda = new Propaganda();
            propaganda.setTitulo("Teste " + i);
            propaganda.setCorpo("Propaganda de teste para ver como fica a lista de propagandas que estou terminando de fazer!");
            propaganda.setDataFim(new Date());
            propaganda.setEstabId(i * 52 + 4);
            propagandas.add(propaganda);
        }
        lvOportunidades.setAdapter(new PropagandaListAdapter(getApplicationContext(), R.layout.propaganda_list_item, propagandas));


        final SwipeDetector swipeDetector = new SwipeDetector();
        final View[] listViewItem = {null};
        lvOportunidades.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
/*
        lvOportunidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listViewItem[0] = adapterView.getSelectedView().findViewById(R.id.rlPropaganda);
                view.setTranslationX(swipeDetector.deltaX);

                if (swipeDetector.swipeDetected()) {
                    if (swipeDetector.getAction() == SwipeDetector.Action.RL) {
                        view.setTranslationX(swipeDetector.deltaX);
                    } else {
                        view.setTranslationX(swipeDetector.deltaX);
                    }
                }
            }
        });
        */

        btnMaisLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPopupLogic();
            }
        });
    }

    private void doPopupLogic() {

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        rlDimScreen.setVisibility(View.VISIBLE);

        View popupView = inflater.inflate(R.layout.cadastrar_estabelecimento_popup, null, false);
        PopupWindow popupCadastrarEstabelecimento = new PopupWindow(popupView,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);

        /* Set popup background */
        popupCadastrarEstabelecimento.setOutsideTouchable(true);
        popupCadastrarEstabelecimento.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.editbox_background));
        popupCadastrarEstabelecimento.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                rlDimScreen.setVisibility(View.GONE);
            }
        });

        Button btnCadastrarEstabelecimento = (Button)popupView.findViewById(R.id.btnCadastrarEstabelecimento);
        final EditText etCadastrarEstabelecimento = (EditText)popupView.findViewById(R.id.etCadastrarEstabelecimento);

        btnCadastrarEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Callback<String> cadastraEstabelecimentoCallback = new Callback<String>() {
                    @Override
                    public void call() {
                        String resposta;
                        if(this.getResult().equals("")){
                            resposta = getString(R.string.erro_web_request);
                        } else {
                            resposta = this.getResult();
                        }
                        final String respostaFinal = resposta;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), respostaFinal, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                };

                String tokenEstabelecimento = etCadastrarEstabelecimento.getText().toString();
                if(tokenEstabelecimento != null && !tokenEstabelecimento.equals("")) {
                    usuarioManager.cadastraEstabelecimento(cadastraEstabelecimentoCallback, tokenEstabelecimento);
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.digite_codigo_estabelecimento), Toast.LENGTH_LONG).show();
                }
            }
        });

        /* Set popup location */
        popupCadastrarEstabelecimento.showAtLocation(findViewById(R.id.rlMain), Gravity.CENTER, 0, 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
