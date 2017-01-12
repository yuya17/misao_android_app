package com.example.contactlistviewnamephhone_sonu;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import static android.R.attr.resource;

/**
 * Created by yuyanozaki on 2017/01/12.
 */

public class ContactAdapter extends ArrayAdapter<ContactAttribute>{//This is adapter between Contact and List
    private Activity activity;
    private List<ContactAttribute> item;
    private int row;
    private ContactAttribute attribute;

    public ContactAdapter(Activity act,int row, List<ContactAttribute> item) {
        super(act,row,item);
        this.activity= act;
        this.item = item;
        this.row = row;
    }

    public View getView(final int position, View convertview, ViewGroup parent){
        View view = convertview;
        ViewHolder holder;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row,null);
            holder = new ViewHolder();
            view.setTag(holder);

        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        if((item==null)||(position+1)>item.size()){
            return view;
        }

        attribute = item.get(position);
        holder.name = (TextView) view.findViewById(R.id.tv_Name);
        holder.phone = (TextView) view.findViewById(R.id.tv_Phone);

        if(holder.name!=null && attribute.getname()!=null && attribute.getname().trim().length()>0){
            holder.name.setText(Html.fromHtml(attribute.getname()));
        }
        if(holder.phone!=null && attribute.getphone()!=null && attribute.getphone().trim().length()>0){
            holder.phone.setText(Html.fromHtml(attribute.getphone()));
        }
        return view;



    }

    public class ViewHolder{
        public TextView name;
        public TextView phone;

    }

}
