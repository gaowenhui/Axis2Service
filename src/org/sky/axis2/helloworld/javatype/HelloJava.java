package org.sky.axis2.helloworld.javatype;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;

public class HelloJava {

         public String sayHello(String name) throws XMLStreamException {

                   StringBuffer hello = new StringBuffer();

                   hello.append("hello: ");

                   hello.append(name);

                   return hello.toString();

         }

}