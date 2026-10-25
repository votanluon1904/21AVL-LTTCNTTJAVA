package ThiGK;

public class Node {
    Student data; // Du lieu la mot sinh vien
    Node left; // Nhanh ben trai
    Node right; // Nhanh ben phai

    // Ham khoi tao Node
    public Node(Student data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}