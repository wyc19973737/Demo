package com.userMis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.userMis.model.DictModel;
import com.userMis.util.FormatEmpty;
import com.userMis.util.JDBCUtil;

public class DictDao {
    private String schema = "test2";
    private String table = "dict";
    private String field = "code,name,parent_code";
    
    public int insert(DictModel dm) {
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
            ps.setString(1, dm.getCode());
            ps.setString(2, dm.getName());
            ps.setString(3, dm.getParentCode());
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
    
    public int updateByAccount(DictModel dm) {
        StringBuffer sql = new StringBuffer("update ");
        sql.append(schema).append(".").append(table);
        sql.append(" set id=id ");
        
        List<Object> list = new ArrayList<>();
        String name = dm.getName();
        if (name != null) {
            sql.append(",name=?");
            list.add(name);
        }
        String parentCode = dm.getParentCode();
        if (parentCode != null) {
            sql.append(",parent_code=?");
            list.add(parentCode);
        }
        sql.append(" where code=?");
        list.add(dm.getCode());
        
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
    
    public int deleteByAccount(DictModel dm) {
        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where code=?");
        
        int result = -1;
        PreparedStatement ps = null;
        Connection con = JDBCUtil.getConnection();
        try {
            ps = con.prepareStatement(sql.toString());
            ps.setString(1, dm.getCode());
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
    
    public int deleteAllByAccount(List<DictModel> dictList) {
        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where code in(?");
        List<String> list = new ArrayList<>();
        list.add(dictList.get(0).getCode());
        for (int i = 1; i < dictList.size(); i++) {
            sql.append(",?");
            list.add(dictList.get(i).getCode());
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
    
    public List<DictModel> selectList(DictModel dm) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(field);
        sql.append(" from ").append(schema).append(".").append(table);
        sql.append(" where 1=1");
        List<Object> list = new ArrayList<>();
        appedWhere(list, sql, dm);// 拼接查询条件
        
        String orderBy = dm.getOrderBy();
        if (!FormatEmpty.isEmpty(orderBy)) {
            sql.append(" order by ").append(orderBy);
        }
        
        if (dm.isPageOn()) {
            sql.append(" limit ?,?");
            list.add(dm.getRowStart());
            list.add(dm.getPageLimit());
        }
        
        List<DictModel> userList = new ArrayList<>();
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
                DictModel d = new DictModel();
                d.setId(rs.getInt("id"));
                d.setCode(rs.getString("code"));
                d.setName(rs.getString("name"));
                d.setParentCode(rs.getString("parent_code"));
                userList.add(d);
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
    
    public int getCount(DictModel dm) {
        StringBuffer sql = new StringBuffer("select count(1) from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where 1=1");
        List<Object> list = new ArrayList<>();
        if (dm != null) {
            appedWhere(list, sql, dm);
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
    
    private void appedWhere(List<Object> list, StringBuffer sql, DictModel dm) {
        String code = dm.getCode();
        if (!FormatEmpty.isEmpty(code)) {
            sql.append(" and code = ?");
            list.add(code);
        }
        
        String name = dm.getName();
        if (!FormatEmpty.isEmpty(name)) {
            sql.append(" and name like ?");
            list.add(name);
        }
        
        String parentCode = dm.getParentCode();
        if (!FormatEmpty.isEmpty(parentCode)) {
            sql.append(" and parent_code = ?");
            list.add(parentCode);
        }
    }
}
