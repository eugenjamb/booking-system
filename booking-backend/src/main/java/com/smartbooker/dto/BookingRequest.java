package com.smartbooker.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest {
    private LocalDate date;
    private LocalTime time;
    private String serviceType;
    private Long userId;

    // Getters
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
