package com.example.departement.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {

    // Configuration du MessageDispatcherServlet pour gérer les requêtes SOAP
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/soap/*");
    }

    // Définition d'un WSDL et d'un schéma XSD pour l'entité Etudiant
    @Bean(name = "etudiant")
    public DefaultWsdl11Definition etudiantWsdlDefinition(XsdSchema etudiantSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName("EtudiantService");
        wsdlDefinition.setLocationUri("/soap");
        wsdlDefinition.setTargetNamespace("http://example.com/departement");
        wsdlDefinition.setSchema(etudiantSchema);
        return wsdlDefinition;
    }

    @Bean
    public XsdSchema etudiantSchema() {
        return new SimpleXsdSchema(new ClassPathResource("etudiant.xsd"));
    }

    // Définition d'un WSDL et d'un schéma XSD pour l'entité Enseignant
    @Bean(name = "enseignant")
    public DefaultWsdl11Definition enseignantWsdlDefinition(XsdSchema enseignantSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName("EnseignantService");
        wsdlDefinition.setLocationUri("/soap");
        wsdlDefinition.setTargetNamespace("http://example.com/departement");
        wsdlDefinition.setSchema(enseignantSchema);
        return wsdlDefinition;
    }

    @Bean
    public XsdSchema enseignantSchema() {
        return new SimpleXsdSchema(new ClassPathResource("enseignant.xsd"));
    }

    // Définition d'un WSDL et d'un schéma XSD pour l'entité CadreAdmin
    @Bean(name = "cadreAdmin")
    public DefaultWsdl11Definition cadreAdminWsdlDefinition(XsdSchema cadreAdminSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setPortTypeName("CadreAdminService");
        wsdlDefinition.setLocationUri("/soap");
        wsdlDefinition.setTargetNamespace("http://example.com/departement");
        wsdlDefinition.setSchema(cadreAdminSchema);
        return wsdlDefinition;
    }

    @Bean
    public XsdSchema cadreAdminSchema() {
        return new SimpleXsdSchema(new ClassPathResource("cadreAdmin.xsd"));
    }
}

