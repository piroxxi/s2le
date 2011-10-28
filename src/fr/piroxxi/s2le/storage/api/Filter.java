package fr.piroxxi.s2le.storage.api;

import fr.piroxxi.s2le.server.model.BasicEntity;


public interface Filter<T extends BasicEntity> {

	/**
	 * Renvoie vrai si l'entité doit être gardée.
	 */
	boolean filter(T entitie);

}
