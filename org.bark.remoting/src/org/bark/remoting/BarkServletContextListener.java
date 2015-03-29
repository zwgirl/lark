package org.bark.remoting;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

@WebListener
public class BarkServletContextListener implements ServletContextListener {
	public final static String INJECTOR = "org.bark.remoting.injector";

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		contextEvent.getServletContext().removeAttribute(INJECTOR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("application.properties");

		Properties prop = new Properties();
		try {
			prop.load(is);
			int size = prop.size();
			Module[] modules = new Module[size];

			int count = 0;
			for (String key : prop.stringPropertyNames()) {
				String moduleName = prop.getProperty(key);

				Class<? extends Module> moduleClazz = (Class<? extends Module>) Class
						.forName(moduleName);
				modules[count] = moduleClazz.newInstance();

			}
			Injector injector = Guice.createInjector(modules);
			contextEvent.getServletContext().setAttribute(INJECTOR, injector);

		} catch (ClassNotFoundException |InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}
}
