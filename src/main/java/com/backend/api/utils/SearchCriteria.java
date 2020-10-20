package com.backend.api.utils;

import java.util.List;

public class SearchCriteria {
  private String connector;
  private String key;
  private String operation;
  private Object value;
  private List<?> notIn;

  public SearchCriteria(String key, String operation, Object value, String connector) {
    this.key = key;
    this.operation = operation;
    this.value = value;
    this.connector = connector;
  }

  public SearchCriteria(List<?> notIn, String key) {
    this.key = key;
    this.notIn = notIn;
    this.operation = ",";
    this.connector = "";
    this.value = "";
  }

  public String getConnector() {
    return connector;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public boolean isOrPredicate() {
    return connector.equals("|");
  }

  public void setConnector(String connector) {
    this.connector = connector;
  }

  public List<?> getNotIn() {
    return notIn;
  }

  public void setNotIn(List<?> notIn) {
    this.notIn = notIn;
  }

}