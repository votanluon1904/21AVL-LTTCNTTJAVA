package customermanager;

import java.io.Serializable;

public class Node implements Serializable {
    Customer data;
    Node left, right;

    public Node(Customer data) {
        this.data = data;
        left = right = null;
    }
}