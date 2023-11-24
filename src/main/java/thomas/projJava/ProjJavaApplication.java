package thomas.projJava;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class ProjJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjJavaApplication.class, args);
	}

	@Operation(summary = "Hello world",description = "hello world",hidden = true)
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	@Operation(summary = "Lecture iste personnages",description = "Renvoie la liste des personnages")
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
	@GetMapping("/hero/{id}")
	public Hero getHeroInfo(@PathVariable(value ="id")int id){
		Warrior hero = new Warrior(id, "Spozer");
		return hero;
	}
	@Operation(summary = "Modification personnage",description = "Change les infos du personnage correspondant à l'id")
	@PutMapping("/hero/{id}")
	public Hero updateHeroInfo(@PathVariable(value = "id")int id){
		Warrior hero = new Warrior(id,"Spozer");
		hero.setId(6);
		return hero;
	}
	@Operation(summary = "Supprime le hero",description = "Supprime le personage correspondant à l'ID")
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
	@PostMapping("/hero")
	public String createHero(@RequestBody()Hero hero){
		return hero.getType();
	}
}
