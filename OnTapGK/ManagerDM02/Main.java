package ManagerDM02;

import java.io.*; // Import toan bo cac class ho tro doc/ghi file IO
import java.util.LinkedList; // Import cau truc du lieu LinkedList
import java.util.Scanner; // Import Scanner de nhap du lieu tu ban phim

// Khai bao lop Main (dong vai tro la Student_Manage) chua chuong trinh chinh
public class Main {
    static Scanner scan = new Scanner(System.in); // Khoi tao bien toan cuc Scanner de dung chung[cite: 11]
    static LinkedList<Student> studentList = new LinkedList<>(); // Khoi tao danh sach lien ket luu sinh vien[cite: 11]

    // Cau 1: Ham doc danh sach sinh vien tu file
    @SuppressWarnings("unchecked") // Bo qua canh bao ep kieu du lieu khi doc file[cite: 11]
    public static LinkedList<Student> readFromFile(String path) {
        LinkedList<Student> list = null; // Khoi tao danh sach tam thoi la null
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) { // Mo luong doc file an toan
            list = (LinkedList<Student>) ois.readObject(); // Doc object tu file va ep kieu ve LinkedList[cite: 11]
            System.out.println("Doc file thanh cong!"); // In thong bao thanh cong khong dau
        } catch (Exception e) { // Bat cac ngoai le xay ra (vi du: file khong ton tai)
            System.out.println("File chua ton tai hoac loi doc file, he thong se tao moi."); // In thong bao bao loi
                                                                                             // khong dau
        }
        return list != null ? list : new LinkedList<>(); // Neu list khac null thi tra ve list, neu null thi tra ve list
                                                         // moi[cite: 11]
    }

    // Cau 2: Ham luu danh sach sinh vien ra file
    public static boolean saveToFile(LinkedList<Student> list, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) { // Mo luong ghi file an
                                                                                            // toan[cite: 12]
            oos.writeObject(list); // Tien hanh ghi nguyen danh sach object ra file[cite: 12]
            return true; // Tra ve true neu ghi thanh cong
        } catch (Exception e) { // Bat ngoai le neu qua trinh luu that bai
            System.out.println("Loi khi luu file: " + e.getMessage()); // In thong bao loi
            return false; // Tra ve false vi that bai
        }
    }

    // Cau 3: Ham xuat thong tin danh sach sinh vien
    public static void displayStudentList(LinkedList<Student> list) {
        if (list.isEmpty()) { // Kiem tra neu danh sach rong
            System.out.println("Danh sach hien tai dang trong!"); // In thong bao khong dau
            return; // Thoat khoi ham
        }
        // In tieu de cua cac cot thong tin theo chuan dinh dang
        System.out.printf("%-15s %-25s %-10s %-15s\n", "Ma SV", "Ho Ten", "Diem TB", "Xep loai");
        for (Student student : list) { // Duyet tung sinh vien trong danh sach[cite: 12]
            System.out.println(student.toString()); // In thong tin cua sinh vien do ra man hinh[cite: 12]
        }
    }

    // Cau 4: Ham them sinh vien vao danh sach
    public static void addStudent(LinkedList<Student> list) {
        System.out.print("Nhap ma sinh vien: "); // In dong nhap ma sinh vien khong dau
        String id = scan.nextLine(); // Doc ma sinh vien tu ban phim
        System.out.print("Nhap ho ten sinh vien: "); // In dong nhap ho ten khong dau
        String name = scan.nextLine(); // Doc ho ten sinh vien tu ban phim

        Student student = new Student(id, name); // Khoi tao doi tuong Sinh vien moi tu du lieu vua nhap[cite: 12]
        list.add(student); // Them doi tuong sinh vien vao cuoi danh sach lien ket[cite: 12]
        System.out.println("Them sinh vien thanh cong. Vui long cap nhat diem o chuc nang so 8."); // In thong bao nhac
                                                                                                   // nho khong dau
    }

    // Cau 5: Ham phu tro tim kiem vi tri cua sinh vien theo ma sinh vien
    public static int findStudentById(LinkedList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) { // Duyet tu dau den cuoi danh sach[cite: 12]
            if (list.get(i).getStudent_id().equalsIgnoreCase(id)) { // Neu ma sv tai vi tri i trung voi id can tim
                                                                    // (khong xet hoa thuong)[cite: 12]
                return i; // Tra ve vi tri i do
            }
        }
        return -1; // Tra ve -1 neu tim het ma van khong thay[cite: 12]
    }

    // Cau 7: Ham xoa sinh vien khoi danh sach theo ma sinh vien
    public static void deleteStudent(LinkedList<Student> list) {
        System.out.print("Nhap ma sinh vien can xoa: "); // In yeu cau nhap ma khong dau
        String id = scan.nextLine(); // Doc ma sinh vien can xoa
        int index = findStudentById(list, id); // Goi ham tim vi tri sinh vien[cite: 12]
        if (index != -1) { // Neu index khac -1 tuc la co tim thay[cite: 12]
            list.remove(index); // Thuc hien xoa sinh vien tai vi tri do[cite: 13]
            System.out.println("Xoa sinh vien thanh cong."); // In thong bao da xoa khong dau
        } else { // Neu khong tim thay
            System.out.println("Khong tim thay sinh vien voi ma nay."); // In thong bao loi khong dau[cite: 13]
        }
    }

    // Cau 6: Ham sap xep danh sach sinh vien theo ho ten
    public static void sortStudentsByName(LinkedList<Student> list) {
        // Su dung ham sort cua LinkedList ket hop Lambda Expression de so sanh
        // ten[cite: 13]
        list.sort((s1, s2) -> s1.getStudent_name().compareToIgnoreCase(s2.getStudent_name())); // So sanh ho ten 2 doi
                                                                                               // tuong k phan biet hoa
                                                                                               // thuong
        System.out.println("Da sap xep danh sach theo ten."); // In thong bao hoan tat khong dau[cite: 13]
    }

    // Cau 8: Ham cap nhat diem trung binh va xep loai cua sinh vien
    public static void updateStudentResult(LinkedList<Student> list) {
        System.out.print("Nhap ma sinh vien can cap nhat diem: "); // In yeu cau nhap ma khong dau[cite: 13]
        String id = scan.nextLine(); // Doc ma sinh vien
        int index = findStudentById(list, id); // Tim vi tri cua sinh vien do trong danh sach[cite: 13]

        if (index != -1) { // Neu tim thay sinh vien[cite: 13]
            System.out.print("Nhap diem trung binh moi: "); // Yeu cau nhap diem moi khong dau[cite: 13]
            double result = scan.nextDouble(); // Doc diem dang so thuc[cite: 13]
            scan.nextLine(); // Xoa bo dem ki tu xuong dong con thua sau khi nhap so[cite: 13]

            list.get(index).setStudent_result(result); // Cap nhat lai diem cho sinh vien tai vi tri index[cite: 13]
            System.out.println("Cap nhat diem thanh cong. Xep loai da tu dong cap nhat."); // In thong bao thanh cong
                                                                                           // khong dau[cite: 13]
        } else { // Neu khong tim thay
            System.out.println("Khong tim thay sinh vien can cap nhat."); // In thong bao loi khong dau[cite: 13]
        }
    }

    // Ham Menu thuc thi chinh chuong trinh
    public static void main(String[] args) {
        String filePath = "students.dat"; // Dat ten file luu du lieu mac dinh[cite: 14]
        int choice; // Khai bao bien luu tuy chon cua nguoi dung[cite: 14]

        do { // Vong lap chay menu
            System.out.println("\n--- HE THONG QUAN LY SINH VIEN ---"); // In tieu de he thong khong dau
            System.out.println("1. Doc danh sach sinh vien tu file"); // Chuc nang 1 khong dau
            System.out.println("2. Luu danh sach sinh vien ra file"); // Chuc nang 2 khong dau
            System.out.println("3. Xuat thong tin danh sach sinh vien"); // Chuc nang 3 khong dau
            System.out.println("4. Them sinh vien moi"); // Chuc nang 4 khong dau
            System.out.println("5. Tim kiem sinh vien theo ID"); // Chuc nang 5 khong dau
            System.out.println("6. Xoa sinh vien theo ID"); // Chuc nang 6 khong dau
            System.out.println("7. Sap xep danh sach theo Ho Ten"); // Chuc nang 7 khong dau
            System.out.println("8. Cap nhat diem va xep loai"); // Chuc nang 8 khong dau
            System.out.println("0. Thoat chuong trinh"); // Chuc nang 0 thoat phan mem khong dau
            System.out.print("Nhap lua chon cua ban: "); // Yeu cau nhap phuong an khong dau

            choice = scan.nextInt(); // Doc so lua chon cua nguoi dung
            scan.nextLine(); // Don sach bo dem de nhap chuoi luot tiep theo

            switch (choice) { // Cau truc re nhanh theo lua chon cua nguoi dung[cite: 14]
                case 1: // Truong hop chon 1
                    studentList = readFromFile(filePath); // Goi ham doc file va gan vao danh sach
                    break; // Ngat case
                case 2: // Truong hop chon 2
                    if (saveToFile(studentList, filePath)) { // Kiem tra xem goi ham luu file co true khong
                        System.out.println("Luu du lieu thanh cong."); // In thong bao luu thanh cong khong dau
                    } else { // Neu false
                        System.out.println("Loi luu du lieu."); // In thong bao loi khong dau
                    }
                    break; // Ngat case
                case 3: // Truong hop chon 3
                    displayStudentList(studentList); // Goi ham in danh sach
                    break; // Ngat case
                case 4: // Truong hop chon 4
                    addStudent(studentList); // Goi ham them sinh vien
                    break; // Ngat case
                case 5: // Truong hop chon 5
                    System.out.print("Nhap ma sinh vien can tim: "); // Yeu cau nhap ma tim kiem khong dau
                    String idToSearch = scan.nextLine(); // Doc id can tim
                    int index = findStudentById(studentList, idToSearch); // Goi ham tim kiem tra ve index
                    if (index != -1) { // Neu index hop le
                        System.out.printf("%-15s %-25s %-10s %-15s\n", "Ma SV", "Ho Ten", "Diem TB", "Xep loai"); // In
                                                                                                                  // lai
                                                                                                                  // header
                                                                                                                  // bang
                        System.out.println(studentList.get(index)); // In thong tin cua sinh vien tim duoc
                    } else { // Neu index la -1
                        System.out.println("Khong tim thay sinh vien."); // Bao khong tim thay
                    }
                    break; // Ngat case
                case 6: // Truong hop chon 6 (tuong ung xoa theo id)
                    deleteStudent(studentList); // Goi ham xoa sinh vien
                    break; // Ngat case
                case 7: // Truong hop chon 7 (tuong ung sap xep danh sach)
                    sortStudentsByName(studentList); // Goi ham sap xep ten
                    break; // Ngat case
                case 8: // Truong hop chon 8
                    updateStudentResult(studentList); // Goi ham cap nhat diem va xep loai
                    break; // Ngat case
                case 0: // Truong hop chon 0
                    System.out.println("Dang thoat chuong trinh..."); // In cau chao tam biet khong dau
                    break; // Ngat case
                default: // Neu nhap cac so ngoai pham vi tu 0-8
                    System.out.println("Lua chon khong hop le. Vui long nhap lai."); // In thong bao chon sai khong dau
            }
        } while (choice != 0); // Vong lap ton tai chung nao chua chon 0[cite: 14]
    }
}
