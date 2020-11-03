package io.ctdev.framework.model;

public class Product {
    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public void setEmail(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static Product.Builder newBuilder() {
        return new Product.Builder();
    }

    public Product() {
    }

    private Product(final Product.Builder builder) {
        name = builder.name;
        price = builder.price;
    }

    public static final class Builder {
        private String name;
        private String price;

        private Builder() {
        }

        public Product.Builder withProductName(final String val) {
            name = val;
            return this;
        }

        public Product.Builder withPrice(final String val) {
            price = val;
            return this;
        }


        public Product build() {
            return new Product(this);
        }
    }

}
