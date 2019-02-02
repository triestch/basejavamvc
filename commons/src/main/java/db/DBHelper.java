package db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
            FileInputStream is = new FileInputStream("/dbcp.properties");
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("数据库配置文件读取失败", e);
        }

        try {
            ds = BasicDataSourceFactory.createDataSource(properties);
            qr=new QueryRunner();
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
            }
            finally {
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
            }
        }
    }

    public static void clearconFromThreadlocal() {
        //在用户事务处理完毕进行con.close()方法调用时，需要调用这个方法将线程池清空，不然下一次还是拿的该连接，进行操作就会挂
        Connection conn = getConn();
        try{
            if(conn!=null){
                conn.close();
            }
        }catch (Exception e) {
            throw new RuntimeException("关闭事务失败",e);
        }finally{
            //千万注意，解除当前线程上绑定的链接（从threadlocal容器中移除对应当前线程的链接）
            t.remove();
            conn=null;
        }
    }

    public static void closeAuto()
    {
        Connection conn = getConn();

        try {
            if(conn.getAutoCommit())
            {
                clearconFromThreadlocal();
            }
        } catch (SQLException e) {
            throw new RuntimeException("获取连接是否自动提交失败",e);
        }


    }

    /*查询实体列表*/
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        try {
            Connection conn = getConn();
            entityList = qr.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            throw new RuntimeException(getTableName(entityClass)+"查询实体列表失败",e);
        }

        closeAuto();
        return entityList;
    }


    /**
     * 查询实体
     */
    public static <T>  T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        try {
            Connection conn = getConn();
            entity = qr.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            throw new RuntimeException(getTableName(entityClass)+"查询实体失败",e);
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
            throw new RuntimeException("查询MapList失败",e);
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
            throw new RuntimeException("更新sql失败",e);
        }
        closeAuto();
        return rows;
    }

    /**
     * 插入实体
     */
    public static <T>  boolean insertEntity(Class<T> entityClass,String tbName, Map<String, Object> fieldMap) {
        if (fieldMap==null||fieldMap.isEmpty()) {
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
    public static <T>  boolean updateEntity(Class<T> entityClass,String tbName, String id, Map<String, Object> fieldMap) {
        if (fieldMap==null||fieldMap.isEmpty()) {
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

    public static <T> boolean fakedeleteEntity(Class<T> entityClass,String tbName,  String id)
    {
        String sql = "update " + tbName + " set isdel=0 where pkid='"+id+"'";

        return executeUpdate(sql) == 1;
    }

    /**
     * 删除实体
     */
    public static <T>  boolean deleteEntity(Class<T> entityClass,String tbName,  Object... params) {
        String sql = "delete from " + tbName + " where pkid=?";

        return executeUpdate(sql, params) == 1;
    }

    /**
     * 通过类名获取表名
     */
    private static String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }

}
