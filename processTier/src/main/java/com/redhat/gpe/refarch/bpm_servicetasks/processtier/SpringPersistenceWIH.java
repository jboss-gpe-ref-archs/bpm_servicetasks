package com.redhat.gpe.refarch.bpm_servicetasks.processtier;

import java.util.concurrent.atomic.AtomicInteger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.runtime.process.WorkItemHandler;
import org.jbpm.process.workitem.AbstractLogOrThrowWorkItemHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import org.springframework.jdbc.core.JdbcTemplate; // enable me

public class SpringPersistenceWIH extends AbstractLogOrThrowWorkItemHandler {
    
    private static final Logger log = LoggerFactory.getLogger(SpringPersistenceWIH.class);
    private static AtomicInteger counter = new AtomicInteger();
    private static Object cpLock = new Object();

    // JdbcTemplate is thread-safe
    //private static JdbcTemplate jdbcTemplate; // enable me

    public SpringPersistenceWIH() {}

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            getJdbcTemplate();
            //jdbcTemplate.update("INSERT INTO customer(id, firstname, lastname) values(?,?,?)", counter.getAndIncrement(), "Azra and Alex", "Bride"); //enable me

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
        //enable me
        /*
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
        */
    }
}
