package io.openliberty.sample.sse.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;

import io.openliberty.sample.sse.model.Song;

@Path("/client")
public class RadioClient {

    private static final long listenTime = Long.getLong("radio.client.listenTimeMillis", 45000);
    private static final String LS = System.getProperty("line.separator");
    private static final int port = Integer.getInteger("default.http.port", 9080);
    private static final String warContext = System.getProperty("app.context.root", "SseRadioSample");

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response runAsClient() {
        List<Song> playList = new ArrayList<Song>();
        Client client = ClientBuilder.newClient();
        client.register(SongReader.class);
        WebTarget target = client.target("http://localhost:" + port + "/" + warContext + "/radio/songData");
        try (SseEventSource source = SseEventSource.target(target).build()) {
            source.register(new Consumer<InboundSseEvent>(){

            @Override
            public void accept(InboundSseEvent event) {
                System.out.println("Received new event: " + event);
                // called when we receive a new event
                Song song = event.readData(Song.class);
                playList.add(song);
                System.out.println("Now playing " + song.getTitle() + " by " + song.getArtist());
            }}, new Consumer<Throwable>(){

              @Override
              public void accept(Throwable t) {
                  // called when something went wrong
                  t.printStackTrace();
              }}, new Runnable() {

                @Override
                public void run() {
                    // called when our connection is closed
                    System.out.println("All done for now!");
                }});

            source.open();
            Thread.sleep(listenTime);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        return toResponse(playList);
    }

    private static Response toResponse(List<Song> playList) {
        StringBuilder sb = new StringBuilder("Played:");
        for (Song song : playList) {
            sb.append(LS).append(song.getTitle()).append(" by ").append(song.getArtist());
        }
        return Response.ok(sb.toString()).build();
    }
}
