package services;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mitchell on 26/03/2017.
 */
public class TestService {
    SolarPosition sp;

    @Before
    public void setup() {
        sp = new SolarPosition();

    }

    @Test
    public void testService() {
        JSONObject answer = sp.getClichedMessage(2004, 5, 12, 11, 24, 5000, 5000, 11);
        assertEquals("{\"zenith\":96.79502581157911,\"azimuth\":65.31775809695122}", answer.toJSONString());
    }
}
