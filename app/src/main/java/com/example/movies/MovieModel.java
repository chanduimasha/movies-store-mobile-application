package com.example.movies;

public class MovieModel{
    private int id;
    private String movieName;
    private int movieYear;
    private String director;
    private String actors;
    private int ratings;
    private String reviews;
    private String favourites;

    public MovieModel(int id, String movieName, int movieYear, String director, String actors, int ratings, String reviews, String favourites) {
        this.id = id;
        this.movieName = movieName;
        this.movieYear = movieYear;
        this.director = director;
        this.actors = actors;
        this.ratings = ratings;
        this.reviews = reviews;
        this.favourites = favourites;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieYear=" + movieYear +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", ratings=" + ratings +
                ", reviews='" + reviews + '\'' +
                ", favourites='" + favourites + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getFavourites() {
        return favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }

}
