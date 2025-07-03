package br.com.alura.screenmatchspring.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate releaseDate;

    public Episode(Integer seasonNumber, EpisodesData episodesData){
        this.season = seasonNumber;
        this.title = episodesData.title();
        this.episodeNumber = episodesData.episodeNumber();
        this.rating = Double.valueOf(episodesData.imdbRating());
        this.releaseDate = LocalDate.parse(episodesData.releaseDate());
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Double getImdbRating() {
        return rating;
    }

    public void setImdbRating(Double imdbRating) {
        this.rating = imdbRating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
