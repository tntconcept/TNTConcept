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

package com.autentia.tnt.tracking.hibernate.dao;

import com.autentia.tnt.dao.DataAccException;
import com.autentia.tnt.dao.SearchCriteria;
import com.autentia.tnt.dao.SortCriteria;
import com.autentia.tnt.dao.hibernate.HibernateManagerBase;
import com.autentia.tnt.tracking.EntityChange;

import java.util.List;


public class EntityChangeDAO extends HibernateManagerBase<EntityChange> {

    public EntityChange getById(int id) throws DataAccException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<EntityChange> search(SortCriteria crit) throws DataAccException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<EntityChange> search(SearchCriteria search, SortCriteria sort) throws DataAccException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(EntityChange to) throws DataAccException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(EntityChange to) throws DataAccException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(EntityChange entityChange) throws DataAccException {
		super.insert(entityChange);
	}
}
