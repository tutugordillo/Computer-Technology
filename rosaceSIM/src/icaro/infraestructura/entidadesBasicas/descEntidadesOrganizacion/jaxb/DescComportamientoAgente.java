//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.12 at 02:01:10 PM CEST 
//


package icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DescComportamientoAgente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DescComportamientoAgente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="nombreComportamiento" use="required" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}NombreAgente" />
 *       &lt;attribute name="localizacionComportamiento" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tipo" use="required" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}TipoAgente" />
 *       &lt;attribute name="rol" use="required" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}RolAgente" />
 *       &lt;attribute name="localizacionClaseAcciones" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="localizacionFicheroReglas" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="localizacionFicheroAutomata" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescComportamientoAgente")
@XmlSeeAlso({
    DescComportamientoAgenteCognitivo.class,
    DescComportamientoAgenteReactivo.class
})
public class DescComportamientoAgente {

    @XmlAttribute(name = "nombreComportamiento", required = true)
    protected String nombreComportamiento;
    @XmlAttribute(name = "localizacionComportamiento")
    protected String localizacionComportamiento;
    @XmlAttribute(name = "tipo", required = true)
    protected TipoAgente tipo;
    @XmlAttribute(name = "rol", required = true)
    protected RolAgente rol;
    @XmlAttribute(name = "localizacionClaseAcciones")
    protected String localizacionClaseAcciones;
    @XmlAttribute(name = "localizacionFicheroReglas")
    protected String localizacionFicheroReglas;
    @XmlAttribute(name = "localizacionFicheroAutomata")
    protected String localizacionFicheroAutomata;

    /**
     * Gets the value of the nombreComportamiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreComportamiento() {
        return nombreComportamiento;
    }

    /**
     * Sets the value of the nombreComportamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreComportamiento(String value) {
        this.nombreComportamiento = value;
    }

    /**
     * Gets the value of the localizacionComportamiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalizacionComportamiento() {
        return localizacionComportamiento;
    }

    /**
     * Sets the value of the localizacionComportamiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalizacionComportamiento(String value) {
        this.localizacionComportamiento = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoAgente }
     *     
     */
    public TipoAgente getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoAgente }
     *     
     */
    public void setTipo(TipoAgente value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the rol property.
     * 
     * @return
     *     possible object is
     *     {@link RolAgente }
     *     
     */
    public RolAgente getRol() {
        return rol;
    }

    /**
     * Sets the value of the rol property.
     * 
     * @param value
     *     allowed object is
     *     {@link RolAgente }
     *     
     */
    public void setRol(RolAgente value) {
        this.rol = value;
    }

    /**
     * Gets the value of the localizacionClaseAcciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalizacionClaseAcciones() {
        return localizacionClaseAcciones;
    }

    /**
     * Sets the value of the localizacionClaseAcciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalizacionClaseAcciones(String value) {
        this.localizacionClaseAcciones = value;
    }

    /**
     * Gets the value of the localizacionFicheroReglas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalizacionFicheroReglas() {
        return localizacionFicheroReglas;
    }

    /**
     * Sets the value of the localizacionFicheroReglas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalizacionFicheroReglas(String value) {
        this.localizacionFicheroReglas = value;
    }

    /**
     * Gets the value of the localizacionFicheroAutomata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalizacionFicheroAutomata() {
        return localizacionFicheroAutomata;
    }

    /**
     * Sets the value of the localizacionFicheroAutomata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalizacionFicheroAutomata(String value) {
        this.localizacionFicheroAutomata = value;
    }

}
