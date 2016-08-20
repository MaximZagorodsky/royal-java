package com.transport.service.iml;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import com.transport.model.Address;
import com.transport.model.GoogleDistance;
import com.transport.service.GoogleMapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Proxima on 08.08.2016.
 */
@Service
public class GoogleMapServiceImpl implements GoogleMapService {

    @Value("api_key")
    private String API_KEY;

    @Override
    public List<GoogleDistance> getDistance(List<Address> loadingAddress, List<Address> unLoadingAddress) {
        System.out.println("GetDistance");
        System.out.println(loadingAddress);
        System.out.println(unLoadingAddress);

        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDZ-VaWEjQbT64fZtb80JfOzJvH6KPcbH0");
        //<editor-fold desc="Description">
        //        GeocodingResult[] results = GeocodingApi.geocode(context,
//                "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//        System.out.println(results[0].formattedAddress);
        DistanceMatrix matrix = null;
        //</editor-fold>
        String[] origins = createArrayFromList(loadingAddress);
        String[] destinations = createArrayFromList(unLoadingAddress);
        System.out.println("origins:" + Arrays.toString(origins));
        System.out.println("destinetions:" + Arrays.toString(destinations));
        try {
            matrix = DistanceMatrixApi.newRequest(context)
                    .origins(origins)
                    .destinations(destinations)
                    .mode(TravelMode.DRIVING).units(Unit.IMPERIAL).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getResultsOfDistanceRequest(matrix);
    }

    private String[] createArrayFromList(List<Address> list) {
        String[] arrayOfAddresses = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayOfAddresses[i] = list.get(i).getAddress() + ", " + list.get(i).getZip();
        }
        return arrayOfAddresses;
    }

    private List<GoogleDistance> getResultsOfDistanceRequest(DistanceMatrix matrix) {
        //<editor-fold desc="printLn">
//        System.out.print("distance: " + matrix.rows[0].elements[0].distance.humanReadable);
//        System.out.print("duration:" + matrix.rows[0].elements[0].duration.humanReadable + ",\n");
//        System.out.print("distance: " + matrix.rows[0].elements[1].distance.humanReadable);
//        System.out.println("duration:" + matrix.rows[0].elements[1].duration.humanReadable);
//
//        System.out.print("distance row1: " + matrix.rows[1].elements[0].distance.humanReadable);
//        System.out.print("duration row1:" + matrix.rows[1].elements[0].duration.humanReadable+ ",\n");
//        System.out.print("distance row1: " + matrix.rows[1].elements[1].distance.humanReadable);
//        System.out.println("duration row1:" + matrix.rows[1].elements[1].duration.humanReadable);
        //</editor-fold>
        List<GoogleDistance> googleDistances = new ArrayList<>();
        for (int i = 0; i < matrix.rows.length; i++) {
            GoogleDistance googleDistance = new GoogleDistance();
            googleDistance.setDistance(matrix.rows[0].elements[0].distance.humanReadable);
            googleDistance.setDuration(matrix.rows[0].elements[0].duration.humanReadable);
            googleDistance.setFrom(matrix.originAddresses[0]);
            googleDistance.setTo(matrix.destinationAddresses[0]);

//            System.out.print("distance: " + matrix.rows[0].elements[0].distance.humanReadable);
//        System.out.print("duration:" + (matrix.rows[0].elements[0].duration.humanReadable));
            googleDistances.add(googleDistance);
        }
        return googleDistances;
    }
}
