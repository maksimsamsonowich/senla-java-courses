package com.github.dao;

import com.github.entity.Location;

public interface ILocationDao {

    void createLocation(Location location);
    Location readLocation(Location location);
    Location updateLocationAddress(Location location, String newAddress);
    void deleteLocation(Location location);

}
