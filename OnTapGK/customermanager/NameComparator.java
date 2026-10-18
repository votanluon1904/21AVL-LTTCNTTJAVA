package customermanager;

import java.util.Comparator;

public class NameComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return c1.getFirstName().compareToIgnoreCase(c2.getFirstName());
    }
}