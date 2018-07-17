package com.bean;

import com.aop.DataName;
import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Administrator on 2018/7/6 0006.
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DataName(name="年龄",type = true)
    private String age;
    @DataName(name="姓名",type = true)
    private String name;
    @DataName(name="性别",type = true)
    private String sex;
    @Transient
    String item;

}
