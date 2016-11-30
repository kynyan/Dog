package dog.dao;

import dog.model.Dog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.unitils.reflectionassert.util.HibernateUtil;

import javax.persistence.*;
import javax.transaction.UserTransaction;

import java.util.Properties;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@Test
@ContextConfiguration(locations = {"classpath:app-context.xml", "classpath:Dog.hbm.xml"})//, "classpath:hsqldb-config.xml", "classpath:application.properties", "classpath:Dog.hbm.xml"})
public class DaoTest2 {//extends AbstractTransactionalTestNGSpringContextTests {
//    @PersistenceContext
//    EntityManager em;

    @Test
    public void mustCreateNewDog() {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        DogDaoSessionImpl dogHibernateDao = new DogDaoSessionImpl(session);
        Transaction transaction = session.getTransaction();
        transaction.begin();
        //add random dog
        Dog addedDog = dogHibernateDao.addDog(Dog.random());
        flushAndClearSession(session);
        //fetch added dog
        Dog fetchedDog = dogHibernateDao.getDogById(addedDog.getId());
        assertReflectionEquals(addedDog, fetchedDog);
        transaction.rollback();
    }

    private void flushAndClearSession(Session session) {
        session.flush();
        session.clear();
    }

    protected SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        //log settings
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        //driver settings
        properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
        properties.put("hibernate.connection.url", "jdbc:hsqldb:mem:test");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");

        return new Configuration().configure("hsqldb-config.xml")
                .addResource("resources/Dog.hbm.xml")
//                .addProperties(properties)
//                .addResource("Dog.hbm.xml")
//                .addAnnotatedClass(Dog.class)
                .buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(properties)
                                .build()
                );
    }


}
