package ru.kpfu.itis.arifulina.net.model;

public class Service {
    private int serviceId;
    private String name;
    private int duration;

    public Service(int serviceId, String name, int duration) {
        this.serviceId = serviceId;
        this.name = name;
        this.duration = duration;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
