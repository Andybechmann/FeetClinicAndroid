package com.example.bruger.feetclinic.UI.Therapist;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.REST.TherapistRest;
import com.example.bruger.feetclinic.R;

import java.util.ArrayList;

/**
 * Created by Buster on 25-02-2016.
 */
public class TherapistListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Therapist> therapists;
    private static LayoutInflater inflater = null;


    public TherapistListViewAdapter(Context context, ArrayList<Therapist> data)
    {
        mContext = context;
        therapists = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return therapists.size();
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
            view = inflater.inflate(R.layout.list_therapists, null);
        }
        TextView name = (TextView) view.findViewById(R.id.name);


        name.setText(therapists.get(position).getName());


        return view;
    }


}
