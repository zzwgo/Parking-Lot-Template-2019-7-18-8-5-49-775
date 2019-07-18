package com.thoughtworks.parking_lot.service.impl;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public void deleteParkingLot(Long id) {
        parkingLotRepository.deleteById(id);
    }

    @Override
    public List<ParkingLot> getParkingLots(int pageNum, int pageSize) {
        Pageable pageable= PageRequest.of(pageNum-1,pageSize);
        Page<ParkingLot> page=parkingLotRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public ParkingLot getParkingLotByID(Long id) {
        return parkingLotRepository.findById(id).orElse(null);
    }

    @Override
    public ParkingLot updateParkingLot(Long id, ParkingLot parkingLot) {
        ParkingLot current_lot=getParkingLotByID(id);
        current_lot.setCapacity(parkingLot.getCapacity());
        return parkingLotRepository.save(current_lot);
    }

    @Override
    public ParkingLot addParkingOrder(Long id, ParkingOrder parkingOrder) {
        ParkingLot current_lot=getParkingLotByID(id);
        int busyVacancy= (int) current_lot.getParkingOrders().stream().filter(ParkingOrder::isOpen).count();
        if(current_lot.getCapacity()-busyVacancy>0){
            current_lot.getParkingOrders().add(parkingOrder);
        }
        parkingOrder.setName(current_lot.getName());
        return  parkingLotRepository.save(current_lot);
    }

}
