package com.utilities;

import com.entities.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmValidator implements Validator<Film> {
    @Override
    public List<String> validate(Film Film) {
        List<String> errors = new ArrayList<>();

        if (Film.getTitle().length() <= 10)
            errors.add("Title");
        if (Film.getYear() < 1960)
            errors.add("Year");
        if (Film.getDirector().length() <= 10)
            errors.add("Director");
        if (Film.getCast().length() <= 10)
            errors.add("Cast");
        if (Film.getDuration() <= 5)
            errors.add("Duration");

        return errors;
    }
}
