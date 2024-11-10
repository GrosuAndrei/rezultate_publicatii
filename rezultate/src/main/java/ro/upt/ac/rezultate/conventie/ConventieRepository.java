package ro.upt.ac.rezultate.conventie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventieRepository extends JpaRepository<Conventie,Integer>
{
	Conventie findById(int id);
}
