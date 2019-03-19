package cn.xiaoluo.security.springsecurity.properties;/**
 * @author luowenqin
 * @create 2019-03-19 17:04
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *   @ClassName BrowserProperties
 *   @Description TODO
 *   @Author luowenqin
 *   @Date 2019/3/19 17:04
 *   @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "immoc.security.browser")
public class BrowserProperties implements Serializable {

    private  String loginPage ="/login.html";
}
