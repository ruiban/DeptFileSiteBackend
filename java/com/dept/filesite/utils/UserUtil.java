package com.dept.filesite.utils;

import com.dept.filesite.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @className: UserUtil
 * @description:
 * @author: 201998
 * @create: 2019-12-27 14:05
 */

public class UserUtil {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
