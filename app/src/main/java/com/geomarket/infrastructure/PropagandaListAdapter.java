package com.geomarket.infrastructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geomarket.R;
import com.geomarket.model.Propaganda;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Luiz on 13/01/2015.
 */
public class PropagandaListAdapter extends ArrayAdapter<Propaganda> {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);

    public PropagandaListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PropagandaListAdapter(Context context, int resource, List<Propaganda> items) {
        super(context, resource, items);
    }

    @Override
    public LinearLayout getView(int position, View convertView, ViewGroup group) {

        LinearLayout v = (LinearLayout)convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());

            LinearLayout root = new LinearLayout(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            root.setLayoutParams(params);
            root.setOrientation(LinearLayout.VERTICAL);
            v = (LinearLayout)vi.inflate(R.layout.propaganda_list_item, root, true);

        }

        Propaganda p = getItem(position);

        if (p != null) {

            TextView tvTituloPropagandaListItem = (TextView) v.findViewById(R.id.tvTituloPropagandaListItem);
            TextView tvDataTituloPropagandaListItem = (TextView) v.findViewById(R.id.tvDataTituloPropagandaListItem);
            TextView tvCorpoPropagandaListItem = (TextView) v.findViewById(R.id.tvCorpoPropagandaListItem);
            TextView tvEstabelecimentoPropagandaListItem = (TextView) v.findViewById(R.id.tvEstabelecimentoPropagandaListItem);

            if(tvTituloPropagandaListItem != null){
                tvTituloPropagandaListItem.setText(p.getTitulo());
            }

            if(tvDataTituloPropagandaListItem != null) {
                tvDataTituloPropagandaListItem.setText(dateFormatter.format(p.getDataFim()));
            }

            if(tvCorpoPropagandaListItem != null) {
                tvCorpoPropagandaListItem.setText(p.getCorpo());
            }

            if(tvEstabelecimentoPropagandaListItem != null){
                tvEstabelecimentoPropagandaListItem.setText("" + p.getEstabId());
            }
        }

        v.setOnTouchListener(new SwipeDetector());

        return v;

    }
}
