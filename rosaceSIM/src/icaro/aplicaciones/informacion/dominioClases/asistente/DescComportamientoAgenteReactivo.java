//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.11.26 at 01:54:43 AM CET 
//


package icaro.aplicaciones.informacion.dominioClases.asistente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DescComportamientoAgenteReactivo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DescComportamientoAgenteReactivo">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:icaro:aplicaciones:descripcionOrganizaciones}DescComportamientoAgente">
 *       &lt;sequence>
 *         &lt;element name="rutaAutomata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rutaAcciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescComportamientoAgenteReactivo", propOrder = {
    "rutaAutomata",
    "rutaAcciones"
})
public class DescComportamientoAgenteReactivo
    extends DescComportamientoAgente
{

    protected String rutaAutomata;
    protected String rutaAcciones;

    /**
     * Gets the value of the rutaAutomata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaAutomata() {
        return rutaAutomata;
    }

    /**
     * Sets the value of the rutaAutomata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaAutomata(String value) {
        this.rutaAutomata = value;
    }

    /**
     * Gets the value of the rutaAcciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaAcciones() {
        return rutaAcciones;
    }

    /**
     * Sets the value of the rutaAcciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaAcciones(String value) {
        this.rutaAcciones = value;
    }

}