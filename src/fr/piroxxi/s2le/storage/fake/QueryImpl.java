package fr.piroxxi.s2le.storage.fake;

import java.util.ArrayList;
import java.util.List;

import fr.piroxxi.s2le.model.BasicEntity;
import fr.piroxxi.s2le.storage.api.Filter;
import fr.piroxxi.s2le.storage.api.Query;

public class QueryImpl<T extends BasicEntity> implements Query<T> {

	private List<Filter<T>> filters;
	private final StorageImpl storage;
	private final Class<T> clazz;

	public QueryImpl(Class<T> clazz, StorageImpl storage) {
		this.clazz = clazz;
		this.storage = storage;
		filters = new ArrayList<Filter<T>>();
	}

	@Override
	public List<T> retrieveAsList() {
		ArrayList<T> results = new ArrayList<T>();
		List<T> entities = storage.getAllEntities(clazz);

		for (T entity : entities) {
			boolean isValid = true;
			for (Filter<T> f : filters) {
				if (!f.filter(entity)) {
					isValid = false;
					break;
				}
			}
			if (isValid) {
				results.add(entity);
			}
		}
		return results;
	}

	@Override
	public void addFilter(Filter<T> filter) {
		filters.add(filter);
	}

}
