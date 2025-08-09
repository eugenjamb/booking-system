package com.smartbooker.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingResponse {
    private Long id;
    private String serviceType;
    private LocalDate date;
    private LocalTime time;
    private String userEmail;
    private boolean confirmed;

    // Getters
    public Long getId() {
        return id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
