package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import Calculator.*;
import org.json.simple.JSONObject;

import java.util.GregorianCalendar;

// The Java class will be hosted at the URI path "/helloworld"
@Path("SolarPosition")
public class SolarPosition {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/json")
    public JSONObject getClichedMessage(@QueryParam("year") int year, @QueryParam("month") int month, @QueryParam("day") int day,
                                        @QueryParam("hour") int hour, @QueryParam("minute") int minute,
                                        @QueryParam("longitude") int longitude, @QueryParam("lattitude") int lattitude,
                                        @QueryParam("elevation") int elevation) {
        final GregorianCalendar dateTime = new GregorianCalendar(year, month, day, hour, minute);

        AzimuthZenithAngle position = SPA.calculateSolarPosition(
                dateTime,
                lattitude, // latitude (degrees)
                longitude, // longitude (degrees)
                elevation, // elevation (m)
                DeltaT.estimate(dateTime) // delta T (s)
        );
        System.out.println("SPA: " + position);
        SolarSon son = new SolarSon(position.getAzimuth(), position.getZenithAngle());

        JSONObject result = new JSONObject();
        result.put("azimuth", position.getAzimuth());
        result.put("zenith", position.getZenithAngle());

        return result;
    }
}