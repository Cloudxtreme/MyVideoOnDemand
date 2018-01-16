package com.utilities;

import com.entities.Film;

import java.util.Comparator;

public class Comparators {

    public static class CompareYear implements Comparator<Film> {
        @Override
        public int compare(Film film, Film t1) {
            if (film.getYear() > t1.getYear()) {
                return 1;
            } else if (film.getYear() < t1.getYear()) {
                return -1;
            }
            return 0;
        }
    }

    public static class CompareTitle implements Comparator<Film> {
        @Override
        public int compare(Film film, Film t1) {
            if (film.compareTo(t1) > 0) {
                return 1;
            } else if (film.compareTo(t1) < 0) {
                return -1;
            }
            return 0;
        }
    }
}
