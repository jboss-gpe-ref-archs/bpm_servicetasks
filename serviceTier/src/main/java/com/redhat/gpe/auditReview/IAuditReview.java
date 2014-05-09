package com.redhat.gpe.auditReview;

import com.redhat.gpe.refarch.bpm_servicetasks.domain.Policy;

public interface IAuditReview {

    // implementation returns boolean indicating either approval or rejection of policy
    boolean addPolicy(Policy policyObj);
}
