package com.camunda.bpm.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "ProcessDelegate")
public class ProcessDelegate implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(ProcessDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {

        // SeTING VARIABLES IN PROCESS
        execution.setVariable("name", "Muhammad Younus Baig");

        String name = (String) execution.getVariable("name");

        logger.info(name);

    }

}