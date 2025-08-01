package com.routemax.teamroutemax.controller;

import com.routemax.teamroutemax.entity.Parcel;
import com.routemax.teamroutemax.service.ParcelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parcels")
public class UserParcelController {

    private final ParcelService parcelService;

    public UserParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    // Show form
    @GetMapping("/track")
    public String showTrackForm() {
        return "trackparcel";
    }

    // Handle form submit
    @PostMapping("/track")
    public String handleTrackRequest(@RequestParam("trackingId") String trackingId, Model model) {
        Parcel parcel = parcelService.findByTrackingId(trackingId);
        if (parcel == null) {
            model.addAttribute("error", "No parcel found with tracking ID: " + trackingId);
        } else {
            model.addAttribute("parcel", parcel);
        }
        return "trackparcel";
    }
}

