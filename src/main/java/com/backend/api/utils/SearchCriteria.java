package com.backend.api.utils;

public class SearchCriteria {
  private String connector;
  private String key;
  private String operation;
  private Object value;

  public SearchCriteria(String key, String operation, Object value, String connector) {
    this.key = key;
    this.operation = operation;
    this.value = value;
    this.connector = connector;
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

}