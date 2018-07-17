package com.bean;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@Data
@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 1000)
    private String url;
    private String name;
    private String remark;
    @Column(length = 2)
    private Integer status;
}
