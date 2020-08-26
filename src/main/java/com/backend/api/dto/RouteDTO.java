package com.backend.api.dto;

import java.io.Serializable;

import com.backend.api.domain.Route;
import com.backend.api.domain.enums.RouteType;

public class RouteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String description;
    private Integer type;
    private String url;
    private String icon;
    private Route father;
    private String method;

    public RouteDTO() {
    }

    

    public RouteDTO(String description, RouteType type, String url, String icon, Route father, String method) {
        this.description = description;
        this.type = type.getCod();
        this.url = url;
        this.icon = icon;
        this.father = father;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RouteType getTipo() {
        return RouteType.toEnum(type);
    }

    public void setTipo(RouteType type) {
        this.type = type.getCod();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcone() {
        return icon;
    }

    public void setIcone(String icon) {
        this.icon = icon;
    }

    public Route getPai() {
        return father;
    }

    public void setPai(Route father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return "RouteDTO [description=" + description + ", url=" + url + "]";
    }
    

   

}