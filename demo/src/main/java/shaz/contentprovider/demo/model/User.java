package shaz.contentprovider.demo.model;

/**
 * Created by ${Shahbaz} on 16-11-2017
 */

public class User {
    private long ID;
    private String name;
    private String email;
    private String address;

    public User(long ID, String name, String email, String address) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
