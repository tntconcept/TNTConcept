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

package com.autentia.tnt.dao.search;

import com.autentia.tnt.businessobject.Department;
import com.autentia.tnt.businessobject.Organization;
import com.autentia.tnt.businessobject.Position;
import com.autentia.tnt.businessobject.Tag;
import com.autentia.tnt.dao.ITransferObject;
import com.autentia.tnt.dao.SearchCriteria;
import java.util.ArrayList;
import java.util.List;


public class AdvancedSearchContactSearch extends SearchCriteria {

    private List<Tag> tags;
    private boolean tagsSet;

    private List<Position> positions;
    private boolean positionsSet;

    private List<Organization> organizations;
    private boolean organizationsSet;

    private String country;
    private boolean countrySet;

    private String name;
    private boolean nameSet;

    private List<Department> departments;
    private boolean departmentsSet;

	public boolean isTagsSet() {
		return tagsSet;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
		this.tagsSet = true;
	}

	public void unsetTags() {
		this.tagsSet = false;
	}

 	public boolean isPositionsSet() {
		return positionsSet;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
		this.positionsSet = true;
	}

	public void unsetPositions() {
		this.positionsSet = false;
	}

    public boolean isOrganizationsSet() {
		return organizationsSet;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
		this.organizationsSet = true;
	}

	public void unsetOrganizations() {
		this.organizationsSet = false;
	}

    public boolean isCountrySet() {
		return countrySet;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
		this.countrySet = true;
	}

	public void unsetCountry() {
		this.countrySet = false;
	}  
    
    public boolean isNameSet() {
		return nameSet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.nameSet = true;
	}

	public void unsetName() {
		this.nameSet = false;
	}
    
    public boolean isDepartmentsSet() {
		return departmentsSet;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
		this.departmentsSet = true;
	}

	public void unsetDepartments() {
		this.departmentsSet = false;
	}

	public String getContactHQL() {
		StringBuilder ret = new StringBuilder();
		int iArgNum = 0;
        // ret.append ("select distinct(c.name), o.name, d.name, cipo.name, c.email, c.phone, c.fax, c.country, c.province.name, c.city, c.postalCode, c.address from Contact c left join c.contactInfos ci left join ci.department d left join ci.organization o left join ci.position cipo left join c.tags t ");
           ret.append ("select distinct(c.name), o.name, d.name, cipo.name, c.email, c.phone, c.fax, c.country, c.province.name, c.city, c.postalCode, c.address from Contact c left join c.contactInfos ci left join ci.department d left join ci.organization o left join ci.position cipo left join c.tags t ");
        int retLength = ret.length();

        // busqueda en contactos activos
        // -----------------------------

        if (isTagsSet()) {
			ret.append((ret.length() == retLength) ? "WHERE " : " AND ");
			ret.append("t.id IN (:arg" + (iArgNum++) + ")");
		}

        if (isPositionsSet()) {
            ret.append((ret.length() == retLength) ? "WHERE " : " AND ");
			ret.append("cipo.id IN (:arg" + (iArgNum++) + ")");
		}

        if (isOrganizationsSet()) {
			ret.append((ret.length() == retLength) ? "WHERE " : " AND ");
			ret.append("o.id IN (:arg" + (iArgNum++) + ")");
		}

        if (isCountrySet()) {
			ret.append((ret.length() == retLength) ? "WHERE " : " AND ");
			if (getCountry() == null) {
				ret.append("c.country is NULL");
			} else {
				ret.append("c.country like :arg" + (iArgNum++));
			}
		}

        if (isNameSet()) {
            ret.append((ret.length() == retLength) ? "WHERE " : " AND ");
			if (getName() == null) {
				ret.append("c.name is NULL");
			} else {
				ret.append("c.name like :arg" + (iArgNum++));
			}
		}

        if (isDepartmentsSet()) {
			ret.append((ret.length() == retLength) ? "WHERE " : " AND ");
			ret.append("d.id IN (:arg" + (iArgNum++) + ")");
		}

        ret.append(")");
        return ret.toString();
    }

    public String getContactChangesHQL () {

		StringBuilder ret = new StringBuilder();
		int iArgNum = 0;
        ret.append ("from EntityChange e where e.entityName = 'com.autentia.tnt.businessobject.Contact'");

        // busqueda en historico de contactos
        // ----------------------------------

        if (isTagsSet()) {
            ret.append(" AND e.field = 'contact.tags' and e.oldValue in (select t.name from Tag t where t.id in (:arg" + (iArgNum++) + "))");
		}

        if (isPositionsSet()) {
            ret.append(" AND e.field = 'contact.position' and e.oldValue in (select p.name from Position p where p.id in (:arg" + (iArgNum++) + "))");
		}

        if (isOrganizationsSet()) {
            ret.append(" AND e.field = 'contact.organization' and e.oldValue in (select p.name from Organization p where p.id in (:arg" + (iArgNum++) + "))");
		}

        if (isCountrySet()) {
            ret.append(" AND e.field = 'contact.country' and e.oldValue like :arg" + (iArgNum++));
		}

        if (isNameSet()) {
            ret.append(" AND e.field = 'contact.name' and e.oldValue like :arg" + (iArgNum++));
		}

        if (isDepartmentsSet()) {
            ret.append(" AND e.field = 'contact.department' and e.oldValue in (select d.name from Department d where d.id in (:arg" + (iArgNum++) + "))");
		}

        ret.append(")");
        return ret.toString();
    }

    public String getPositionHQL() {
		StringBuilder ret = new StringBuilder();
		int iArgNum = 0;

        // busqueda en puestos activos
        // ---------------------------

        // ret.append ("select distinct(o.name), d.name, p.name, p.email, p.phone, p.fax, p.country, p.province.name, p.city, p.postalCode, p.address from Position p left join p.departments d left join d.organizations o left join p.tags t ");
           ret.append ("select distinct(o.name), d.name, p.name, p.email, p.phone, p.fax, p.country, p.province.name, p.city, p.postalCode, p.address from Position p left join p.departments d left join d.organizations o left join p.tags t ");
        ret.append ("where p.id not in (select distinct(position.id) from ContactInfo)");

        if (isTagsSet()) {
			ret.append(" and t.id IN (:arg" + (iArgNum++) + ")");
		}

        if (isPositionsSet()) {
			ret.append(" and p.id IN (:arg" + (iArgNum++) + ")");
		}

        if (isOrganizationsSet()) {
			ret.append(" and o.id IN (:arg" + (iArgNum++) + ")");
		}

        if (isCountrySet()) {
			if (getCountry() == null) {
				ret.append(" and p.country is NULL");
			} else {
				ret.append(" and p.country like :arg" + (iArgNum++));
			}
		}

        if (isNameSet()) {
			if (getName() == null) {
				ret.append(" and p.name is NULL");
			} else {
				ret.append(" and p.name like :arg" + (iArgNum++));
			}
		}

        if (isDepartmentsSet()) {
			ret.append(" and d.id IN (:arg" + (iArgNum++) + ")");
		}

        return ret.toString();
    }

    public Object[] getContactArguments() {

        ArrayList<Object> ret = new ArrayList<Object>();

        // argumentos en contactos
        // -----------------------

        if (isTagsSet()) {
			List<Integer> params = new ArrayList<Integer>();
			for (ITransferObject to : getTags()) {
				params.add(to.getId());
			}
			ret.add(params);
		}

        if (isPositionsSet()) {
			List<Integer> params = new ArrayList<Integer>();
			for (ITransferObject to : getPositions()) {
				params.add(to.getId());
			}
			ret.add(params);
		}

         if (isOrganizationsSet()) {
			List<Integer> params = new ArrayList<Integer>();
			for (ITransferObject to : getOrganizations()) {
				params.add(to.getId());
			}
			ret.add(params);
		}

        if (isCountrySet()) {
			ret.add('%' + getCountry() + '%');
		}

		if (isNameSet()) {
			ret.add('%' + getName() + '%');
		}

        if (isDepartmentsSet()) {
			List<Integer> params = new ArrayList<Integer>();
			for (ITransferObject to : getDepartments()) {
				params.add(to.getId());
			}
			ret.add(params);
		}

        return ret.toArray();
    }

    public Object[] getPositionArguments() {

        ArrayList<Object> ret = new ArrayList<Object>();

        // argumentos en puestos
        // ---------------------

        if (isTagsSet()) {
			List<Integer> params = new ArrayList<Integer>();
			for (ITransferObject to : getTags()) {
				params.add(to.getId());
			}
			ret.add(params);
		}

        if (isPositionsSet()) {
			List<Integer> params = new ArrayList<Integer>();
			for (ITransferObject to : getPositions()) {
				params.add(to.getId());
			}
			ret.add(params);
		}

         if (isOrganizationsSet()) {
			List<Integer> params = new ArrayList<Integer>();
			for (ITransferObject to : getOrganizations()) {
				params.add(to.getId());
			}
			ret.add(params);
		}

        if (isCountrySet()) {
			ret.add('%' + getCountry() + '%');
		}

        if (isNameSet()) {
			ret.add('%' + getName() + '%');
		}
        
        if (isDepartmentsSet()) {
			List<Integer> params = new ArrayList<Integer>();
			for (ITransferObject to : getDepartments()) {
				params.add(to.getId());
			}
			ret.add(params);
		}

        return ret.toArray();
    }

    @Override
    public void reset() {
        unsetCountry();
        unsetDepartments();
        unsetName();
        unsetOrganizations();
        unsetPositions();
        unsetTags();
    }

    @Override
    public String toString() {
        final StringBuilder ret = new StringBuilder();

        ret.append("AdvancedSearchContactSearch{");

        if (isCountrySet()) {
			ret.append("(country");
			ret.append("=" + getCountry());
			ret.append(")");
		}

        if (isDepartmentsSet()) {
			ret.append("(department={");
			for (Object o : getDepartments().toArray()) {
				ret.append("," + o);
			}
			ret.append("})");
        }

        if (isNameSet()){
			ret.append("(name");
			ret.append("=" + getName());
			ret.append(")");
		}

        if (isOrganizationsSet()) {
			ret.append("(organization={");
			for (Object o : getOrganizations().toArray()) {
				ret.append("," + o);
			}
			ret.append("})");
        }

        if (isPositionsSet()) {
			ret.append("(position={");
			for (Object o : getPositions().toArray()) {
				ret.append("," + o);
			}
			ret.append("})");
        }

        if (isTagsSet()) {
			ret.append("(tag={");
			for (Object o : getTags().toArray()) {
				ret.append("," + o);
			}
			ret.append("})");
        }

		ret.append("}");
		return ret.toString();
    }

    @Override
    public String getHQL() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] getArguments() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
