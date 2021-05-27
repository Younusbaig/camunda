package com.camunda.bpm.camunda.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ProcessData {

    private String nameClient;

    private String nameApplicant;

    private String carMerchandise;

    private Integer amountMerchandise;

    private String valueMerchandise;

    private String idTasks;

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNameApplicant() {
        return nameApplicant;
    }

    public void setNameApplicant(String nameApplicant) {
        this.nameApplicant = nameApplicant;
    }

    public String getCarMerchandise() {
        return carMerchandise;
    }

    public void setCarMerchandise(String carMerchandise) {
        this.carMerchandise = carMerchandise;
    }

    public Integer getAmountMerchandise() {
        return amountMerchandise;
    }

    public void setAmountMerchandise(Integer amountMerchandise) {
        this.amountMerchandise = amountMerchandise;
    }

    public String getValueMerchandise() {
        return valueMerchandise;
    }

    public void setValueMerchandise(String valueMerchandise) {
        this.valueMerchandise = valueMerchandise;
    }

    public String getIdTasks() {
        return idTasks;
    }

    public void setIdTasks(String idTasks) {
        this.idTasks = idTasks;
    }
}

