package org.sky.axis2.helloworld;

import org.apache.axiom.om.OMAbstractFactory;

import org.apache.axiom.om.OMElement;

import org.apache.axiom.om.OMFactory;

import org.apache.axiom.om.OMNamespace;

import org.apache.axis2.AxisFault;

import org.apache.axis2.Constants;

import org.apache.axis2.addressing.EndpointReference;

import org.apache.axis2.client.Options;

import org.apache.axis2.client.ServiceClient;

import org.apache.axis2.context.ConfigurationContext;

import org.apache.axis2.context.ConfigurationContextFactory;

//非阻塞式双工 测试成功
public class HelloWorldWithReturnDualNonBlock {

	private static EndpointReference targetEPR = new EndpointReference(

	"http://localhost:8086/Axis2Service/services/HelloWorld");

	public static boolean finish = false;

	public void sayHello() {

		OMFactory fac = OMAbstractFactory.getOMFactory();

		OMNamespace omNs = fac.createOMNamespace(

		"http://helloworld.axis2.sky.org", "");

		OMElement method = fac.createOMElement("sayHello", omNs);

		OMElement name = fac.createOMElement("name", omNs);

		name.setText("文森特");

		method.addChild(name);

		method.build();

		Options options = new Options();

		options.setTo(targetEPR);

		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

		options.setUseSeparateListener(true);

		options.setAction("urn:sayHello");

		ServiceClient sender = null;

		HelloWorldNonBlockCB callback = new HelloWorldNonBlockCB();

		try {

			sender = new ServiceClient();

			sender.engageModule(Constants.MODULE_ADDRESSING);

			sender.setOptions(options);

			sender.sendReceiveNonBlocking(method, callback);

			synchronized (callback) {

				try {

					callback.wait();

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				sender.cleanup();

			} catch (Exception e) {

			}

		}

	}

	public static void main(String[] args) {

		HelloWorldWithReturnDualNonBlock testClient = new HelloWorldWithReturnDualNonBlock();

		testClient.sayHello();

	}

}