package fr.piroxxi.s2le.storage.morphia;

import fr.piroxxi.s2le.server.model.BasicEntity;
import fr.piroxxi.s2le.storage.api.Query;

public class Storage implements fr.piroxxi.s2le.storage.api.Storage {

	@Override
	public <T extends BasicEntity> T getEntity(Class<T> clazz, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasicEntity> void store(Class<T> clazz, T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends BasicEntity> void delete(Class<T> clazz, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends BasicEntity> Query<T> createQuery(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
