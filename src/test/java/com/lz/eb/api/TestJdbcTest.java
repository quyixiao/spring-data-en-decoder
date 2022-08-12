package com.lz.eb.api;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestJdbcTest {

    @Test
    public void jdbcQuery() {
        Connection conn = null;
        PreparedStatement pstemt = null;
        try {
            //注册加载jdbc驱动
            Class.forName("com.mysql.jdbc.Driver");
            //打开连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lz_test?characterEncoding=utf-8", "ldd_biz", "123456");
            //创建执行对象
            String sql = "select * from lt_stage_borrow sb left join lt_user_phone up on sb.unique_code = up.unique_code ";
            pstemt = conn.prepareStatement(sql);
            //执行sql语句
            ResultSet rs = pstemt.executeQuery();
            System.out.println("---------");
            //展开结果集
            while (rs.next()) {
                System.out.println(rs.getString("unique_code"));
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
