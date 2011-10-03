/*
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

   	function actualizeTimeFields(field, tabId) 
   	{
   		var startHour = document.getElementById('activity:'+tabId+':startTimeHour');
   		var startMinute = document.getElementById('activity:'+tabId+':startTimeMinute');
   		
   		var endHour = document.getElementById('activity:'+tabId+':endTimeHour');
   		var endMinute = document.getElementById('activity:'+tabId+':endTimeMinute');
   		
   		var duration = document.getElementById('activity:'+tabId+':duration');
   		
   		var aDate = new Date();
   		aDate.setHours(parseInt(startHour.value, 10));
   		aDate.setMinutes(parseInt(startMinute.value, 10));
   		aDate.setSeconds(0);
   		
   		if ((field.id == "activity:"+tabId+":startTimeHour") || (field.id == "activity:"+tabId+":duration"))
   		{
   			aDate.setTime(aDate.getTime()+parseFloat(duration.value) * 60 * 60 * 1000);
			endMinute.value = aDate.getMinutes();
			return aDate.getHours();
    	}
   		
   		if (field.id == "activity:"+tabId+":startTimeMinute")
   		{
   			aDate.setTime(aDate.getTime()+parseFloat(duration.value) * 60 * 60 * 1000);
   			endHour.value = aDate.getHours();
   			return aDate.getMinutes();
   		}
   		
   		if ((field.id == "activity:"+tabId+":endTimeHour")||(field.id == "activity:"+tabId+":endTimeMinute"))
   		{
   			var bDate = new Date();
    		bDate.setTime(aDate.getTime());
    		bDate.setHours(parseInt(endHour.value, 10));
    		bDate.setMinutes(parseInt(endMinute.value, 10));
    		bDate.setSeconds(0);
    			    		
    		return (bDate.getTime() - aDate.getTime()) / (60 * 60 * 1000);
    	}
   	}
   	
   	