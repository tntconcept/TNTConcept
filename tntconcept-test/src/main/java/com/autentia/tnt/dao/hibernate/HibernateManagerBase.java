/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * HibernateManagerBase.java
 */
package com.autentia.tnt.dao.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.IDataAccessObject;
import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.util.HibernateUtil;

/**
 * Clase abstracta base de todas las clases que resuelven la l�gica de negocio de la aplicaci�n
 * Hace trabajo com�n a la hora de acceder a base de datos mediante hibernate
 *
 * @author <a href="www.autentia.com">AUTENTIA</a>
 */

public abstract class HibernateManagerBase<T extends ITransferObject> implements IDataAccessObject<T>
{

  /** El objeto logger */
  private static final Log log = LogFactory.getLog(HibernateManagerBase.class);

  /**
   * Get all existing objects of an entity
   * @param <T> type of entity the manager works with
   * @param clazz entity class
   * @param crit sort criteria (can be null)
   * @return the list of all existing object ordered by criteria
   * @throws DataAccException
   */
  protected List<T> list( Class<T> clazz, SortCriteria crit ) throws DataAccException
  {
    return (List<T>)getAll( clazz.getName(), crit );
  }

  /**
   * Get DAOs matching searching criteria
   * @param <T> type of entity the manager works with
   * @param clazz entity class
   * @param search search criteria
   * @param sort sort criteria (can be null)
   * @return the list of objects matching the criteria
   * @throws DataAccException
   */
  protected List<T> search( Class<T> clazz, SearchCriteria search, SortCriteria sort ) throws DataAccException
  {
    log.debug("search - clazz='"+clazz+"' search='"+search+"' sort='"+sort+"'");

    List<T> ret = null;

    try
    {
      // Append FROM condition
      StringBuilder query = new StringBuilder("FROM ");
      query.append(clazz.getName());

      query.append( " p" );

      // Append WHERE condition
			if( search!=null )
			{
				query.append( " " );
				query.append( search.getHQL() );
			}

      // Append ORDER BY condition
      if(sort!=null)
      {
        query.append( " " );
        query.append(sort.getHQL());
      }

      // Make query
      ret = (List<T>) search( query.toString(), (search==null) ? new Object[0] : search.getArguments() );

      log.debug("search - found "+ret.size()+" objects of type "+clazz.getName() );
    }
    catch(Exception ex)
    {
      log.error("search - error searching",ex);
      throw new DataAccException("Error searching "+clazz.getName()+" objects",ex);
    }

    return ret;
  }

  /**
   * Perform a custom Hibernate HQL search.
   * @param hql the hql query
   * @oaram args query parameters
   * @return a List of Objects with the result
   * @throws DataAccException
   */
  @SuppressWarnings("unchecked")
  protected List search( String hql, Object...args ) throws DataAccException
  {
    log.debug("search - hql='"+hql+"'");

    Session session = null;
    List<Object> ret = null;

    try
    {
      session = HibernateUtil.getSessionFactory().getCurrentSession();
      if(!session.getTransaction().isActive()) {
    	  session.beginTransaction();
      }

      Query q = session.createQuery( hql );
      for( int i=0 ; i<args.length ; i++ )
      {
        if( args[i] instanceof Collection )
        {
          q.setParameterList( "arg"+i, (Collection)args[i] );
        }
        else
        {
          q.setParameter( "arg"+i, args[i] );
        }
      }
      ret = q.list();

      log.debug("search - found "+ret.size()+" objects" );
    }
    catch(Exception ex)
    {
      log.error("search - error searching",ex);
      throw new DataAccException("Error searching '"+hql+"'",ex);
    }

    return ret;
  }

  /* Sin revisar **********************************************************************************************/

  /** El objeto logger para el cach� */
  private static Log loggerCache = LogFactory.getLog(HibernateManagerBase.class);

  /** Cach� de objetos de base de datos */
  private static Hashtable cache = new Hashtable();

  /** Indicador de si las operaciones que se van a efectuar en esta instancia van a interactuar con el cach�*/
  protected boolean cacheable = false;

  /**
   * Crea una nueva instancia de esta clase
   */
  protected HibernateManagerBase()
  {}

  /**
   * Crea una nueva instancia de esta clase
   * @param cacheable indica si las operaciones en esta instancia deben tener en
   * cuenta la cach�
   */
  protected HibernateManagerBase(boolean cacheable)
  {
    this.cacheable=cacheable;
  }

  public void setCacheable(boolean cacheable)
  {
    this.cacheable=cacheable;
  }
  public boolean isCacheable()
  {
    return cacheable;
  }

  /**
   * Devuelve un objeto de negocio por clave primaria.
   *
   * @param pk Una instancia de la clave primaria
   * @return Una instancia del objeto recuperado de base de datos o null en el caso de no
   *         encontrar ning�n objeto con la clave primaria especificada
   */
  protected <T extends Object> T loadByPk(Class<T> theClass, Serializable pk) throws DataAccException
  {
    T objResult = null;
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    objResult = loadByPk(theClass,pk,session);
    return objResult;
  }

  /**
   * Devuelve un objeto de negocio por clave primaria.
   *
   * @param obj Instancia vac�a del objeto que se desea recuperar
   * @param pk Una instancia de la clave primaria
   * @param session sesi�n de hibernate
   * @return Una instancia del objeto recuperado de base de datos o null en el caso de no
   *         encontrar ning�n objeto con la clave primaria especificada
   */
  protected <T extends Object> T loadByPk(Class<T> theClass, Serializable pk, Session session) throws DataAccException
  {
    log.debug("loadByPk");
    T objResult = null;
    try
    {
      objResult = (T)session.load(theClass, pk);
      log.debug("Objeto con clave '"+ pk + "' recuperado");
      return (T)objResult;
    }
    catch(Exception ex)
    {
      String msg = "Error recuperando el objeto de tipo '" + theClass + "' con clave primaria '" + pk + "': ";
      log.error("Error recuperando el objeto con clave '" + pk + "': "+ msg);
      throw new DataAccException(msg, ex);
    }
  }

  /**
   * Devuelve un objeto de negocio por clave primaria
   *
   * @param pk Una instancia de la clave primaria
   * @return Una instancia del objeto recuperado de base de datos
   */
  protected Object getByPk(Serializable pk) throws DataAccException
  {
    throw new DataAccException("HibernateManagerBase.getByPk. M�todo no soportado por esta clase");
  }

  /**
   * Inserta un objeto de negocio en base de datos
   *
   * @param obj a insertar
   */
  public void insert(T obj) throws DataAccException
  {
    log.debug("insert");
    Session session = null;
    try
    {
      session = HibernateUtil.getSessionFactory().getCurrentSession();
      Date d = new Date();
      obj.setInsertDate(d);
      obj.setUpdateDate(d);
      session.save(obj);
      log.debug("objeto correctamente insertado");
    }
    catch(Exception ex)
    {
      log.error("insert - exception",ex);
      StringBuffer msg = new StringBuffer("Error insertando el objeto: ");
      msg.append(obj);

      throw new DataAccException(msg.toString(),ex);
    }

  }

  /**
   * Actualiza una objeto de negocio en base de datos
   *
   * @param obj el objeto de negocio a actualizar
   */
  public void update(ITransferObject obj,Serializable pk) throws DataAccException
  {
    log.debug("update");
    Session session = null;
    try
    {
      session = HibernateUtil.getSessionFactory().getCurrentSession();
      obj.setUpdateDate(new Date());
      session.merge(obj);
      log.debug("objeto con clave '"+ pk +"' correctamente actualizado");

      if(this.cacheable)
        HibernateManagerBase.removeCacheFor(obj.getClass().getName());
    }
    catch(Exception ex)
    {
      String msg = "Error actualizando el objeto '" + obj + "' con clave primaria '" + pk + "'";
      log.debug("error actualizando el objeto '"+ pk +"': " + msg);
      throw new DataAccException(msg, ex);
    }
  }

  /**
   * Elimina un objeto de negocio de base de datos
   * @deprecated use this method only from derived classes, and use delete() methods in derived classes from other places
   * @param obj el objeto de negocio a eliminar
   * @param pk clave primaria del objeto de negocio a eliminar
   */
  public void delete(Object obj,Serializable pk) throws DataAccException
  {
    log.debug("delete");
    Class clazz = obj.getClass();
    Session session = null;
    try
    {
      session = HibernateUtil.getSessionFactory().getCurrentSession();

      // Cargamos el objeto antiguo
      Object objOld = session.load(clazz, pk);
      log.debug("objeto con clave '"+ pk +"' recuperado");
      // Borramos
      session.delete(objOld);
      log.debug("objeto con clave '"+ pk +"' correctamente eliminado");
      if(this.cacheable)
        HibernateManagerBase.removeCacheFor(clazz.getName());
      doAfterDelete(pk);
    }
    catch(Exception ex)
    {
      StringBuffer msg = new StringBuffer("Error borrando el objeto '");
      msg.append(pk);
      log.debug("error eliminando el objeto '"+ pk +"': " + msg.toString());
      throw new DataAccException(msg.toString(),ex);
    }

  }

  /**
   * Devuelve todas las ocurrencias de un objeto de negocio
   */
  protected List getAll() throws DataAccException
  {
    throw new DataAccException("HibernateManagerBase.getAll. M�todo no soportado por esta clase");
  }

  /**
   * @deprecated use getAll(Class<T> clazz,SortCriteria) instead
   */
  protected <T extends Object> List<T> getAll( String clazz, String[] orderFields ) throws DataAccException
  {
    return (List<T>)getAll( clazz, new SortCriteria(orderFields) );
  }

  /**
   * Devuelve todas las ocurrencias de un objeto de negocio. Si est� en la cach�, la lista
   * la devuelve de la misma y no accede a base de datos
   * @deprecated use getAll(Class<T> clazz,SortCriteria) instead
   * @param nombre de la clase que representa el objeto de negocio que deseamos recuperar
   * @param orderByFieldName lista de nombres de campos por los que queremos efectuar la ordenaci�n
   * @param descending si la ordenacion es descendente o ascendente
   */
  protected List getAll(String className,SortCriteria crit) throws DataAccException
  {
    Session session = null;
    List lResult = null;
    try
    {
      if(this.cacheable)
      {
        // Intentamos recuperar la informaci�n de la cach�
        lResult = HibernateManagerBase.getAllFromCache(className);
      }
      if(lResult==null)
      {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        // Montamos la query HQL
        StringBuffer query = new StringBuffer("FROM ");
        query.append(className);
        if(crit!=null)
        {
          query.append(" ");
          query.append(crit.getHQL());
        }
        lResult = session.createQuery(query.toString()).list();
        if(this.cacheable)
        {
          HibernateManagerBase.updateCache(className,lResult);
        }
      }
      return lResult;
    }
    catch(Exception ex)
    {
      String msg = "Error recuperando la lista de objetos de tipo '" + className + "'";
      log.debug(msg);
      throw new DataAccException(msg, ex);
    }

  }

  /**
   * Actualiza la cach� con un nuevo listado
   *
   * @param className Nombre de la clase cuyo listado se desea actualizar
   * @param list Lista de objetos que se desea actualizar
   */
  private static synchronized void updateCache(String className,List list)
  {
    loggerCache.debug("actualizando cach� para "+ className);
    if(!hasListFor(className))
      // Solo en el caso de que no exista la lista, la guardamos en la cach�
      putInCache(className,list);
    loggerCache.debug("cach� para "+ className +" actualizada");
  }

  /**
   * Pregunta a la cach� si tiene una lista para la clase pasada como par�metro
   *
   * @param className Nombre de la clase
   * @return true si ya tiene lista para la clase, o false en caso contrario
   */
  private static synchronized boolean hasListFor(String className)
  {
    return cache.containsKey(className);
  }

  /**
   * Pone una nueva lista en la cach�
   * @param className Nombre de la clase cuyo listado se desea actualizar
   * @param list Lista de objetos que se desea actualizar
   */
  private static synchronized void putInCache(String className,List list)
  {
    cache.put(className,list);
    loggerCache.debug("Insertada en la cach� la lista de "+ className);
  }

  /**
   * Elimina de la cache la lista de objetos de un tipo
   * @param className Nombre de la clase cuyo listado se desea eliminar
   */
  protected static synchronized void removeCacheFor(String className)
  {
    if(cache.containsKey(className))
    {
      cache.remove(className);
      loggerCache.debug("Eliminada la lista de "+ className + " de la cache");
    }
    else
      loggerCache.debug("Se esta intentando eliminar "+ className + " de la cache y no se encuentra en la misma????");
  }

  /**
   * Devuelve la lista de objetos solicitada
   * @param className Nombre de la clase cuyo listado se desea recuperar
   * @return la lista de objetos, o null en el caso de que no exista dicha lista en cache
   */
  private static synchronized List getAllFromCache(String className)
  {
    List result = null;
    if(cache.containsKey(className))
    {
      result = (List)cache.get(className);
      loggerCache.debug("Recuperada de cache la lista de "+ className);
    }
    else
      loggerCache.debug("No hay lista en cache para "+ className);
    return result;
  }

  /**
   * This method is intended to be overriden in order to add specific behavior
   * to be executed after delete an entity.
   * @throws DataAccException
   */
  public void doAfterDelete(Serializable pk) throws DataAccException {
	  // Do nothing
  }

  public void flush() throws DataAccException {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().flush();
		} catch (Exception e) {
			throw new DataAccException("Can´t do all action in database.", e);
		}
	}
}
