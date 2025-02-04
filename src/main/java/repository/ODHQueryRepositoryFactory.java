package repository;

import java.lang.reflect.Proxy;

public class ODHQueryRepositoryFactory {
    public static <T> T create(Class<T> repositoryInterface) {
        if(!ODHRepository.class.isAssignableFrom(repositoryInterface)) {
            throw new IllegalArgumentException(repositoryInterface.getName() + " is not an ODHRepository");
        }

        return (T) Proxy.newProxyInstance(
                repositoryInterface.getClassLoader(),
                new Class<?>[]{repositoryInterface},
                new ODHRepositoryInvocationHandler()
        );
    }
}
