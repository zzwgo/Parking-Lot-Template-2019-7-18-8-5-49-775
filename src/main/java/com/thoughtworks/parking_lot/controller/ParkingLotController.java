package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {
    private static final int defaultPageSize=15;
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping
    public ParkingLot addParkingLot(@RequestBody ParkingLot parkingLot){
        return parkingLotService.addParkingLot(parkingLot);
    }
    @DeleteMapping("/{id}")
    public void deleteParkingLot(@PathVariable Long id){
        parkingLotService.deleteParkingLot(id);
    }
    @GetMapping(value = "",params ={"page"})
    public List<ParkingLot> getParkingLots(@RequestParam int page){
        return parkingLotService.getParkingLots(page,defaultPageSize);
    }

}
