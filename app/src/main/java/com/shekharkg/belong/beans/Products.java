package com.shekharkg.belong.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ShekharKG on 10/9/2015.
 */
public class Products {

  int id;
  String name;
  String brand;
  String category;
  @SerializedName("category_id")
  int categoryId;
  @SerializedName("avg_rating")
  float avgRating;
  @SerializedName("rating_count")
  int ratingCount;
  @SerializedName("review_count")
  int reviewCount;
  @SerializedName("stock_info")
  String stockInfo;
  @SerializedName("'deal_count'")
  int dealCount;
  @SerializedName("store_count")
  int storeCount;
  @SerializedName("min_price")
  float minPrice;
  @SerializedName("min_price_str")
  String minPriceString;
  @SerializedName("key_features")
  List<String[]> keyFeatures;
  String url;
  @SerializedName("images_o")
  ImagesO imagesOs;
  List<Images> images;
  @SerializedName("biq_score")
  int bigScore;
  @SerializedName("curated_review_count")
  int curatedReviewCount;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public float getAvgRating() {
    return avgRating;
  }

  public void setAvgRating(float avgRating) {
    this.avgRating = avgRating;
  }

  public int getRatingCount() {
    return ratingCount;
  }

  public void setRatingCount(int ratingCount) {
    this.ratingCount = ratingCount;
  }

  public int getReviewCount() {
    return reviewCount;
  }

  public void setReviewCount(int reviewCount) {
    this.reviewCount = reviewCount;
  }

  public String getStockInfo() {
    return stockInfo;
  }

  public void setStockInfo(String stockInfo) {
    this.stockInfo = stockInfo;
  }

  public int getDealCount() {
    return dealCount;
  }

  public void setDealCount(int dealCount) {
    this.dealCount = dealCount;
  }

  public int getStoreCount() {
    return storeCount;
  }

  public void setStoreCount(int storeCount) {
    this.storeCount = storeCount;
  }

  public float getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(float minPrice) {
    this.minPrice = minPrice;
  }

  public String getMinPriceString() {
    return minPriceString;
  }

  public void setMinPriceString(String minPriceString) {
    this.minPriceString = minPriceString;
  }

  public List<String[]> getKeyFeatures() {
    return keyFeatures;
  }

  public void setKeyFeatures(List<String[]> keyFeatures) {
    this.keyFeatures = keyFeatures;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ImagesO getImagesOs() {
    return imagesOs;
  }

  public void setImagesOs(ImagesO imagesOs) {
    this.imagesOs = imagesOs;
  }

  public List<Images> getImages() {
    return images;
  }

  public void setImages(List<Images> images) {
    this.images = images;
  }

  public int getBigScore() {
    return bigScore;
  }

  public void setBigScore(int bigScore) {
    this.bigScore = bigScore;
  }

  public int getCuratedReviewCount() {
    return curatedReviewCount;
  }

  public void setCuratedReviewCount(int curatedReviewCount) {
    this.curatedReviewCount = curatedReviewCount;
  }
}
