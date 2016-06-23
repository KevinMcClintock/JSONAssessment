package com.example.kmcclintock.androidassessmentkevinmcclintock;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by KMcClintock on 8/06/2016.
 * TODO add more description of my code
 */
public class ProductAdapter extends ArrayAdapter<GetProducts.Products> {

    private Context context;

    private List<GetProducts.Products> productList;
        private int resource;

        public ProductAdapter(Context context, int resource, List<GetProducts.Products> objects){
            super(context,resource,objects);
            productList = objects;
            this.resource = resource;
            this.context = context;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.productName = (TextView) row.findViewById(R.id.productName);
            holder.productImage = (ImageView) row.findViewById(R.id.productImageView);
            row.setTag(holder);
        } else  {
            holder = (ViewHolder) row.getTag();
        }

        //Set Text and Image
        holder.productName.setText(productList.get(position).getProductName());
        //Used third party lib
        Picasso.with(context).load(productList.get(position).getImageURL()).into(holder.productImage);

        return row;

    }
    static class ViewHolder{

        ImageView productImage;
        TextView productName;
    }
}
