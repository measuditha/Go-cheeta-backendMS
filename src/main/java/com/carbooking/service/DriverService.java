package com.carbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbooking.model.DriverDto;
import com.carbooking.model.LocationDto;
import com.carbooking.repo.Driver;
import com.carbooking.repo.DriverRepository;
import com.carbooking.repo.Location;
import com.carbooking.repo.LocationRepository;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LocationRepository locationRepository;


    public Driver getDriver(String driverId) {
        return driverRepository.findById(driverId).get();
    }
    @Transactional
    public void updateDriver(DriverDto driverDto) {
        Driver driver = getDriver(driverDto);
        driverRepository.save(driver);
        List<LocationDto> driverLocations = driverDto.getOutletLocationDto();
        for (LocationDto locationDto : driverLocations) {
            Location location = getLocation(locationDto);
            locationRepository.save(location);
        }
    }
    private Location getLocation(LocationDto dto) {
        Location location = new Location();
        location.setCarRegistrationNo(dto.getCarId());
        location.setCity(dto.getCity());
        location.setCountry(dto.getCountry());
        location.setCustomerId(dto.getCustomerId());
        location.setDriverId(dto.getDriverId());
        location.setFlatNo(dto.getFlatNo());
        location.setPinCode(dto.getPinCode());
        location.setStreet(dto.getStreet());

        return location;
    }
    private Driver getDriver(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setContactNo(driverDto.getContactNo());
        driver.setEmailAddress(driverDto.getEmailAddress());
        driver.setName(driverDto.getName());

        return driver;
    }
}
