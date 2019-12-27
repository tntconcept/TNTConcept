<?xml version="1.0"?>
<!--

    TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
    Copyright (C) 2007 Autentia Real Bussiness Solution S.L.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="cat-edit">
		
	<script type="text/javascript">
	<xsl:comment>
			/**
			 * Dada una cadena de tokens separados por "_" y una posición (comenzando por 0), devuelve el token que ocupa esa posición. 
			 * Por ejemplo, para cadena: aaaaaaa_bbbbb y pos: 0
			 * La función devolvería:  aaaaaaa
			 */				
			function extrae(cadena, pos){
				lista = cadena.split("_");
				return lista[pos]; 
			}
			
			/**
			 * Envia el formulario con un determinado código de acción
			 * @param listId: Identificador de la lista.
			 */
			function sendToAux(listId, indice){
				var lista = document.getElementById(listId);
				var index = lista.selectedIndex;
				 
				if (index == -1){
					alert("Debe seleccionar el elemento sobre el cuál desea realizar la operación");
				} else {
					var value = lista.options[index].value;
					var name  = extrae(value, indice);
					var operationField	  = document.getElementById('operation');
					
					// Introducimos el name en un input hidden y enviamos el formulario
					operationField.name  = name;
					operationField.value = name;					
					//document.forms[0].submit();
					submitForm(); 					
				}
			}
	</xsl:comment>
	</script>
		
	<div id="{$renderId}" style="display:block;">
	  <!-- Este control será el que contenta el código de la operación.
			Lo importante es el name y no el value
 		-->	  
	  <input type="hidden" id="operation" name="" value=""/>
	  
	  <table style="border-style:solid;border-width:1px;border-color:#dadada">
	  	<colgroup>
	  		<col width="300px"/>
	  		<col width="350px"/>
	  		<col width="350px"/>
	  	</colgroup>
	  	<thead>
	  		<tr style="background-color:#eeeeff;">
	  			<th>VARIABLES NO MOSTRADAS</th>
	  			<th>FILAS</th>
	  			<th>COLUMNAS</th>
	  		</tr>
	  	</thead>
	  	<tfoot>
	  		<tr>
	  			<td colspan="3">
	  				 <hr/>
					  <div id="navigatorButtons">
				          <input type="submit" value="{@ok-title}" name="{@ok-id}"/>
				          <xsl:text> </xsl:text>
				          <input type="submit" value="{@cancel-title}" name="{@cancel-id}"/>
					  </div>
	  			</td>
	  		</tr>
	  	</tfoot>
	  	<tbody>
			<tr style="vertical-align:top">
				<td><xsl:apply-templates select="cat-category[@name='Filtro']"/></td>
				<td><xsl:apply-templates select="cat-category[@name='Filas']"/></td>
				<td><xsl:apply-templates select="cat-category[@name='Columnas']"/></td>
			</tr>
		</tbody>
	  </table>
	</div>
	 
</xsl:template>


<xsl:template match="cat-category[@name='Filtro']">
	<div id="navitagorFiltro">
		<select name="filterList" id="filterList"  size="7" style="width:100%">
			<xsl:for-each select="cat-item">
				<option>
					<xsl:attribute name="value">
						<xsl:value-of select="@id"/>
						<xsl:text>_</xsl:text>
						<xsl:value-of select="cat-button[@icon='row.png']/@id"/>
						<xsl:text>_</xsl:text>
						<xsl:value-of select="cat-button[@icon='column.png']/@id"/>
					</xsl:attribute>
					
					<xsl:value-of select="@name"/>
					<xsl:apply-templates select="slicer-value"/> 
				</option>
			</xsl:for-each>
		</select>
		
		<input type="button" title="Enviar a filas"	 value="A filas"	onclick="sendToAux('filterList', 1);"/>
		<input type="button" title="Enviar a columnas" value="A columnas" onclick="sendToAux('filterList', 2);"/>
		<input type="button" title="Editar filtro"	 value="Editar"	onclick="sendToAux('filterList', 0);"/>
  	</div>
</xsl:template>

<xsl:template match="cat-category[@name='Filas']">
	<div id="navitagorFilas">
		<select name="rowsList" id="rowsList"  size="7" style="width:100%">
			<xsl:for-each select="cat-item">
				<option>
					<xsl:attribute name="value">
						<xsl:value-of select="@id"/>
						<xsl:text>_</xsl:text>						
						<xsl:value-of select="cat-button[@icon='column.png']/@id"/>
						<xsl:text>_</xsl:text>
						<xsl:value-of select="cat-button[@icon='filter.png']/@id"/>
						<xsl:text>_</xsl:text>
						<xsl:value-of select="move-button[@style='bwd']/@id"/>
						<xsl:text>_</xsl:text>
						<xsl:value-of select="move-button[@style='fwd']/@id"/>					
					</xsl:attribute>
					<xsl:value-of select="@name"/>
					<xsl:apply-templates select="slicer-value"/> 
				</option>
			</xsl:for-each>
		</select>
		<input type="button" title="Editar filtro"	 value="Editar"	onclick="sendToAux('rowsList', 0);"/>
		<input type="button" title="Enviar a columnas" value="A columnas"		onclick="sendToAux('rowsList', 1);"/>
		<input type="button" title="Quitar variable"	  value="Quitar"	onclick="sendToAux('rowsList', 2);"/>
		<input type="button" title="Subir" value="Subir"	onclick="sendToAux('rowsList', 3);"/>
		<input type="button" title="Bajar" value="Bajar"	onclick="sendToAux('rowsList', 4);"/>
  	</div>
</xsl:template>


<xsl:template match="cat-category[@name='Columnas']">
	<div id="navitagorColumnas">
		<select name="colsList" id="colsList"  size="7" style="width:100%">
			<xsl:for-each select="cat-item">
				<option>
					<xsl:attribute name="value">
						<xsl:value-of select="@id"/>
						<xsl:text>_</xsl:text>						
						<xsl:value-of select="cat-button[@icon='row.png']/@id"/>
						<xsl:text>_</xsl:text>
						<xsl:value-of select="cat-button[@icon='filter.png']/@id"/>
						<xsl:text>_</xsl:text>
						<xsl:value-of select="move-button[@style='bwd']/@id"/>
						<xsl:text>_</xsl:text>
						<xsl:value-of select="move-button[@style='fwd']/@id"/>					
					</xsl:attribute>
					<xsl:value-of select="@name"/>
					<xsl:apply-templates select="slicer-value"/> 
				</option>
			</xsl:for-each>
		</select>
		<input type="button" title="Editar filtro"	 value="Editar"	onclick="sendToAux('colsList', 0);"/>
		<input type="button" title="Enviar a columnas" value="A filas"	onclick="sendToAux('colsList', 1);"/>
		<input type="button" title="Quitar variable"	  value="Quitar"	onclick="sendToAux('colsList', 2);"/>
		<input type="button" title="Subir" value="Subir"	onclick="sendToAux('colsList', 3);"/>
		<input type="button" title="Bajar" value="Bajar"	onclick="sendToAux('colsList', 4);"/>
  	</div>
</xsl:template>


<xsl:template match="slicer-value">
	<span style="font-size:11px;color:#227722">
  		<xsl:text> (</xsl:text>
  		<xsl:value-of select="@level"/>
  		<xsl:text>=</xsl:text>
  		<xsl:value-of select="@label"/>
  		<xsl:text>)</xsl:text>
 	</span>
</xsl:template>


</xsl:stylesheet>
