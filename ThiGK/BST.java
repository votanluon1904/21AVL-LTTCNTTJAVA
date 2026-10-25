package ThiGK;

import java.util.ArrayList;

public class BST {
    Node root;

    // Ham khoi tao cay rong
    public BST() {
        root = null;
    }

    // --- YEU CAU 1: Them vao cay ---
    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private Node insertRec(Node root, Student student) {
        if (root == null) {
            root = new Node(student);
            return root;
        }
        if (student.getId() < root.data.getId()) {
            root.left = insertRec(root.left, student); // Nho hon thi qua trai
        } else if (student.getId() > root.data.getId()) {
            root.right = insertRec(root.right, student); // Lon hon thi qua phai
        }
        return root;
    }

    // In danh sach dang bang (Duyet In-order de tang dan theo ID)
    // In danh sach co ban
    public void printAll() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            root.data.printInfo();
            inorderRec(root.right);
        }
    }

    // Ham ho tro luu tat ca sinh vien tren cay vao mot ArrayList
    public void addToList(Node root, ArrayList<Student> list) {
        if (root != null) {
            addToList(root.left, list);
            list.add(root.data);
            addToList(root.right, list);
        }
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        addToList(root, list);
        return list;
    }

    // --- YEU CAU 3: Tim kiem sinh vien ---
    public Student search(int id) {
        Node result = searchRec(root, id);
        if (result != null) {
            return result.data;
        }
        return null;
    }

    private Node searchRec(Node root, int id) {
        if (root == null || root.data.getId() == id) {
            return root;
        }
        if (root.data.getId() > id) {
            return searchRec(root.left, id);
        }
        return searchRec(root.right, id);
    }

    // --- YEU CAU 3: Xoa sinh vien ---
    public void delete(int id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, int id) {
        if (root == null) {
            return root;
        }

        if (id < root.data.getId()) {
            root.left = deleteRec(root.left, id);
        } else if (id > root.data.getId()) {
            root.right = deleteRec(root.right, id);
        } else {
            // Tim thay node can xoa
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Co 2 con: tim node nho nhat ben phai de thay the
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data.getId());
        }
        return root;
    }

    private Student minValue(Node root) {
        Student minV = root.data;
        while (root.left != null) {
            minV = root.left.data;
            root = root.left;
        }
        return minV;
    }
}