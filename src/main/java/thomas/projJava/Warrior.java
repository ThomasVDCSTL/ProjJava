package thomas.projJava;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Classe enfant de Hero")
public class Warrior extends Hero{
    public Warrior(String name) {
        super(name, "Guerrier", 10);
    }
}
