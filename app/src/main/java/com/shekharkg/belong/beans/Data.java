package com.shekharkg.belong.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ShekharKG on 10/9/2015.
 */
public class Data {

  @SerializedName("took")
  float timeTook;

  @SerializedName("total")
  int totalNumberOfProducts;

  @SerializedName("selected_tags")
  List<Tag> selectedTagsList;

  @SerializedName("selected_facets")
  List<SelectedFacets> selectedFacetsList;

  @SerializedName("folders")
  List<Folders> foldersList;

  @SerializedName("products")
  List<Product> productList;

  String prev;
  String next;

  public float getTimeTook() {
    return timeTook;
  }

  public void setTimeTook(float timeTook) {
    this.timeTook = timeTook;
  }

  public int getTotalNumberOfProducts() {
    return totalNumberOfProducts;
  }

  public void setTotalNumberOfProducts(int totalNumberOfProducts) {
    this.totalNumberOfProducts = totalNumberOfProducts;
  }

  public List<Tag> getSelectedTagsList() {
    return selectedTagsList;
  }

  public void setSelectedTagsList(List<Tag> selectedTagsList) {
    this.selectedTagsList = selectedTagsList;
  }

  public List<SelectedFacets> getSelectedFacetsList() {
    return selectedFacetsList;
  }

  public void setSelectedFacetsList(List<SelectedFacets> selectedFacetsList) {
    this.selectedFacetsList = selectedFacetsList;
  }

  public List<Folders> getFoldersList() {
    return foldersList;
  }

  public void setFoldersList(List<Folders> foldersList) {
    this.foldersList = foldersList;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  public String getPrev() {
    return prev;
  }

  public void setPrev(String prev) {
    this.prev = prev;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }
}
