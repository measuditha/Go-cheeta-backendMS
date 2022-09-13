package com.carbooking.web;

import com.carbooking.repo.Driver;
import com.carbooking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbooking.model.DriverDto;
import com.carbooking.repo.Driver;
import com.carbooking.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping(value = "/{driverId}")
    public Driver getDriver(@PathVariable String driverId){
        return driverService.getDriver(driverId);
    }

    @PutMapping
    public void updateDriver(@RequestBody DriverDto driverDto) {
        driverService.updateDriver(driverDto);
    }
}
