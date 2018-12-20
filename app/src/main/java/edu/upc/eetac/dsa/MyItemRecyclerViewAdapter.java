package edu.upc.eetac.dsa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import edu.upc.eetac.dsa.ItemFragment.OnListFragmentInteractionListener;
import edu.upc.eetac.dsa.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private List<Cities> Cities;
    private Element element;

    public MyItemRecyclerViewAdapter(List<Cities> items, OnListFragmentInteractionListener listener) {
        Cities = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        Cities answer= ((Cities) Cities.get(position));
        /*Element answer= ((Element) Element)*/
        holder.text.setText(answer.getElements());
        Picasso.get()
                .load(answer.municipiEscut)
                .resize(10, 10)
                .centerCrop()
                .into(holder.escudo);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView City;
        public TextView codigo;
        public ImageView escudo;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            City=(TextView) view.findViewById(android.R.id.escudo);
            codigo=(TextView) view.findViewById(android.R.id.textCodigo);
            escudo=(ImageView) view.findViewById(android.R.id.escudo);
        }


    }
}
