package com.redhat.gpe.auditReview;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.redhat.gpe.refarch.bpm_domain.domain.Policy;

@WebService(targetNamespace="urn:com.redhat.gpe.auditReview:1.0",
            serviceName="AuditReview", 
            portName="AuditReviewPort")
public class AuditReview implements IAuditReview {
    
    Logger log = LoggerFactory.getLogger("AuditReview");

    public boolean addPolicy(Policy policyObj) {
        log.info("addPolicy() policy = "+policyObj);
        if(policyObj.getPrice() > 750)
            return false;
        else
            return true;
    }
}
