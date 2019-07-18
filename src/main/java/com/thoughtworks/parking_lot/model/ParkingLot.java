package com.thoughtworks.parking_lot.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @Min(value = 0)
    private int capacity;
    private String location;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parkID")
    private List<ParkingOrder> parkingOrders;

    public List<ParkingOrder> getParkingOrders() {
        return parkingOrders;
    }

    public void setParkingOrders(List<ParkingOrder> parkingOrders) {
        this.parkingOrders = parkingOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public boolean isFull(){
        int busyVacancy= (int) getParkingOrders().stream().filter(ParkingOrder::isOpen).count();
        return  getCapacity()-busyVacancy<=0;
    }
}
