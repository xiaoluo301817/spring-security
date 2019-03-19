package cn.xiaoluo.security.springsecurity.service;

import cn.xiaoluo.security.springsecurity.entity.TbUser;

import java.util.List;

/**
 * @author luowenqin
 * @create 2019-03-19 15:57
 */
public interface TbUserService {


    /**
     * 用户增加方法
     * @param user
     * @return
     */
    public boolean addTbUser(TbUser user);


    /**
     * 用户删除方法
     * @param ids
     * @return
     */
    public boolean deleteTbUser(Integer[] ids);



    /**
     * 用户修改方法
     * @param user
     * @return
     */
    public boolean updateTbUser(TbUser user);


    /**
     * 查询所用用户集合数据
     * @return
     */
    public List<TbUser> findAll();


    /**
     * 单条数据查询
     * @param id
     * @return
     */
    public TbUser findOne(Integer id);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public TbUser findOneByUsername(String username);


    /**
     * 用户登录信息
     * @param username
     * @param password
     * @return
     */
    public TbUser queryLogin(String username, String password);
}
