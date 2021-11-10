package com.github.dao;

import com.github.entity.Location;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LocationDao extends Dao<Location> {

    public LocationDao(Set<Location> locations) {
        super(locations);
    }

    @Override
    public Location update(Location location) {
        Location loca = super.update(location);
        loca.setAddress(location.getAddress());
        loca.setCapacity(location.getCapacity());
        loca.setInstitutionName(loca.getInstitutionName());
        return loca;
    }
}
