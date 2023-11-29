package thomas.projJava;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(example = """
        {
        "id": 1,
        "name": "Spozer",
        "type": "Guerrier",
        "hp": 10
        }
        """,description = "Classe abstraite parent de warrior et wizard définissant un personnage et ses caractéristiques")
@Entity
@Table(name = "hero")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String type;
    private int hp;

    public Hero(String name, String type, int hp) {
        this.name = name;
        this.type = type;
        this.hp = hp;
    }

    public Hero() {

    }

    @Override
    public String toString() {
        return "ID: "+this.id+" // Name: "+this.name+" // Type: "+this.type+" // HP: "+this.hp+" // ";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
