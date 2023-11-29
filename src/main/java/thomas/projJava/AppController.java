package thomas.projJava;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Repository
public class AppController {

    private final HeroDao heroDAO;

    public AppController(HeroDao heroDao) {
        this.heroDAO = heroDao;
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
    public List<Hero> getHeroList() {
        return heroDAO.findAll();
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
    @PutMapping("/hero")
    public void updateHeroInfo(@Valid @RequestBody Hero mectoub){
        heroDAO.save(mectoub);
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
    public void deleteHero(@PathVariable(value="id")int id){
        heroDAO.deleteById(id);
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
    public void createHero(@Valid @RequestBody Hero machin){
        heroDAO.save(machin);
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
    public Optional<Hero> getHeroById(@PathVariable Integer id) {
        return heroDAO.findById(id);
    }


}
