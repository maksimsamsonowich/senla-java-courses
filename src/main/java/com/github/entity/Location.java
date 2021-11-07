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
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Location location = (Location) object;
        return id == location.getId() && institutionName.equals(location.getInstitutionName())
                && address.equals(location.getAddress()) && capacity == location.getCapacity();
    }
}
