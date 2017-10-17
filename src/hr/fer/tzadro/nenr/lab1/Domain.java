package hr.fer.tzadro.nenr.lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Domain implements IDomain {

    public static IDomain intRange(int first, int last) {
        return new SimpleDomain(first, last);
    }

    public static IDomain combine(IDomain d1, IDomain d2) {
        List<IDomain> list = new ArrayList<>();
        list.addAll(Arrays.asList(d1.getComponents()));
        list.addAll(Arrays.asList(d2.getComponents()));
        return new CompositeDomain(list.toArray(new SimpleDomain[list.size()]));
    }
}