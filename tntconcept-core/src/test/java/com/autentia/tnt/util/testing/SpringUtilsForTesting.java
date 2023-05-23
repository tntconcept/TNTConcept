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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.autentia.tnt.util.testing;

import com.autentia.tnt.util.SpringUtils;
import org.springframework.context.ApplicationContext;

public class SpringUtilsForTesting {

    private static ApplicationContext appCtx;
    
	public synchronized static void configure( ApplicationContext ctx )
	{
		// Do not let configure more than once
		if( appCtx == null )
		{
			appCtx = ctx;
		}
		
		SpringUtils.configureTest( ctx);
		
	}

    public static Object getSpringBean( String name )
	{
		return appCtx.getBean(name);
	}
    

}
