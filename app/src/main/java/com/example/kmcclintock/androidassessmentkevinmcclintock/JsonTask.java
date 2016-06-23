package com.example.kmcclintock.androidassessmentkevinmcclintock;


import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KMcClintock on 8/06/2016.
 * TODO Add full description of what is happening in this class
 * This class is designed to handle the call to the web service
 * All network calls are performed in a background thread to ensure the
 * Main UI thread doesn't get blocked up.
 */
public class JsonTask extends AsyncTask<String,String,List<GetProducts.Products>> {



    public Activity activity;

    public JsonTask(Activity act){

        this.activity = act;
    }

    //TODO Add onPreExecute with loading view, ran out of time.

    public JsonResponse delegate = null;

    // Perform bulk operations in the background thread.
    @Override
    protected List<GetProducts.Products> doInBackground(String... params) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
//// TODO: 8/06/2016 fix try catch

        try {
            //Url of the data source
            URL url = new URL("http://mobcategories.s3-website-eu-west-1.amazonaws.com");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();

            JSONArray parentArray = new JSONArray(finalJson);

            //Json array products
            List<GetProducts> productList = new ArrayList<>();
            List<GetProducts.Products> productType = new ArrayList<>();


            //Loop through parent array first
            /**TODO Must re-order the Array to display items by category
            *   e.g -->     | Food | Drinks|
            *                 cake    beer
            */
            for (int i = 0; i < parentArray.length() ; i++) {

                JSONObject finalObject = parentArray.getJSONObject(i);

                GetProducts getProducts = new GetProducts();
                getProducts.setCategoryId(finalObject.getInt("id"));
                getProducts.setName(finalObject.getString("name"));

                //Loop through products array nested inside parent array
                for (int j = 0; j < finalObject.getJSONArray("products").length() ; j++) {

                    JSONObject productObject = finalObject.getJSONArray("products").getJSONObject(j);

                    GetProducts.Products getProductType = new GetProducts.Products();
                    getProductType.setProductName(productObject.getString("name"));

                    //Must combine webservice URL with image extension to get full image URL
                    getProductType.setImageURL(url + productObject.getString("url"));

                        //Get salePrice Objects nested in Products Array
                        JSONObject salePrice = productObject.getJSONObject("salePrice");
                        getProductType.setProductCurrency(salePrice.getString("currency"));
                        getProductType.setProductPrice(salePrice.getDouble("amount"));

                    //Adding objects to the list
                    productType.add(getProductType);

                }

                productList.add(getProducts);

            }
            //Return the list
            return productType;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            //Check if null in case of null pointer error
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<GetProducts.Products> result) {

        //Set up the adapter
        ProductAdapter adapter = new ProductAdapter(this.activity, R.layout.detail_layout, result);

        //Once executed send the result to the MainActivity to be displayed on load
        delegate.processFinish(adapter);
    }

}



