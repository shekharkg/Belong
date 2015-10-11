package com.shekharkg.belong.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shekharkg.belong.R;
import com.shekharkg.belong.beans.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ShekharKG on 10/11/2015.
 */
public class CustomListAdapter extends BaseAdapter {

  public List<Product> productList;
  private Context context;

  public CustomListAdapter(List<Product> productList, Context context) {
    this.productList = productList;
    this.context = context;
  }

  @Override
  public int getCount() {
    return productList.size();
  }

  @Override
  public Object getItem(int position) {
    return productList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.single_row, parent, false);
      ViewHolder viewHolder = new ViewHolder();
      viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
      viewHolder.titleTV = (TextView) convertView.findViewById(R.id.titleTV);
      viewHolder.priceTV = (TextView) convertView.findViewById(R.id.priceTV);
      viewHolder.storeTv = (TextView) convertView.findViewById(R.id.storeTV);
      viewHolder.ratingTV = (TextView) convertView.findViewById(R.id.ratingTV);
      viewHolder.voteTv = (TextView) convertView.findViewById(R.id.votesTV);
      convertView.setTag(viewHolder);
    }

    ViewHolder holder = (ViewHolder) convertView.getTag();

    Product product = productList.get(position);
    holder.titleTV.setText(product.getName());
    holder.priceTV.setText("Rs. " + product.getMinPriceString());
    holder.storeTv.setText(product.getStoreCount() + (product.getStoreCount() > 1 ? " Stores" : " Store"));
    holder.ratingTV.setText(String.valueOf(product.getAvgRating()));
    holder.voteTv.setText(product.getRatingCount() + (product.getRatingCount() > 1 ? " Votes" : " Vote"));

    Picasso.with(context)
        .load(TextUtils.isEmpty(product.getImagesOs().getL()) ? null : product.getImagesOs().getL())
        .placeholder(android.R.drawable.star_big_off)
        .error(android.R.drawable.stat_notify_error)
        .into(holder.imageView);

    return convertView;
  }

  class ViewHolder {
    ImageView imageView;
    TextView titleTV, priceTV, storeTv, ratingTV, voteTv;
  }
}
