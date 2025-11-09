package model;

/**
 * Model class for Product/Item
 */
public class Product {
    private String stt;
    private String productName;
    private String origin;
    private String specification;
    private String unit;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    
    public Product() {
        this.quantity = 0;
        this.unitPrice = 0.0;
        this.totalPrice = 0.0;
    }
    
    public Product(String stt, String productName, String origin, String specification, 
                   String unit, double unitPrice) {
        this.stt = stt;
        this.productName = productName;
        this.origin = origin;
        this.specification = specification;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.quantity = 0;
        this.totalPrice = 0.0;
    }
    
    public void calculateTotal() {
        this.totalPrice = this.quantity * this.unitPrice;
    }
    
    // Getters and Setters
    public String getStt() {
        return stt;
    }
    
    public void setStt(String stt) {
        this.stt = stt;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public String getSpecification() {
        return specification;
    }
    
    public void setSpecification(String specification) {
        this.specification = specification;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        calculateTotal();
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

