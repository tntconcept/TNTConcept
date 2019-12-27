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

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:output method="html" indent="no" encoding="US-ASCII"/>
<xsl:param name="context"/>
<xsl:param name="renderId"/>
<xsl:param name="token"/>
<xsl:param name="imgpath" select="'jpivot/toolbar'"/>

<xsl:template match="tool-bar">
  <table border="0" cellspacing="1" cellpadding="0"  id="{$renderId}">
    <xsl:apply-templates/>
  </table>
</xsl:template>

<xsl:template match="tool-button">
  <tr>
    <td align="left">
      <input type="image" name="{@id}" src="{$context}/{$imgpath}/{@img}.png" border="0" title="{@title}" width="24" height="24"/>
    </td>
  </tr>
</xsl:template>

<xsl:template match="tool-sep">
  <tr>
    <td align="left">
      <div style="margin-top: 2px"/>
    </td>
  </tr>
</xsl:template>

<xsl:template match="img-button">
  <tr>
    <td align="left">
      <a href="{@href}">
        <xsl:if test="@target">
          <xsl:attribute name="target"><xsl:value-of select="@target"/></xsl:attribute>
        </xsl:if>
        <img src="{$context}/{$imgpath}/{@img}.png" border="0" title="{@title}" width="24" height="24"/>
      </a>
    </td>
  </tr>
</xsl:template>

</xsl:stylesheet>
