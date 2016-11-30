package dog.dao;

import dog.model.Dog;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@Test
@ContextConfiguration(locations = "classpath:app-context.xml")
@Rollback(true)
public class DaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private DogHibernateDaoImpl dogHibernateDao;

    @Test
    public void mustCreateNewDog() {
        //add random dog
        Dog addedDog = dogHibernateDao.addDog(Dog.random());
        flushAndClearSession(dogHibernateDao.getSessionFactory());
        //fetch added dog
        Dog fetchedDog = dogHibernateDao.getDogById(addedDog.getId());
        assertReflectionEquals(addedDog, fetchedDog);
    }

    @Test
    public void mustUpdateDog() {
        //add random dog
        Dog addedDog = dogHibernateDao.addDog(Dog.random());
        flushAndClearSession(dogHibernateDao.getSessionFactory());
        //update created dog
        Dog newDog = Dog.random();
        newDog.setId(addedDog.getId());
        dogHibernateDao.updateDog(newDog);
        flushAndClearSession(dogHibernateDao.getSessionFactory());
        Dog fetchedDog = dogHibernateDao.getDogById(addedDog.getId());
        assertReflectionEquals(newDog, fetchedDog);
    }

    @Test
    public void mustDeleteDog() {
        //add random dog and remove it
        Dog addedDog = dogHibernateDao.addDog(Dog.random());
        dogHibernateDao.deleteDog(addedDog.getId());
        flushAndClearSession(dogHibernateDao.getSessionFactory());
        //try to fetch removed dog
        Dog fetchedDog = dogHibernateDao.getDogById(addedDog.getId());
        assertEquals(null, fetchedDog);
    }

    private void flushAndClearSession(SessionFactory sessionFactory) {
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }
}
