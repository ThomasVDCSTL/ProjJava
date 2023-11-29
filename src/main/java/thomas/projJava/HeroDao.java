package thomas.projJava;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroDao extends JpaRepository<Hero, Integer> {

    public List<Hero> findByHpGreaterThan(int hp);

}
