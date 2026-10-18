package customermanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BST implements Serializable {
    private Node root;

    public BST() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(Customer customer) {
        root = insertRec(root, customer);
    }

    private Node insertRec(Node root, Customer customer) {
        if (root == null) {
            root = new Node(customer);
            return root;
        }
        if (customer.getId().compareToIgnoreCase(root.data.getId()) < 0)
            root.left = insertRec(root.left, customer);
        else if (customer.getId().compareToIgnoreCase(root.data.getId()) > 0)
            root.right = insertRec(root.right, customer);
        return root;
    }

    public void printTableHeader() {
        System.out.println("+------------+---------------------------+--------+------+-------+");
        System.out.println("| ID         | Full Name                 | Gender | Age  | Blood |");
        System.out.println("+------------+---------------------------+--------+------+-------+");
    }

    public void printTableFooter() {
        System.out.println("+------------+---------------------------+--------+------+-------+");
    }

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

    public void getListRec(Node root, List<Customer> list) {
        if (root != null) {
            getListRec(root.left, list);
            list.add(root.data);
            getListRec(root.right, list);
        }
    }

    public void printAgeStats() {
        List<Customer> list = new ArrayList<>();
        getListRec(root, list);
        if (list.isEmpty()) {
            System.out.println("Danh sach trong!");
            return;
        }

        int sumAge = 0;
        Customer maxAgeC = list.get(0);
        Customer minAgeC = list.get(0);

        for (Customer c : list) {
            sumAge += c.getAge();
            if (c.getAge() > maxAgeC.getAge())
                maxAgeC = c;
            if (c.getAge() < minAgeC.getAge())
                minAgeC = c;
        }

        System.out.println("-> Tuoi trung binh: " + (double) sumAge / list.size());
        System.out.println("-> Khach hang lon tuoi nhat: " + maxAgeC.getName() + " (" + maxAgeC.getAge() + " tuoi)");
        System.out.println("-> Khach hang nho tuoi nhat: " + minAgeC.getName() + " (" + minAgeC.getAge() + " tuoi)");
    }

    public void printByCondition(String type, String value) {
        List<Customer> list = new ArrayList<>();
        getListRec(root, list);

        printTableHeader();
        for (Customer c : list) {
            if (type.equals("gender") && c.getGender().equalsIgnoreCase(value)) {
                System.out.println(c);
            } else if (type.equals("blood") && c.getBlood().equalsIgnoreCase(value)) {
                System.out.println(c);
            }
        }
        printTableFooter();
    }

    public void printMaleSortedByName() {
        List<Customer> males = new ArrayList<>();
        getListRec(root, males);

        males.removeIf(c -> !c.getGender().equalsIgnoreCase("Nam"));

        // Su dung NameComparator theo dung cau truc file
        Collections.sort(males, new NameComparator());

        printTableHeader();
        for (Customer c : males)
            System.out.println(c);
        printTableFooter();
    }

    public void printMaleAgeStats() {
        List<Customer> list = new ArrayList<>();
        getListRec(root, list);

        Customer maxMale = null, minMale = null;
        for (Customer c : list) {
            if (c.getGender().equalsIgnoreCase("Nam")) {
                if (maxMale == null || c.getAge() > maxMale.getAge())
                    maxMale = c;
                if (minMale == null || c.getAge() < minMale.getAge())
                    minMale = c;
            }
        }

        if (maxMale != null) {
            System.out.println("-> Nam lon tuoi nhat: " + maxMale.getName() + " (" + maxMale.getAge() + " tuoi)");
            System.out.println("-> Nam nho tuoi nhat: " + minMale.getName() + " (" + minMale.getAge() + " tuoi)");
        } else {
            System.out.println("Khong co khach hang nam trong danh sach.");
        }
    }

    public Customer searchByID(String id) {
        Node res = searchRec(root, id);
        return res != null ? res.data : null;
    }

    private Node searchRec(Node root, String id) {
        if (root == null || root.data.getId().equalsIgnoreCase(id))
            return root;
        if (root.data.getId().compareToIgnoreCase(id) > 0)
            return searchRec(root.left, id);
        return searchRec(root.right, id);
    }

    public void deleteByID(String id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, String id) {
        if (root == null)
            return root;

        if (id.compareToIgnoreCase(root.data.getId()) < 0)
            root.left = deleteRec(root.left, id);
        else if (id.compareToIgnoreCase(root.data.getId()) > 0)
            root.right = deleteRec(root.right, id);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data.getId());
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
}