package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;

import java.util.List;

public interface ParkingLotService {
    ParkingLot addParkingLot(ParkingLot parkingLot);
    void deleteParkingLot(Long id);
    List<ParkingLot> getParkingLots(int pageNum, int pageSize );
    ParkingLot getParkingLotByID(Long id);
    ParkingLot updateParkingLot(Long id, ParkingLot parkingLot);

    ParkingLot addParkingOrder(Long id, ParkingOrder parkingOrder);
}
