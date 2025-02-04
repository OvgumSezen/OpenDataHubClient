package queryGenerator;

import queryGenerator.impl.ODHQueryGeneratorInvocationHandler;

import java.lang.reflect.Proxy;

public class ODHQueryGeneratorFactory {
    public static <T> T create(Class<T> repositoryInterface) {
        if(!ODHQueryGenerator.class.isAssignableFrom(repositoryInterface)) {
            throw new IllegalArgumentException(repositoryInterface.getName() + " is not an ODHQueryGenerator");
        }

        return (T) Proxy.newProxyInstance(
                repositoryInterface.getClassLoader(),
                new Class<?>[]{repositoryInterface},
                new ODHQueryGeneratorInvocationHandler()
        );
    }
}
