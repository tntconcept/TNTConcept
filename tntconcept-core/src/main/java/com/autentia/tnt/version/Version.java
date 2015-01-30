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

package com.autentia.tnt.version;

import com.autentia.tnt.util.HibernateUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

/**
 *
 * @author ivan
 */
public class Version implements Comparable<Version>, Cloneable
{
  private static final Log log = LogFactory.getLog(Version.class);
  
  public static final int MAJOR = 0;
  public static final int MINOR = 1;
  public static final int PATCH = 2;
  
  private static Version appVersion = null;
  
  private int major = 0;
  private int minor = 0;
  private int patch = 0;
  
  public static Version getApplicationVersion()
  {
    if( appVersion==null )
    {
      try
      {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/autentia/tnt/version/info.properties");
        Properties props = new Properties();
        props.load(is);

        appVersion = new Version( props.getProperty("number") );
      }
      catch( Exception e )
      {
        log.fatal("static - cannot read version",e);
      }
    }

    return appVersion;
  }
  
  public static Version getDatabaseVersion() throws SQLException
  {
    Session ses = HibernateUtil.getSessionFactory().openSession();
    
    try
    {
      return getDatabaseVersion( ses.connection() );
    }
    catch( SQLException e )
    {
      throw e;
    }
    finally
    {
      ses.close();
    }
  }

  public static Version getDatabaseVersion( Connection con ) throws SQLException
  {
    Statement stmt = null;
    ResultSet rs = null;
    String ret = null;

    try {
      stmt = con.createStatement();
      rs = stmt.executeQuery("select version from Version");

      if( rs.next() ){
        ret = rs.getString("version");
      }
    } catch( SQLException e ) {
      throw e;
    } finally {
    	if( rs!=null ){
    		try { 
    			rs.close(); 
    		} catch (SQLException e){
    	    	log.error("Error al liberar el resultset", e);
    		}
    	}
    	if( stmt!=null ) {
    		try { 
    			stmt.close(); 
    		} catch (SQLException e){
    			log.error("Error al liberar el statement", e);
    		}
    	}
      }
    
    return new Version(ret==null?"0":ret);
  }
  
  /** Creates a new instance of Version */
  public Version( int major, int minor, int patch )
  {
    this.major = major;
    this.minor = minor;
    this.patch = patch;
  }
  
  /** Creates a new instance of Version */
  public Version( String number ) throws IllegalArgumentException
  {
    // Remove "-SNAPSHOT" if present
    if( number.endsWith("-SNAPSHOT") ){
      number = number.substring(0,number.length()-9);
    }
    
    String[] numbers = number.split("\\.");
    
    if(numbers.length < 1 || numbers.length > 3){
    	throw new IllegalArgumentException("Invalid version number: "+number);
    }
    
    if(numbers.length >=1 ){
    	this.major = Integer.parseInt(numbers[0]);
    }
    
    if(numbers.length>=2){
    	 this.minor = Integer.parseInt(numbers[1]);
    }
    
    if(numbers.length == 3){
    	this.patch = Integer.parseInt(numbers[2]);
    }
        
  }
  
  public int getMajor()
  {
    return major;
  }
  
  public int getMinor()
  {
    return minor;
  }
  
  public int getPatch()
  {
    return patch;
  }
  
  public Version clone() throws CloneNotSupportedException
  {
    return (Version)super.clone();
  }
  
  public String toString()
  {
    return toString(PATCH);
  }
  
  public String toString(int level)
  {
    StringBuilder sb = new StringBuilder();
    
    if( level>=MAJOR ){
      sb.append(Integer.toString(major));
      if( level>=MINOR ){
        sb.append(".");
        sb.append(Integer.toString(minor));
        if( level>=PATCH ){
          sb.append(".");
          sb.append(Integer.toString(patch));
        }
      }
    }
    
    return sb.toString();
  }
  
  public boolean equals(Object that)
  {
    if( !(that instanceof Version) )
    {
      return false;
    }
    else
    {
      return compareTo((Version)that)==0;
    }
  }
  
  public int compareTo(Version that)
  {
    return compareTo(that,PATCH);
  }
  
  public int compareTo(Version that, int level)
  {
    int diff;
    
    diff = this.major - that.major;
    if( diff!=0 || level==MAJOR ) return diff;
    
    diff = this.minor - that.minor;
    if( diff!=0 || level==MINOR ) return diff;
    
    diff = this.patch - that.patch;
    return diff;
  }
  
  public int hashCode(){
	  return (this.major + "." + this.minor + "." + this.patch).hashCode();
  }

}
