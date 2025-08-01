package com.routemax.teamroutemax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routemax.teamroutemax.entity.Parcel;
import com.routemax.teamroutemax.entity.User;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    // To list parcels belonging to a specific user
    List<Parcel> findByUser(User user);
    boolean existsByTrackingId(String trackingId);
    Parcel findByTrackingId(String trackingId);

}
