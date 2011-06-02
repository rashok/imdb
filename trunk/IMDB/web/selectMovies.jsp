<%-- 
    Document   : cadastro
    Created on : 12/04/2011, 13:39:47
    Author     : Valter
--%>

<html>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file="head.jsp"%>
    <body class="page-elements page-sidebar-right">
        <%@include file="header.jsp" %>

        <!-- Page title and breadcrumbs -->
        <section id="page-title" class="clearfix">
            <div class="container_12">
                <!-- Title and page summary -->
                <hgroup id="title-text" class="grid_8">
                    <% String artistName = (String) session.getAttribute("artistName");%>
                    <% String artistGender = (String) session.getAttribute("artistGender");%>
                    <% if (artistName != null && artistGender != null) {%>
                    <h2>Buscar</h2>
                    <h3>Busque pelo(s) filme(s) que <% out.print(artistName + "(" + artistGender + ")");%> participou</h3>
                    <% } else if (artistName != null) {%>
                    <h2>Buscar</h2>
                    <h3>Busque pelo(s) filme(s) que <% out.print(artistName);%> dirigiu</h3>
                    <% } else { %>
                    <h2>Buscar</h2>
                    <h3>Busque pelo seu artista primeiro </h3>
                    <% }%>
                </hgroup>
                <!-- /Title and page summary -->
                
                
                <!-- Site search form -->
                <div id="title-search" class="grid_4 alt-form">
                <% if (artistName != null) {%>
                    <form action="ManageMovie" id="site-search-form" class="inline">

                        <input type="text" name="search" id="search"  />

                        <input type="hidden" name="action" value="search" />
                        <input type="hidden" name="index" value="1" />
                        <input type="submit" value="Buscar" />
                    </form>
                    <% } %>
                </div>
                <!-- /Site search form -->

            </div>
        </section>
        <!-- /Page title and breadcrumbs -->

        <!-- Page body -->
        <div id="page-body" class="container_12 clearfix">
            <!-- Page content -->
            <div class="page-content grid_8">

                <!-- Content boxes -->
                <section>
                <% if (artistName != null) { %>
                    <h2 class="underlined-header">Resultado(s)</h2>
                    <% List<String> results = (List<String>) session.getAttribute("results");
                       int i = 0;
                       int index = (Integer) session.getAttribute("index");
                       int result = 0;
                    %>
                       <form method="post" action="ManageArtist">
                    <% while (i < 20) {
                           String[] split =  results.get(result).split("&&");
                    %>
                    <p>
                    <label for="cf_name">Título:</label>
                    <input type="checkbox" name="result-<% out.print(result);%>" value="<%= split[0]%>" /> "<%=split[1]%>"> <br/>
                    <% if (artistGender != null) { %>
                    <label for="cf_name">Papel:</label>
                    <input type="text" name="info-<% out.print(result);%>" />
                    <% } else { %>
                    <label for="cf_name">Informação:</label>
                    <input type="text" name="info-<% out.print(result);%>" />
                    <% } %>
                    </p>
                    <%     i++;
                           result++;
                       } 
                    %>
                           <br/>
                           <br/>
                           <p>
                               <input type="hidden" name="action" value="register" />
                               <input type="submit" value="Cadastrar" />
                           </p>
                       </form>
                    <%
                        session.removeAttribute("results");
                        String search = (String) session.getAttribute("search");
                    %>

                    <% if (index > 1){ %>
                        <a href="ManageMovie?search=<% out.print(search);%>&action=search&index=<%= index-1 %>" > Anteriores</a>
                    <% } %>
                    <a href="ManageMovie?search=<% out.print(search);%>&action=search&index=<%= index+1 %>" >Proximos</a>

                    <% } else { %>
                           <h2 class="underlined-header">Sem artista para buscar</h2>
                           <h3>Busque pelo seu artista <a href="cadastro.jsp"> aqui </a> </h3>
                    <% } %>
                </section>
            </div>
        </div>
                    
        <!-- /Code -->

        <%@include file="bottom.jsp" %>

        <%@include  file="footer.jsp" %>

        <%@include file="scripts.jsp" %>



    </body>

    <!-- Mirrored from dbooom.com/themes/html/manifest/features/elements.html by HTTrack Website Copier/3.x [XR&CO'2010], Mon, 04 Apr 2011 01:52:52 GMT -->
    <!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=utf-8"><!-- /Added by HTTrack -->
</html>