package com.repository;

import com.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/7/12 0012.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User queryById(Integer id);

}
