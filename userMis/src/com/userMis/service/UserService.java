package com.userMis.service;

import java.util.List;

import com.userMis.dao.UserDao;
import com.userMis.model.UserModel;

public class UserService {

    private UserDao userDao = new UserDao();
    
    public UserModel getUserByAccount(String account) {
        UserModel um = new UserModel();
        um.setAccount(account);
        List<UserModel> list = getUserList(um);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
    public List<UserModel> getUserList(UserModel um) {
        return userDao.selectList(um);
    }
    
    /**
     * @param um
     * @return ""=成功 "失败"
     */
    public String add(UserModel um) {
        return userDao.insert(um) > 0 ? "" : "失败";
    }
    
    /**
     * @param um
     * @return ""=成功 "修改失败"=失败
     */
    public String update(UserModel um) {
        return userDao.updateByAccount(um) > 0 ? "" : "修改失败";
    }
    
    /**
     * @param um
     * @return ""=成功 "删除失败"=失败
     */
    public String delete(UserModel um) {
        return userDao.deleteByAccount(um) > 0 ? "" : "删除失败";
    }
    
    /**
     * @param list
     * @return ""=成功 "删除失败"=失败
     */
    public String deleteAll(List<UserModel> list) {
        return userDao.deleteAllByAccount(list) == list.size() ? "" : "删除失败";
    }
    
    public int getCount(UserModel um) {
        return userDao.getCount(um);
    }
}
