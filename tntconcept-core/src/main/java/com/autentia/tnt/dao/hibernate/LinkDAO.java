package com.autentia.tnt.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.businessobject.Link;
import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.SpringUtils;

public class LinkDAO extends HibernateManagerBase<Link> {

	/** Logger */
	private static final Log log = LogFactory.getLog(LinkDAO.class);
	
	public static LinkDAO getDefault(){
	    return (LinkDAO)SpringUtils.getSpringBean("daoLink");
	}
	
	/** 
	   * Constructor
	   * @deprecated do not construct DAOs alone: use Spring's declared beans
	   */
	public LinkDAO(){
		super(false);
	}
	
	public Link getById(int id) throws DataAccException {
		return super.getByPk(Link.class, id);
	}

	public List<Link> search(SortCriteria crit) throws DataAccException {
		return super.list(Link.class, crit);
	}

	public List<Link> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
		return super.search(Link.class, search, sort);
	}

	public void insert(Link dao) throws DataAccException {
		super.insert(dao);
		
	}

	public void insertWithoutUser(Link dao) throws DataAccException {
		super.insertWithoutUser(dao);
		
	}

	public void delete(Link obj) throws DataAccException {
		super.delete(obj, obj.getId());
	}

	@Override
	public void update(Link to) throws DataAccException {
		// TODO Auto-generated method stub
		
	}

}
