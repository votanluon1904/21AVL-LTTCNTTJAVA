package ManagerStudent;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

// FILE: Main.java
// CHUC NANG: Noi thuc thi chuong trinh, chay thu nghiem cac tinh nang
public class Main {
    public static void main(String[] args) {
        BST tree = new BST(); // Khoi tao cay BST

        // --- THUC HIEN YEU CAU 1 ---
        System.out.println("=== YEU CAU 1: NHAP VA XUAT DANH SACH ===");
        // Nhap danh sach 10 sinh vien vao cay
        tree.insert(new Student(104, "Le Van Minh", "nam", 6));
        tree.insert(new Student(106, "Do Phuong Mai", "nu", 8.5));
        tree.insert(new Student(102, "Le Thi Hoang Anh", "nu", 2.5));
        tree.insert(new Student(107, "Pham Tuan Hai", "nam", 4));
        tree.insert(new Student(101, "Tran Van Tra", "nam", 9));
        tree.insert(new Student(103, "Le Vu Long", "nam", 3));
        tree.insert(new Student(108, "Phan Thi Yen", "nu", 7));
        tree.insert(new Student(109, "Le Van Chau", "nam", 6.5));
        tree.insert(new Student(112, "Pham Tra My", "nu", 7.5));
        tree.insert(new Student(105, "Dong Van Trang", "nam", 5.5));

        // Xuat danh sach sinh vien
        tree.printAll();

        // --- THUC HIEN YEU CAU 2 ---
        System.out.println("\n=== YEU CAU 2: THONG KE ===");
        tree.printStats(); // Goi ham in thong ke

        // --- THUC HIEN YEU CAU 3 ---
        System.out.println("\n=== YEU CAU 3: TIM KIEM VA XOA THEO ID ===");
        int searchId = 107; // ID can tim
        System.out.println("1. Tim sinh vien co ID = " + searchId);
        Student found = tree.searchByID(searchId); // Thuc hien tim kiem
        if (found != null) { // Neu tim thay
            tree.printTableHeader(); // In header
            System.out.println(found); // In thong tin sinh vien
            tree.printTableFooter(); // In footer
        } else {
            System.out.println("-> Khong tim thay!"); // Bao loi
        }

        int deleteId = 103; // ID can xoa
        System.out.println("2. Tien hanh xoa sinh vien co ID = " + deleteId);
        tree.deleteByID(deleteId); // Thuc hien xoa
        System.out.println("-> Danh sach sau khi xoa:");
        tree.printAll(); // In lai danh sach de kiem tra

        // --- THUC HIEN YEU CAU 4 ---
        System.out.println("\n=== YEU CAU 4: DANH SACH DAT / ROT ===");
        List<Student> passList = new ArrayList<>(); // Khoi tao ArrayList luu sv Dat
        List<Student> failList = new ArrayList<>(); // Khoi tao ArrayList luu sv Rot

        // Goi ham trich xuat tu cay vao 2 mang vua tao
        tree.extractPassFail(passList, failList);

        // a. Sap xep theo Diem thi (Giam dan hoac Tang dan)
        // Dùng Lambda expression so sanh thuoc tinh result (Tang dan)
        passList.sort((s1, s2) -> Double.compare(s1.getResult(), s2.getResult()));
        failList.sort((s1, s2) -> Double.compare(s1.getResult(), s2.getResult()));

        System.out.println("--- Danh sach DAT (Sap xep tang dan theo Diem) ---");
        tree.printTableHeader();
        for (Student s : passList)
            System.out.println(s); // Duyet in tung sv trong list dat
        tree.printTableFooter();

        System.out.println("\n--- Danh sach ROT (Sap xep tang dan theo Diem) ---");
        tree.printTableHeader();
        for (Student s : failList)
            System.out.println(s); // Duyet in tung sv trong list rot
        tree.printTableFooter();

        // b. Sap xep theo Ten (Alpha B)
        // Dùng Lambda expression ket hop method getFirstName() de sap xep chu cai
        passList.sort((s1, s2) -> s1.getFirstName().compareToIgnoreCase(s2.getFirstName()));
        failList.sort((s1, s2) -> s1.getFirstName().compareToIgnoreCase(s2.getFirstName()));

        System.out.println("\n--- Danh sach DAT (Sap xep Alpha B theo Ten) ---");
        tree.printTableHeader();
        for (Student s : passList)
            System.out.println(s); // Duyet in lai tung sv
        tree.printTableFooter();

        System.out.println("\n--- Danh sach ROT (Sap xep Alpha B theo Ten) ---");
        tree.printTableHeader();
        for (Student s : failList)
            System.out.println(s); // Duyet in lai tung sv
        tree.printTableFooter();
    }
}
/*
 * Cho class Student (ID, Name, Gender, Result)
 * Student std1 (104,"Le Van Minh","nam",6)
 * Student std2 (106,"Do Phuong Mai","nu",8.5)
 * Student std3 (102,"Le Thi Hoang Anh","nu",2.5)
 * Student std4 (107,"Pham Tuan Hai","nam",4)
 * Student std5 (101,"Tran Van Tra","nam",9)
 * Student std6 (103,"Le Vu Long","nam",3)
 * Student std7 (108,"Phan Thi Yen","nu",7)
 * Student std8 (109,"Le Van Chau","nam",6.5)
 * Student std9 (112,"Pham Tra My","nu",7.5)
 * Student std10 (105,"Dong Van Trang","nam",5.5)
 * 
 * Yêu cầu 1: (2 đ)
 * - Nhập danh sách 10 sinh viên tổ chức theo dạng cây Binary Search Tree (BST)
 * với nhãn các node theo ID.
 * - Xuất danh sách sinh viên được sắp xếp theo ID theo định dạng bảng.
 * Yêu cầu 2: (3 đ)
 * - Tính tổng số sinh viên.
 * - Tính điểm trung bình của sinh viên.
 * - Tính tỉ lệ sinh viên Giỏi >=8 Khá >=7 Trung bình >= 5 Yếu <5.
 * - Tìm sinh viên nam có điểm thi cao nhất, thấp nhất.
 * Yêu cầu 3: (2 đ)
 * - Tìm sinh viên theo ID.
 * - Xóa sinh viên khỏi danh sách theo ID.
 * Yêu cầu 4: (3 đ)
 * - Trích và xuất danh sách sinh viên đạt (có Result >=5) và rớt (có Result <5)
 * vào ArrayList hoặc LinkedList.
 * - Sắp xếp danh sách sinh viên đạt và danh sách sinh viên rớt theo điểm thi.
 * - Sắp xếp danh sách sinh viên đạt và danh sách sinh viên rớt theo tên.
 * 
 * Sinh viên làm bài thi trong 60 phút, nộp Report bài thi vào file Pdf: Code mã
 * nguồn + Chụp hình kết quả chạy các yêu cầu.
 */