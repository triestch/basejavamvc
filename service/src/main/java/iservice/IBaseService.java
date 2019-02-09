package iservice;

import java.util.List;
import java.util.Map;

public interface IBaseService<T> {
    List<T> queryEntityList(String sql, Object... params);

    T queryEntity(String sql, Object... params);

    List<Map<String, Object>> executeQuery(String sql, Object... params);

    boolean insertEntity(T entityClass);

    boolean updateEntity(T entityClass, String id);

    boolean fakedeleteEntity(String id);

    boolean deleteEntity(Object... params);
}
