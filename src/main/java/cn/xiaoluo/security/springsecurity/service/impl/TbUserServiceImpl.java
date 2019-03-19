package cn.xiaoluo.security.springsecurity.service.impl;/**
 * @author luowenqin
 * @create 2019-03-19 15:59
 */

import cn.xiaoluo.security.springsecurity.entity.TbUser;
import cn.xiaoluo.security.springsecurity.entity.TbUserExample;
import cn.xiaoluo.security.springsecurity.mapper.TbUserMapper;
import cn.xiaoluo.security.springsecurity.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *   @ClassName TbUserServiceImpl
 *   @Description TODO
 *   @Author luowenqin
 *   @Date 2019/3/19 15:59
 *   @Version 1.0
 **/
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser findOneByUsername(String username) {
        System.out.println("username="+username);
        return tbUserMapper.selectByUniqueKey(username);
    }

    @Override
    public boolean addTbUser(TbUser user) {
        boolean flag = false;
        int insert = tbUserMapper.insert(user);
        if (insert > 0) flag = true;
        return flag;
    }

    @Override
    public boolean deleteTbUser(Integer[] ids) {
        boolean flag = false;
        for (Integer id : ids) {
            int i = tbUserMapper.deleteByPrimaryKey(id);
            if (i > 0) flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateTbUser(TbUser tbUser) {

        boolean flag = false;
        int insert = tbUserMapper.updateByPrimaryKey(tbUser);
        if (insert > 0) flag = true;
        return flag;


    }

    @Override
    public List<TbUser> findAll() {
        return tbUserMapper.selectByExample(null);
    }

    @Override
    public TbUser findOne(Integer id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }



    @Override
    public TbUser queryLogin(String username, String password) {
        TbUser user = null;
        TbUserExample userExample = new TbUserExample();
        TbUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(password).andStatusEqualTo("1");
        List<TbUser> tbUsers = tbUserMapper.selectByExample(userExample);
        user = tbUsers.size() > 0 ? user = tbUsers.get(0) : null;
        return user;
    }

    ;
}
