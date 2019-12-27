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

  <xsl:template match="popup-menu">
    <a href="#" onMouseover="cssdropdown.dropit(this, event, '{@id}')">
      <xsl:call-template name="image-name" />
    </a>
    <div id="{@id}" class="dropmenudiv">
      <xsl:apply-templates />
    </div>
  </xsl:template>

  <xsl:template match="popup-group">
    <strong style="padding-left: {@level}em">
      <xsl:call-template name="image-name" />
    </strong>
    <xsl:apply-templates />
  </xsl:template>

  <xsl:template match="popup-item">
    <a href="{@href}" style="padding-left: {@level}em">
      <xsl:call-template name="image-name" />
    </a>
  </xsl:template>

  <xsl:template name="image-name">
    <xsl:if test="@image">
      <img border="0" src="{$context}{@image}" />
      <xsl:text> </xsl:text>
    </xsl:if>
    <xsl:value-of select="@label" />
  </xsl:template>

</xsl:stylesheet>
