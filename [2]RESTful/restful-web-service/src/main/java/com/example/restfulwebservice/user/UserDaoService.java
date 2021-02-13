package com.example.restfulwebservice.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util. *;

@Service
//@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3; // 사용자 수

    static { //static 블럭
        users.add(new User(1, "Kenneth", new Date(), "pass1", "701010-1111111"));
        users.add(new User(2, "Alice", new Date(), "pass2", "801010-2222222"));
        users.add(new User(3, "Elena", new Date(), "pass2", "901010-1111111"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for(User user:users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public User updateById(int id, String newName) {
        for(User user:users) {
            if(user.getId() == id) {
                user.setName(newName);
                user.setJoinDate(new Date());
                return user;
            }
        }
        return null;
    }
}
