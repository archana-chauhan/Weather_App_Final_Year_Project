package com.archana.weathertest;


public class Phrase {

    static String phrase;

    public static String Phraseget(String icon) {
        switch (icon) {
            case "01d":
                phrase = "Look how beautiful it is out it's time to have fun !";
                break;
            case "02d":
                phrase = "Cloudy, but estimated yourself happy, it's sunny !";
                break;
            case "03d":
                phrase = "Clouds but hey, at least it is not raining";
                break;
            case "04d":
                phrase = "Clouded sky, you avoid some rain";
                break;
            case "09d":
                phrase = "It's raining umbrella and coat are in the game, do the plants need water :)";
                break;
            case "10d":
                phrase = "Rain + sun = rainbow sky :D ";
                break;
            case "11d":
                phrase = "It's raining man OUOHOHOH";
                break;
            case "13d":
                phrase = "Wild Night !";
                break;
            case "01n":
                phrase = "Full moon, watch out for werewolves";
                break;
            case "02n":
                phrase = "It is night there are a few clouds";
                break;
            case "03n":
                phrase = "Neutral weather: clouds";
                break;
            case "04n":
                phrase = "Showers all shelters! No seriously, clouds nothing too";
                break;
            case "09n":
                phrase = "Purple rain, PURPLE RAIN ! ";
                break;
            case "10n":
                phrase = "Purple rain, PURPLE RAIN ! ";
                break;
            case "11n":
                phrase = "The spark between us, it happens so it falls from the sky";
                break;
            case "13n":
                phrase = "Battle of snowballs, Snowman !!!";
                break;

        }
        return phrase;
    }
}
