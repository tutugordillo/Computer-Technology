//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.11.26 at 01:54:43 AM CET 
//


package icaro.aplicaciones.informacion.dominioClases.asistente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DescOrganizacion_QNAME = new QName("urn:icaro:aplicaciones:descripcionOrganizaciones", "DescOrganizacion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DescRecursosAplicacion }
     * 
     */
    public DescRecursosAplicacion createDescRecursosAplicacion() {
        return new DescRecursosAplicacion();
    }

    /**
     * Create an instance of {@link ComponenteGestionado }
     * 
     */
    public ComponenteGestionado createComponenteGestionado() {
        return new ComponenteGestionado();
    }

    /**
     * Create an instance of {@link Instancias }
     * 
     */
    public DescInstancias createInstancias() {
        return new DescInstancias();
    }

    /**
     * Create an instance of {@link DescripcionComponentes }
     * 
     */
    public DescripcionComponentes createDescripcionComponentes() {
        return new DescripcionComponentes();
    }

    /**
     * Create an instance of {@link DescComportamientoAgente }
     * 
     */
    public DescComportamientoAgente createDescComportamientoAgente() {
        return new DescComportamientoAgente();
    }

    /**
     * Create an instance of {@link Nodo }
     * 
     */
    public Nodo createNodo() {
        return new Nodo();
    }

    /**
     * Create an instance of {@link DescComportamientoGestores }
     * 
     */
    public DescComportamientoGestores createDescComportamientoGestores() {
        return new DescComportamientoGestores();
    }

    /**
     * Create an instance of {@link DescComportamientoAgenteCognitivo }
     * 
     */
    public DescComportamientoAgenteCognitivo createDescComportamientoAgenteCognitivo() {
        return new DescComportamientoAgenteCognitivo();
    }

    /**
     * Create an instance of {@link InstanciaGestor }
     * 
     */
    public InstanciaGestor createInstanciaGestor() {
        return new InstanciaGestor();
    }

    /**
     * Create an instance of {@link Instancia }
     * 
     */
    public Instancia createInstancia() {
        return new Instancia();
    }

    /**
     * Create an instance of {@link DescRecursoAplicacion }
     * 
     */
    public DescRecursoAplicacion createDescRecursoAplicacion() {
        return new DescRecursoAplicacion();
    }

    /**
     * Create an instance of {@link Gestores }
     * 
     */
    public Gestores createGestores() {
        return new Gestores();
    }

    /**
     * Create an instance of {@link AgentesAplicacion }
     * 
     */
    public AgentesAplicacion createAgentesAplicacion() {
        return new AgentesAplicacion();
    }

    /**
     * Create an instance of {@link PropiedadesGlobales }
     * 
     */
    public PropiedadesGlobales createPropiedadesGlobales() {
        return new PropiedadesGlobales();
    }

    /**
     * Create an instance of {@link ComponentesGestionados }
     * 
     */
    public ComponentesGestionados createComponentesGestionados() {
        return new ComponentesGestionados();
    }

    /**
     * Create an instance of {@link DescComportamientoAgenteReactivo }
     * 
     */
    public DescComportamientoAgenteReactivo createDescComportamientoAgenteReactivo() {
        return new DescComportamientoAgenteReactivo();
    }

    /**
     * Create an instance of {@link Propiedad }
     * 
     */
    public Propiedad createPropiedad() {
        return new Propiedad();
    }

    /**
     * Create an instance of {@link DescComportamientoAgentes }
     * 
     */
    public DescComportamientoAgentes createDescComportamientoAgentes() {
        return new DescComportamientoAgentes();
    }

    /**
     * Create an instance of {@link DescComportamientoAgentesAplicacion }
     * 
     */
    public DescComportamientoAgentesAplicacion createDescComportamientoAgentesAplicacion() {
        return new DescComportamientoAgentesAplicacion();
    }

    /**
     * Create an instance of {@link ListaPropiedades }
     * 
     */
    public ListaPropiedades createListaPropiedades() {
        return new ListaPropiedades();
    }

    /**
     * Create an instance of {@link DescOrganizacion }
     * 
     */
    public DescOrganizacion createDescOrganizacion() {
        return new DescOrganizacion();
    }

    /**
     * Create an instance of {@link RecursosAplicacion }
     * 
     */
    public RecursosAplicacion createRecursosAplicacion() {
        return new RecursosAplicacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DescOrganizacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:icaro:aplicaciones:descripcionOrganizaciones", name = "DescOrganizacion")
    public JAXBElement<DescOrganizacion> createDescOrganizacion(DescOrganizacion value) {
        return new JAXBElement<DescOrganizacion>(_DescOrganizacion_QNAME, DescOrganizacion.class, null, value);
    }

}
