// package com.routemax.teamroutemax.controller;

// import com.routemax.teamroutemax.service.ParcelService;
// import com.routemax.teamroutemax.entity.Parcel;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.UUID;

// @RestController
// @RequestMapping("/api/admin/parcels")
// public class ParcelController {

//     private final ParcelService parcelService;

//     public ParcelController(ParcelService parcelService) {
//         this.parcelService = parcelService;
//     }

//     @PostMapping
//     public ResponseEntity<?> addParcel(@RequestBody Parcel parcel) {
//         parcelService.saveParcel(parcel);        // <-- use correct service method
//         return ResponseEntity.ok("Parcel added successfully");
//     }

//     @GetMapping
//     public List<Parcel> getAllParcels() {
//         return parcelService.getAllParcels();    // <-- use correct service method
//     }
  

// @PostMapping
// public ResponseEntity<?> addParcel(@RequestBody Parcel parcel) {
//     parcel.setTrackingId(UUID.randomUUID().toString()); // ✅ generate ID
//     parcelService.saveParcel(parcel);
//     return ResponseEntity.ok("Parcel added successfully");
// }

// }
// package com.routemax.teamroutemax.controller;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.routemax.teamroutemax.dto.ParcelDTO;
// import com.routemax.teamroutemax.entity.Parcel;
// import com.routemax.teamroutemax.service.ParcelService;

// @RestController
// @RequestMapping("/api/admin/parcels")
// public class ParcelController {

//     private final ParcelService parcelService;

//     public ParcelController(ParcelService parcelService) {
//         this.parcelService = parcelService;
//     }

// @PostMapping
// public ResponseEntity<?> addParcel(@RequestBody Parcel parcel) {
//     String trackingId;
//     do {
//         trackingId = generateTrackingId(); // custom logic
//     } while (parcelService.trackingIdExists(trackingId)); // ✅ ensure uniqueness

//     parcel.setTrackingId(trackingId);
//     parcelService.saveParcel(parcel);
//     return ResponseEntity.ok("Parcel added successfully with tracking ID: " + trackingId);
// }

// // ✅ Utility method to generate a 7-character tracking ID like RM83XZ1
// private String generateTrackingId() {
//     String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//     StringBuilder sb = new StringBuilder("RM");
//     for (int i = 0; i < 5; i++) {
//         sb.append(characters.charAt((int)(Math.random() * characters.length())));
//     }
//     return sb.toString();
// }


//     // @GetMapping
//     // public List<Parcel> getAllParcels() {
//     //     return parcelService.getAllParcels();
//     // }
// //     @GetMapping
// // public List<ParcelDTO> getAllParcels() {
// //     return parcelService.getAllParcels().stream().map(ParcelDTO::new).toList();
// // }

// @GetMapping
// public List<ParcelDTO> getAllParcels() {
//     return parcelService.getAllParcels()
//                         .stream()
//                         .map(ParcelDTO::new)
//                         .collect(Collectors.toList());
// }


// }
package com.routemax.teamroutemax.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.routemax.teamroutemax.dto.ParcelDTO;
import com.routemax.teamroutemax.entity.Parcel;
import com.routemax.teamroutemax.repository.ParcelRepository;
import com.routemax.teamroutemax.service.MailService;
import com.routemax.teamroutemax.service.ParcelService;

@RestController
@RequestMapping("/api/admin/parcels")
public class ParcelController {

    private final ParcelService parcelService;
    private final ParcelRepository parcelRepository;

    private final MailService mailService;

    public ParcelController(ParcelService parcelService, ParcelRepository parcelRepository, MailService mailService) {
        this.parcelService = parcelService;
        this.parcelRepository = parcelRepository;
        this.mailService =mailService;
}

    @PostMapping
    public ResponseEntity<?> addParcel(@RequestBody Parcel parcel) {
        String trackingId;
        do {
            trackingId = generateTrackingId();
        } while (parcelService.trackingIdExists(trackingId));

        parcel.setTrackingId(trackingId);

        // Default status if blank
        if (parcel.getStatus() == null || parcel.getStatus().isBlank()) {
            parcel.setStatus("In transit");
        }

        parcelRepository.save(parcel);

        // ✅ Send email
        if (parcel.getReceiverEmail() != null && !parcel.getReceiverEmail().isBlank()) {
            mailService.sendParcelNotification(parcel.getReceiverEmail(), parcel.getDescription(), trackingId,
    parcel.getSenderName(),
    parcel.getSenderAddress(),
    parcel.getReceiverName(),
    parcel.getReceiverAddress()
);
        }

        return ResponseEntity.ok("Parcel added successfully with tracking ID: " + trackingId);
    }

    // ✅ Get all parcels (as DTOs)
    @GetMapping
    public List<ParcelDTO> getAllParcels() {
        return parcelService.getAllParcels()
                .stream()
                .map(ParcelDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ Update only the status of a parcel
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateParcelStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        Optional<Parcel> optionalParcel = parcelRepository.findById(id);
        if (optionalParcel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parcel not found");
        }

        Parcel parcel = optionalParcel.get();
        String newStatus = payload.get("status");

        if (newStatus != null && !newStatus.isBlank()) {
            parcel.setStatus(newStatus);
            parcelRepository.save(parcel);
            return ResponseEntity.ok("Status updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid status");
        }
    }

    // ✅ Helper method for generating tracking ID like RM93H2X
    private String generateTrackingId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder("RM");
        for (int i = 0; i < 5; i++) {
            sb.append(characters.charAt((int) (Math.random() * characters.length())));
        }
        return sb.toString();
    }

}
