package dog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

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

}
