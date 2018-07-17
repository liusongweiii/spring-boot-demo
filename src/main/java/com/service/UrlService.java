package com.service;

import com.bean.Url;
import com.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@Service
public class UrlService {

    @Autowired
    UrlRepository repository;

    public List<Url> findByStatus(Integer status){
        return repository.findByStatus(status);
    }

    public Url save(Url url){return repository.save(url);}


}
