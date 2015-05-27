package org.sky.axis2.helloworld;

import org.apache.axis2.addressing.EndpointReference;

import org.apache.axiom.om.OMAbstractFactory;

import org.apache.axiom.om.OMElement;

import org.apache.axiom.om.OMFactory;

import org.apache.axiom.om.OMNamespace;

import org.apache.axis2.AxisFault;

import org.apache.axis2.addressing.EndpointReference;

import org.apache.axis2.client.Options;

import org.apache.axis2.client.ServiceClient;

import org.apache.axis2.client.async.AxisCallback;

import org.apache.axis2.context.MessageContext;

////非阻塞式测试类 测试成功
public class HelloWorldWithReturnNonBlock {
	

	private static EndpointReference targetEPR = new EndpointReference(

	"http://localhost:8086/Axis2Service/services/HelloWorld");

	public void sayHello() {

		OMFactory fac = OMAbstractFactory.getOMFactory();

		OMNamespace omNs = fac.createOMNamespace(

		"http://helloworld.axis2.sky.org", "");

		OMElement method = fac.createOMElement("sayHello", omNs);

		OMElement name = fac.createOMElement("name", omNs);

		name.setText("麦克");

		method.addChild(name);

		method.build();

		Options options = new Options();

		options.setTo(targetEPR);

		ServiceClient sender = null;

		try {

			HelloWorldNonBlockCB callback = new HelloWorldNonBlockCB();

			sender = new ServiceClient();

			sender.setOptions(options);

			sender.sendReceiveNonBlocking(method, callback);

			synchronized (callback) {

				try {

					callback.wait();

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

			}

		} catch (AxisFault e) {

			e.printStackTrace();

		} finally {

			if (sender != null)

				try {

					sender.cleanup();

				} catch (Exception e) {

				}

		}

	}

	public static void main(String[] args) {

		HelloWorldWithReturnNonBlock testClient = new HelloWorldWithReturnNonBlock();

		testClient.sayHello();

	}

}