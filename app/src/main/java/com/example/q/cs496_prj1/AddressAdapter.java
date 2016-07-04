package com.example.q.cs496_prj1;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.Telephony;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter< AddressAdapter.ViewHolder> {
    private Context context;
    ArrayList<Address> data = null;
    int item_layout;

    public AddressAdapter(Context c, int l, ArrayList<Address> d) {
        this.context = c;
        this.item_layout = l;
        this.data = d;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_addressitem, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        final Address addr = data.get(position);
        holder.mNameView.setText(addr.get_name());
        holder.mAddrView.setText(addr.get_addr());
    }

    @Override
    public int getItemCount () {
        return this.data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mAddrView;

        public ViewHolder(View view) {
            super (view);
            mView = view;

            mNameView = (TextView) view.findViewById(R.id.item_name);
            mAddrView = (TextView) view.findViewById(R.id.item_addr);
        }
    }
}
