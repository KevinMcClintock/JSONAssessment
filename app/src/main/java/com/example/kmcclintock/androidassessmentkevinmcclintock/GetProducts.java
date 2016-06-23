package com.example.kmcclintock.androidassessmentkevinmcclintock;

import java.util.List;

/**
 * Created by KMcClintock on 8/06/2016.
 * //TODO information on class
 * Set all the getters and getters
 */
public class GetProducts {

    private String name;
    private int categoryId;

    private List<Products> productsList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    //Json contains an Array inside another Array
    static class Products{

        private String productType;
        private String productName;
        private String imageURL;


        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }
        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }


       private Double productPrice;
       private String productCurrency;

       public Double getProductPrice() {
           return productPrice;
       }

       public void setProductPrice(Double productPrice) {
           this.productPrice = productPrice;
       }
       public String getProductCurrency() {
           return productCurrency;
       }

       public void setProductCurrency(String productCurrency) {
           this.productCurrency = productCurrency;
       }

 }

}
