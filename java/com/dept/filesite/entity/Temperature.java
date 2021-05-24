package com.dept.filesite.entity;

/**
 * @className: Temperature
 * @description: 体温实体类
 * @author: 201998
 * @create: 2020-04-25 16:38
 */
public class Temperature {
    private int id;
    private String office;
    private String name;
    private String temperatureAM;
    private String temperaturePM;
    private String forenoon;
    private String afternoon;
    private String note;
    private String today;
    private int mask;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperatureAM() {
        return temperatureAM;
    }

    public void setTemperatureAM(String temperatureAM) {
        this.temperatureAM = temperatureAM;
    }

    public String getTemperaturePM() {
        return temperaturePM;
    }

    public void setTemperaturePM(String temperaturePM) {
        this.temperaturePM = temperaturePM;
    }

    public String getForenoon() {
        return forenoon;
    }

    public void setForenoon(String forenoon) {
        this.forenoon = forenoon;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", office='" + office + '\'' +
                ", name='" + name + '\'' +
                ", temperatureAM='" + temperatureAM + '\'' +
                ", temperaturePM='" + temperaturePM + '\'' +
                ", forenoon='" + forenoon + '\'' +
                ", afternoo='" + afternoon + '\'' +
                ", note='" + note + '\'' +
                ", today='" + today + '\'' +
                ", mask='" + mask + '\'' +
                '}';
    }
}
