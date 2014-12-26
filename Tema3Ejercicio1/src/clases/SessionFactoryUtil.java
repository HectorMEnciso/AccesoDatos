package clases;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistryBuilder SSR;
	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration().configure();
		SSR= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(SSR.build());
	return sessionFactory;
	}
}
