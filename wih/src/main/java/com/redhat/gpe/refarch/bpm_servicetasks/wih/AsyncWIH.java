package com.redhat.gpe.refarch.bpm_servicetasks.wih;

import java.util.Map;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.context.variable.VariableScopeInstance;
import org.jbpm.process.workitem.AbstractLogOrThrowWorkItemHandler;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncWIH extends AbstractLogOrThrowWorkItemHandler {
    
    private static final Logger log = LoggerFactory.getLogger(AsyncWIH.class);
    private static Object cpLock = new Object();

    private KieSession sessionObj = null;

    public AsyncWIH() {}

    public AsyncWIH(KieSession sessionObj) {
	this.sessionObj = sessionObj;
    }

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            RuleFlowProcessInstance pInstance = (RuleFlowProcessInstance)sessionObj.getProcessInstance(workItem.getProcessInstanceId());
            new Thread(new Runnable() {
                public void run() {
                    // call jbpm executor SLSB ...
                    // http://docs.jboss.org/jbpm/v6.0.1/userguide/jBPMAsyncExecution.html#d0e16507
                }
            }).start();
        }catch(Throwable x) {
            x.printStackTrace();
            handleException(x);
        }
    }

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        log.warn("abortWorkItem() what should be the biz-logic here ?");
    }

}
