package com.example.bruger.feetclinic.UI.Treatment;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.R;

import java.util.ArrayList;

/**
 * Created by Buster on 25-02-2016.
 */
public class TreatmentListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Treatment> treatments;
    private static LayoutInflater inflater = null;


    public TreatmentListViewAdapter(Context context, ArrayList<Treatment> data) {
        mContext = context;
        treatments = data;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return treatments.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(convertView==null){
            view = inflater.inflate(R.layout.list_treatments, null);
        }
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView price = (TextView) view.findViewById(R.id.price);

        name.setText(treatments.get(position).getName());
        price.setText("price: " + treatments.get(position).getPrice());

        return view;
    }


}
