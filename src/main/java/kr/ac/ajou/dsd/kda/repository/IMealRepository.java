package kr.ac.ajou.dsd.kda.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import kr.ac.ajou.dsd.kda.model.Meal;

/**
 * interface that defines auto generated methods by their names for accessing the repository
 * @author Torben Tietze <torben.tietze@googlemail.com>
 *
 */
public interface IMealRepository extends JpaRepository<Meal, UUID> {
	
	List<Meal> findAll();
	Page<Meal> findAll(Pageable pageRequest);
	List<Meal> findByEnglishNameOrTransliteratedNameContainingIgnoreCase(String englishName, String transliteratedName, Pageable pageRequest);

}
