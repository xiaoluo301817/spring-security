package cn.xiaoluo.security.springsecurity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author luowenqin
 * @create 2019-03-12 11:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnObject implements Serializable {
    private  Object content;
}
