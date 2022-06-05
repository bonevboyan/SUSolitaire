package Model.Common;

import java.util.List;

public class Sorter {
    //bubble sort a list - for leaderboard
    public static <E extends Comparable<E>> void sortList(List<E> list) {
        int n = list.size();
        E temp;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < (n - i - 1); j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
