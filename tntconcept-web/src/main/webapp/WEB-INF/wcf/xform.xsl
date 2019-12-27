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

<!-- ================= form with 'automatic' 2-column table layout ================= -->

<xsl:param name="form-border" select="'1'"/>


<xsl:template match="xform[@style='twocolumn-omit-table']">
  <xsl:apply-templates mode="twocolumn"/>
</xsl:template>

<xsl:template match="xform[@style='twocolumn']">
  <table border="{$form-border}" cellspacing="0" cellpadding="2" id="{$renderId}">
    <xsl:apply-templates select="@width"/>
    <xsl:apply-templates mode="twocolumn"/>
  </table>
</xsl:template>

<!--
wie "twocolumn", jedoch werden keine globalen stylesheet parameter verwendet
(das wuerde zu mehrfachen verwendung der renderId fuehren)
-->
<xsl:template match="xform[@style='nested']">
  <table border="1" cellspacing="0" cellpadding="2" id="{@id}">
    <xsl:apply-templates select="@width"/>
    <xsl:apply-templates mode="twocolumn"/>
  </table>
</xsl:template>

<!-- ignore hidden elements -->
<xsl:template mode="twocolumn" match="*[@hidden='true']"/>

<xsl:template mode="twocolumn" match="label|textField|password|listBox1|listBoxN|checkBox|radioButton|fileUpload">
  <xsl:call-template name="show-error"/>
  <tr>
    <td class="xform-label">
      <xsl:value-of select="@label"/>
    </td>
    <td class="xform-input">
      <xsl:apply-templates select="."/>
    </td>
  </tr>
</xsl:template>



<!-- textarea takes the whole space -->
<xsl:template mode="twocolumn" match="textArea">
  <xsl:call-template name="show-error"/>
  <tr>
    <td colspan="2" class="xform-both">
      <xsl:if test="@label">
        <xsl:value-of select="@label"/>
        <br/>
      </xsl:if>
      <xsl:apply-templates select="."/>
    </td>
  </tr>
</xsl:template>


<!-- displays a row with buttons -->
<xsl:template mode="twocolumn" match="buttons">
  <tr>
    <td colspan="2" align="right">
      <div align="right">
        <xsl:apply-templates/>
      </div>
    </td>
  </tr>
</xsl:template>


<!-- title text only -->
<xsl:template mode="twocolumn" match="title">
  <tr>
    <th colspan="2" class="xform-title">
      <!-- support both <title>blah</title> and <title value="blah"/> -->
      <xsl:value-of select="@value"/>
      <xsl:apply-templates/>
    </th>
  </tr>
</xsl:template>

<!-- subtitle text only -->
<xsl:template mode="twocolumn" match="subtitle">
  <tr>
    <th colspan="2" class="xform-subtitle">
      <!-- support both <title>blah</title> and <title value="blah"/> -->
      <xsl:value-of select="@value"/>
      <xsl:apply-templates/>
    </th>
  </tr>
</xsl:template>

<!-- title with close button(s) -->
<xsl:template mode="twocolumn" match="title[imgButton]">
  <tr>
    <th colspan="2" class="xform-title">
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
          <th align="left" class="xform-title">
            <xsl:value-of select="@value"/>
          </th>
          <td align="right" class="xform-close-button">
            <xsl:apply-templates/>
          </td>
        </tr>
      </table>
    </th>
  </tr>
</xsl:template>


<!-- displays text in two columns -->
<xsl:template mode="twocolumn" match="text">
  <xsl:call-template name="show-error"/>
  <tr>
    <td colspan="2" class="xform-both">
      <!-- support both <text>blah</text> and <text value="blah"/> -->
      <xsl:value-of select="@value"/>
      <xsl:apply-templates/>
    </td>
  </tr>
</xsl:template>


<!-- escapes to manual layout after checking the @error attribute -->
<xsl:template mode="twocolumn" match="manual">
  <xsl:call-template name="show-error"/>
  <xsl:apply-templates/>
</xsl:template>
<!-- ignored if already in manual mode -->
<xsl:template match="manual"/>

<!-- inverse of manual -->
<xsl:template match="twocolumn">
  <xsl:call-template name="show-error"/>
  <xsl:apply-templates mode="twocolumn"/>
</xsl:template>
<!-- ignored if already in twocolumn mode -->
<xsl:template mode="twocolumn" match="twocolumn"/>

<!-- block with multiple columns -->
<xsl:template mode="twocolumn" match="multicolumn">
  <xsl:call-template name="show-error"/>
  <tr>
    <td colspan="2">
      <table>
        <tr>
          <xsl:apply-templates mode="multicolumn" select="column"/>
        </tr>
      </table>
    </td>
  </tr>
</xsl:template>
<!-- single column inside a multicolumn element -->
<xsl:template mode="multicolumn" match="column">
  <td valign="top">
    <table>
      <xsl:apply-templates mode="twocolumn"/>
    </table>
  </td>
</xsl:template>

<!-- displays a table row with error message -->
<xsl:template name="show-error">
  <xsl:if test="@error">
    <tr>
      <td colspan="2" class="xform-error">
        <xsl:value-of select="@error"/>
      </td>
    </tr>
  </xsl:if>
</xsl:template>

<xsl:template mode="twocolumn" match="errorElement">
  <xsl:call-template name="show-error"/>
</xsl:template>

<!-- =============== form without automatic layout ===================== -->

<xsl:template match="xform[@style='manual']">
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="error-list">
  <ul>
    <xsl:for-each select="//*[@error]">
      <li>
        <xsl:value-of select="@error"/>
      </li>
    </xsl:for-each>
  </ul>
</xsl:template>

<xsl:template match="error-message">
  <xsl:value-of select="//@error"/>
</xsl:template>

</xsl:stylesheet>
