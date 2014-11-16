
package com.redhat.gpe.refarch.bpm_servicetasks.soa.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.redhat.gpe.refarch.bpm_servicetasks.soa.domain package. 
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

    private final static QName _Policy_QNAME = new QName("domain.soa.bpm_servicetasks.refarch.gpe.redhat.com", "Policy");

    public ObjectFactory() {
    }

    public Policy createPolicy() {
        return new Policy();
    }

    public Driver createDriver() {
        return new Driver();
    }

    @XmlElementDecl(namespace = "domain.soa.bpm_servicetasks.refarch.gpe.redhat.com", name = "Policy")
    public JAXBElement<Policy> createPolicy(Policy value) {
        return new JAXBElement<Policy>(_Policy_QNAME, Policy.class, null, value);
    }

}
