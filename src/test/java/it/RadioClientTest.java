package it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

public class RadioClientTest {

    static String warContext = System.getProperty("war.name", "SseRadioSample");
    static int port = Integer.getInteger("liberty.test.port", 9080);

    @Test
    public void testRandomSongData() throws Throwable {
        String clientUrl = "http://localhost:" + port + "/" + warContext + "/radio/client";// + port;
        System.out.println("Trying to invoke: " + clientUrl);
        GetMethod get = new GetMethod(clientUrl);
        HttpClient httpclient = new HttpClient();
        int result = httpclient.executeMethod(get);

        String response = get.getResponseBodyAsString();
        System.out.println(result + ": " + response);

        assertTrue(200 == result);
        assertTrue(response.contains("Played"));
        assertTrue(response.split("\n").length > 2); // expect more than one to be played
    }
}
