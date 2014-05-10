package com.redhat.gpe.cxf;

import javax.xml.namespace.QName;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientCallback;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import com.redhat.gpe.refarch.bpm_servicetasks.domain.Policy;
import com.redhat.gpe.refarch.bpm_servicetasks.domain.Driver;

public class DynamicClientTest {

    public static final String INTERFACE_REFERENCE = "interface_reference";
    public static final String OPERATION_REFERENCE = "operation_reference";
    public static final String WSDL_LOCATION = "wsdl_location";
    public static final String NAMESPACE = "namespace";

    private Logger log = LoggerFactory.getLogger("DynamicClientTest");

    @Test
    public void invokeSoapService() throws Exception{
        String interfaceRef = System.getProperty(INTERFACE_REFERENCE);
        String operationRef = System.getProperty(OPERATION_REFERENCE);
        String wLocation = System.getProperty(WSDL_LOCATION);
        String nameSpace = System.getProperty(NAMESPACE);
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("\n\tinterfaceRef = "+interfaceRef);
        sBuilder.append("\n\toperationRef = "+operationRef);
        sBuilder.append("\n\twLocation = "+wLocation);
        sBuilder.append("\n\tnameSpace = "+nameSpace);
        log.info(sBuilder.toString());

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //ClassLoader cLoader = Class.forName("com.redhat.gpe.refarch.bpm_servicetasks.domain.Policy").getClassLoader();
        ClassLoader cLoader = Policy.class.getClassLoader();
        Client client = dcf.createClient(wLocation, new QName(nameSpace, interfaceRef), cLoader, null);

        Policy policyObj = new Policy();
        Driver driverObj = new Driver();
        policyObj.setPolicyType("AUTO");
        policyObj.setVehicleYear(2013);
        policyObj.setDriver(driverObj);
        driverObj.setDriverName("Azra");
        driverObj.setAge(21);
        driverObj.setNumberOfAccidents(0);
        driverObj.setNumberOfTickets(1);
        Object[] results = client.invoke(operationRef, policyObj);

        for(Object result : results){
            log.info("invokeSoapService result = "+result);
        }
    }
}
