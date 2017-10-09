# sample-jaxrs-sse

Here is a simple demo of Server Sent Events - a new part of the JAX-RS 2.1 spec.  This sample application is described in a
blog post that provides a [quick overview of SSE](https://www.linkedin.com/pulse/my-favorite-part-jax-rs-21-implementers-view-j-andrew-mccright/).

This sample creates a "radio" service similar to Pandora or Spotify that will randomly "play" songs.  The JAX-RS resource on
the server sends events that tell the client what song is currently playing, along with the artist performing the song and the
time (in milliseconds) that the song will last.  For this demo, all of the song lengths are significantly shortened.

There is a server-based client that will consume those song events and simply print them out as they are received.  This is
the approach used for the integration test.  There is also a Javascript-based client that can be used when deploying this
sample manually.

This sample uses an old build of OpenLiberty, but should be able to run in any Java EE application server that supports JAX-RS
2.1.

To run, either clone or download this project's source, and then enter: `mvn install`
