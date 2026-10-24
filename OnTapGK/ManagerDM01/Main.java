package ManagerDM01;

import java.io.*; // Import thu vien ho tro nhap xuat file
import java.util.LinkedList; // Import thu vien danh sach lien ket LinkedList
import java.util.Scanner; // Import thu vien de doc du lieu tu ban phim

// Lop Customer dai dien cho thong tin khach hang, implement Serializable de doc/ghi file doi tuong
class Customer implements Serializable {
    private String customerId; // Khai bao thuoc tinh ma so tai khoan
    private String customerName; // Khai bao thuoc tinh ho ten khach hang
    private double customerAccount; // Khai bao thuoc tinh so du tai khoan
    private String customerClass; // Khai bao thuoc tinh phan loai khach hang

    // Ham khoi tao doi tuong Customer
    public Customer(String customerId, String customerName, double customerAccount) {
        this.customerId = customerId; // Gan gia tri ma so khach hang
        this.customerName = customerName; // Gan gia tri ho ten
        this.customerAccount = customerAccount; // Gan gia tri so du tai khoan
        this.customerClass = classifyCustomer(customerAccount); // Tu dong phan loai khach hang dua tren so du
    }

    // Ham phu tro de phan loai khach hang
    private String classifyCustomer(double account) {
        if (account > 500) { // Neu tai khoan lon hon 500 trieu
            return "Gold"; // Tra ve loai Gold[cite: 2]
        } else if (account >= 100) { // Neu tai khoan tu 100 den 500 trieu
            return "Silver"; // Tra ve loai Silver[cite: 2]
        } else { // Cac truong hop con lai (duoi 100 trieu)
            return "Normal"; // Tra ve loai Normal[cite: 2]
        }
    }

    // Ham lay ma so khach hang
    public String getCustomerId() {
        return customerId; // Tra ve customerId
    }

    // Ham lay ho ten khach hang
    public String getCustomerName() {
        return customerName; // Tra ve customerName
    }

    // Ham lay so du tai khoan
    public double getCustomerAccount() {
        return customerAccount; // Tra ve customerAccount
    }

    // Ham lay phan loai khach hang
    public String getCustomerClass() {
        return customerClass; // Tra ve customerClass
    }

    // Ghi de ham toString de hien thi thong tin doi tuong
    @Override
    public String toString() {
        // Dinh dang chuoi de in ra man hinh thanh cac cot
        return String.format("%-10s %-25s %-20.2f %-10s", customerId, customerName, customerAccount, customerClass);
    }
}

// Lop Main chua chuong trinh chinh
public class Main {

    // Ham doc danh sach khach hang tu file[cite: 3]
    @SuppressWarnings("unchecked") // Tat canh bao ep kieu an toan
    public static LinkedList<Customer> readFromFile(String path) {
        LinkedList<Customer> customers = new LinkedList<>(); // Khoi tao danh sach rong mac dinh
        try {
            FileInputStream fis = new FileInputStream(path); // Mo luong doc file
            ObjectInputStream ois = new ObjectInputStream(fis); // Mo luong doc doi tuong tu file
            customers = (LinkedList<Customer>) ois.readObject(); // Doc va ep kieu doi tuong thanh LinkedList
            ois.close(); // Dong luong doc doi tuong
            fis.close(); // Dong luong doc file
            System.out.println("Doc danh sach khach hang tu file thanh cong."); // In thong bao thanh cong khong dau
        } catch (Exception e) { // Bat loi neu co ngoai le xay ra
            System.out.println("Khong tim thay file hoac file loi, he thong se tao danh sach moi."); // In thong bao loi
                                                                                                     // khong dau
        }
        return customers; // Tra ve danh sach khach hang
    }

    // Ham luu danh sach khach hang ra file[cite: 3]
    public static boolean saveToFile(LinkedList<Customer> customers, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path); // Mo luong ghi file
            ObjectOutputStream oos = new ObjectOutputStream(fos); // Mo luong ghi doi tuong ra file
            oos.writeObject(customers); // Ghi danh sach khach hang vao file
            oos.close(); // Dong luong ghi doi tuong
            fos.close(); // Dong luong ghi file
            System.out.println("Luu danh sach khach hang ra file thanh cong."); // In thong bao thanh cong khong dau
            return true; // Tra ve true nghia la luu thanh cong
        } catch (Exception e) { // Bat loi ngoai le
            System.out.println("Luu file that bai."); // In thong bao that bai khong dau
            return false; // Tra ve false
        }
    }

    // Ham hien thi danh sach khach hang[cite: 4]
    public static void displayCustomerList(LinkedList<Customer> customers) {
        if (customers.isEmpty()) { // Kiem tra xem danh sach co rong khong
            System.out.println("Danh sach khach hang trong."); // In thong bao danh sach trong khong dau
            return; // Thoat ham hien thi
        }
        System.out.printf("%-5s %-10s %-25s %-20s %-10s\n", "STT", "ID", "Ten khach hang", "Tai khoan (Trieu)",
                "Phan loai"); // In tieu de cac cot
        int index = 1; // Khoi tao bien dem so thu tu
        for (Customer customer : customers) { // Duyet qua tung khach hang trong danh sach
            System.out.printf("%-5d %s\n", index++, customer.toString()); // In so thu tu va thong tin khach hang bang
                                                                          // ham toString
        }
    }

    // Ham them khach hang moi vao danh sach[cite: 4]
    public static void addCustomer(LinkedList<Customer> customers, Scanner scan) {
        System.out.print("Nhap ma tai khoan khach hang (ID): "); // Yeu cau nhap ID
        String id = scan.nextLine(); // Doc ID
        System.out.print("Nhap ho ten khach hang: "); // Yeu cau nhap ten
        String name = scan.nextLine(); // Doc ten
        System.out.print("Nhap so du tai khoan (Don vi Trieu VND): "); // Yeu cau nhap so du
        double account = scan.nextDouble(); // Doc so du
        scan.nextLine(); // Xoa ki tu xuong dong bi thua trong bo dem

        Customer customer = new Customer(id, name, account); // Tao doi tuong khach hang moi
        customers.add(customer); // Them khach hang vao danh sach
        System.out.println("Them khach hang thanh cong."); // In thong bao thanh cong khong dau
    }

    // Ham tim kiem khach hang theo ID[cite: 5]
    public static int findCustomerById(LinkedList<Customer> customers, String id) {
        for (int i = 0; i < customers.size(); i++) { // Vong lap duyet qua toan bo danh sach
            if (customers.get(i).getCustomerId().equals(id)) { // Neu ma khach hang trung voi ma can tim
                return i; // Tra ve vi tri cua khach hang trong danh sach
            }
        }
        return -1; // Tra ve -1 neu khong tim thay
    }

    // Ham sap xep danh sach khach hang theo ten[cite: 5]
    public static void sortCustomersByName(LinkedList<Customer> customers) {
        customers.sort((c1, c2) -> c1.getCustomerName().compareToIgnoreCase(c2.getCustomerName())); // Su dung bieu thuc
                                                                                                    // Lambda va ham
                                                                                                    // sort de sap xep
        System.out.println("Da sap xep danh sach khach hang theo ten."); // In thong bao hoan tat khong dau
    }

    // Ham xoa khach hang theo ID[cite: 6]
    public static void deleteCustomerById(LinkedList<Customer> customers, String id) {
        int index = findCustomerById(customers, id); // Goi ham tim kiem de lay vi tri khach hang can xoa
        if (index != -1) { // Neu tim thay (index khac -1)
            customers.remove(index); // Xoa khach hang khoi danh sach
            System.out.println("Xoa khach hang thanh cong."); // In thong bao xoa thanh cong khong dau
        } else { // Neu khong tim thay
            System.out.println("Khong tim thay khach hang voi ID nay de xoa."); // In thong bao loi khong dau
        }
    }

    // Ham main, noi thuc thi chuong trinh[cite: 6]
    public static void main(String[] args) {
        LinkedList<Customer> customers = new LinkedList<>(); // Khoi tao danh sach lien ket khach hang rong
        Scanner scan = new Scanner(System.in); // Khoi tao do tuong Scanner de doc ban phim
        int choice; // Bien luu tru lua chon cua nguoi dung
        String fileName = "CustomerList.dat"; // Ten file du lieu luu tru

        do { // Vong lap Menu
            System.out.println("\n--- CHUONG TRINH QUAN LY KHACH HANG ---"); // In tieu de Menu
            System.out.println("1. Doc danh sach khach hang tu file"); // Chuc nang 1
            System.out.println("2. Luu thong tin khach hang ra file"); // Chuc nang 2
            System.out.println("3. Xuat thong tin danh sach khach hang"); // Chuc nang 3
            System.out.println("4. Them khach hang vao danh sach"); // Chuc nang 4
            System.out.println("5. Tim kiem khach hang theo ID"); // Chuc nang 5
            System.out.println("6. Sap xep danh sach khach hang theo ten"); // Chuc nang 6
            System.out.println("7. Xoa khach hang theo ID"); // Chuc nang 7
            System.out.println("0. Thoat chuong trinh"); // Chuc nang 0 (Thoat)
            System.out.print("Nhap lua chon cua ban: "); // Yeu cau nhap lua chon

            choice = scan.nextInt(); // Doc so nguyen nguoi dung chon
            scan.nextLine(); // Xoa ki tu enter con thua

            switch (choice) { // Kiem tra lua chon bang switch-case[cite: 7]
                case 1: // Truong hop 1
                    customers = readFromFile(fileName); // Goi ham doc file
                    break; // Ket thuc case
                case 2: // Truong hop 2
                    saveToFile(customers, fileName); // Goi ham luu file
                    break; // Ket thuc case
                case 3: // Truong hop 3
                    displayCustomerList(customers); // Goi ham hien thi danh sach
                    break; // Ket thuc case
                case 4: // Truong hop 4
                    addCustomer(customers, scan); // Goi ham them khach hang
                    break; // Ket thuc case
                case 5: // Truong hop 5
                    System.out.print("Nhap ID khach hang can tim: "); // Yeu cau nhap ID tim kiem
                    String searchId = scan.nextLine(); // Doc ID tim kiem
                    int index = findCustomerById(customers, searchId); // Goi ham tim kiem
                    if (index != -1) { // Neu vi tri tra ve khac -1
                        System.out.println("Tim thay khach hang:"); // In thong bao tim thay
                        System.out.printf("%-5s %-10s %-25s %-20s %-10s\n", "STT", "ID", "Ten khach hang",
                                "Tai khoan (Trieu)", "Phan loai"); // In tieu de cot
                        System.out.printf("%-5d %s\n", 1, customers.get(index).toString()); // In thong tin khach hang
                                                                                            // do ra
                    } else { // Neu khong tim thay
                        System.out.println("Khong tim thay khach hang voi ID da nhap."); // In thong bao that bai
                    }
                    break; // Ket thuc case
                case 6: // Truong hop 6
                    sortCustomersByName(customers); // Goi ham sap xep danh sach
                    break; // Ket thuc case
                case 7: // Truong hop 7
                    System.out.print("Nhap ID khach hang can xoa: "); // Yeu cau nhap ID can xoa
                    String deleteId = scan.nextLine(); // Doc ID can xoa
                    deleteCustomerById(customers, deleteId); // Goi ham xoa khach hang
                    break; // Ket thuc case
                case 0: // Truong hop 0
                    System.out.println("Dang thoat chuong trinh..."); // In thong bao thoat
                    break; // Ket thuc case
                default: // Cac truong hop nhap sai so
                    System.out.println("Lua chon khong hop le. Vui long thu lai."); // In thong bao loi
            }
        } while (choice != 0); // Vong lap chay lien tuc den khi chon 0

        scan.close(); // Dong bo doc ban phim truoc khi ket thuc
    }
}