package ro.upt.ac.rezultate.publicatii;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicatieRepository extends JpaRepository<Publicatie,Integer> {
	Publicatie findById(int id);
}
