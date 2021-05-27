package com.camunda.bpm.camunda.api;

import java.util.List;
import javax.ws.rs.Path;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.camunda.bpm.camunda.dto.ProcessData;
import com.camunda.bpm.camunda.enumeration.TaskEnum;
import com.camunda.bpm.camunda.enumeration.DecisionEnum;
import com.camunda.bpm.camunda.service.ProcessService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/process")
public class ProcessApi {


    private final ProcessService processService;
    private Logger logger = LoggerFactory.getLogger(ProcessApi.class);

    public ProcessApi(ProcessService processService) {
        this.processService = processService;
    }

    @PostMapping
    @ApiOperation(value = "Start a camunda process.", notes = "Start a camunda process.", response = Void.class)
    public void start(@RequestBody ProcessData processData) {

        processService.start(processData);
    }

    @GetMapping(path = "/tasks/processData")
    @ApiOperation(value = "Seeks tasks data.", notes = "Seeks task data.", response = List.class)
    public List<ProcessData> SearchTasksData() {

        return processService.SearchTasksData(TaskEnum.taskSubmit);
    }

    @GetMapping(path = "/tasks/request")
    @ApiOperation(value = "Search Tasks Request.", notes = "Search Tasks Request.", response = List.class)
    public List<ProcessData> SearchTasksRequest() {

        return processService.SearchTasksData(TaskEnum.taskReceive);
    }

    @GetMapping(path = "/task/{idTask}")
    @ApiOperation(value = "Search Tasks.", notes = "Search Tasks.", response = List.class)
    public ProcessData SearchTaskById(@PathVariable(name = "idTasks") String idTask) {

        return processService.SearchTasks(idTask);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/task/processData/{decision}")
    @ApiOperation(value = "Complete tasks.", notes = "Complete tasks.", response = Void.class)
    public void completeTasks(@RequestBody ProcessData processData,
                                       @PathVariable(name = "decision") DecisionEnum decision) {

        processService.completeTaskData(processData,decision);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/task/request")
    @ApiOperation(value = "Complete task request.", notes = "Complete task request.", response = Void.class)
    public void completeTaskRequest(@RequestBody ProcessData processData) {

        processService.completeTaskRequest(processData);
    }


}