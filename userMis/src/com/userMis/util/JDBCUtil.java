package com.userMis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * MySQL JDBC工具类，
 * 使用前请先调用loadProfile(String profileUrl)方法，加载配置文件，否则将使用默认配置文件
 */
public class JDBCUtil {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static ResourceBundle bundle = ResourceBundle.getBundle("com.userMis.util.default");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");
    
    private static LinkedList<Connection> conList = new LinkedList<>();
    
    static {
        try {
            Class.forName(DRIVER);
            init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static void init() {
        for (int i = 0; i < 5; i++) {
            conList.add(getCon());
        }
    }
    
    public static Connection getConnection() {
        if (conList.isEmpty()) {
            return getCon();
        } else {
            return conList.pop();
        }
    }
    
    /**
     * 加载配置文件，文件内需有url(数据库url)、user(用户名)、password(用户密码)
     * @param profileUrl 配置文件路径
     */
    public static void loadProfile(String profileUrl) {
        bundle = ResourceBundle.getBundle(profileUrl);
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");
    }
    
    /**
     * 获取连接对象
     * @return Connection
     * @throws SQLException
     */
    private static synchronized Connection getCon() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 关闭连接
     * @param ps PreparedStatement
     * @param rs ResultSet
     * @throws SQLException
     */
    public static void close(PreparedStatement ps, ResultSet rs) throws SQLException {
        close(null, ps, rs);
    }
    
    public static void close(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (ps != null) {
            ps.close();
            ps = null;
        }
        if (con != null) {
            conList.add(con);
        }
    }
    
    public static void close(Connection con, PreparedStatement ps) throws SQLException {
        close(con, ps, null);
    }
    
    /**
     * 关闭连接
     * @param ps PreparedStatement
     * @throws SQLException
     */
    public static void close(PreparedStatement ps) throws SQLException {
        close(null, ps, null);
    }
    
    /**
     * 关闭连接
     * @param con Connection
     * @throws SQLException
     */
    public static void close(Connection con) throws SQLException {
        close(con, null, null);
    }
    
    private JDBCUtil() {}
}
