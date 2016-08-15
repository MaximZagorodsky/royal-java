package com.transport.service;

import com.transport.model.Address;
import com.transport.model.GoogleDistance;

import java.util.List;

/**
 * Created by Proxima on 08.08.2016.
 */

public interface GoogleMapService {
    List<GoogleDistance> getDistance(List<Address> address, List<Address> unLoadingAddress);
}
