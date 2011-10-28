package fr.piroxxi.s2le.shared.model;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import fr.piroxxi.s2le.server.model.Category;

@ProxyFor(Category.class)
public interface CategoryProxy extends EntityProxy {
	String getParentId();
	String getCategoryName();
}
