package io.openliberty.sample.sse.client;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import io.openliberty.sample.sse.model.Song;

@Provider
public class SongReader implements MessageBodyReader<Song> {

    @Override
    public boolean isReadable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
        return clazz.isAssignableFrom(Song.class);
    }

    @Override
    public Song readFrom(Class<Song> clazz, Type type, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> map, InputStream is) throws IOException, WebApplicationException {

        JsonReader reader = Json.createReader(is);
        JsonObject object = reader.readObject();
        String title = object.getString("title");
        String artist = object.getString("artist");
        long lengthMillis = object.getInt("lengthMillis");

        Song song = new Song(title, artist, lengthMillis);
        return song;
    }
}
