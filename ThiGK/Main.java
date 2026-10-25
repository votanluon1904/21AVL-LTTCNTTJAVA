package ThiGK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        // Them du lieu mau tu yeu cau
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

        while (choice != 0) {
            System.out.println("\n===== CHUONG TRINH QUAN LY SINH VIEN =====");
            System.out.println("1. Yeu cau 1: Xuat danh sach sinh vien (BST)");
            System.out.println("2. Yeu cau 2: Tinh toan thong ke");
            System.out.println("3. Yeu cau 3: Tim kiem va xoa theo ID");
            System.out.println("4. Yeu cau 4: Quan ly danh sach Dat/Rot");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Nhap lua chon cua ban: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- DANH SACH SINH VIEN TREN CAY ---");
                    tree.printAll();
                    break;

                case 2:
                    System.out.println("\n--- THONG KE SINH VIEN ---");
                    ArrayList<Student> listAll = tree.getAllStudents();
                    if (listAll.isEmpty()) {
                        System.out.println("Danh sach trong!");
                        break;
                    }

                    int total = listAll.size();
                    double sumResult = 0;
                    int gioi = 0, kha = 0, tb = 0, yeu = 0;

                    Student maxNam = null;
                    Student minNam = null;

                    for (int i = 0; i < listAll.size(); i++) {
                        Student s = listAll.get(i);
                        sumResult = sumResult + s.getResult();

                        // Xet hoc luc
                        if (s.getResult() >= 8) {
                            gioi++;
                        } else if (s.getResult() >= 7) {
                            kha++;
                        } else if (s.getResult() >= 5) {
                            tb++;
                        } else {
                            yeu++;
                        }

                        // Xet nam cao nhat / thap nhat
                        if (s.getGender().equals("nam")) {
                            if (maxNam == null || s.getResult() > maxNam.getResult()) {
                                maxNam = s;
                            }
                            if (minNam == null || s.getResult() < minNam.getResult()) {
                                minNam = s;
                            }
                        }
                    }

                    System.out.println("- Tong so sinh vien: " + total);
                    System.out.println("- Diem trung binh: " + (sumResult / total));
                    System.out.println("- Ti le phan tram:");
                    System.out.println("  + Gioi (>=8): " + ((double) gioi / total * 100) + "%");
                    System.out.println("  + Kha (>=7): " + ((double) kha / total * 100) + "%");
                    System.out.println("  + Trung binh (>=5): " + ((double) tb / total * 100) + "%");
                    System.out.println("  + Yeu (<5): " + ((double) yeu / total * 100) + "%");

                    if (maxNam != null) {
                        System.out
                                .println("- Nam diem cao nhat: " + maxNam.getName() + " (" + maxNam.getResult() + ")");
                        System.out
                                .println("- Nam diem thap nhat: " + minNam.getName() + " (" + minNam.getResult() + ")");
                    } else {
                        System.out.println("- Khong co sinh vien nam!");
                    }
                    break;

                case 3:
                    System.out.print("Nhap 1 de TIM KIEM, 2 de XOA sinh vien: ");
                    int type = sc.nextInt();
                    System.out.print("Nhap ID sinh vien: ");
                    int id = sc.nextInt();

                    if (type == 1) {
                        Student found = tree.search(id);
                        if (found != null) {
                            System.out.println("Da tim thay sinh vien:");
                            found.printInfo();
                        } else {
                            System.out.println("Khong tim thay sinh vien co ID la: " + id);
                        }
                    } else if (type == 2) {
                        tree.delete(id);
                        System.out.println("Da xoa sinh vien (neu ton tai) khoi cay.");
                    }
                    break;

                case 4:
                    ArrayList<Student> listToSplit = tree.getAllStudents();
                    ArrayList<Student> listDat = new ArrayList<>();
                    ArrayList<Student> listRot = new ArrayList<>();

                    // Trich xuat danh sach
                    for (int i = 0; i < listToSplit.size(); i++) {
                        Student s = listToSplit.get(i);
                        if (s.getResult() >= 5) {
                            listDat.add(s);
                        } else {
                            listRot.add(s);
                        }
                    }

                    System.out.println("\n--- TRICH XUAT DANH SACH DAT VA ROT ---");

                    // Sap xep theo diem thi bang Comparator kieu truyen thong
                    Collections.sort(listDat, new Comparator<Student>() {
                        @Override
                        public int compare(Student s1, Student s2) {
                            if (s1.getResult() > s2.getResult())
                                return 1;
                            if (s1.getResult() < s2.getResult())
                                return -1;
                            return 0;
                        }
                    });

                    Collections.sort(listRot, new Comparator<Student>() {
                        @Override
                        public int compare(Student s1, Student s2) {
                            if (s1.getResult() > s2.getResult())
                                return 1;
                            if (s1.getResult() < s2.getResult())
                                return -1;
                            return 0;
                        }
                    });

                    System.out.println("\n* Danh sach DAT (Sap xep theo diem tang dan):");
                    for (int i = 0; i < listDat.size(); i++)
                        listDat.get(i).printInfo();

                    System.out.println("\n* Danh sach ROT (Sap xep theo diem tang dan):");
                    for (int i = 0; i < listRot.size(); i++)
                        listRot.get(i).printInfo();

                    // Sap xep theo ten bang Comparator kieu truyen thong
                    Collections.sort(listDat, new Comparator<Student>() {
                        @Override
                        public int compare(Student s1, Student s2) {
                            return s1.getFirstName().compareToIgnoreCase(s2.getFirstName());
                        }
                    });

                    Collections.sort(listRot, new Comparator<Student>() {
                        @Override
                        public int compare(Student s1, Student s2) {
                            return s1.getFirstName().compareToIgnoreCase(s2.getFirstName());
                        }
                    });

                    System.out.println("\n* Danh sach DAT (Sap xep theo ten A-Z):");
                    for (int i = 0; i < listDat.size(); i++)
                        listDat.get(i).printInfo();

                    System.out.println("\n* Danh sach ROT (Sap xep theo ten A-Z):");
                    for (int i = 0; i < listRot.size(); i++)
                        listRot.get(i).printInfo();

                    break;

                case 0:
                    System.out.println("Tam biet!");
                    break;

                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai!");
                    break;
            }
        }
        sc.close();
    }
}