package com.github.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location {

    private int id;
    private String institutionName;
    private String address;
    private int capacity;

    @Override
    public boolean equals(Object object) {
        Location location = (Location) object;
        return id == location.getId();
    }
}
