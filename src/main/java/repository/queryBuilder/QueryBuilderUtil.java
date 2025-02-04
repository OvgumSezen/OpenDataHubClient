package repository.queryBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryBuilderUtil {
    private static final Map<String, String> operatorMap = new HashMap<>();

    static{
        operatorMap.put("Equals", "eq");
        operatorMap.put("NotEquals", "neq");
        operatorMap.put("LessThan", "lt");
        operatorMap.put("LessThanEqualTo", "lteq");
        operatorMap.put("GreaterThan", "gt");
        operatorMap.put("GreaterThanEqualTo", "gteq");
        operatorMap.put("Regex", "re");
        operatorMap.put("InsensitiveRegex", "ire");
        operatorMap.put("NegatedRegex", "nre");
        operatorMap.put("NegatedInsensitiveRegex", "nire");
        operatorMap.put("BoundingBoxIntersects", "bbi");
        operatorMap.put("BoundingBoxContains", "bbc");
        operatorMap.put("DistanceLessThan", "dlt");
        operatorMap.put("In", "in");
        operatorMap.put("NotIn", "nin");
    }

    public static String extractOperator(String methodName) {
        for(String key: operatorMap.keySet()) {
            if(methodName.endsWith(key)){
                return operatorMap.get(key);
            }
        }
        return "";
    }

    public static String extractAlias(String methodName, String operator) {
        return methodName.replace("findBy", "").replace(operator, "").toLowerCase();
    }

    public static String extractValue(Object value) {
        StringBuilder sb = new StringBuilder();

        if (value instanceof List<?> values) {
            sb.append("(");
            for(int i = 0; i < values.size(); i++) {
                sb.append(extractValue(values.get(i)));
                if (i < values.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append(")");
        } else {
            sb.append(handleValue(value));
        }

        return sb.toString();
    }

    private static String handleValue(Object value) {
        if (value instanceof String) {
            return ((String) value).replace(",", "\\,").replace("\"", "\\\""); // escape special characters
        } else {
            return value.toString();
        }
    }
}
