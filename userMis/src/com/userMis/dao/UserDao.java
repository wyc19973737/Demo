package com.userMis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.userMis.model.UserModel;
import com.userMis.util.FormatEmpty;
import com.userMis.util.JDBCUtil;

public class UserDao {
    private String schema = "test2";
    private String table = "user";
    private String field = "account,password,pet_name,sex,hobby,address,head_img";
    
    public int insert(UserModel um) {
        StringBuffer sql = new StringBuffer("insert into ");
        sql.append(schema).append(".").append(table);
        sql.append("(").append(field).append(")");
        sql.append("value(?");
        int len = field.split(",").length;
        for (int i = 1; i < len; i++) {
            sql.append(",?");
        }
        sql.append(")");
        
        int result = -1;
        PreparedStatement ps = null;
        Connection con = JDBCUtil.getConnection();
        try {
            ps = con.prepareStatement(sql.toString());
            ps.setString(1, um.getAccount());
            ps.setString(2, um.getPassword());
            ps.setString(3, um.getPetName());
            ps.setString(4, um.getSex());
            ps.setString(5, um.getHobby());
            ps.setString(6, um.getAddress());
            ps.setString(7, um.getHeadImg());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.close(con, ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public int updateByAccount(UserModel um) {
        StringBuffer sql = new StringBuffer("update ");
        sql.append(schema).append(".").append(table);
        sql.append(" set id=id ");
        
        List<Object> list = new ArrayList<>();
        String password = um.getPassword();
        if (password != null) {
            sql.append(",password=?");
            list.add(password);
        }
        String petName = um.getPetName();
        if (petName != null) {
            sql.append(",pet_name=?");
            list.add(petName);
        }
        String sex = um.getSex();
        if (sex != null) {
            sql.append(",sex=?");
            list.add(sex);
        }
        String hobby = um.getHobby();
        if (hobby != null) {
            sql.append(",hobby=?");
            list.add(hobby);
        }
        String address = um.getAddress();
        if (address != null) {
            sql.append(",address=?");
            list.add(address);
        }
        String headImg = um.getHeadImg();
        if (headImg != null) {
            sql.append(",head_img=?");
            list.add(headImg);
        }
        sql.append(" where account=?");
        list.add(um.getAccount());
        
        int result = -1;
        PreparedStatement ps = null;
        Connection con = JDBCUtil.getConnection();
        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 1; i <= list.size(); i++) {
                ps.setObject(i, list.get(i - 1));
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.close(con, ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public int deleteByAccount(UserModel um) {
        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where account=?");
        
        int result = -1;
        PreparedStatement ps = null;
        Connection con = JDBCUtil.getConnection();
        try {
            ps = con.prepareStatement(sql.toString());
            ps.setString(1, um.getAccount());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.close(con, ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public int deleteAllByAccount(List<UserModel> userList) {
        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where account in(?");
        List<String> list = new ArrayList<>();
        list.add(userList.get(0).getAccount());
        for (int i = 1; i < userList.size(); i++) {
            sql.append(",?");
            list.add(userList.get(i).getAccount());
        }
        sql.append(")");
        
        int result = -1;
        PreparedStatement ps = null;
        Connection con = JDBCUtil.getConnection();
        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 0; i < list.size(); i++) {
                ps.setObject(i + 1, list.get(i));
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.close(con, ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public List<UserModel> selectList(UserModel um) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(field);
        sql.append(" from ").append(schema).append(".").append(table);
        sql.append(" where 1=1");
        List<Object> list = new ArrayList<>();
        appedWhere(list, sql, um);// 拼接查询条件
        
        String orderBy = um.getOrderBy();
        if (!FormatEmpty.isEmpty(orderBy)) {
            sql.append(" order by ").append(orderBy);
        }
        
        if (um.isPageOn()) {
            sql.append(" limit ?,?");
            list.add(um.getRowStart());
            list.add(um.getPageLimit());
        }
        
        List<UserModel> userList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = JDBCUtil.getConnection();
        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 0; i < list.size(); i++) {
                ps.setObject(i + 1, list.get(i));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel u = new UserModel();
                u.setId(rs.getInt("id"));
                u.setAccount(rs.getString("account"));
                u.setPassword(rs.getString("password"));
                u.setPetName(rs.getString("pet_name"));
                u.setSex(rs.getString("sex"));
                u.setHobby(rs.getString("hobby"));
                u.setAddress(rs.getString("address"));
                u.setHeadImg(rs.getString("head_img"));
                userList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.close(con, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }
    
    public int getCount(UserModel um) {
        StringBuffer sql = new StringBuffer("select count(1) from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where 1=1");
        List<Object> list = new ArrayList<>();
        if (um != null) {
            appedWhere(list, sql, um);
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        Connection con = JDBCUtil.getConnection();
        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 0; i < list.size(); i++) {
                ps.setObject(i + 1, list.get(i));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtil.close(con, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    private void appedWhere(List<Object> list, StringBuffer sql, UserModel um) {
        String account = um.getAccount();
        if (!FormatEmpty.isEmpty(account)) {
            sql.append(" and account = ?");
            list.add(account);
        }
        
        String password = um.getPassword();
        if (!FormatEmpty.isEmpty(password)) {
            sql.append(" and password = ?");
            list.add(password);
        }
        
        String petName = um.getPetName();
        if (!FormatEmpty.isEmpty(petName)) {
            sql.append(" and pet_name = ?");
            list.add(petName);
        }
        
        String sex = um.getSex();
        if (!FormatEmpty.isEmpty(sex)) {
            sql.append(" and sex = ?");
            list.add(sex);
        }
        
        String hobby = um.getHobby();
        if (!FormatEmpty.isEmpty(hobby)) {
            sql.append(" and sex like ?");
            list.add("%" + hobby + "%");
        }
        
        String address = um.getAddress();
        if (!FormatEmpty.isEmpty(address)) {
            sql.append(" and address = ?");
            list.add(address);
        }
    }
}
