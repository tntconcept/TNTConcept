/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L. Copyright (C) 2007 Autentia Real Bussiness
 * Solution S.L. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * ChangePasswordBean.java
 */

package com.autentia.tnt.bean;

/**
 * Bean que se ocupa de la funcionalidad de cambio de password
 *
 * @author <a href="www.autentia.com">AUTENTIA</a>
 */
public class ChangePasswordBean extends AbstractPasswordBean {

    private static final long serialVersionUID = 4873802999327338762L;

    @Override
    public String returnError(Exception ex) {
        return super.getReturnError("ChangePasswordBean.changePassword. ", ex);
    }

}
