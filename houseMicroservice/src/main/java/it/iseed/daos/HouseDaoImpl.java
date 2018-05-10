package it.iseed.daos;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.House;

@Repository
@Transactional
public class HouseDaoImpl implements HouseDao{

	private static final String SELECT = "SELECT b FROM House b WHERE 1 = 1";

	private static final String DEFAULT_RANGE = "2";


	@PersistenceContext
	public EntityManager entityManager;

	public House findById(int id){
		return entityManager.find(House.class, id);
	}

	@Override
	public List<House> findByCityName(String cityName) {
		TypedQuery<House> query = entityManager.createQuery("SELECT h FROM House h WHERE h.city.name = :name", House.class);
		query.setParameter("name", cityName);
		return query.getResultList();
	}

	@Override
	public List<House> findByFilterParametersAndCityName(Map<String, String> parameters) {
		TypedQuery<House> query = entityManager.createQuery(filterAndCityNameQueryBuilder(parameters), House.class);
		return query.getResultList();
	}
	
	@Override
	public List<House> findByFilterParametersAndMapCoordinates(Map<String, String> parameters) {
		TypedQuery<House> query = entityManager.createQuery(filterAndMapCoordinatesQueryBuilder(parameters), House.class);
		return query.getResultList();
	}

	private String filterQueryBuilder(Map<String, String> parameters) {
		String minPrice, maxPrice, minArea, maxArea, type, E_class;

		minPrice = parameters.get("minPrice");
		maxPrice = parameters.get("maxPrice");
		minArea = parameters.get("minArea");
		maxArea = parameters.get("maxArea");
		type = parameters.get("type");
		E_class = parameters.get("E_class");

		StringBuilder query = new StringBuilder();

		query.append(SELECT);

		if(minPrice != null && maxPrice != null && !minPrice.equals("") && !maxPrice.equals("")) {
			query.append(" and");
			query.append(" b.price>=" + minPrice);
			query.append(" and");
			query.append(" b.price<=" + maxPrice);
		}

		if(minArea != null && maxArea != null && !minArea.equals("") && !maxArea.equals("")) {
			query.append(" and");
			query.append(" b.area>=" + minArea);
			query.append(" and");
			query.append(" b.area<=" + maxArea);
		}

		if(type != null && !type.equals(""))
			query.append(" and b.type='" + type + "'");

		if(E_class != null && !E_class.equals(""))
			query.append(" and b.E_class='" + E_class + "'");

		return query.toString();

	}

	private String filterAndCityNameQueryBuilder(Map<String, String> parameters) {
		StringBuilder query = new StringBuilder();

		query.append(filterQueryBuilder(parameters));
		
		String cityName = parameters.get("city");
		
		query.append(" and b.city.name = '" + cityName +"'");
		
		return query.toString();

	}

	private String filterAndMapCoordinatesQueryBuilder(Map<String, String> parameters) {		
		StringBuilder query = new StringBuilder();

		query.append(filterQueryBuilder(parameters));
		
		String range, latitude, longitude;
		latitude = parameters.get("latitude");
		longitude = parameters.get("longitude");
		range = parameters.get("range");

		if(range != null && !range.equals(""))
			query.append(zoneQueryBuilder(longitude, latitude, range));
		else
			query.append(zoneQueryBuilder(longitude, latitude, DEFAULT_RANGE));
			
		return query.toString();
		
	}

	private String zoneQueryBuilder(String longitude, String latitude, String range) {
		return " and (POW ( ( 69.1 * ( b.longitude - " + longitude + " ) * cos( " + latitude + " / 57.3 ) ) , 2 ) " +
				"+ POW( ( 69.1 * ( b.latitude - " + latitude + " ) ) , 2 ) ) < " + range;
	}

}

