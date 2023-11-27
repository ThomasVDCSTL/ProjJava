package thomas.projJava;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
@RestController
public class ProjJavaApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProjJavaApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8081"));
		app.run(args);
	}

	@Operation(summary = "Hello world",description = "hello world",hidden = true)
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@Operation(summary = "Lecture liste personnages",description = "Renvoie la liste des personnages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liste de héro renvoyée",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Hero.class))}),
			@ApiResponse(responseCode = "400", description = "Liste non envoyée, le format n'est pas bon",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "liste de héro non-envoyée la liste n'existe pas",
					content = @Content)})
	@GetMapping("/hero")
	public ArrayList<Hero> getHeroList() {
		Warrior hero = new Warrior(1, "Spozer");
		Wizard hero2 = new Wizard(2,"Rezops");
		ArrayList<Hero> al = new ArrayList<>();
		al.add(hero);
		al.add(hero2);
		return al;
	}

	@Operation(summary = "Lecture personnage",description = "Renvoie un le personage correspondant à l'ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Héros retourné",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Hero.class))}),
			@ApiResponse(responseCode = "400", description = "Héros non retourné, le format n'est pas bon",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Héros non retourné car la liste n'existe pas",
					content = @Content)})
	@GetMapping("/hero/{id}")
	public Hero getHeroInfo(@PathVariable(value ="id")int id){
		Warrior hero = new Warrior(id, "Spozer");
		return hero;
	}

	@Operation(summary = "Modification personnage",description = "Change les infos du personnage correspondant à l'id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Héros mis à jour dans la liste",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Hero.class))}),
			@ApiResponse(responseCode = "400", description = "Héros non mis à jour, le format n'est pas bon",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Héros non mis à jour car la liste n'existe pas",
					content = @Content)})
	@PutMapping("/hero/{id}")
	public Hero updateHeroInfo(@PathVariable(value = "id")int id){
		Warrior hero = new Warrior(id,"Spozer");
		hero.setId(6);
		return hero;
	}

	@Operation(summary = "Supprime le hero",description = "Supprime le personage correspondant à l'ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Héros supprimé de la liste",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Hero.class))}),
			@ApiResponse(responseCode = "400", description = "Héros non supprimé, le format n'est pas bon",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Héros non supprimé car la liste n'existe pas",
					content = @Content)})
	@DeleteMapping("/hero/{id}")
	public String deleteHero(@PathVariable(value="id")int id){
		String str="";
		Warrior hero = new Warrior(1, "Spozer");
		Wizard hero2 = new Wizard(2,"Rezops");
		ArrayList<Hero> al = new ArrayList<Hero>();
		al.add(hero);
		al.add(hero2);
		for (Hero heroo:al
			 ) {
			if (heroo.getId()!=id){
				str+=heroo.toString();
			}
		}
		return str;
	}

	@Operation(summary = "Création personnage", description = "Créer un nouveau personnage avec auto-increment id, avec les infos du request body")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Héros créé ",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = Hero.class))}),
			@ApiResponse(responseCode = "400", description = "Héros non créé, le format n'est pas bon",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Héros non créé car la liste n'existe pas",
					content = @Content)})
	@PostMapping("/hero")
	public Hero createHero(@RequestBody()String name){
		int rdm = (int) (Math.random()*2)+1;
		Hero perso;
		if (rdm==1){
			perso = new Warrior(3,name);
			return perso;
		}
		perso = new Wizard(3,name);
		return perso;
	}
}
