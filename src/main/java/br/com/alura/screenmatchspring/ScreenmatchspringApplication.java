package br.com.alura.screenmatchspring;

import br.com.alura.screenmatchspring.model.EpisodesData;
import br.com.alura.screenmatchspring.model.SeriesData;
import br.com.alura.screenmatchspring.service.ConsumoApi;
import br.com.alura.screenmatchspring.service.DataConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchspringApplication {

	public static void main(String[] args) throws Exception{

		SpringApplication.run(ScreenmatchspringApplication.class, args);
		System.out.println();

		var consumoApi = new ConsumoApi();
		var json = consumoApi.getData(
				"https://www.omdbapi.com/?t=supernatural&apikey=57134a81"
		);

		System.out.println(json);

		DataConverter converter = new DataConverter();
		SeriesData seriesData = converter.getData(json, SeriesData.class);
		System.out.println(seriesData);

		json = consumoApi.getData("https://www.omdbapi.com/?t=supernatural&season=1&episode=2&apikey=57134a81");
		EpisodesData episodesData = converter.getData(json, EpisodesData.class);
		System.out.println(episodesData);

		for (int i = 1; i <= seriesData.totalSeasons(); i++){
			json = consumoApi.getData("https://www.omdbapi.com/?t=supernatural&season=1&episode=2&apikey=57134a81");
		}

	}

}
