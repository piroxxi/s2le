package fr.piroxxi.s2le.storage.api;

import java.util.List;

import fr.piroxxi.s2le.model.BasicEntity;

public interface Query<T extends BasicEntity> {

	void addFilter(Filter<T> filter);

	List<T> retrieveAsList();

}
