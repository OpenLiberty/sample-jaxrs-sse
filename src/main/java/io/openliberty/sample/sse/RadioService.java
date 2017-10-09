package io.openliberty.sample.sse;

import io.openliberty.sample.sse.model.Song;

public interface RadioService {

    Song getCurrentSong();

    void waitForSongToEnd();
}
