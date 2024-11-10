package ro.upt.ac.rezultate.companie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanieRepository extends JpaRepository<Companie,Integer>
{
	Companie findById(int id);
}
