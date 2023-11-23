package thomas.projJava;

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
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	@GetMapping("/hero")
	public String getHeroList() {
		Warrior hero = new Warrior(1, "Spozer");
		Wizard hero2 = new Wizard(2,"Rezops");
		String str=hero.toString()+hero2.toString();
		return str;
	}
	@PostMapping("/mabeat")
	public String setSmth(@RequestBody String elem){
		return elem;
	}
	@GetMapping("/hero/{id}")
	public String getHeroInfo(@PathVariable(value ="id")int id){
		Warrior hero = new Warrior(id, "Spozer");
		return hero.toString();
	}
	@PutMapping("/hero/{id}")
	public String updateHeroInfo(@PathVariable(value = "id")int id){
		Warrior hero = new Warrior(id,"Spozer");
		hero.setId(6);
		return hero.toString();
	}
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
			if (heroo.getId()==id){
				al.remove(heroo);
			}else {
				str+=heroo.toString();
			}
		}
		return str;
	}
}
