package ManagerStudent;

import java.util.ArrayList;
import java.util.List;

// FILE: BST.java
// CHUC NANG: Dinh nghia Cay nhi phan tim kiem (BST) quan ly danh sach Sinh vien
public class BST {
    private Node root; // Khai bao nut goc cua cay

    // Constructor khoi tao cay rong
    public BST() {
        this.root = null; // Gan nut goc bang null
    }

    // --- YEU CAU 1: NHAP & XUAT THEO DANG BANG ---

    // Phuong thuc them 1 sinh vien vao cay
    public void insert(Student student) {
        root = insertRec(root, student); // Goi ham de quy de them vao dung vi tri
    }

    // Ham de quy thuc hien viec chen nut vao cay theo logic BST (Nhan la ID)
    private Node insertRec(Node root, Student student) {
        if (root == null) { // Neu nut hien tai rong (hoac den nut la)
            return new Node(student); // Tao va tra ve nut moi
        }
        // Neu ID can chen nho hon ID nut hien tai -> Di sang nhanh trai
        if (student.getId() < root.data.getId()) {
            root.left = insertRec(root.left, student);
        }
        // Neu ID can chen lon hon ID nut hien tai -> Di sang nhanh phai
        else if (student.getId() > root.data.getId()) {
            root.right = insertRec(root.right, student);
        }
        return root; // Tra ve nut hien tai
    }

    // Phuong thuc in tieu de cua bang
    public void printTableHeader() {
        System.out.println("+-------+---------------------------+------------+-------+"); // In duong vien tren
        System.out.println("| ID    | Ho va Ten                 | Gioi tinh  | Diem  |"); // In tieu de cac cot
        System.out.println("+-------+---------------------------+------------+-------+"); // In duong vien duoi tieu de
    }

    // Phuong thuc in dong ke ket thuc bang
    public void printTableFooter() {
        System.out.println("+-------+---------------------------+------------+-------+"); // In duong vien duoi cung
    }

    // Phuong thuc xuat toan bo cay (duyet LNR de sap xep tang dan theo ID)
    public void printAll() {
        printTableHeader(); // In tieu de
        inorderRec(root); // Goi de quy in danh sach
        printTableFooter(); // In chan bang
    }

    // Ham de quy duyet cay theo thu tu Inorder (Left - Node - Right)
    private void inorderRec(Node root) {
        if (root != null) { // Neu nut khong rong
            inorderRec(root.left); // Duyet toan bo nhanh trai truoc
            System.out.println(root.data); // In thong tin cua nut hien tai
            inorderRec(root.right); // Duyet toan bo nhanh phai sau
        }
    }

    // Ham phu tro de duyet cay va do toan bo du lieu vao List
    public void getListRec(Node root, List<Student> list) {
        if (root != null) { // Neu nut khong rong
            getListRec(root.left, list); // Duyet trai
            list.add(root.data); // Them du lieu vao danh sach
            getListRec(root.right, list); // Duyet phai
        }
    }

    // --- YEU CAU 2: TINH TOAN THONG KE ---
    public void printStats() {
        List<Student> list = new ArrayList<>(); // Tao 1 danh sach rong
        getListRec(root, list); // Lay toan bo sinh vien tu cay vao danh sach

        if (list.isEmpty()) { // Neu danh sach rong
            System.out.println("Danh sach trong!"); // Thong bao
            return; // Ket thuc ham
        }

        int totalStudents = list.size(); // Lay tong so sinh vien
        double totalScore = 0; // Bien luu tong diem

        // Cac bien dem so luong tung xep loai
        int countGioi = 0, countKha = 0, countTB = 0, countYeu = 0;

        // Duyet qua toan bo sinh vien trong danh sach
        for (Student s : list) {
            totalScore += s.getResult(); // Cong don diem vao tong diem

            // Xep loai sinh vien
            if (s.getResult() >= 8)
                countGioi++; // Diem >= 8 la Gioi
            else if (s.getResult() >= 7)
                countKha++; // 7 <= Diem < 8 la Kha
            else if (s.getResult() >= 5)
                countTB++; // 5 <= Diem < 7 la TB
            else
                countYeu++; // Diem < 5 la Yeu
        }

        // Tinh diem trung binh
        double averageScore = totalScore / totalStudents;

        // In ket qua yeu cau 2.1 va 2.2
        System.out.println("1. Tong so sinh vien: " + totalStudents);
        System.out.println("2. Diem trung binh chung: " + String.format("%.2f", averageScore));

        // In ket qua yeu cau 2.3 (Tinh va in ti le phan tram)
        System.out.println("3. Ti le xep loai:");
        System.out.println("   - Gioi (>=8): " + String.format("%.2f", (double) countGioi / totalStudents * 100) + "%");
        System.out.println("   - Kha (>=7): " + String.format("%.2f", (double) countKha / totalStudents * 100) + "%");
        System.out.println(
                "   - Trung binh (>=5): " + String.format("%.2f", (double) countTB / totalStudents * 100) + "%");
        System.out.println("   - Yeu (<5): " + String.format("%.2f", (double) countYeu / totalStudents * 100) + "%");

        // Yeu cau 2.4: Tim sinh vien nam diem cao nhat, thap nhat
        Student maxMale = null; // Khoi tao bien luu nam cao nhat rong
        Student minMale = null; // Khoi tao bien luu nam thap nhat rong

        for (Student s : list) { // Duyet lai danh sach
            if (s.getGender().equalsIgnoreCase("nam")) { // Kiem tra neu la gioi tinh Nam
                // Neu chua co maxMale hoac diem hien tai > diem maxMale
                if (maxMale == null || s.getResult() > maxMale.getResult()) {
                    maxMale = s; // Cap nhat maxMale
                }
                // Neu chua co minMale hoac diem hien tai < diem minMale
                if (minMale == null || s.getResult() < minMale.getResult()) {
                    minMale = s; // Cap nhat minMale
                }
            }
        }

        System.out.println("4. Thong ke sinh vien Nam:");
        if (maxMale != null) { // Neu ton tai it nhat 1 nam
            System.out.println("   - Nam diem cao nhat: " + maxMale.getName() + " (" + maxMale.getResult() + " diem)");
            System.out.println("   - Nam diem thap nhat: " + minMale.getName() + " (" + minMale.getResult() + " diem)");
        } else {
            System.out.println("   - Khong co sinh vien Nam trong danh sach."); // Truong hop khong co Nam
        }
    }

    // --- YEU CAU 3: TIM KIEM VA XOA THEO ID ---

    // Phuong thuc tim kiem
    public Student searchByID(int id) {
        Node res = searchRec(root, id); // Goi de quy tim kiem
        return res != null ? res.data : null; // Neu tim thay tra ve data, khong thi null
    }

    // De quy tim kiem
    private Node searchRec(Node root, int id) {
        if (root == null || root.data.getId() == id) { // Neu cay rong hoac tim thay nut dung ID
            return root; // Tra ve nut do
        }
        if (root.data.getId() > id) { // Neu ID nut hien tai lon hon ID can tim
            return searchRec(root.left, id); // Tim tiep ben trai
        }
        return searchRec(root.right, id); // Nguoc lai tim tiep ben phai
    }

    // Phuong thuc xoa
    public void deleteByID(int id) {
        root = deleteRec(root, id); // Goi de quy xoa va cap nhat lai root
    }

    // De quy xoa nut
    private Node deleteRec(Node root, int id) {
        if (root == null)
            return root; // Neu khong tim thay nut can xoa, dung

        // Buoc 1: Di tim nut can xoa
        if (id < root.data.getId()) { // Nho di trai
            root.left = deleteRec(root.left, id);
        } else if (id > root.data.getId()) { // Lon di phai
            root.right = deleteRec(root.right, id);
        } else {
            // Buoc 2: Da tim thay nut can xoa
            // Truong hop 1 & 2: Nut chi co 1 con (trai hoac phai) hoac la nut la (0 con)
            if (root.left == null)
                return root.right; // Tra ve con phai noi len
            else if (root.right == null)
                return root.left; // Tra ve con trai noi len

            // Truong hop 3: Nut co du 2 con
            // Tim sinh vien co ID nho nhat ben nhanh phai de thay the
            root.data = minValue(root.right);
            // Xoa nut nho nhat do o ben nhanh phai
            root.right = deleteRec(root.right, root.data.getId());
        }
        return root; // Tra ve nut hien tai da duoc cap nhat
    }

    // Ham tim gia tri nho nhat cua 1 nhanh (dung cho viec xoa nut 2 con)
    private Student minValue(Node root) {
        Student minv = root.data; // Gan gia tri dau tien
        while (root.left != null) { // Cu chay xuong tan cung ben trai
            minv = root.left.data; // Cap nhat lai gia tri nho nhat
            root = root.left; // Di tiep xuong con trai
        }
        return minv; // Tra ve ket qua
    }

    // --- YEU CAU 4: TRICH XUAT DANH SACH DAT / ROT ---
    public void extractPassFail(List<Student> passList, List<Student> failList) {
        List<Student> all = new ArrayList<>(); // Tao list luu tat ca
        getListRec(root, all); // Do du lieu tu cay ra list

        for (Student s : all) { // Duyet toan bo
            if (s.getResult() >= 5.0) { // Neu diem >= 5
                passList.add(s); // Dua vao list Dat
            } else { // Nguoc lai
                failList.add(s); // Dua vao list Rot
            }
        }
    }
}