package util;

import entities.User;

/**
 * Created by jsarafajr on 05.08.14.
 */
public class CheckUser {
    public static boolean isAdmin(User user) {
        if (user == null) return false;
        return user.getUserGroupByGroupId().getType().equals("Адміністратор");
    }

    public static boolean isModer(User user) {
        if (user == null) return false;
        return user.getUserGroupByGroupId().getType().equals("Модератор");
    }

    public static boolean isRedactor(User user) {
        if (user == null) return false;
        return user.getUserGroupByGroupId().getType().equals("Редактор");
    }
}
