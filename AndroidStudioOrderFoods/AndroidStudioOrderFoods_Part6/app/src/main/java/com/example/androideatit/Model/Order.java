package com.example.androideatit.Model;

public class Order {
    private String ProductId;
    private String ProductName;
    private String Qty;
    private String Price;
    private String Discount;

    public Order() {}

    public Order(String productId, String productName, String qty, String price, String discount) {
        ProductId = productId;
        ProductName = productName;
        Qty = qty;
        Price = price;
        Discount = discount;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
