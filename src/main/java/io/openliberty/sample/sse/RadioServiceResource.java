package io.openliberty.sample.sse;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import io.openliberty.sample.sse.impl.RandomRadioServiceImpl;
import io.openliberty.sample.sse.model.Song;

@ApplicationPath("/radio")
@Path("/songData")
public class RadioServiceResource extends Application {

    final static RadioService radioService = RandomRadioServiceImpl.getInstance();

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void songDataStream(@Context SseEventSink eventSink, @Context Sse sse) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Song song = radioService.getCurrentSong();
                while (song != null) {
                    System.out.println("Sending song: " + song);
                        OutboundSseEvent event = sse.newEventBuilder()
                            .mediaType(MediaType.APPLICATION_JSON_TYPE)
                            .data(Song.class, song)
                            .build();
                        eventSink.send(event);
                        radioService.waitForSongToEnd();
                        song = radioService.getCurrentSong();
                }
            }
        };
        new Thread(r).start();
    }
}
