package com.backend.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.backend.api.domain.enums.RouteType;

@Entity
public class Route extends Base {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    private String description;
    private Integer type;
    private String url;
    private String method;
    private String icon;
    private String category;
    private Route father;

    @ManyToMany(mappedBy = "routes")
    private List<UserProfile> profiles = new ArrayList<>();

    public Route() {
    }

    

    public Route(Integer id, String description, RouteType type, String url, String icon, Route father, String method, String category) {
        this.id = id;
        this.description = description;
        this.type = type.getCod();
        this.url = url;
        this.icon = icon;
        this.father = father;
        this.method = method;
        this.category = category;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RouteType getType() {
        return RouteType.toEnum(type);
    }

    public void setType(RouteType type) {
        this.type = type.getCod();
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

    public List<UserProfile> getUserProfiles() {
        return profiles;
    }

    public void setUserProfiles(List<UserProfile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return "Route [id=" + id + ", url=" + url + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((method == null) ? 0 : method.hashCode());
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
        Route other = (Route) obj;
        if (method == null) {
            if (other.method != null)
                return false;
        } else if (!method.equals(other.method))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.matches(other.url))
            return false;
        return true;
    }

}