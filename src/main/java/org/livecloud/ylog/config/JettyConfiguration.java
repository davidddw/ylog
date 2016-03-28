package org.livecloud.ylog.config;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.codahale.metrics.servlets.MetricsServlet;

@Configuration
public class JettyConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private MetricRegistry metricRegistry;

	@Autowired
	private HealthCheckRegistry healthCheckRegistry;

	@Value("${jetty.port:8080}")
	private int jettyPort;

	@Bean
	public WebAppContext jettyWebAppContext() throws IOException {

		WebAppContext ctx = new WebAppContext();
		ctx.setContextPath("/");

		// System.out.println(new
		// ClassPathResource("webapp").getURI().toString());
		ctx.setWar(new ClassPathResource("webapp").getURI().toString());

		/* Disable directory listings if no index.html is found. */
		ctx.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
		ctx.setInitParameter("ttfFile", "/WEB-INF/resources/verdanab.ttf");

		/*
		 * Create the root web application context and set it as a servlet
		 * attribute so the dispatcher servlet can find it.
		 */
		GenericWebApplicationContext webApplicationContext = new GenericWebApplicationContext();
		webApplicationContext.setParent(applicationContext);
		webApplicationContext.refresh();
		ctx.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webApplicationContext);
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				".*/.*jsp-api-[^/]*\\.jar$|.*/.*jsp-[^/]*\\.jar$|.*/.*taglibs[^/]*\\.jar$");
		/*
		 * Set the attributes that the Metrics servlets require. The Metrics
		 * servlet is added in the WebAppInitializer.
		 */
		ctx.setAttribute(MetricsServlet.METRICS_REGISTRY, metricRegistry);
		ctx.setAttribute(HealthCheckServlet.HEALTH_CHECK_REGISTRY, healthCheckRegistry);
		ctx.setAttribute("org.eclipse.jetty.webapp.basetempdir", "/usr/tmp");
		ctx.setPersistTempDirectory(true);

		ctx.setErrorHandler(createErrorHandler());
		ctx.addEventListener(new WebAppInitializer());

		return ctx;
	}

	@Bean
	public ErrorHandler createErrorHandler() {
		ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
		errorHandler.addErrorPage(404, "/errorpage");
		return errorHandler;
	}

	/**
	 * Jetty Server bean.
	 * <p/>
	 * Instantiate the Jetty server.
	 */
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server jettyServer() throws IOException {

		/* Create the server. */
		Server server = new Server();

		/* Create a basic connector. */
		ServerConnector httpConnector = new ServerConnector(server);
		httpConnector.setPort(jettyPort);
		server.addConnector(httpConnector);

		server.setHandler(jettyWebAppContext());

		return server;
	}
}