package edu.upc.eetac.dsa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Cities> Cities;
    private Element element;



    /* element.getIne()
       element.getMunicipiNom()
       element.getMunicifiEscut(); */

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView City;
        public TextView codigo;
        public ImageView escudo;

        public ViewHolder(View v) {
            super(v);
           /* text = (TextView) v.findViewById(android.R.id.text1);*/
            City=(TextView) v.findViewById(android.R.id.escudo);
            codigo=(TextView) v.findViewById(android.R.id.textCodigo);
            escudo=(ImageView) v.findViewById(android.R.id.escudo);

        }
    }

    public RecyclerViewAdapter(List<Cities> data) {
        this.Cities = data;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main2,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Cities answer= ((Cities) Cities.get(position));
        Element answer= ((Element) Element)
        holder.text.setText(answer.getElements());
        Picasso.get()
                .load(answer.municipiEscut)
                .resize(10, 10)
                .centerCrop()
                .into(holder.escudo);
    }

    @Override
    public int getItemCount() {
        return Cities.size();
    }

}