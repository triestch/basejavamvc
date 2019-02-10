package db;

import comuse.PageBean;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBHelper {
    private static Properties properties = new Properties();
    private static BasicDataSource ds;// 单例的池
    private static QueryRunner qr;
    private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();

    static {

        try {
            //FileInputStream is = new FileInputStream("/dbcp.properties");
            InputStream is = DBHelper.class.getClassLoader().getResourceAsStream("dbcp.properties");
            properties.load(is);

            //properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            //properties.setProperty("url","jdbc:mysql://127.0.0.1:3306/cdagency?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
            //properties.setProperty("username","root");
            //properties.setProperty("password","123456!@#");
            //properties.setProperty("maxActive","30");
            //properties.setProperty("maxIdle","10");
            //properties.setProperty("maxWait","1000");
            //properties.setProperty("removeAbandoned","false");
            //properties.setProperty("removeAbandonedTimeout","120");
            //properties.setProperty("testOnBorrow","true");
            //properties.setProperty("logAbandoned","true");

        } catch (IOException e) {
            throw new RuntimeException("数据库配置文件读取失败", e);
        }

        try {
            ds = BasicDataSourceFactory.createDataSource(properties);
            qr = new QueryRunner();
        } catch (Exception e) {
            throw new RuntimeException("数据库连接池创建失败", e);
        }
    }


    public static Connection getConn() {
        Connection conn = t.get();
        if (conn == null) {
            //如果没有连接对象为空我们就去获得一个然后存到t中
            try {
                conn = ds.getConnection();
            } catch (Exception e) {
                throw new RuntimeException("获取数据库连接失败", e);
            } finally {
                t.set(conn);
            }
        }
        return conn;
    }


    public static void beginTransaction() {
        Connection conn = getConn();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException("开始事务失败", e);
            } finally {
                //??
                t.set(conn);
            }
        }
    }

    public static void commitTransaction() {
        Connection conn = getConn();
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("提交事务失败", e);
            } finally {
                t.remove();
                conn = null;
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        Connection conn = getConn();
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("回滚事务失败", e);
            } finally {
                t.remove();
                conn = null;
            }
        }
    }

    public static void clearconFromThreadlocal() {
        //在用户事务处理完毕进行con.close()方法调用时，需要调用这个方法将线程池清空，不然下一次还是拿的该连接，进行操作就会挂
        Connection conn = getConn();
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("关闭事务失败", e);
        } finally {
            //千万注意，解除当前线程上绑定的链接（从threadlocal容器中移除对应当前线程的链接）
            t.remove();
            conn = null;
        }
    }

    public static void closeAuto() {
        /*Connection conn = getConn();

        try {
            if(conn.getAutoCommit())
            {
                clearconFromThreadlocal();
            }
        } catch (SQLException e) {
            throw new RuntimeException("获取连接是否自动提交失败",e);
        }*/


    }

    /*查询实体列表*/
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        try {
            Connection conn = getConn();
            entityList = qr.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            throw new RuntimeException(getTableName(entityClass) + "查询实体列表失败", e);
        }

        closeAuto();
        return entityList;
    }


    /**
     * 查询实体
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        try {
            Connection conn = getConn();
            entity = qr.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            throw new RuntimeException(getTableName(entityClass) + "查询实体失败", e);
        }
        closeAuto();
        return entity;
    }

    /**
     * 执行查询语句
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result;
        try {
            Connection conn = getConn();
            result = qr.query(conn, sql, new MapListHandler(), params);
        } catch (Exception e) {
            throw new RuntimeException("查询MapList失败", e);
        }
        closeAuto();
        return result;
    }

    /**
     * 执行更新语句（update、insert、delete）
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            Connection conn = getConn();
            rows = qr.update(conn, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException("更新sql失败", e);
        }
        closeAuto();
        return rows;
    }

    /**
     * 插入实体
     */
    public static <T> boolean insertEntity(String tbName, Map<String, Object> fieldMap) {
        if (fieldMap == null || fieldMap.isEmpty()) {
            return false;
        }

        String sql = "insert into " + tbName;
        StringBuilder columns = new StringBuilder(" (");
        StringBuilder values = new StringBuilder(" (");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ") ");
        values.replace(values.lastIndexOf(", "), values.length(), ") ");
        sql += columns + " values " + values;

        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 更新实体
     */
    public static <T> boolean updateEntity(String tbName, String id, Map<String, Object> fieldMap) {
        if (fieldMap == null || fieldMap.isEmpty()) {
            return false;
        }

        String sql = "update " + tbName + " set ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("=?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " where pkid=?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;
    }

    public static <T> boolean fakedeleteEntity(String tbName, String id) {
        String sql = "update " + tbName + " set isdel=0 where pkid='" + id + "'";

        return executeUpdate(sql) == 1;
    }

    /**
     * 删除实体
     */
    public static <T> boolean deleteEntity(String tbName, Object... params) {
        String sql = "delete from " + tbName + " where pkid=?";

        return executeUpdate(sql, params) == 1;
    }


    public static <T> void queryPageProcedure(PageBean<T> pageBean,Class<T> entityClass)
    {
        boolean bl = false;
        Connection conn = getConn();
        CallableStatement cs=null;
        String sql="call exec_pagination(?,?,?,?,?,?,?)";
        try {
            cs=conn.prepareCall(sql);

            cs.setString("_tablename",pageBean.getTbName());
            cs.setString("_table_key",pageBean.getTbId());
            cs.setString("_fields",pageBean.getTbColumn());
            cs.setString("_where",pageBean.getTbWhere());
            cs.setString("_order",pageBean.getTbOrder());
            cs.setInt("_pagecurrent",pageBean.getPc());
            cs.setInt("_pagesize",pageBean.getPs());

            cs.execute();

            ResultSet rs1 =cs.getResultSet();

            pageBean.setBeanList((List<T>) resultToList(rs1,entityClass));



            bl=cs.getMoreResults();
            if(bl) {
                ResultSet rs2 =cs.getResultSet();

                while(rs2.next()) {
                    pageBean.setTr(rs2.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("分页查询失败",e);
        }
    }

    //resultToList方法
    public static <T> List<T> resultToList(ResultSet resultSet, Class<T> clazz) {
        //创建一个 T 类型的数组
        List<T> list = new ArrayList<>();
        try {

            //获取resultSet 的列的信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            //遍历resultSet
            while (resultSet.next()) {
                //通过反射获取对象的实例
                T t = clazz.getConstructor().newInstance();

                //遍历每一列
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    //获取列的名字
                    String fName = metaData.getColumnLabel(i + 1);
                    //因为列的名字和我们EMP中的属性名是一样的，所以通过列的名字获得其EMP中属性
                    Field field = clazz.getDeclaredField(fName.toLowerCase());
                    //因为属性是私有的，所有获得其对应的set 方法。set+属性名首字母大写+其他小写
                    String setName = "set" + fName.toUpperCase().substring(0, 1) + fName.substring(1).toLowerCase();
                    //因为属性的类型和set方法的参数类型一致，所以可以获得set方法
                    Method setMethod = clazz.getMethod(setName, field.getType());
                    //执行set方法，把resultSet中的值传入emp中，  再继续循环传值
                    setMethod.invoke(t, resultSet.getObject(fName));
                }
                //把赋值后的对象 加入到list集合中
                list.add(t);
            }

        } catch (Exception e) {
            throw new RuntimeException("查询数据持久化失败",e);
        }
        // 返回list
        return list;
    }

    /**
     * 通过类名获取表名
     */
    private static String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }

}
