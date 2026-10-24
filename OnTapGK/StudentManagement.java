import java.util.*;

// Lớp đại diện cho đối tượng Sinh viên
class Student {
    private int id;
    private String name;
    private String gender;
    private double result;

    public Student(int id, String name, String gender, double result) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.result = result;
    }

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

    @Override
    public String toString() {
        return String.format("| %-5d | %-22s | %-8s | %-6.2f |", id, name, gender, result);
    }
}

// Lớp Node của cây BST
class Node {
    Student data;
    Node left, right;

    public Node(Student data) {
        this.data = data;
        left = right = null;
    }
}

// Lớp quản lý Cây nhị phân tìm kiếm (BST) và các yêu cầu xử lý
class BST {
    Node root;

    public BST() {
        root = null;
    }

    // Yêu cầu 1: Thêm sinh viên vào cây theo ID
    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private Node insertRec(Node root, Student student) {
        if (root == null)
            return new Node(student);
        if (student.getId() < root.data.getId())
            root.left = insertRec(root.left, student);
        else if (student.getId() > root.data.getId())
            root.right = insertRec(root.right, student);
        return root;
    }

    public void printHeader() {
        System.out.println("+-------+------------------------+----------+--------+");
        System.out.println("| ID    | Full Name              | Gender   | Result |");
        System.out.println("+-------+------------------------+----------+--------+");
    }

    public void printFooter() {
        System.out.println("+-------+------------------------+----------+--------+");
    }

    // Xuất danh sách theo thứ tự tăng dần của ID (Duyệt Inorder LNR trên BST)
    public void printInOrder() {
        printHeader();
        inOrderRec(root);
        printFooter();
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.data);
            inOrderRec(root.right);
        }
    }

    // Chuyển dữ liệu từ cây BST sang ArrayList để phục vụ thống kê và sắp xếp
    public void toList(Node root, List<Student> list) {
        if (root != null) {
            toList(root.left, list);
            list.add(root.data);
            toList(root.right, list);
        }
    }

    // Yêu cầu 2: Thống kê tổng số, điểm TB, tỉ lệ xếp loại và tìm Nam điểm Max/Min
    public void processRequirement2() {
        List<Student> list = new ArrayList<>();
        toList(root, list);
        if (list.isEmpty()) {
            System.out.println("Danh sach trong!");
            return;
        }

        int total = list.size();
        double sum = 0;
        int gioi = 0, kha = 0, tb = 0, yeu = 0;

        Student maxNam = null;
        Student minNam = null;

        for (Student s : list) {
            sum += s.getResult();
            double r = s.getResult();

            // Xếp loại học lực
            if (r >= 8.0)
                gioi++;
            else if (r >= 7.0)
                kha++;
            else if (r >= 5.0)
                tb++;
            else
                yeu++;

            // Tìm sinh viên nam điểm cao/thấp nhất
            if (s.getGender().equalsIgnoreCase("nam")) {
                if (maxNam == null || r > maxNam.getResult())
                    maxNam = s;
                if (minNam == null || r < minNam.getResult())
                    minNam = s;
            }
        }

        double avg = sum / total;
        System.out.println("-> Tong so sinh vien: " + total);
        System.out.println("-> Diem trung binh cua tat ca sinh vien: " + String.format("%.2f", avg));
        System.out.println("-> Ti le sinh vien:");
        System.out.println("   + Gioi (>= 8.0)      : " + String.format("%.2f", ((double) gioi / total) * 100) + "% ("
                + gioi + "/" + total + ")");
        System.out.println("   + Kha (7.0 - 7.9)    : " + String.format("%.2f", ((double) kha / total) * 100) + "% ("
                + kha + "/" + total + ")");
        System.out.println("   + Trung binh (5-6.9) : " + String.format("%.2f", ((double) tb / total) * 100) + "% ("
                + tb + "/" + total + ")");
        System.out.println("   + Yeu (< 5.0)        : " + String.format("%.2f", ((double) yeu / total) * 100) + "% ("
                + yeu + "/" + total + ")");

        if (maxNam != null) {
            System.out.println("-> Sinh vien nam diem cao nhat: " + maxNam.getName() + " (" + maxNam.getResult() + ")");
            System.out
                    .println("-> Sinh vien nam diem thap nhat: " + minNam.getName() + " (" + minNam.getResult() + ")");
        } else {
            System.out.println("-> Khong co sinh vien nam trong danh sach.");
        }
    }

    // Yêu cầu 3a: Tìm kiếm sinh viên theo ID
    public Student searchByID(int id) {
        return searchRec(root, id);
    }

    private Student searchRec(Node root, int id) {
        if (root == null)
            return null;
        if (root.data.getId() == id)
            return root.data;
        if (id < root.data.getId())
            return searchRec(root.left, id);
        return searchRec(root.right, id);
    }

    // Yêu cầu 3b: Xóa sinh viên theo ID
    public void deleteByID(int id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, int id) {
        if (root == null)
            return null;
        if (id < root.data.getId())
            root.left = deleteRec(root.left, id);
        else if (id > root.data.getId())
            root.right = deleteRec(root.right, id);
        else {
            // Node có 0 hoặc 1 con
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node có 2 con: Lấy node nhỏ nhất ở cây con phải để thay thế
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data.getId());
        }
        return root;
    }

    private Student minValue(Node root) {
        Student minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Yêu cầu 4: Trích xuất Đạt/Rớt vào ArrayList, sắp xếp theo điểm và tên
    public void processRequirement4() {
        List<Student> list = new ArrayList<>();
        toList(root, list);

        List<Student> passed = new ArrayList<>();
        List<Student> failed = new ArrayList<>();

        for (Student s : list) {
            if (s.getResult() >= 5.0)
                passed.add(s);
            else
                failed.add(s);
        }

        System.out.println("\n--- DANH SACH BAN DAU ---");
        System.out.println("Danh sach Dat (>= 5.0):");
        printList(passed);
        System.out.println("Danh sach Rot (< 5.0):");
        printList(failed);

        // Sắp xếp theo điểm thi (tăng dần)
        passed.sort(Comparator.comparingDouble(Student::getResult));
        failed.sort(Comparator.comparingDouble(Student::getResult));

        System.out.println("\n--- SAU KHI SAP XEP THEO DIEM THI (TANG DAN) ---");
        System.out.println("Danh sach Dat:");
        printList(passed);
        System.out.println("Danh sach Rot:");
        printList(failed);

        // Sắp xếp theo tên (Alphabet)
        passed.sort(Comparator.comparing(Student::getName));
        failed.sort(Comparator.comparing(Student::getName));

        System.out.println("\n--- SAU KHI SAP XEP THEO TEN (ALPHABET) ---");
        System.out.println("Danh sach Dat:");
        printList(passed);
        System.out.println("Danh sach Rot:");
        printList(failed);
    }

    public void printList(List<Student> list) {
        printHeader();
        for (Student s : list) {
            System.out.println(s);
        }
        printFooter();
    }
}

// Lớp chính chạy chương trình
public class StudentManagement {
    public static void main(String[] args) {
        BST bst = new BST();

        // Thêm 10 sinh viên mẫu vào BST (Yêu cầu 1)
        bst.insert(new Student(104, "Le Van Minh", "nam", 6.0));
        bst.insert(new Student(106, "Do Phuong Mai", "nu", 8.5));
        bst.insert(new Student(102, "Le Thi Hoang Anh", "nu", 2.5));
        bst.insert(new Student(107, "Pham Tuan Hai", "nam", 4.0));
        bst.insert(new Student(101, "Tran Van Tra", "nam", 9.0));
        bst.insert(new Student(103, "Le Vu Long", "nam", 3.0));
        bst.insert(new Student(108, "Phan Thi Yen", "nu", 7.0));
        bst.insert(new Student(109, "Le Van Chau", "nam", 6.5));
        bst.insert(new Student(112, "Pham Tra My", "nu", 7.5));
        bst.insert(new Student(105, "Dong Van Trang", "nam", 5.5));

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n================ QUAN LY SINH VIEN (BST) ================");
            System.out.println("1. Yeu cau 1: Xuat danh sach sinh vien theo ID (BST Inorder)");
            System.out.println("2. Yeu cau 2: Thong ke (Tong so, Diem TB, Ti le, Nam Max/Min)");
            System.out.println("3. Yeu cau 3: Tim kiem va Xoa sinh vien theo ID");
            System.out.println("4. Yeu cau 4: Trich xuat, sap xep danh sach Dat/Rot theo Diem va Ten");
            System.out.println("5. Thoat chuong trinh");
            System.out.print("Chon chuc nang (1-5): ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("\n>>> YEU CAU 1: DANH SACH SINH VIEN SAP XEP THEO ID <<<");
                    bst.printInOrder();
                    break;
                case 2:
                    System.out.println("\n>>> YEU CAU 2: THONG KE THONG TIN SINH VIEN <<<");
                    bst.processRequirement2();
                    break;
                case 3:
                    System.out.println("\n>>> YEU CAU 3: TIM KIEM VA XOA THEO ID <<<");
                    System.out.print("Nhap ID sinh vien can tim: ");
                    int searchId = Integer.parseInt(scanner.nextLine());
                    Student found = bst.searchByID(searchId);
                    if (found != null) {
                        System.out.println("Tim thay sinh vien:");
                        bst.printHeader();
                        System.out.println(found);
                        bst.printFooter();
                    } else {
                        System.out.println("Khong tim thay sinh vien co ID: " + searchId);
                    }

                    System.out.print("Nhap ID sinh vien can xoa: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    bst.deleteByID(deleteId);
                    System.out.println("Da xoa (neu ton tai). Danh sach moi sau khi xoa:");
                    bst.printInOrder();
                    break;
                case 4:
                    System.out.println("\n>>> YEU CAU 4: TRICH XUAT VA SAP XEP DAT/ROT <<<");
                    bst.processRequirement4();
                    break;
                case 5:
                    System.out.println("Cam ơn ban da su dung chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai tu 1 den 5.");
            }
        } while (choice != 5);
        scanner.close();
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