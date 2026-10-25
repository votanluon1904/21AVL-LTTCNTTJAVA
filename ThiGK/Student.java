package ThiGK;

public class Student {
    // Khai bao cac thuoc tinh cua sinh vien
    private int id;
    private String name;
    private String gender;
    private double result;

    // Ham khoi tao (Constructor)
    public Student(int id, String name, String gender, double result) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.result = result;
    }

    // Cac ham Getter de lay thong tin
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public double getResult() {
        return result;
    }

    // Ham lay ten cuoi cung de sap xep theo ten (Alpha B)
    public String getFirstName() {
        String[] parts = name.trim().split(" "); // Cat chuoi bang khoang trang
        return parts[parts.length - 1]; // Lay tu cuoi cung
    }

    // Ham in thong tin sinh vien ra man hinh
    public void printInfo() {
        System.out.println("ID: " + id + "\t Ten: " + name + "\t Gioi: " + gender + "\t Diem: " + result);
    }
}