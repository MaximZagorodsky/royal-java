//package com.transport;
//
//import org.junit.experimental.categories.Category;
//
//package com.google.maps;
//
//        import static org.junit.Assert.assertEquals;
//        import static org.junit.Assert.assertTrue;
//
//        import com.google.maps.model.LatLng;
//        import com.google.mockwebserver.MockResponse;
//        import com.google.mockwebserver.MockWebServer;
//
//        import org.apache.http.NameValuePair;
//        import org.apache.http.client.utils.URLEncodedUtils;
//        import org.junit.Test;
//        import org.junit.experimental.categories.Category;
//
//        import java.net.URI;
//        import java.util.List;
//
//@Category(MediumTests.class)
//public class DistanceMatrixApiTest {
//
//    private GeoApiContext context = new GeoApiContext().setApiKey("AIzaFakeKey");
//
//    @Test
//    public void testLatLngOriginDestinations() throws Exception {
//        MockResponse response = new MockResponse();
//        response.setBody("");
//        MockWebServer server = new MockWebServer();
//        server.enqueue(response);
//        server.play();
//        context.setBaseUrlForTesting("http://127.0.0.1:" + server.getPort());
//
//        DistanceMatrixApi.newRequest(context)
//                .origins(new LatLng(-31.9522, 115.8589),
//                        new LatLng(-37.8136, 144.9631))
//                .destinations(new LatLng(-25.344677, 131.036692),
//                        new LatLng(-13.092297, 132.394057))
//                .awaitIgnoreError();
//
//        List<NameValuePair> actualParams =
//                parseQueryParamsFromRequestLine(server.takeRequest().getRequestLine());
//        assertParamValue("-31.95220000,115.85890000|-37.81360000,144.96310000", "origins", actualParams);
//        assertParamValue("-25.34467700,131.03669200|-13.09229700,132.39405700", "destinations", actualParams);
//
//        server.shutdown();
//    }
//
//    private void assertParamValue(String expectedValue, String paramName, List<NameValuePair> params)
//            throws Exception {
//        boolean paramFound = false;
//        for (NameValuePair pair : params) {
//            if (pair.getName().equals(paramName)) {
//                paramFound = true;
//                assertEquals(expectedValue, pair.getValue());
//            }
//        }
//        assertTrue(paramFound);
//    }
//
//    private List<NameValuePair> parseQueryParamsFromRequestLine(String requestLine) throws Exception {
//        // Extract the URL part from the HTTP request line
//        String[] chunks = requestLine.split("\\s");
//        String url = chunks[1];
//
//        return URLEncodedUtils.parse(new URI(url), "UTF-8");
//    }
//}