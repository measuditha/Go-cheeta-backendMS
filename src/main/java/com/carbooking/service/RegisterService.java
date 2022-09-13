package com.carbooking.service;

import java.util.ArrayList;
import java.util.List;

import com.carbooking.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carbooking.model.LocationDto;
import com.carbooking.model.RegisterDto;

@Component
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    public Register add(RegisterDto dto) {
    	System.out.println("dto" + dto);
        Register register = getRegister(dto);
        Customer customer = getCustomer(dto);
        Admin admin = getAdmin(dto);
        Driver driver = getDriver(dto);

        Register registerResponse = registerRepository.save(register);
        if(customer != null) {
        	System.out.println("customer......" );
            customerRepository.save(customer);
            //Location location = getCustomerLocation(dto);
            //locationRepository.save(location);
        }
        if(driver != null) {
        	System.out.println("deealer......" );
            driverRepository.save(driver);
			/*
			 * List<Location> locations =
			 * getDriverLocations(dto.getDriverDto().getOutletLocationDto()); for (Location
			 * location: locations) { locationRepository.save(location); }
			 */
        }
        return registerResponse;
        
    }

    private Driver getDriver(RegisterDto dto) {
    	if(dto.getDriverDto().getName() == null) {
    		return  null;
    	}
        if(dto.getDriverDto() != null) {
            Driver driver = new Driver();
            driver.setContactNo(dto.getDriverDto().getContactNo());
            driver.setDriverId(dto.getDriverDto().getDriverId());
            driver.setEmailAddress(dto.getDriverDto().getEmailAddress());
            driver.setName(dto.getDriverDto().getName());
            //driver.setOutletAddress(getDriverLocations(dto.getDriverDto().getOutletLocationDto()));
            return driver;
        }
        return null;
    }

    private Admin getAdmin(RegisterDto dto) {
        if(dto.getAdminDto().getName() == null) {
            return  null;
        }
        if(dto.getAdminDto() != null) {
            Admin admin = new Admin();
            admin.setContactNo(dto.getAdminDto().getContactNo());
            admin.setAdminId(dto.getAdminDto().getAdminId());
            admin.setEmailAddress(dto.getAdminDto().getEmailAddress());
            admin.setName(dto.getAdminDto().getName());
            //admin.setOutletAddress(getAdminLocations(dto.getAdminDto().getOutletLocationDto()));
            return admin;
        }
        return null;
    }

    private List<Location> getDriverLocations(List<LocationDto> outletLocationDto) {
        List<Location> locations = new ArrayList<>();
        for (LocationDto locationDto: outletLocationDto) {
            Location location = getLocation(locationDto);
            locations.add(location);
        }
        return locations;
    }

    private Customer getCustomer(RegisterDto dto) {
    	if(dto.getCustomerDto().getName() == null) {
    		return null;
    	}
        if(dto.getCustomerDto() != null) {
            Customer customer = new Customer();
            customer.setCustomerId(dto.getCustomerDto().getCustomerId());
            customer.setContactNo(dto.getCustomerDto().getContactNo());
            customer.setEmailAddress(dto.getCustomerDto().getEmailAddress());
            customer.setName(dto.getCustomerDto().getName());
            //customer.setLocation(getCustomerLocation(dto));
            return customer;
        }
        return null;
    }

    private Location getCustomerLocation(RegisterDto dto){
        LocationDto locationDto = dto.getCustomerDto().getLocationDto();
        return getLocation(locationDto);
    }

    private Location getLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setStreet(locationDto.getStreet());
        location.setPinCode(locationDto.getPinCode());
        location.setFlatNo(locationDto.getFlatNo());
        location.setCity(locationDto.getCity());
        location.setCountry(locationDto.getCountry());
        if(locationDto.getCustomerId() != null)
            location.setCustomerId(locationDto.getCustomerId());
        if(locationDto.getDriverId() != null)
            location.setDriverId(locationDto.getDriverId());
        return location;
    }

    private Register getRegister(RegisterDto dto) {
        Register register = new Register();
        register.setUserType(dto.getUserType());
        register.setUsername(dto.getUsername());
        register.setPassword(dto.getPassword());

        return register;
    }

}