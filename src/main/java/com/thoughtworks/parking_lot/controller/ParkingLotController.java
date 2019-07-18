package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
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
    @GetMapping("/{id}")
    public ParkingLot getParkingLotByID(@PathVariable Long id){
        return parkingLotService.getParkingLotByID(id);
    }
    @PutMapping("/{id}")
    public ParkingLot updateParkingLot(@PathVariable Long id,@RequestBody ParkingLot parkingLot){
        return parkingLotService.updateParkingLot(id,parkingLot);
    }
    @PostMapping("/{id}/orders")
    public ParkingLot addParkingOrder(@PathVariable Long id,@RequestBody ParkingOrder parkingOrder) throws Exception {
        return parkingLotService.addParkingOrder(id,parkingOrder);
    }
    @DeleteMapping("/{lotId}/orders/{orderID}")
    public void closeParkingOrder(@PathVariable Long lotId,@PathVariable Long orderID){
         parkingLotService.closeParkingOrder(lotId,orderID);
    }

}
