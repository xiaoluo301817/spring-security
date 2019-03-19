package cn.xiaoluo.security.springsecurity.support;/**
 * @author luowenqin
 * @create 2019-03-19 16:53
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *   @ClassName SimpleResponse
 *   @Description TODO
 *   @Author luowenqin
 *   @Date 2019/3/19 16:53
 *   @Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse  implements  java.io.Serializable{
    private  Object content;
}
