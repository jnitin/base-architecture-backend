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

}