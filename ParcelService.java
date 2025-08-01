package com.routemax.teamroutemax.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.routemax.teamroutemax.entity.Parcel;
import com.routemax.teamroutemax.entity.User;
import com.routemax.teamroutemax.repository.ParcelRepository;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;

    //@Autowired
    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    // Save a new parcel or update
    public Parcel saveParcel(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    // Get list of all parcels (admin use)
    public List<Parcel> getAllParcels() {
        return parcelRepository.findAll();
    }

    // Get parcels for a specific user
    public List<Parcel> getParcelsByUser(User user) {
        return parcelRepository.findByUser(user);
    }

    // Find parcel by ID
    public Optional<Parcel> getParcelById(Long id) {
        return parcelRepository.findById(id);
    }

    // Delete parcel by ID
    public void deleteParcel(Long id) {
        parcelRepository.deleteById(id);
    }
    public boolean trackingIdExists(String trackingId) {
    return parcelRepository.existsByTrackingId(trackingId);
}
public Parcel findByTrackingId(String trackingId) {
    return parcelRepository.findByTrackingId(trackingId);
}

}
