package io.openliberty.sample.sse.impl;

import io.openliberty.sample.sse.RadioService;
import io.openliberty.sample.sse.model.Song;

public class RandomRadioServiceImpl implements RadioService, Runnable {

    private static RandomRadioServiceImpl instance = new RandomRadioServiceImpl();

    private boolean running;
    private Song currentSong;

    public synchronized static RadioService getInstance() {
        if (!instance.isRunning()) {
            instance.start();
        }
        return instance;
    }

    @Override
    public Song getCurrentSong() {
        return currentSong;
    }

    @Override
    public synchronized void waitForSongToEnd() {
        try {
            wait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        running = true;
        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void run() {
        while (running) {
            String[] songData = SongList.SONGS[ (int) (Math.random() * SongList.SONGS.length)].split("\\|");
            synchronized(this) {
                currentSong = new Song(songData[0], songData[1], Long.parseLong(songData[2]));
                this.notifyAll();
            }

            try {
                Thread.sleep(currentSong.getLengthMillis());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
