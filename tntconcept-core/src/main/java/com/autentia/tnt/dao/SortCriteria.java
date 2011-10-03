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

/**
 *
 */
package com.autentia.tnt.dao;

import java.util.*;

/**
 * Helper bean to specify sort criteria to manager's list methods.
 * @author ivan
 */
public class SortCriteria {
  /** List of fields */
  private List<String> fields = new ArrayList<String>();
  
  /** List of ascending/descending flags */
  private List<Boolean> ascendings = new ArrayList<Boolean>();

  /**
   * Empty constructor.
   */
  public SortCriteria(){
  }

  /**
   * Convenience constructor to sort by one only field
   * @param field field name
   * @param ascending order direction
   */
  public SortCriteria( String field, boolean ascending ){
    add(field,ascending);
  }
  
  /**
   * Convenience constructor to sort by one only field ascending
   * @param field field name
   */
  public SortCriteria( String field ){
    this(field,true);
  }
  
  /**
   * Convenience constructor to sort by multiple fields
   * @param fields name of fields
   * @param ascendings order direction (can be null to set all to true)
   */
  public SortCriteria( String[] fields, boolean[] ascendings ) {
    // Check arguments
    if( ascendings!=null && (fields.length != ascendings.length) ){
      throw new IllegalArgumentException("SortCriteria: fields.length must be equal to ascendings.length");
    }
    
    // Process arguments
    for( int i=0 ; i<fields.length ; i++ ){
      add(fields[i], (ascendings!=null) ? ascendings[i] : true );
    }
  }
  
  /**
   * Convenience constructor to sort by multiple fields ascending
   * @param fields name of fields
   * @param ascendings order direction
   */
  public SortCriteria( String[] fields ) {
    this(fields,null);
  }
  
  /**
   * Add a sort criterion to this object
   * @param field name of field
   * @param ascending whether to sort ascending/descending
   */
  public void add( String field, boolean ascending ){
    fields.add(field);
    ascendings.add(ascending);
  }
  
  /**
   * Return HQL statement to sort by this criteria
   * @return an String with format " ORDER BY field1 [desc], field2 [desc], field3 [desc], ..."
   */
  public String getHQL(){
    StringBuilder sb = new StringBuilder();
    if( fields.size()>0 ) {
      sb.append("ORDER BY ");
      for( int i=0 ; i<fields.size() ; i++ ){
        if( i>0 ){
          sb.append(",");
        }
        sb.append( fields.get(i) );
        if( !ascendings.get(i) ){
          sb.append(" DESC");
        }
      }
    }
    return sb.toString();
  }
}








