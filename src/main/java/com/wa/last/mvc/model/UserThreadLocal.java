package com.wa.last.mvc.model;

import com.wa.last.mvc.pojo.entity.User;

/**
 * @author
 * @date
 */
public final class UserThreadLocal {

    private static ThreadLocal<User> value = new ThreadLocal<>();

    public UserThreadLocal(User user) {
        this.value.set(user);
    }

    public User getValue() {
        return value.get();
    }
}
