package thomas.projJava;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Classe enfant de Hero")
public class Warrior extends Hero{
    public Warrior(int id, String name) {
        super(id, name, "Guerrier", 10);
    }
}
