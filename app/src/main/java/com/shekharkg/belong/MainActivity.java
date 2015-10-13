package com.shekharkg.belong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loopj.android.http.RequestParams;
import com.michael.easydialog.EasyDialog;
import com.shekharkg.belong.adapter.CustomListAdapter;
import com.shekharkg.belong.adapter.FilterAdapter;
import com.shekharkg.belong.beans.Data;
import com.shekharkg.belong.beans.Folders;
import com.shekharkg.belong.beans.Product;
import com.shekharkg.belong.beans.SelectedFacets;
import com.shekharkg.belong.utils.CallBack;
import com.shekharkg.belong.utils.NetworkClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBack, AbsListView.OnScrollListener {

  private ListView listView;
  private HorizontalScrollView filterScrollView;
  private RadioGroup filterRG;
  private CustomListAdapter customListAdapter;
  private android.support.v7.widget.CardView progressView;

  private Data data;
  private int pageCount;
  private boolean isApiCalledOnScrollToEnd;
  private List<String> selectedTagsList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    pageCount = 1;
    listView = (ListView) findViewById(R.id.listView);
    progressView = (CardView) findViewById(R.id.progressView);
    filterScrollView = (HorizontalScrollView) findViewById(R.id.filterScrollView);
    filterRG = (RadioGroup) findViewById(R.id.filterRG);
    selectedTagsList = new ArrayList<>();
    selectedTagsList.add("mobiles");

    new NetworkClient().getDevices(MainActivity.this, MainActivity.this, new RequestParams(),
        pageCount++, true, selectedTagsList);
  }

  @Override
  public void successOperation(Object object, boolean isCalledFirstTime) {

    List<Product> productList = null;
    if (!isCalledFirstTime)
      productList = new ArrayList<>(data.getProductList());

    data = (Data) object;

    if (!isCalledFirstTime)
      for (int i = 0; i < productList.size(); i++)
        data.getProductList().add(i, productList.get(i));

    if (isCalledFirstTime) {
      customListAdapter = new CustomListAdapter(data.getProductList(), this);
      listView.setAdapter(customListAdapter);
      listView.setVisibility(View.VISIBLE);
      filterScrollView.setVisibility(View.VISIBLE);
      listView.setOnScrollListener(this);
      filterRG.removeAllViews();
      showFilterOptions();
    } else {
      customListAdapter.productList = data.getProductList();
      customListAdapter.notifyDataSetChanged();
    }
    isApiCalledOnScrollToEnd = false;
    progressView.setVisibility(View.GONE);
  }

  private void showFilterOptions() {
    for (final Folders folders : data.getFoldersList()) {
      final RadioButton radioButton = (RadioButton) LayoutInflater.from(this)
          .inflate(R.layout.radio_button, null);
      radioButton.setText(folders.getName());

      radioButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          View view = MainActivity.this.getLayoutInflater().inflate(R.layout.filter_dialog, null);
          Button clearAll = (Button) view.findViewById(R.id.clearAll);
          Button apply = (Button) view.findViewById(R.id.apply);
          ListView filterListView = (ListView) view.findViewById(R.id.filterListView);
          final FilterAdapter adapter = new FilterAdapter(folders.getFacetses(), MainActivity.this);
          filterListView.setAdapter(adapter);

          final EasyDialog easyDialog = new EasyDialog(MainActivity.this)
              .setLayout(view)
              .setLocationByAttachedView(v)
              .setGravity(EasyDialog.GRAVITY_BOTTOM)
              .setAnimationTranslationShow(EasyDialog.DIRECTION_X, 1000, -600, 100, -50, 50, 0)
              .setAnimationAlphaShow(1000, 0.3f, 1.0f)
              .setAnimationTranslationDismiss(EasyDialog.DIRECTION_X, 500, -50, 800)
              .setAnimationAlphaDismiss(500, 1.0f, 0.0f)
              .setTouchOutsideDismiss(true)
              .setMatchParent(true)
              .setMarginLeftAndRight(24, 24)
              .setBackgroundColor(Color.WHITE)
              .setOutsideColor(Color.TRANSPARENT)
              .show();

          apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              for (SelectedFacets facets : folders.getFacetses()) {
                if (facets.isSelected() && !selectedTagsList.contains(facets.getTag()))
                  selectedTagsList.add(facets.getTag());

                if(selectedTagsList.contains(facets.getTag())  && !facets.isSelected())
                  selectedTagsList.remove(selectedTagsList.indexOf(facets.getTag()));

              }

              pageCount = 1;
              progressView.setVisibility(View.VISIBLE);
              new NetworkClient().getDevices(MainActivity.this, MainActivity.this,
                  new RequestParams(), pageCount++, true, selectedTagsList);
              easyDialog.dismiss();
            }
          });

          clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              for (SelectedFacets facets : folders.getFacetses()) {
                if (selectedTagsList.contains(facets.getTag()))
                  selectedTagsList.remove(selectedTagsList.indexOf(facets.getTag()));
                facets.setIsSelected(false);
              }
              adapter.notifyDataSetChanged();
            }
          });
        }
      });

      filterRG.addView(radioButton);
    }
  }

  @Override
  public void failedOperation(Object object, boolean isCalledFirstTime) {
    progressView.setVisibility(View.GONE);
    showRetryDialog((String) object, isCalledFirstTime);
  }

  private void showRetryDialog(String errorMsg, final boolean isCalledFirstTime) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(errorMsg);

    builder.setPositiveButton("Retry",
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            progressView.setVisibility(View.VISIBLE);
            new NetworkClient().getDevices(MainActivity.this, MainActivity.this,
                new RequestParams(), pageCount++, isCalledFirstTime, selectedTagsList);
          }
        }
    );
    builder.show();
  }

  @Override
  public void onScrollStateChanged(AbsListView view, int scrollState) {

  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    final int lastItem = firstVisibleItem + visibleItemCount;
    if (lastItem == totalItemCount) {
      if (!isApiCalledOnScrollToEnd && data.getProductList().size() < data.getTotalNumberOfProducts()) {
        progressView.setVisibility(View.VISIBLE);
        new NetworkClient().getDevices(MainActivity.this, MainActivity.this,
            new RequestParams(), pageCount++, false, selectedTagsList);
      }
      isApiCalledOnScrollToEnd = true;
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_reset) {
      selectedTagsList.clear();
      selectedTagsList.add("mobiles");
      pageCount = 1;
      progressView.setVisibility(View.VISIBLE);
      new NetworkClient().getDevices(MainActivity.this, MainActivity.this, new RequestParams(),
          pageCount++, true, selectedTagsList);

      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
