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

<xsl:template match="move-button[@style='fwd']">
  <input border="0" type="image" name="{@id}" src="{$context}/wcf/changeorder/move-down.png" width="9" height="9"/>
</xsl:template>

<xsl:template match="move-button[@style='bwd']">
  <input border="0" type="image" name="{@id}" src="{$context}/wcf/changeorder/move-up.png" width="9" height="9"/>
</xsl:template>

<xsl:template match="move-button[@style='cut']">
  <input border="0" type="image" name="{@id}" src="{$context}/wcf/changeorder/cut.png" width="9" height="9" title="{@title}"/>
</xsl:template>

<xsl:template match="move-button[@style='uncut']">
  <input border="0" type="image" name="{@id}" src="{$context}/wcf/changeorder/uncut.png" width="9" height="9" title="{@title}"/>
</xsl:template>

<xsl:template match="move-button[@style='paste-before']">
  <input border="0" type="image" name="{@id}" src="{$context}/wcf/changeorder/paste-before.png" width="9" height="9" title="{@title}"/>
</xsl:template>

<xsl:template match="move-button[@style='paste-after']">
  <input border="0" type="image" name="{@id}" src="{$context}/wcf/changeorder/paste-after.png" width="9" height="9" title="{@title}"/>
</xsl:template>

<xsl:template match="move-button">
  <img src="{$context}/wcf/changeorder/move-empty.png" width="9" height="9"/>
</xsl:template>

</xsl:stylesheet>
