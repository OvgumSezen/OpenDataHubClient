package repository;

import repository.queryBuilder.ODHQueryBuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ODHRepositoryInvocationHandler implements InvocationHandler {
    private final ODHQueryBuilder queryStringGenerator;

    public ODHRepositoryInvocationHandler() {
        this.queryStringGenerator = new ODHQueryBuilder();  // Instantiate the query generator
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String methodName = method.getName();
        String query = queryStringGenerator.generateQuery(methodName,  args);
        System.out.println("Generated query: " + query);
        return query;
    }
}
