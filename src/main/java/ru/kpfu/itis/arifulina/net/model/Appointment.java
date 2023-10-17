package ru.kpfu.itis.arifulina.net.model;

import java.sql.Timestamp;

public class Appointment {
    private int appointmentId;
    private String customerPhone;
    private Timestamp appointmentTime;
    private int masterId;
    private int serviceId;

    public Appointment(int appointmentId, String customerPhone, Timestamp appointmentTime, int masterId, int serviceId) {
        this.appointmentId = appointmentId;
        this.customerPhone = customerPhone;
        this.appointmentTime = appointmentTime;
        this.masterId = masterId;
        this.serviceId = serviceId;
    }

    public Appointment(String customerPhone, Timestamp appointmentTime, int masterId, int serviceId) {
        this.customerPhone = customerPhone;
        this.appointmentTime = appointmentTime;
        this.masterId = masterId;
        this.serviceId = serviceId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId() {
        this.serviceId = serviceId;
    }
}
