package com.service;

import com.bean.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/12 0012.
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User add(User user){
        return repository.save(user);
    }

    public User queryById(Integer id){return repository.queryById(id);}

}
