package com.service;

import com.bean.SysLog;
import com.repository.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/7/13 0013.
 */
@Service
public class SysLogService {

    @Autowired
    SysLogRepository repository;

    public SysLog save(SysLog log){return repository.save(log);}

}
