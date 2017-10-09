package io.openliberty.sample.sse.impl;

public class SongList {

    /* Note, most apps probably would use an external data store for data like
     * this, rather than putting it in the code.  This is done to make things a
     * little simpler for the sample.  Also, the song length in milliseconds is
     * much shorter than the actual length of the song, this is intended to more
     * rapidly show how server sent events work.
     */
    public static String[] SONGS = new String[]{
            "Handle With Care|The Traveling Wilburys|5000",
            "Ball and a Biscuit|The White Stripes|6000",
            "Stairway to Heaven|Led Zeppelin|7000",
            "Make You Better|The Decemberists|4000",
            "Scars|Felling Giants|4500",
            "Revolution|The Beatles|3500",
            "Hold On|Alabama Shakes|5000",
            "The Cave|Mumford and Sons|3800",
            "Don't Haunt This Place|The Rural Alberta Advantage|2500",
            "Come As You Are|Nirvana|4000",
            "Surfin' USA|The Beach Boys|2800",
            "One of Us|Joan Osbourne|4000",
            "Chasing Cars|Snow Patrol|4000"
    };
}
