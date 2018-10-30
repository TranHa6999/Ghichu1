package vn.edu.poly.thithat.model;

public class Model {
    String id;
    String name;
    long price;

    public Model(String id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
