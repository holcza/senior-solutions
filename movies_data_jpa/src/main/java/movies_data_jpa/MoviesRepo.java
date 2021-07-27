package movies_data_jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface MoviesRepo extends JpaRepository<Movie,Long> {

}
