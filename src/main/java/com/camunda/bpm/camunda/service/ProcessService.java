package com.camunda.bpm.camunda.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.camunda.bpm.camunda.dto.ProcessData;
import com.camunda.bpm.camunda.enumeration.TaskEnum;
import com.camunda.bpm.camunda.enumeration.DecisionEnum;
import com.camunda.bpm.camunda.helper.ProcessScopeHelper;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;



@Service
public class ProcessService {

    private static final String INSPECT = "process_younus";

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final ProcessScopeHelper processScope;

    public ProcessService(RuntimeService runtimeService, TaskService taskService, ProcessScopeHelper processScope) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.processScope = processScope;
    }

    public void start(ProcessData processData) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("processData", processData);


        runtimeService.startProcessInstanceByKey(INSPECT, processData.getNameApplicant(), variables);
    }

    public List<ProcessData> SearchTasksData(TaskEnum assignmentCode) {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey(assignmentCode.toString())
                .list();

        if (tasks != null && tasks.size() == 0) {
            return new ArrayList<ProcessData>();
        }

        return tasks.stream().map(task -> {
            ProcessData processData = processScope.getProcessData(task.getProcessInstanceId());
            processData.setIdTasks(task.getId());

            return processData;
        }).collect(Collectors.toList());
    }

    public ProcessData SearchTasks(String idAssignment) {
        Task task = taskService.createTaskQuery()
                .taskId(idAssignment)
                .singleResult();

        ProcessData processData = processScope.getProcessData(task.getProcessInstanceId());
        processData.setIdTasks(task.getId());

        return processData;
    }

    public void completeTaskData(ProcessData processData, DecisionEnum decision) {
        Task task = taskService.createTaskQuery()
                .taskId(processData.getIdTasks())
                .singleResult();

        String processId = task.getProcessInstanceId();

        processScope.setDecision(processId, decision);
        processScope.setProcessData(processId, processData);

        taskService.complete(task.getId());
    }

    public void completeTaskRequest(ProcessData processData) {
        taskService.complete(processData.getIdTasks());
    }

}