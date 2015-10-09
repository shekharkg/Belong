package com.shekharkg.belong.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ShekharKG on 10/9/2015.
 */
public class Images {

  @SerializedName("40x40")
  String _40_40;
  @SerializedName("360x360")
  String _360_360;
  @SerializedName("180x180")
  String _180_180;
  @SerializedName("75x75")
  String _75_75;
  @SerializedName("125x125")
  String _125_125;

  public String get_40_40() {
    return _40_40;
  }

  public void set_40_40(String _40_40) {
    this._40_40 = _40_40;
  }

  public String get_360_360() {
    return _360_360;
  }

  public void set_360_360(String _360_360) {
    this._360_360 = _360_360;
  }

  public String get_180_180() {
    return _180_180;
  }

  public void set_180_180(String _180_180) {
    this._180_180 = _180_180;
  }

  public String get_75_75() {
    return _75_75;
  }

  public void set_75_75(String _75_75) {
    this._75_75 = _75_75;
  }

  public String get_125_125() {
    return _125_125;
  }

  public void set_125_125(String _125_125) {
    this._125_125 = _125_125;
  }
}
