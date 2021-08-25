package com.backend.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Routine extends Base {
    private String description;
    private Integer code;

    @ManyToMany(mappedBy = "routines")
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "routines")
    private List<UserProfile> profiles = new ArrayList<>();
}