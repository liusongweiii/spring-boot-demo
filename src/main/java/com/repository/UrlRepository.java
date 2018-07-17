package com.repository;

import com.bean.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
public interface UrlRepository extends JpaRepository<Url,Integer> {

    List<Url> findByStatus(Integer status);

}
