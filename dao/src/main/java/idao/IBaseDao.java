package idao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
    List<T> queryEntityList(String sql, Object... params);

    T queryEntity(String sql, Object... params);

    List<Map<String, Object>> executeQuery(String sql, Object... params);

    boolean insertEntity(Map<String, Object> fieldMap);

    boolean updateEntity(String id, Map<String, Object> fieldMap);

    boolean fakedeleteEntity(String id);

    boolean deleteEntity(Object... params);
}
