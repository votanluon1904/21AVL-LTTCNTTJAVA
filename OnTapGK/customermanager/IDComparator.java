package customermanager;

import java.util.Comparator;

public class IDComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return c1.getId().compareToIgnoreCase(c2.getId());
    }
}