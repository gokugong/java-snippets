package misc;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class GenericMisc {
    public static <T extends Comparable<? super T>> T max(
            List<? extends T> list) {
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T t = i.next();
            if (t.compareTo(result) > 0)
                result = t;
        }
        return result;
    }
//
// NOT WORKING
//    public static <T> T max2(
//            List<? extends T> list) {
//        if (list == null || list.isEmpty()) return null;
//
//        if (list.size() == 1) return list.get(0);
//
//        //TODO: NOT WORKING
//        T result = list.stream().max(Comparator.comparing(<? extends T> K -> K));
//        return result;
//    }
}
