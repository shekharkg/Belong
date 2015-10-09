package com.shekharkg.belong.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ShekharKG on 10/9/2015.
 */
public class SelectedFacets {

  String label;
  String tag;
  int count;
  @SerializedName("is_selected")
  boolean isSelected;

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setIsSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }
}
