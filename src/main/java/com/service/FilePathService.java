package com.service;

import com.repository.FilePathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@Service
public class FilePathService {

    @Autowired
    FilePathRepository repository;

}
