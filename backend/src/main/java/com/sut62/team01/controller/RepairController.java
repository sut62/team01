package com.sut62.team01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;
import com.sut62.team01.entity.RoomBooking;
import com.sut62.team01.entity.Repair;
import com.sut62.team01.entity.DeviceName;
import com.sut62.team01.entity.DeviceType;
import com.sut62.team01.repository.RoomBookingRepository;
import com.sut62.team01.repository.RepairRepository;
import com.sut62.team01.repository.DeviceTypeRepository;
import com.sut62.team01.repository.DeviceNameRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RepairController{
    
    @Autowired
    private final RepairRepository repairRepository;
    
    @Autowired
    private  DeviceTypeRepository deviceTypeRepository;

    @Autowired
    private DeviceNameRepository deviceNameRepository;
    

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    RepairController (RepairRepository repairRepository){
       this.repairRepository = repairRepository;
    }
    

    @GetMapping("/Repair")
    public Collection<Repair> getAllRepairs() { // collection ส่งกลับทั้งหมด
        return repairRepository.findAll().stream().collect(Collectors.toList());

    }

    @PostMapping("/Repair/{roomBooking_id}/{DeviceType_id}/{DeviceName_id}/{list}")
    public Repair newRepair(Repair newRepair,
    @PathVariable String list,
    @PathVariable long roomBooking_id,
    @PathVariable long DeviceType_id,
    @PathVariable long DeviceName_id)

    {
        
        DeviceType type = deviceTypeRepository.findById(DeviceType_id);
        DeviceName name = deviceNameRepository.findById(DeviceName_id);
        RoomBooking enrolled = roomBookingRepository.findById(roomBooking_id);

        newRepair.setEnrolled(enrolled);
        newRepair.setList(list);
        newRepair.setRepairDate(new Date());
        newRepair.setType(type);
        newRepair.setName(name);

        return repairRepository.save(newRepair);


    }

    


}