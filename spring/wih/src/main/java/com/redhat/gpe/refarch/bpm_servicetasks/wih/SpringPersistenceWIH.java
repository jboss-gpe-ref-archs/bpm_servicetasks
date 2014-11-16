package com.redhat.gpe.refarch.bpm_servicetasks.wih;

import java.util.Map;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.context.variable.VariableScopeInstance;
import org.jbpm.process.workitem.AbstractLogOrThrowWorkItemHandler;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringPersistenceWIH extends AbstractLogOrThrowWorkItemHandler {
    
    private static final Logger log = LoggerFactory.getLogger(SpringPersistenceWIH.class);
    private static Object cpLock = new Object();

    // JdbcTemplate is thread-safe
    private static JdbcTemplate jdbcTemplate; // enable me

    private KieSession sessionObj = null;

    public SpringPersistenceWIH() {}

    public SpringPersistenceWIH(KieSession sessionObj) {
    this.sessionObj = sessionObj;
    }

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            getJdbcTemplate();
            jdbcTemplate.update("INSERT INTO customer(firstname, lastname) values(?,?)", "Azra and Alex", "Bride");

            RuleFlowProcessInstance pInstance = (RuleFlowProcessInstance)sessionObj.getProcessInstance(workItem.getProcessInstanceId());
            log.info("executeWorkItem() just inserted record into customer table with pInstanceId = "+pInstance.getId());
            
            VariableScopeInstance vScopeInstance = (VariableScopeInstance) pInstance.getContextInstance(VariableScope.VARIABLE_SCOPE);
            Map<String, Object> variables = vScopeInstance.getVariables();
            StringBuilder sBuilder = new StringBuilder("executeWorkItem() original pVariables = ");
            for(Entry<String, Object> entry: variables.entrySet()){
                sBuilder.append("\n"+entry.getKey()+" : "+entry.getValue());
            }
            log.info(sBuilder.toString());

            // notify manager that work item has been completed
            manager.completeWorkItem(workItem.getId(), null);
        }catch(Throwable x) {
            x.printStackTrace();
            handleException(x);
        }
    }

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        log.warn("abortWorkItem() what should be the biz-logic here ?");
    }

    private synchronized static void getJdbcTemplate() throws Exception {
        if(jdbcTemplate != null)
            return;

        synchronized(cpLock) {
            if(jdbcTemplate != null)
                return;

            Context jContext = new InitialContext();
            DataSource testCP = (DataSource)jContext.lookup("java:jboss/datasources/test-cp-xa");
            log.info("start() testCP = "+testCP);
            jdbcTemplate = new JdbcTemplate(testCP);
        }
    }
}
