package fr.piroxxi.s2le.storage.fake;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gwt.dev.util.collect.Lists;

import fr.piroxxi.s2le.server.model.BasicEntity;
import fr.piroxxi.s2le.server.model.User;
import fr.piroxxi.s2le.storage.api.Query;
import fr.piroxxi.s2le.storage.api.Storage;

public class StorageImpl implements Storage {

	/*
	 * Ici, on a une Map database, qui contient des maps collection, qui
	 * contient les objets des differents types.
	 */
	private Map<Class<? extends BasicEntity>, Map<String, ? extends BasicEntity>> dataBase = new HashMap<Class<? extends BasicEntity>, Map<String, ? extends BasicEntity>>();

	public StorageImpl() {
		User piroxxi = new User("piroxxi", "piroxxi", "piroxxi@gmail.com");
		store(User.class, piroxxi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasicEntity> T getEntity(Class<T> clazz, String id) {
		System.out.println("Appel à getEntity (" + clazz + "," + id + ");");
		Map<String, T> collection = (Map<String, T>) dataBase.get(clazz);
		if (collection == null) {
			return null;
		}

		return collection.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasicEntity> void store(Class<T> clazz, T entity) {
		System.out.println("Appel à store (" + entity + ");");
		Map<String, T> collection = (Map<String, T>) dataBase.get(clazz);
		if (collection == null) {
			System.out.println("creation de la collection.");
			collection = new HashMap<String, T>();
			dataBase.put(clazz, collection);
		}

		if (entity.getId() == null) {
			// nb : les User on déja un id, qui est leur nom. :p
			System.out.println("c'est une entité toute neuve");
			entity.setId(UUID.randomUUID().toString());
			entity.setVersion(new Long(0));
		} else {
			if (entity.getVersion() == null) {
				System.out.println("l'entité à stocker n'a pas de version.");
				entity.setVersion(new Long(0));
			}

			T e2 = collection.get(entity.getId());
			if (e2 != null) { // shouldn't happend.
				if (e2.getVersion().compareTo(entity.getVersion()) == 1) {
					System.out
							.println("la version de l'entité à sauver ("
									+ entity.getVersion()
									+ ") est plus petite que la version de l'entité actuelle ("
									+ e2.getVersion() + ")");
					entity.setVersion(entity.getVersion() + 1);
				} else {
					System.out
							.println("la version de l'entité à sauver ("
									+ entity.getVersion()
									+ ") est plus grande ou égale à la version de l'entité actuelle ("
									+ e2.getVersion() + ")");
					return;
				}

			}

		}

		collection.put(entity.getId(), entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasicEntity> void delete(Class<T> clazz, String id) {
		System.out.println("Appel à delete (" + clazz + "," + id + ");");

		Map<String, T> collection = (Map<String, T>) dataBase.get(clazz);
		if (collection == null) {
			return;
		}

		collection.remove(id);
	}

	@Override
	public <T extends BasicEntity> Query<T> createQuery(Class<T> clazz) {
		return new QueryImpl<T>(clazz, this);
	}

	@SuppressWarnings("unchecked")
	protected <T extends BasicEntity> List<T> getAllEntities(Class<T> clazz) {
		Map<String, T> collection = (Map<String, T>) dataBase.get(clazz);
		if (collection == null) {
			return null;
		}

		return Lists.create(collection.values());
	}
}
