package fr.piroxxi.s2le.storage.api;

import fr.piroxxi.s2le.model.BasicEntity;

public interface Storage {

	<T extends BasicEntity> T getEntity(Class<T> clazz, String id);

	<T extends BasicEntity> void store(Class<T> clazz, T entity);

	<T extends BasicEntity> void delete(Class<T> clazz, String id);

	<T extends BasicEntity> Query<T> createQuery(Class<T> clazz);
	
}
