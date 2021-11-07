package com.github.dao;

import com.github.dto.LocationDto;
import com.github.entity.Location;
import com.github.exceptions.location.NoSuchLocationException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class LocationDao implements ILocationDao {

    private Set<Location> locations;

    public LocationDao() {
        this.locations = new HashSet<>();
    }

    @Override
    public void createLocation(Location location) {
        locations.add(location);
    }

    @Override
    public Location readLocation(Location location) {
        return getEntity(location);
    }

    @Override
    public Location updateLocationAddress(Location location, String newAddress) {
        Location somePlace = getEntity(location);
        somePlace.setAddress(newAddress);
        return somePlace;
    }

    @Override
    public void deleteLocation(Location location) {
        locations.remove(getEntity(location));
    }

    private Location getEntity(Location location) {
        return locations.stream()
                .filter(location::equals)
                .findFirst()
                .orElseThrow(() -> new NoSuchLocationException("There is no such location;"));
    }
}
