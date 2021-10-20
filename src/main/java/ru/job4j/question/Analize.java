package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> map = new HashMap<>();
        for (User elem : previous) {
            map.put(elem.getId(), elem);
        }
        for (User elem : current) {
            User prevUser = map.putIfAbsent(elem.getId(), elem);
            if (prevUser != null) {
                if (!elem.equals(prevUser)) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }
}
