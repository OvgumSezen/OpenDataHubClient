package repository.queryBuilder;

public class ODHQueryBuilder {
    public String generateQuery(String methodName, Object[] vals) {
        return generateQueryString(methodName, vals[0]);
    }

    private String generateQueryString(String methodName, Object val) {
        StringBuilder queryBuilder = new StringBuilder();

        if(!methodName.startsWith("findBy")) {
            return null;
        }

        String operator = QueryBuilderUtil.extractOperator(methodName);
        String alias = QueryBuilderUtil.extractAlias(methodName, operator);
        String value = QueryBuilderUtil.extractValue(val);

        queryBuilder.append(alias).append(".").append(operator).append(".").append(value);
        return queryBuilder.toString();
    }
}
