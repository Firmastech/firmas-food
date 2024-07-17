package danieldjgomes.larica.util;

import java.util.HashMap;
import java.util.Map;

public class DynamicLog {
    private static final InheritableThreadLocal<Map<String, Object>> CONTEXT = new InheritableThreadLocal<>(){
        protected Map<String, Object> childValue(Map<String, Object> parentValue) {
            return parentValue == null ? null : new HashMap<>(parentValue);
        }
    };

    private DynamicLog() {
    }

    public static void put(String key, Object value) {
        Map<String, Object> thredmap = (Map)CONTEXT.get();
        if (thredmap == null) {
            CONTEXT.set(new HashMap<>(Map.of()));
        }
        ((Map)CONTEXT.get()).put(key, value);
    }

    public static Map<String, Object> get() {
        Map<String, Object> thredmap = (Map)CONTEXT.get();
        return (Map)( thredmap == null? new HashMap(Map.of()) : thredmap);
    }

    public static void destroy() {
        CONTEXT.remove();
    }
}
