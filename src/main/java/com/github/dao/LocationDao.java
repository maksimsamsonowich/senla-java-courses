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
    public Location update(Location location, String newAddress) {
        Location loca = super.update(location);
        loca.setAddress(newAddress);
        return loca;
    }
}
