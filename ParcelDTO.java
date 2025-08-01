package com.routemax.teamroutemax.dto;

import com.routemax.teamroutemax.entity.Parcel;

public class ParcelDTO {
    private Long id;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    private String receiverEmail;
    private String description;
    private String trackingId;
    private String status;

    public ParcelDTO(Parcel parcel) {
        this.id = parcel.getId();
        this.senderName = parcel.getSenderName();
        this.senderAddress = parcel.getSenderAddress();
        this.receiverName = parcel.getReceiverName();
        this.receiverAddress = parcel.getReceiverAddress();
        this.receiverEmail = parcel.getReceiverEmail();
        this.description = parcel.getDescription();
        this.trackingId = parcel.getTrackingId();
        this.status = parcel.getStatus();
    }

    public Long getId() { return id; }
    public String getSenderName() { return senderName; }
    public String getSenderAddress() { return senderAddress; }
    public String getReceiverName() { return receiverName; }
    public String getReceiverAddress() { return receiverAddress; }
    public String getReceiverEmail() { return receiverEmail; }
    public String getDescription() { return description; }
    public String getTrackingId() { return trackingId; }
    public String getStatus() { return status; }


}
