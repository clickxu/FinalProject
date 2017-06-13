package com.udacity.gradle;

public class JokeTeller {

    private static final String[] PAID_JOKES = {
            "Yeah, I'm a joke! You know what? you too!",
            "I think I'm a good joke, am I?",
            "Hi, my name is Good Joke.",
            "Really? you paid for me?"
    };

    private static final String[] FREE_JOKES = {
            "Yeah, I'm free!",
            "Free~dom~",
            "Do you mind to click the Ad?"
    };

    public static String tell(boolean isPaid) {
        String[] source = isPaid? PAID_JOKES: FREE_JOKES;
        int index = (int) Math.round(Math.random() * source.length) % source.length;
        return source[index];
    }
}
