package com.shekharkg.belong.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ShekharKG on 10/9/2015.
 */
public class Folders {

  String uri;
  String name;

  @SerializedName("facets")
  List<SelectedFacets> facetses;

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<SelectedFacets> getFacetses() {
    return facetses;
  }

  public void setFacetses(List<SelectedFacets> facetses) {
    this.facetses = facetses;
  }
}
