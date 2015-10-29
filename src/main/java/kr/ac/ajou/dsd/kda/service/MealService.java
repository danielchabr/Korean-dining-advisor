package kr.ac.ajou.dsd.kda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.ajou.dsd.kda.KoreanDiningAdvisorApplication;
import kr.ac.ajou.dsd.kda.model.IMeal;
import kr.ac.ajou.dsd.kda.repository.IMealRepository;

/**
 * 
 * @author Torben Tietze <torben.tietze@googlemail.com>
 *
 */
@Service
public class MealService implements IMealService{
	final private Logger log = Logger.getLogger(KoreanDiningAdvisorApplication.class.getName()); 
	
	private IMealRepository mealRepository;

	@Autowired
	MealService(IMealRepository mealRepository){
		this.mealRepository = mealRepository;
	}

	@Override
	public List<IMeal> getMeals(String query, int limit, int start) {
		if(query.isEmpty()) {
			log.info("getall() for empty query");
			return mealRepository.getall();
		}
		
		List<IMeal> mealsFilteredByQuery = new ArrayList<IMeal>();

		// TODO use find method
		mealsFilteredByQuery.addAll(mealRepository.getall());
//		mealsFilteredByQuery.addAll(mealRepository.findInAllNames(query));

		if(mealsFilteredByQuery.size() < limit) {
			return mealsFilteredByQuery;
		} else {
			if(limit >= start) {
//				just limit the mealsList
				List<IMeal> mealsFilteredAndLimited = new ArrayList<IMeal>(); 
				for(int i = 0; i < limit; i++ ) {
					mealsFilteredAndLimited.add(mealsFilteredAndLimited.get(i));
				}
				return mealsFilteredAndLimited;
			} else {
//				 limit and shift
				List<IMeal> mealsFilteredAndLimited = new ArrayList<IMeal>();
				for (int i = start; i < limit + start; i++) {
					mealsFilteredAndLimited.add(mealsFilteredAndLimited.get(i));
				}
				return mealsFilteredAndLimited;
			}
				
		}
	}

	@Override
	public IMeal getMealById(UUID uuid) {
		return mealRepository.getMeal(uuid);
	}

	@Override
	public void insertMeal(IMeal meal) {
		mealRepository.add(meal);
	}

	@Override
	public void updateMeal(IMeal meal) {
		mealRepository.update(meal);
	}

	@Override
	public void deleteMealById(UUID uuid) {
		mealRepository.delete(uuid);		
	}

}
