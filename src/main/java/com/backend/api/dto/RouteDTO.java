package com.backend.api.dto;

import java.io.Serializable;

import com.backend.api.domain.Route;

public class RouteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    private Integer type;
    private String url;
    private String icon;
    private Route father;
    private String method;
    private String category;

    public RouteDTO() {
    }

    public RouteDTO(Integer id, String description, Integer type, String url, String icon, Route father,
            String method, String category) {
        this.description = description;
        this.type = type;
        this.url = url;
        this.icon = icon;
        this.father = father;
        this.method = method;
        this.category = category;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Route getFather() {
        return father;
    }

    public void setFather(Route father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return "RouteDTO [description=" + description + ", url=" + url + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((method == null) ? 0 : method.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RouteDTO other = (RouteDTO) obj;
        if (method == null) {
            if (other.method != null)
                return false;
        } else if (!method.equals(other.method))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

}