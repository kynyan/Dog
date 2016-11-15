package dog.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SomeAspect {
    @After("execution(* dog.service.DogService2.createDog(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Seems like it's wotking");
    }
}
