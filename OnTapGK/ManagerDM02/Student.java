package ManagerDM02;

import java.io.Serializable; // Import thu vien Serializable de ho tro viec doc/ghi doi tuong ra file

// Khai bao lop Student dai dien cho thong tin sinh vien, implement Serializable
public class Student implements Serializable {
    private String student_id; // Khai bao thuoc tinh ma sinh vien
    private String student_name; // Khai bao thuoc tinh ho ten sinh vien
    private double student_result; // Khai bao thuoc tinh diem trung binh
    private String student_rank; // Khai bao thuoc tinh xep loai hoc tap

    // Ham khoi tao (Constructor) voi 2 tham so co ban la ID va Ten
    public Student(String student_id, String student_name) {
        this.student_id = student_id; // Gan tham so ma sinh vien vao thuoc tinh cua lop
        this.student_name = student_name; // Gan tham so ten sinh vien vao thuoc tinh cua lop
        this.student_result = 0.0; // Khoi tao diem trung binh mac dinh la 0.0[cite: 10]
        this.student_rank = "Chua xep loai"; // Khoi tao xep loai mac dinh khi chua co diem[cite: 10]
    }

    // Ham Getter de lay ma sinh vien
    public String getStudent_id() {
        return student_id; // Tra ve ma sinh vien
    }

    // Ham Setter de cap nhat ma sinh vien
    public void setStudent_id(String student_id) {
        this.student_id = student_id; // Gan gia tri moi cho ma sinh vien
    }

    // Ham Getter de lay ho ten sinh vien
    public String getStudent_name() {
        return student_name; // Tra ve ho ten sinh vien
    }

    // Ham Setter de cap nhat ho ten sinh vien
    public void setStudent_name(String student_name) {
        this.student_name = student_name; // Gan gia tri moi cho ho ten sinh vien
    }

    // Ham Getter de lay diem trung binh
    public double getStudent_result() {
        return student_result; // Tra ve diem trung binh
    }

    // Ham Setter de cap nhat diem trung binh va tu dong tinh lai xep loai
    public void setStudent_result(double student_result) {
        this.student_result = student_result; // Gan gia tri diem moi cho thuoc tinh[cite: 10]
        setStudent_rank(); // Goi ham cap nhat xep loai moi khi diem thay doi[cite: 10]
    }

    // Ham Getter de lay thong tin xep loai
    public String getStudent_rank() {
        return student_rank; // Tra ve chuoi xep loai hoc tap
    }

    // Ham Setter phu tro de tu dong phan loai hoc luc dua tren diem
    public void setStudent_rank() {
        if (student_result >= 8.5) { // Neu diem tu 8.5 tro len[cite: 10]
            this.student_rank = "Gioi"; // Xep loai la Gioi (Excellent)
        } else if (student_result >= 6.5) { // Neu diem tu 6.5 den duoi 8.5[cite: 11]
            this.student_rank = "Kha"; // Xep loai la Kha (Good)
        } else if (student_result >= 5.0) { // Neu diem tu 5.0 den duoi 6.5[cite: 11]
            this.student_rank = "Trung binh"; // Xep loai la Trung binh (Average)
        } else { // Truong hop con lai (duoi 5.0)[cite: 11]
            this.student_rank = "Yeu"; // Xep loai la Yeu (Poor)
        }
    }

    // Ghi de phuong thuc toString de dinh dang chuoi khi in doi tuong ra man hinh
    @Override
    public String toString() {
        // Tra ve chuoi duoc dinh dang thanh cac cot cho de nhin, can le trai[cite: 11]
        return String.format("%-15s %-25s %-10.2f %-15s", student_id, student_name, student_result, student_rank);
    }
}
