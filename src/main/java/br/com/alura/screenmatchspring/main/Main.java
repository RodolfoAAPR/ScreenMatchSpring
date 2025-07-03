package br.com.alura.screenmatchspring.main;

import br.com.alura.screenmatchspring.model.Episode;
import br.com.alura.screenmatchspring.model.EpisodesData;
import br.com.alura.screenmatchspring.model.SeasonData;
import br.com.alura.screenmatchspring.model.SeriesData;
import br.com.alura.screenmatchspring.service.ApiService;
import br.com.alura.screenmatchspring.service.DataConverter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private ApiService apiService = new ApiService();
    private DataConverter converter = new DataConverter();

    private final Scanner scanner = new Scanner(System.in);

    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=57134a81";

    public void showMenu(){
        System.out.print("Insira o nome da série para busca: ");
        var serieName = scanner.nextLine();

        var json = apiService.getData(URL + serieName.replace(" ", "+") + API_KEY);


        SeriesData seriesData = converter.getData(json, SeriesData.class);
        System.out.println(seriesData);

        List<SeasonData> seasons = new ArrayList<>();

        for(int i = 1; i < seriesData.totalSeasons(); i++){
            json = apiService.getData(URL + serieName.replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = converter.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
        seasons.forEach(t -> t.listEpisodes().forEach(e -> System.out.println(e.title())));

        List<EpisodesData> episodesData = seasons
                .stream()
                .flatMap(s -> s.listEpisodes().stream())
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Top 5 Episódios!");
        episodesData.stream()
                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodesData::imdbRating).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episode> episodes = seasons
                .stream()
                .flatMap(s -> s.listEpisodes().stream()
                        .map(d -> new Episode(s.season(), d))
                ).collect(Collectors.toList());
    }
}
