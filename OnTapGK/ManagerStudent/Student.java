package ManagerStudent;

// FILE: Student.java
// CHUC NANG: Dinh nghia lop doi tuong Sinh vien
public class Student {
    // Khai bao cac thuoc tinh private de dam bao tinh dong goi
    private int id; // Ma sinh vien (kieu so nguyen)
    private String name; // Ho va ten
    private String gender; // Gioi tinh
    private double result; // Diem thi

    // Constructor khoi tao doi tuong voi day du tham so
    public Student(int id, String name, String gender, double result) {
        this.id = id; // Gan gia tri id
        this.name = name; // Gan gia tri name
        this.gender = gender; // Gan gia tri gender
        this.result = result; // Gan gia tri result
    }

    // Getter lay gia tri ID
    public int getId() {
        return id;
    }

    // Getter lay gia tri Ten
    public String getName() {
        return name;
    }

    // Getter lay gia tri Gioi tinh
    public String getGender() {
        return gender;
    }

    // Getter lay gia tri Diem thi
    public double getResult() {
        return result;
    }

    // Phuong thuc trich xuat Ten (tu cuoi cung trong chuoi Ho Ten) de sap xep
    public String getFirstName() {
        // Cua chuoi bang khoang trang de lay cac tu
        String[] parts = name.trim().split("\\s+");
        // Tra ve tu cuoi cung trong mang
        return parts[parts.length - 1];
    }

    // Ghi de phuong thuc toString de in thong tin duoi dang 1 dong cua bang
    @Override
    public String toString() {
        // Format chuoi: canh trai id 5 ki tu, name 25 ki tu, gender 10 ki tu, result 5
        // ki tu voi 1 so thap phan
        return String.format("| %-5d | %-25s | %-10s | %-5.1f |", id, name, gender, result);
    }
}
