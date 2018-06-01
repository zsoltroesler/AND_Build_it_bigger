package com.example.android.javalibrary;

public class JokeProvider {

    public static final String [] JOKES = {
            "An Android app walks into a bar. Bartender asks, \"Can I get you a drink?\" " +
                    "The app looks disappointed and says, \"That wasn't my intent.\"",
            "Q. Why is a bee\'s hair always sticky? A. Because it uses a honey comb!",
            "Q: Definition of an upgrade? A: Take old bugs out, put new ones in.",
            "Chuck Norris runs Android on his I-Phone",
            "A pregnant fragment walks into a bar. The bartender says, \"Whoa! Whoa! We don't support nested fragments here!\""
    };

    public String getJoke() {
        // Pick a random joke from String[] JOKES between 0 index and length of the array
        int randomIndex = (int) (Math.random() * JOKES.length);

        return JOKES[randomIndex];
    }
}
