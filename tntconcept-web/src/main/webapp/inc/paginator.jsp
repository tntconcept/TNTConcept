<%--

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

--%>

<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%@include file="/inc/tlibs.jsp" %>

<%-- Paginator control --%>
<t:dataScroller for="list" fastStep="10" paginator="true"
                paginatorMaxPages="10" renderFacetsIfSinglePage="false"
                styleClass="scroller" paginatorTableClass="paginator"
                paginatorColumnClass="paginatorCell"
                paginatorActiveColumnClass="paginatorActiveCell">
  <f:facet name="first">
    <t:graphicImage value="/img/arrow-first.gif" border="0" />
  </f:facet>
  <f:facet name="last">
    <t:graphicImage value="/img/arrow-last.gif" border="0" />
  </f:facet>
  <f:facet name="previous">
    <t:graphicImage value="/img/arrow-previous.gif" border="0" />
  </f:facet>
  <f:facet name="next">
    <t:graphicImage value="/img/arrow-next.gif" border="0" />
  </f:facet>
  <f:facet name="fastrewind">
    <t:graphicImage value="/img/arrow-fr.gif" border="0" />
  </f:facet>
  <f:facet name="fastforward">
    <t:graphicImage value="/img/arrow-ff.gif" border="0" />
  </f:facet>
</t:dataScroller>
