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
        // 调用发送
        Object[] result;
        
        //使用RPC方式调用WebService
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        EndpointReference targetEPR = new EndpointReference(serviceUrl);
        options.setTo(targetEPR);
        // 指定要调用的method方法及WSDL文件的命名空间
        //WSDL文件的命名空间<wsdl:definitions>元素的targetNamespace属性值
        QName opAddEntry = new QName("http://impl.service.ws.test.gary.com/", "greeting");
        String userName2 = "20150501的份上";
        Object[] params = new Object[] { userName2 };
        Class[] returnClass = new Class[] { String.class};
        // 调用method方法并输出该方法的返回值
        result = serviceClient.invokeBlocking(opAddEntry, params, returnClass);
        System.out.println("222");
        System.out.println(result[0]);
		}
		catch(Exception e){
			
		}
	}

}
