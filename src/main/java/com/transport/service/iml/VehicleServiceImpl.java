package com.transport.service.iml;

import com.transport.dto.VehicleDTO;
import com.transport.model.Vehicle;
import com.transport.repository.VehicleRepository;
import com.transport.service.VehicleService;
import com.transport.util.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Maksim Zagorodskiy on 7/14/2016.
 */
@Service
//TODO Разобраться с кучей селектов Хабернейта
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAvailableVehicleByPeriodByDate(Long date) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Set<Vehicle> allByPeriod = vehicleRepository.findAllByPeriod(TimeUtil.getStartOfTheDay(date), TimeUtil.getEndOfTheDay(date));
        vehicles.addAll(allByPeriod);
        return vehicles;
    }

    @Override
    public List<Vehicle> getAvailableVehicleByPeriodByDateAndHalfOfADay(Long day) {
        return null;
    }

    @Override
    public List<VehicleDTO> getPreparedListOfAvailableVehicleByDay(Long day) {
        return null;
    }

    @Override
    public List<VehicleDTO> getPreparedListOfAvailableAndPossibleVehicleByDay(Long day) {
        List<Vehicle> vehicles = getAvailableVehicleByPeriodByDate(day);

        return null;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> saveAll(List<Vehicle> list) {
        return vehicleRepository.save(list);
    }

    @Override
    @Transactional
    public List<Vehicle> findAvaliableVehicles(String period) {
        //TODO Реализовать подсчет количества в базе, а не в джава коде
        return vehicleRepository.findByIsAvaliableTrueAndBookingDateIsNullAndPeriodOfDayLike(period);
    }

    @Override
    public List<Vehicle> findAvaliableVehicles() {
        return vehicleRepository.findByIsAvaliableTrueAndBookingDateIsNull();
    }

    @Override
    public List<Vehicle> findAvaliableVehicleByType(String type) {
        return vehicleRepository.findByIsAvaliableTrueAndBookingDateIsNullAndTypeLike(type);
    }
}
