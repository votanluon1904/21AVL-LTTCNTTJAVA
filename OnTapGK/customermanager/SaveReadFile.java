package customermanager;

import java.io.*;

public class SaveReadFile {
    public static void saveTree(BST tree, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tree.getRoot());
            System.out.println("-> Da luu danh sach vao file: " + filename);
        } catch (IOException e) {
            System.out.println("Loi luu file: " + e.getMessage());
        }
    }

    public static Node loadTree(String filename) {
        File file = new File(filename);
        if (!file.exists())
            return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("-> Da nap du lieu khach hang tu file!");
            return (Node) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
        return null;
    }
}