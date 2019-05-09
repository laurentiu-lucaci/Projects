public class Product {
    private String ProductName;
    private double Price;
    private int Quantity;

    /*
    parameteres ProductName,Price,Quantity
     */

    public Product(String ProductName,double Price,int Quantity)
    {
        super();
        this.ProductName=ProductName;
        this.Price=Price;
        this.Quantity=Quantity;
    }


    //return ProductName
    public String getProductName() {
        return ProductName;
    }

    //return Price
    public double getPrice() {
        return Price;
    }

    //return Quantity
    public int getQuantity() {
        return Quantity;
    }

    //parameter ProductName to set
    public void setProductName(String productName) {
        ProductName = productName;
    }

    //parameter Price to set
    public void setPrice(double price) {
        Price = price;
    }

    //patameter Quantity to set
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "Product: "+getProductName()+" Price: "+getPrice()+" Quantity: "+getQuantity();
    }
}
