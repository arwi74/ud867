package com.udacity.gradle.builditbigger.backend;

import com.example.javajokes.Joker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        Joker joker = new Joker();
        Joke joke =  new Joke();
        joke.setJokeString(joker.getJoke());
        return joke;
    }

}
