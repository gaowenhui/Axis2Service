package org.sky.axis2.helloworld;

import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;

import org.apache.axiom.om.OMElement;

import org.apache.axiom.om.OMFactory;

import org.apache.axiom.om.OMNamespace;

import org.apache.axiom.om.OMNode;

import org.apache.axiom.soap.SOAPBody;

import org.apache.axis2.AxisFault;

import org.apache.axis2.addressing.EndpointReference;

import org.apache.axis2.client.Options;

import org.apache.axis2.client.ServiceClient;

import org.apache.axis2.databinding.utils.BeanUtil;

import org.apache.axis2.engine.DefaultObjectSupplier;

public class HelloWorldWithReturnClient {
  
	//传统的客户端  测试成功
	
	private static EndpointReference targetEPR = new EndpointReference(

	"http://localhost:8086/Axis2Service/services/HelloWorld");

	public void sayHello() {

		Options options = new Options();

		options.setAction("urn:sayHello");

		options.setTo(targetEPR);

		ServiceClient sender = null;

		try {

			sender = new ServiceClient();

			sender.setOptions(options);

			OMFactory fac = OMAbstractFactory.getOMFactory();

			OMNamespace omNs = fac

			.createOMNamespace("http://helloworld.axis2.sky.org", "");

			OMElement method = fac.createOMElement("sayHello", omNs);

			OMElement name = fac.createOMElement("name", omNs);

			name.setText("ymk");

			method.addChild(name);

			method.build();

			OMElement response = sender.sendReceive(method);

			System.out.println(response);

			OMElement element = response.getFirstChildWithName(

			new QName("http://helloworld.axis2.sky.org", "name"));

			System.out.println(element.getText());

		} catch (AxisFault e) {

			System.out.println("------Error Occured------");

			e.printStackTrace();

		}

	}

	public static void main(String[] args) {

		HelloWorldWithReturnClient testClient = new HelloWorldWithReturnClient();

		testClient.sayHello();

	}

}