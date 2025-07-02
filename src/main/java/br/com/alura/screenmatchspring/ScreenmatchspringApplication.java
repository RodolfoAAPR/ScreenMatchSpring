package br.com.alura.screenmatchspring;

import br.com.alura.screenmatchspring.main.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchspringApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(ScreenmatchspringApplication.class, args);
		System.out.println();

		Main main = new Main();
		main.showMenu();
	}

}
