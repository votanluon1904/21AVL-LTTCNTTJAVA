package customermanager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST tree = new BST();
        String filename = "customers.dat";

        // Doc file luc khoi dong
        Node loadedRoot = SaveReadFile.loadTree(filename);
        if (loadedRoot != null) {
            tree.setRoot(loadedRoot);
        }

        int choice;
        do {
            System.out.println("\n===== QUAN LY KHACH HANG (BST) =====");
            System.out.println("1. Them khach hang moi");
            System.out.println("2. Xuat toan bo danh sach khach hang");
            System.out.println("3. Thong ke tuoi (Trung binh, Max, Min)");
            System.out.println("4. Xuat danh sach theo Gioi tinh hoac Nhom mau");
            System.out.println("5. Xuat danh sach Nam (Sap xep theo ten)");
            System.out.println("6. Tim KH Nam lon tuoi nhat / nho tuoi nhat");
            System.out.println("7. Tim kiem KH theo ID");
            System.out.println("8. Xoa KH theo ID");
            System.out.println("9. Luu vao file va Thoat");
            System.out.print("Chon chuc nang (1-9): ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhap ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhap Ho Ten: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhap Gioi tinh (Nam/Nu): ");
                    String gender = scanner.nextLine();
                    System.out.print("Nhap Tuoi: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhap Nhom mau (A, B, AB, O): ");
                    String blood = scanner.nextLine();

                    tree.insert(new Customer(id, name, gender, age, blood));
                    System.out.println("Them thanh cong!");
                    break;
                case 2:
                    tree.printAll();
                    break;
                case 3:
                    tree.printAgeStats();
                    break;
                case 4:
                    System.out.print("Loc theo (1-Gioi tinh, 2-Nhom mau): ");
                    int type = Integer.parseInt(scanner.nextLine());
                    if (type == 1) {
                        System.out.print("Nhap gioi tinh can loc (Nam/Nu): ");
                        tree.printByCondition("gender", scanner.nextLine());
                    } else {
                        System.out.print("Nhap nhom mau can loc (A/B/O/AB): ");
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
                    System.out.print("Nhap ID can tim: ");
                    Customer c = tree.searchByID(scanner.nextLine());
                    if (c != null) {
                        tree.printTableHeader();
                        System.out.println(c);
                        tree.printTableFooter();
                    } else {
                        System.out.println("Khong tim thay khach hang!");
                    }
                    break;
                case 8:
                    System.out.print("Nhap ID can xoa: ");
                    tree.deleteByID(scanner.nextLine());
                    System.out.println("Da xoa (neu ton tai)!");
                    break;
                case 9:
                    SaveReadFile.saveTree(tree, filename);
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
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