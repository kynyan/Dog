package dog.service;


import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Test
@ContextConfiguration(locations = "classpath:app-context.xml")
@Rollback(true)
public class ServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    DogService dogService;

    @Autowired
    IDogService dogService2;

    @Test
    public void dogServiceWasCreatedWithCglib() {
        System.out.println(dogService.getClass().getName());
        Assert.assertTrue(DogService.class.isInstance(dogService));
        Assert.assertTrue(dogService.getClass().getCanonicalName().contains("CGLIB"));
        Assert.assertFalse(dogService.getClass().getCanonicalName().contains("Proxy"));
    }

    @Test
    public void dogServiceWasCreatedWithJdk() {
        System.out.println(dogService2.getClass().getName());
        Assert.assertTrue(IDogService.class.isInstance(dogService2));
        Assert.assertFalse(dogService2.getClass().getCanonicalName().contains("CGLIB"));
        Assert.assertTrue(dogService2.getClass().getCanonicalName().contains("Proxy"));
    }

    @Test
    public void jdkProxyMustCreateDog() {
        IDogService dogService3 = new DogService3();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (args != null) {
                    System.out.println("Method called: " + method.getName());
                    System.out.println("Dog created: "+ Arrays.toString(args));
                }
                return method.invoke(dogService3, args);
            }
        };
        IDogService proxy = (IDogService) Proxy.newProxyInstance(dogService3.getClass().getClassLoader(),
                new Class[]{IDogService.class}, invocationHandler);

        Dog expectedDog = Dog.random();
        Dog createdDog = proxy.createDog(expectedDog);
        Assert.assertEquals(expectedDog, createdDog);
    }

    @Test
    public void cglibProxyMustCreateDog() {
        IDogService dogService3 = new DogService3();
        IDogService proxy = (IDogService) Enhancer.create(IDogService.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                return method.invoke(dogService3, args);
            }
        });
        Dog expectedDog = Dog.random();
        Dog createdDog = proxy.createDog(expectedDog);
        Assert.assertEquals(expectedDog, createdDog);
    }

}
