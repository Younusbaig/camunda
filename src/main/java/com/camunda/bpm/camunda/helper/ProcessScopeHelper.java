package com.camunda.bpm.camunda.helper;

import com.camunda.bpm.camunda.dto.ProcessData;
import com.camunda.bpm.camunda.enumeration.DecisionEnum;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

@Component
public class ProcessScopeHelper {


    private final RuntimeService runtimeService;

    public ProcessScopeHelper(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    private static final String PROCESS_DATA = "processData";
    private static final String DECISION = "decision";

    public ProcessData getProcessData(String processId) {
        return (ProcessData) runtimeService.getVariable(processId, PROCESS_DATA);
    }

    public void setProcessData(String processId, ProcessData processData) {
        runtimeService.setVariable(processId, PROCESS_DATA, processData);
    }

    public void setDecision(String processId, DecisionEnum decision) {
        runtimeService.setVariable(processId, DECISION, decision);
    }

}