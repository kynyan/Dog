package dog.dao;

import dog.model.Dog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@Test
@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class DaoTest2 {

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

        return new Configuration()
                .addResource("Dog.hbm.xml")
                .addProperties(properties)
                .buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(properties)
                                .build()
                );
    }


}
