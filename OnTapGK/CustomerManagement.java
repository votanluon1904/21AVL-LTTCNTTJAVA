import java.io.*;
import java.util.*;

// 1. Lớp Customer
class Customer implements Serializable {
    String id;
    String name;
    String gender;
    int age;
    String blood;

    public Customer(String id, String name, String gender, int age, String blood) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.blood = blood;
    }

    // Lấy tên (phần tên cuối cùng) để sắp xếp theo tên (Tiếng Việt)
    public String getFirstName() {
        String[] parts = name.trim().split("\\s+");
        return parts[parts.length - 1];
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-25s | %-6s | %-4d | %-5s |", id, name, gender, age, blood);
    }
}

// 2. Lớp Node cho cây BST
class Node implements Serializable {
    Customer data;
    Node left, right;

    public Node(Customer data) {
        this.data = data;
        left = right = null;
    }
}

// 3. Lớp Cây nhị phân tìm kiếm (BST)
class BST implements Serializable {
    Node root;

    public BST() {
        root = null;
    }

    // Yêu cầu 1: Nhập khách hàng vào cây (Key là ID)
    public void insert(Customer customer) {
        root = insertRec(root, customer);
    }

    private Node insertRec(Node root, Customer customer) {
        if (root == null) {
            root = new Node(customer);
            return root;
        }
        if (customer.id.compareTo(root.data.id) < 0)
            root.left = insertRec(root.left, customer);
        else if (customer.id.compareTo(root.data.id) > 0)
            root.right = insertRec(root.right, customer);
        return root;
    }

    // Header của bảng
    public void printTableHeader() {
        System.out.println("+------------+---------------------------+--------+------+-------+");
        System.out.println("| ID         | Full Name                 | Gender | Age  | Blood |");
        System.out.println("+------------+---------------------------+--------+------+-------+");
    }

    public void printTableFooter() {
        System.out.println("+------------+---------------------------+--------+------+-------+");
    }

    // Yêu cầu 2: Xuất danh sách theo định dạng bảng (LNR)
    public void printAll() {
        printTableHeader();
        inorderRec(root);
        printTableFooter();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.data);
            inorderRec(root.right);
        }
    }

    // Duyệt cây đưa vào ArrayList (Dùng chung cho nhiều hàm thống kê/lọc)
    public void getListRec(Node root, List<Customer> list) {
        if (root != null) {
            getListRec(root.left, list);
            list.add(root.data);
            getListRec(root.right, list);
        }
    }

    // Yêu cầu 3: Tính tuổi trung bình, khách hàng lớn tuổi nhất, nhỏ tuổi nhất
    public void printAgeStats() {
        List<Customer> list = new ArrayList<>();
        getListRec(root, list);
        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }

        int sumAge = 0;
        Customer maxAgeC = list.get(0);
        Customer minAgeC = list.get(0);

        for (Customer c : list) {
            sumAge += c.age;
            if (c.age > maxAgeC.age)
                maxAgeC = c;
            if (c.age < minAgeC.age)
                minAgeC = c;
        }

        System.out.println("-> Tuổi trung bình: " + (double) sumAge / list.size());
        System.out.println("-> Khách hàng lớn tuổi nhất: " + maxAgeC.name + " (" + maxAgeC.age + " tuổi)");
        System.out.println("-> Khách hàng nhỏ tuổi nhất: " + minAgeC.name + " (" + minAgeC.age + " tuổi)");
    }

    // Yêu cầu 4: Xuất danh sách theo giới tính hoặc nhóm máu
    public void printByCondition(String type, String value) {
        List<Customer> list = new ArrayList<>();
        getListRec(root, list);

        printTableHeader();
        for (Customer c : list) {
            if (type.equals("gender") && c.gender.equalsIgnoreCase(value)) {
                System.out.println(c);
            } else if (type.equals("blood") && c.blood.equalsIgnoreCase(value)) {
                System.out.println(c);
            }
        }
        printTableFooter();
    }

    // Yêu cầu 5: Sắp xếp theo tên và xuất KH Nam
    public void printMaleSortedByName() {
        List<Customer> males = new ArrayList<>();
        getListRec(root, males);

        // Lọc lấy danh sách Nam
        males.removeIf(c -> !c.gender.equalsIgnoreCase("Nam"));

        // Sắp xếp theo tên (phần tên cuối)
        males.sort((c1, c2) -> c1.getFirstName().compareToIgnoreCase(c2.getFirstName()));

        printTableHeader();
        for (Customer c : males)
            System.out.println(c);
        printTableFooter();
    }

    // Yêu cầu 6: Tìm KH nam lớn tuổi nhất, nhỏ tuổi nhất
    public void printMaleAgeStats() {
        List<Customer> list = new ArrayList<>();
        getListRec(root, list);

        Customer maxMale = null, minMale = null;
        for (Customer c : list) {
            if (c.gender.equalsIgnoreCase("Nam")) {
                if (maxMale == null || c.age > maxMale.age)
                    maxMale = c;
                if (minMale == null || c.age < minMale.age)
                    minMale = c;
            }
        }

        if (maxMale != null) {
            System.out.println("-> Nam lớn tuổi nhất: " + maxMale.name + " (" + maxMale.age + " tuổi)");
            System.out.println("-> Nam nhỏ tuổi nhất: " + minMale.name + " (" + minMale.age + " tuổi)");
        } else {
            System.out.println("Không có khách hàng nam trong danh sách.");
        }
    }

    // Yêu cầu 7: Tìm kiếm theo ID
    public Customer searchByID(String id) {
        Node res = searchRec(root, id);
        return res != null ? res.data : null;
    }

    private Node searchRec(Node root, String id) {
        if (root == null || root.data.id.equalsIgnoreCase(id))
            return root;
        if (root.data.id.compareToIgnoreCase(id) > 0)
            return searchRec(root.left, id);
        return searchRec(root.right, id);
    }

    // Yêu cầu 8: Xóa khách hàng theo ID
    public void deleteByID(String id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, String id) {
        if (root == null)
            return root;

        if (id.compareToIgnoreCase(root.data.id) < 0)
            root.left = deleteRec(root.left, id);
        else if (id.compareToIgnoreCase(root.data.id) > 0)
            root.right = deleteRec(root.right, id);
        else {
            // Node có 0 hoặc 1 con
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node có 2 con: Lấy node nhỏ nhất bên cây con phải
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data.id);
        }
        return root;
    }

    private Customer minValue(Node root) {
        Customer minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Yêu cầu 9: Lưu file và đọc file (Sử dụng Object Serialization cho tiện lợi
    // với Tree)
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(root);
            System.out.println("-> Đã lưu danh sách vào file: " + filename);
        } catch (IOException e) {
            System.out.println("Lỗi lưu file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists())
            return; // Chạy lần đầu chưa có file thì bỏ qua

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            root = (Node) ois.readObject();
            System.out.println("-> Đã nạp dữ liệu khách hàng từ file!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }
}

// 4. Hàm Main chạy chương trình
public class CustomerManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST tree = new BST();
        String filename = "customers.dat";

        // Đọc dữ liệu ngay khi chạy
        tree.loadFromFile(filename);

        int choice;
        do {
            System.out.println("\n===== QUẢN LÝ KHÁCH HÀNG (BST) =====");
            System.out.println("1. Thêm khách hàng mới");
            System.out.println("2. Xuất toàn bộ danh sách khách hàng");
            System.out.println("3. Thống kê tuổi (Trung bình, Max, Min)");
            System.out.println("4. Xuất danh sách theo Giới tính hoặc Nhóm máu");
            System.out.println("5. Xuất danh sách Nam (Sắp xếp theo tên)");
            System.out.println("6. Tìm KH Nam lớn tuổi nhất / nhỏ tuổi nhất");
            System.out.println("7. Tìm kiếm KH theo ID");
            System.out.println("8. Xóa KH theo ID");
            System.out.println("9. Lưu vào file và Thoát");
            System.out.print("Chọn chức năng (1-9): ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập Họ Tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập Giới tính (Nam/Nữ): ");
                    String gender = scanner.nextLine();
                    System.out.print("Nhập Tuổi: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhập Nhóm máu (A, B, AB, O): ");
                    String blood = scanner.nextLine();
                    tree.insert(new Customer(id, name, gender, age, blood));
                    System.out.println("Thêm thành công!");
                    break;
                case 2:
                    tree.printAll();
                    break;
                case 3:
                    tree.printAgeStats();
                    break;
                case 4:
                    System.out.print("Lọc theo (1-Giới tính, 2-Nhóm máu): ");
                    int type = Integer.parseInt(scanner.nextLine());
                    if (type == 1) {
                        System.out.print("Nhập giới tính cần lọc (Nam/Nữ): ");
                        tree.printByCondition("gender", scanner.nextLine());
                    } else {
                        System.out.print("Nhập nhóm máu cần lọc (A/B/O/AB): ");
                        tree.printByCondition("blood", scanner.nextLine());
                    }
                    break;
                case 5:
                    tree.printMaleSortedByName();
                    break;
                case 6:
                    tree.printMaleAgeStats();
                    break;
                case 7:
                    System.out.print("Nhập ID cần tìm: ");
                    Customer c = tree.searchByID(scanner.nextLine());
                    if (c != null) {
                        tree.printTableHeader();
                        System.out.println(c);
                        tree.printTableFooter();
                    } else {
                        System.out.println("Không tìm thấy khách hàng!");
                    }
                    break;
                case 8:
                    System.out.print("Nhập ID cần xóa: ");
                    tree.deleteByID(scanner.nextLine());
                    System.out.println("Đã xóa (nếu tồn tại)!");
                    break;
                case 9:
                    tree.saveToFile(filename);
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 9);
        scanner.close();
    }
}
/*
 * Bài Lab tổng ôn thi giữa kỳ hiện thực cây nhị phân tìm kiếm:
 * Customer(ID, Name, Gender, Age, Blood)
 * Name: tên đầy đủ họ và tên.
 * 1) Nhập 1 danh sách khách hàng vào cây BST lấy nhãn các node theo
 * customer_ID.
 * 2) Xuất danh sách khách hàng theo định dạng bảng.
 * 3) Tính tuổi trung bình khách hàng, tìm khách hàng có tuổi lớn nhất, nhỏ nhất
 * 4) Xuất danh sách khách hàng nam, danh sách khách hàng nữ, danh sách khách
 * hàng theo nhóm máu (A, B, O).
 * 5) Sắp xếp theo tên và xuất danh sách khách hàng nam
 * 6) Tìm khách hàng nam lớn tuổi nhất, khách hàng nam tuổi nhỏ nhất
 * 7) Tìm kiếm khách hàng theo ID
 * 8) Xoá khách hàng theo ID.
 * 9) Lưu danh sách khách hàng ra file và đọc file lấy danh sách mỗi khi chạy
 * chương trình
 */