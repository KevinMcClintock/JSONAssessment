package com.example.kmcclintock.androidassessmentkevinmcclintock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

/*
*   Created by KMcClintock on 8/06/2016.
*
*/

public class MainActivity extends AppCompatActivity implements JsonResponse {


    private GridView ProductGV;
    JsonTask asyncTask = new JsonTask(this);
    //// TODO: 8/06/2016 fix try catch
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ProductGV = (GridView)findViewById(R.id.productView);

        asyncTask.delegate = MainActivity.this;
        //Execute the async task
        asyncTask.execute();

        //Set click event for images displayed in gridview
        ProductGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                GetProducts.Products item = (GetProducts.Products) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                ImageView imageView = (ImageView) view.findViewById(R.id.productImageView);

                int[] screenLocation = new int[2];
                imageView.getLocationOnScreen(screenLocation);

                //Pass the product name, price, currency and url to DetailsActivity
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", imageView.getWidth()).
                        putExtra("height", imageView.getHeight()).
                        putExtra("title", item.getProductName()).
                        putExtra("image", item.getImageURL()).
                        putExtra("cur", item.getProductCurrency()).
                        putExtra("amount", item.getProductPrice());
                //Start details activity
                startActivity(intent);

            }
        });

}

    @Override
    public void processFinish(Adapter adapter) {
        //Once background thread has finished Set result to adapter
        ProductGV.setAdapter((ListAdapter) adapter);

    }

}











