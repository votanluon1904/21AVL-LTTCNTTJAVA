package customermanager;

import java.io.Serializable;

public class Customer implements Serializable {
    private String id;
    private String name;
    private String gender;
    private int age;
    private String blood;

    public Customer(String id, String name, String gender, int age, String blood) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.blood = blood;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getBlood() {
        return blood;
    }

    // Lay phan ten cuoi cung de sap xep
    public String getFirstName() {
        String[] parts = name.trim().split("\\s+");
        return parts[parts.length - 1];
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-25s | %-6s | %-4d | %-5s |", id, name, gender, age, blood);
    }
}