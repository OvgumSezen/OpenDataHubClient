package queryGenerator.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ODHQueryGeneratorInvocationHandler implements InvocationHandler {
    private final ODHQueryGeneratorInvocationHelper queryStringGenerator;

    public ODHQueryGeneratorInvocationHandler() {
        this.queryStringGenerator = new ODHQueryGeneratorInvocationHelper();  // Instantiate the query generator
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String methodName = method.getName();
        String query = queryStringGenerator.generateQuery(methodName,  args);
        System.out.println("Generated query: " + query);
        return query;
    }
}
