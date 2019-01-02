package com.userMis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.userMis.model.AddrModel;
import com.userMis.util.FormatEmpty;
import com.userMis.util.JDBCUtil;

public class AddrDao {
    private String schema = "test2";
    private String table = "addr";
    private String table2 = "user";
    private String table3 = "dict";
    private String field = "account,name,phone,addr1_code,addr2_code,addr3_code,addr4";
    
    public int insert(AddrModel am) {
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
            ps.setString(1, am.getAccount());
            ps.setString(2, am.getName());
            ps.setString(3, am.getPhone());
            ps.setString(4, am.getAddr1Code());
            ps.setString(5, am.getAddr2Code());
            ps.setString(6, am.getAddr3Code());
            ps.setString(7, am.getAddr4());
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
    
    public int updateById(AddrModel am) {
        StringBuffer sql = new StringBuffer("update ");
        sql.append(schema).append(".").append(table);
        sql.append(" set id=id ");
        
        List<Object> list = new ArrayList<>();
        String name = am.getName();
        if (name != null) {
            sql.append(",name=?");
            list.add(name);
        }
        String phone = am.getPhone();
        if (phone != null) {
            sql.append(",phone=?");
            list.add(phone);
        }
        String addr1Code = am.getAddr1Code();
        if (addr1Code != null) {
            sql.append(",addr1_code=?");
            list.add(addr1Code);
        }
        String addr2Code = am.getAddr2Code();
        if (addr2Code != null) {
            sql.append(",addr2_code=?");
            list.add(addr2Code);
        }
        String addr3Code = am.getAddr3Code();
        if (addr3Code != null) {
            sql.append(",addr3_code=?");
            list.add(addr3Code);
        }
        String addr4 = am.getAddr4();
        if (addr4 != null) {
            sql.append(",addr4=?");
            list.add(addr4);
        }
        sql.append(" where id=?");
        list.add(am.getId());
        
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
    
    public int deleteById(AddrModel am) {
        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where id=?");
        
        int result = -1;
        PreparedStatement ps = null;
        Connection con = JDBCUtil.getConnection();
        try {
            ps = con.prepareStatement(sql.toString());
            ps.setInt(1, am.getId());
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
    
    public int deleteAllById(List<AddrModel> addrList) {
        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where id in(?");
        List<Object> list = new ArrayList<>();
        list.add(addrList.get(0).getId());
        for (int i = 1; i < addrList.size(); i++) {
            sql.append(",?");
            list.add(addrList.get(i).getId());
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
    
    public List<AddrModel> selectList(AddrModel am) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(field);
        addField(sql);
        sql.append(" from ").append(schema).append(".").append(table).append(" as a");
        sql.append(" where 1=1");
        List<Object> list = new ArrayList<>();
        appedWhere(list, sql, am);// 拼接查询条件
        
        String orderBy = am.getOrderBy();
        if (!FormatEmpty.isEmpty(orderBy)) {
            sql.append(" order by ").append(orderBy);
        }
        
        if (am.isPageOn()) {
            sql.append(" limit ?,?");
            list.add(am.getRowStart());
            list.add(am.getPageLimit());
        }
        
        List<AddrModel> addrList = new ArrayList<>();
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
                AddrModel u = new AddrModel();
                u.setId(rs.getInt("id"));
                u.setAccount(rs.getString("account"));
                u.setName(rs.getString("name"));
                u.setPhone(rs.getString("phone"));
                u.setAddr1Code(rs.getString("addr1_code"));
                u.setAddr2Code(rs.getString("addr2_code"));
                u.setAddr3Code(rs.getString("addr3_code"));
                u.setAddr4(rs.getString("addr4"));
                u.setPetName(rs.getString("pet_name"));
                u.setAddr1(rs.getString("addr1"));
                u.setAddr2(rs.getString("addr2"));
                u.setAddr3(rs.getString("addr3"));
                addrList.add(u);
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
        return addrList;
    }

    private void addField(StringBuffer sql) {
        sql.append(",(select pet_name from ").append(schema).append(".").append(table2);
        sql.append(" where a.account = account) as pet_name");
        sql.append(",(select name from ").append(schema).append(".").append(table3);
        sql.append(" where a.addr1_code = code) as addr1");
        sql.append(",(select name from ").append(schema).append(".").append(table3);
        sql.append(" where a.addr2_code = code) as addr2");
        sql.append(",(select name from ").append(schema).append(".").append(table3);
        sql.append(" where a.addr3_code = code) as addr3");
    }
    
    public int getCount(AddrModel am) {
        StringBuffer sql = new StringBuffer("select count(1) from ");
        sql.append(schema).append(".").append(table);
        sql.append(" where 1=1");
        List<Object> list = new ArrayList<>();
        if (am != null) {
            appedWhere(list, sql, am);
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
    
    private void appedWhere(List<Object> list, StringBuffer sql, AddrModel am) {
        Integer id = am.getId();
        if (id != null) {
            sql.append(" and id = ?");
            list.add(id);
        }
        
        String account = am.getAccount();
        if (!FormatEmpty.isEmpty(account)) {
            sql.append(" and account = ?");
            list.add(account);
        }
        
        String name = am.getName();
        if (!FormatEmpty.isEmpty(name)) {
            sql.append(" and name like ?");
            list.add(name);
        }
        
        String phone = am.getPhone();
        if (!FormatEmpty.isEmpty(phone)) {
            sql.append(" and phone like ?");
            list.add(phone);
        }
        
        String addr1Code = am.getAddr1Code();
        if (!FormatEmpty.isEmpty(addr1Code)) {
            sql.append(" and addr1_code = ?");
            list.add(addr1Code);
        }
        String addr2Code = am.getAddr2Code();
        if (!FormatEmpty.isEmpty(addr2Code)) {
            sql.append(" and addr2_code = ?");
            list.add(addr2Code);
        }
        String addr3Code = am.getAddr3Code();
        if (!FormatEmpty.isEmpty(addr3Code)) {
            sql.append(" and addr1_code = ?");
            list.add(addr3Code);
        }
        
        String addr4 = am.getAddr4();
        if (!FormatEmpty.isEmpty(addr4)) {
            sql.append(" and addr4 like ?");
            list.add(addr4);
        }
    }
}
