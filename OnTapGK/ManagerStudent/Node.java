package ManagerStudent;

// FILE: Node.java
// CHUC NANG: Dinh nghia cau truc 1 nut tren Cay nhi phan tim kiem (BST)
public class Node {
    Student data; // Thuoc tinh luu tru du lieu cua nut la 1 doi tuong Student
    Node left; // Con tro tro den nut con ben trai
    Node right; // Con tro tro den nut con ben phai

    // Constructor khoi tao 1 nut moi
    public Node(Student data) {
        this.data = data; // Gan du lieu sinh vien cho nut
        this.left = null; // Mac dinh khoi tao nut con trai la null
        this.right = null;// Mac dinh khoi tao nut con phai la null
    }
}
