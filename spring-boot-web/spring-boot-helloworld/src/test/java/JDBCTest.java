import org.junit.Test;

import java.sql.*;

/**
 * Created by WCY on 2021/4/1
 */
public class JDBCTest {
    // java建立数据库连接
    @Test
    public void executeQuery() throws ClassNotFoundException, SQLException {
        // 重要: 开始工作之前, 确定maven依赖
        /*
            知识点: Class.forName()/new Object(), 都会将类加载到jvm中, 类加载会执行类中静态代码块
            执行Driver类静态代码块将new Driver()注册进DriverManager, DriverManager.getConnection()获取连接
            1 Class.forName("com.mysql.jdbc.Driver");
            2 new com.mysql.jdbc.Driver();
            3 DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            DriverManager: 驱动管理对象
            功能 1 注册驱动 2 获取数据库连接
            Connection: 数据库连接对象
            功能 1 获取sql执行对象 2 管理事务 开启事务, 提交事务, 回滚事务
            Statement: 执行sql对象
            功能 执行sql语句并返回结果
            ResultSet: 结果集对象
            next(): 游标向下一行
            getXxx(参数): 获取数据, Xxx代表数据类型
            参数:
            1 int 代表列编号, 从1开始
            2 String 代表列名称
            PreparedStatement: 执行sql对象
            1 sql注入问题: 在拼接sql时, 有一些的特殊关键字参与字符串拼接, 会造成安全问题
            2 使用sql注入问题: 使用PreparedStatement对象来解决
            预编译sql: 参数使用占位符(?)替代
            定义sql时使用占位符
            Connection.preparedStatement(sql);
            给?赋值
            setXxx(参数1, 参数2) 参数1: ?的位置, 从1开始 参数2: ?的值
            执行sql时, 接收返回结果, 不需要传递sql语句, 之前创建PreparedStatement(sql)已经传递过了

            注意: mysql数据库表主键设置自增, sql中才能使用null作为主键占位符
            后期都会使用PreparedStatement来完成增删改查操作
            1 可以防止sql注入
            2 效率更高
         */
        // 1 加载Mysql驱动, mysql驱动类加载到jvm, 告诉程序使用哪个数据库驱动jar
        Class.forName("com.mysql.jdbc.Driver");

        // 2 建立数据库连接
        String url = "jdbc:mysql:///db_spring_boot?characterEncoding=utf8&useSSL=false"; // jdbc:mysql:///表示默认配置, 即jdbc:mysql://localhost:3306/
        Connection connection = DriverManager.getConnection(url, "root", "root");

        // 关闭自动开启事务(JDBC默认开启事务)
        connection.setAutoCommit(false);

        // 3 定义sql
        String sql = "select * from book";
        //String sql = "update action set action_id = 1 where id = 111";
        //String sql = "delete from action where id = 111";

        // 4 创建执行sql语句对象
        Statement statement = connection.createStatement();

        // 5 执行sql语句得到结果集
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Object param = resultSet.getObject("id");
            System.out.println("param = " + param);
        }
        //boolean execute = statement.execute(sql); // true DQL(select)语句 false DML(insert/update/delete)语句
        //if (execute) {
        //    ResultSet resultSet = statement.getResultSet();
        //    while (resultSet.next()) {
        //        Object param = resultSet.getObject("id");
        //        System.out.println("param = " + param);
        //    }
        //} else {
        //    int updateCount = statement.getUpdateCount();
        //    System.out.println("updateCount = " + updateCount);
        //}

        /*
            statement.executeUpdate() 返回值是影响的行数, 可以通过影响的行数判断DML语句是否执行成功
            例: 数据库数据 data 100, 200
            set data = 100 影响一行
            set data = 150 影响两行
         */

        // 提交事务, JDBC默认自动开启事务, 不能手动提交事务, 需要先关闭自动开启事务
        connection.commit();

        // 6 释放资源
        statement.close();
        connection.close();
    }

    @Test
    public void executeUpdate() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql:///db_spring_boot?characterEncoding=utf8&useSSL=false";
            connection = DriverManager.getConnection(url, "root", "root");
            statement = connection.createStatement();
            // 注意: mysql数据库表主键设置自增, 新增sql中才能使用null作为主键占位符
            String sql = "insert into book values (null, '老人与海', '外国名著', 99)";
            //String sql = "update stu set `name` = 'namespace' where id = 38";
            //String sql = "delete from stu where id = 38";
            int count = statement.executeUpdate(sql);
            System.out.println("count = " + count);
            if (count > 0) {
                System.out.println("添加/修改/删除成功!");
            } else {
                System.out.println("添加/修改/删除失败!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Test
    public void executeQueryFormat() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql:///db_spring_boot?characterEncoding=utf8&useSSL=false";
            connection = DriverManager.getConnection(url, "root", "root");
            statement = connection.createStatement();

            String sql = "select * from book";
            resultSet = statement.executeQuery(sql);

            // 获取数据值
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount(); // 获取总列数
            System.out.printf("总列数 = %d\n", columnCount);
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i); // 获取第i列列名
                System.out.printf("第%d列列名 = %s\n", i, columnName);
            }

            int line = 1;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String course = resultSet.getString("type");
                double score = resultSet.getDouble("price");
                System.out.printf("第%d行: id: %d - name: %s - type: %s - price: %.1f\n", line++, id, name, course, score);
                System.out.println("----------------");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Test
    public void preparedExecuteQuery() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql:///db_spring_boot?characterEncoding=utf-8&useSSL=false";
            connection = DriverManager.getConnection(url, "root", "root");
            String sql = "select * from book where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String course = resultSet.getString("type");
                double score = resultSet.getDouble("price");
                System.out.printf("id: %d - name: %s - type: %s - price: %.1f\n", id, name, course, score);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

}
