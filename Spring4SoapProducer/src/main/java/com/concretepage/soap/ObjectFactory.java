//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.07 at 05:33:36 PM EET 
//


package com.concretepage.soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.concretepage.soap package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.concretepage.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMemeCategoryOfTheDayResponse }
     * 
     */
    public GetMemeCategoryOfTheDayResponse createGetMemeCategoryOfTheDayResponse() {
        return new GetMemeCategoryOfTheDayResponse();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link GetMemeCategoryOfTheDayRequest }
     * 
     */
    public GetMemeCategoryOfTheDayRequest createGetMemeCategoryOfTheDayRequest() {
        return new GetMemeCategoryOfTheDayRequest();
    }

}
