package com.backend.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

@Getter
@Setter
@MappedSuperclass
public class Base implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Version
    private int version;

    /**
     * Chama todos os getters do objeto other para todos os setters deste objeto
     * 
     * @param other Objeto a ser clonado
     */
    public void clone(Base other) {
        Method[] methods = other.getClass().getMethods();
        HashMap<String, Method> getters = new HashMap<>();
        HashMap<String, Method> setters = new HashMap<>();

        for (Method m : methods) {
            if (m.getName().contains("get") && !m.getName().contains("getClass")) {
                String key = m.getName().substring(3);
                getters.put(key, m);
            } else if (m.getName().contains("set")) {
                String key = m.getName().substring(3);
                setters.put(key, m);
            }
        }
        setters.keySet().forEach(key -> {
            try {
                Object getterValue = getters.get(key).invoke(other);
                if (getterValue != null) {
                    setters.get(key).invoke(this, getterValue);
                }
            } catch (Exception e) {
                System.out.println("Um erro ocorreu ao tentar clonar um objeto");
            }
        });
    }

}