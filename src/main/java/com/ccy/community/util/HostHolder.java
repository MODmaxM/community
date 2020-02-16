package com.ccy.community.util;

import com.ccy.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息，代替session对象
 */
@Component
public class HostHolder {

    ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public void setUser(User user) {
        threadLocal.set(user);
    }

    public User getUser() {
        return threadLocal.get();
    }

    public void clear() {
        threadLocal.remove();
    }
}
