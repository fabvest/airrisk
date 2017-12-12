package utils;

import model.Report;
import model.Substance;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory(
                    new StandardServiceRegistryBuilder().build() );
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
//    private static final SessionFactory ourSessionFactory;
//    private static final ServiceRegistry serviceRegistry;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure()
//                    .addAnnotatedClass(Report.class)
//                    .addAnnotatedClass(Substance.class);
//
//
//            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
////            StandardServiceRegistry standardRegistry =
////                    new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
////            Metadata metaData =
////                    new MetadataSources(standardRegistry).getMetadataBuilder().build();
////            ourSessionFactory = metaData.getSessionFactoryBuilder().build();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }
}
