package cn.xiaoluo.security.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbUser implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private Integer age;

    private String email;

    private String sex;

    private String phone;

    private String address;

    private Date createDate;

    private Date updateDate;

    private String status;

}