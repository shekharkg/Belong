package com.shekharkg.belong.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.shekharkg.belong.R;
import com.shekharkg.belong.beans.Product;
import com.shekharkg.belong.beans.SelectedFacets;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ShekharKG on 10/11/2015.
 */
public class FilterAdapter extends BaseAdapter {

  public List<SelectedFacets> selectedFacetses;
  private Context context;

  public FilterAdapter(List<SelectedFacets> selectedFacetses, Context context) {
    this.selectedFacetses = selectedFacetses;
    this.context = context;
  }

  @Override
  public int getCount() {
    return selectedFacetses.size();
  }

  @Override
  public Object getItem(int position) {
    return selectedFacetses.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.filter_single_row, parent, false);
      ViewHolder viewHolder = new ViewHolder();
      viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
      viewHolder.textView = (TextView) convertView.findViewById(R.id.textView3);
      convertView.setTag(viewHolder);
    }

    final SelectedFacets facets = selectedFacetses.get(position);
    final ViewHolder holder = (ViewHolder) convertView.getTag();
    holder.textView.setText(facets.getLabel() + " (" + facets.getCount() + ")");
    if (facets.isSelected())
      holder.checkBox.setChecked(true);
    else
      holder.checkBox.setChecked(false);

    convertView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (holder.checkBox.isChecked()) {
          holder.checkBox.setChecked(false);
          facets.setIsSelected(false);
        } else {
          holder.checkBox.setChecked(true);
          facets.setIsSelected(true);
        }
      }
    });
    return convertView;
  }

  class ViewHolder {
    CheckBox checkBox;
    TextView textView;
  }
}
