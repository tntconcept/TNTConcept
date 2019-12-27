<?xml version="1.0" encoding="ISO-8859-1"?>
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
  <table cellpadding="1" cellspacing="0" border="1" id="{$renderId}">
    <xsl:apply-templates select="cat-category"/>
  </table>
</xsl:template>

<xsl:template match="cat-category">
  <tr>
    <th align="left" class="navi-axis">
      <img src="{$context}/wcf/catedit/{@icon}" width="9" height="9"/>
      <xsl:text> </xsl:text>
      <xsl:value-of select="@name"/>
    </th>
  </tr>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="cat-item">
  <tr>
    <td class="navi-hier">
      <div style="margin-left: 1em">
        <xsl:apply-templates select="cat-button"/>
        <xsl:apply-templates select="move-button"/>
        <xsl:value-of select="@name"/>
      </div>
    </td>
  </tr>
</xsl:template>

<xsl:template match="cat-button[@icon]">
  <input border="0" type="image" src="{$context}/wcf/catedit/{@icon}" name="{@id}" width="9" height="9"/>
  <xsl:text> </xsl:text>
</xsl:template>

<xsl:template match="cat-button">
  <img src="{$context}/wcf/catedit/empty.png" width="9" height="9"/>
  <xsl:text> </xsl:text>
</xsl:template>


</xsl:stylesheet>
