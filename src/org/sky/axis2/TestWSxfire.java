package org.sky.axis2;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;


public class TestWSxfire  {

	/**
	 * @param args
	 * @throws AxisFault 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		try{
		String serviceUrl = "http://localhost:8088/testWebService/GreetingService?wsdl";
        // ���÷���
        Object[] result;
        
        //ʹ��RPC��ʽ����WebService
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        EndpointReference targetEPR = new EndpointReference(serviceUrl);
        options.setTo(targetEPR);
        // ָ��Ҫ���õ�method������WSDL�ļ��������ռ�
        //WSDL�ļ��������ռ�<wsdl:definitions>Ԫ�ص�targetNamespace����ֵ
        QName opAddEntry = new QName("http://impl.service.ws.test.gary.com/", "greeting");
        String userName2 = "20150501�ķ���";
        Object[] params = new Object[] { userName2 };
        Class[] returnClass = new Class[] { String.class};
        // ����method����������÷����ķ���ֵ
        result = serviceClient.invokeBlocking(opAddEntry, params, returnClass);
        System.out.println("222");
        System.out.println(result[0]);
		}
		catch(Exception e){
			
		}
	}

}
