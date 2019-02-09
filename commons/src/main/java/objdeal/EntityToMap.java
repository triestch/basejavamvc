package objdeal;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityToMap {
    public static Map<String, Object> toMap(Object object) {
        Map<String, Object> map = new HashMap();
        for (Field field : object.getClass().getDeclaredFields()){
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(object);
                if(field.getName()!="serialVersionUID") {
                    map.put(field.getName(), o);
                    field.setAccessible(flag);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
