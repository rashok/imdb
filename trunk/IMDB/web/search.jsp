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
                    <h2>Pesquisar</h2>
                    <h3>Pesquise pelos seus filmes, atores e diretos aqui no IMDB!</h3>
                </hgroup>
                <!-- /Title and page summary -->

            </div>
        </section>
        <!-- /Page title and breadcrumbs -->

        <!-- Page body -->
        <div id="page-body" class="container_12 clearfix">
            <!-- Page content -->
            <div class="page-content grid_8">

                <!-- Content boxes -->
                <section>
                        <h2 class="underlined-header">Pesquisa avançada 1</h2>
                        <h4 class="underlined-header">Busca avançada de filmes, que inclui  idiomas  e a especificação de um ou mais atores (busca absoluta e busca relativa). </h4>
                    <!-- Code -->
                    <div class="toggle-content">
                        <form method="post" action="ManageUser">
                            <p>
                                <label for="cf_name">Linguagem <span class="required">(Obrigatório *)</span></label>
                                <select name="language" class="required" required >
                                    <option value="" ></option>
                                    <% for (String language : languages) {
                                            String[] split = language.split("#");
                                    %>       
                                    <option value="<%= split[0]%>" ><%= split[1]%></option>
                                    <% }%>
                                </select>  
                            </p>

                            <p>
                                <label for="cf_name">Ator 1 <span class="required">(Obrigatório *)</span></label>
                                <input type="text" name="actor_1" id="actor_1" class="required" required />
                            </p>

                            <p>
                                <label for="cf_name">Ator 2 <span class="required">(Opcional *)</span></label>
                                <input type="text" name="actor_2" id="actor_2" class="required" />
                            </p>

                            <p>
                                <label for="cf_name">Ator 3 <span class="required">(Opcional *)</span></label>
                                <input type="text" name="actor_3" id="actor_3" class="required" />
                            </p>

                            <p>
                                <input type="hidden" name="action" value="search_advanced_1" />
                                <input type="submit" value="Buscar" />
                            </p>
                        </form>
                        <br/>        
                        <br/>        
                        <br/>        
                        <h2 class="underlined-header">Pesquisa avançada 2</h2>
                        <h4 class="underlined-header">Quais atores trabalharam com apenas gêneros X e/ou Y? Construir um ranking.</h4>
                        <form method="post" action="ManageUser">
                            <p>
                                <label for="cf_name">Gênero <span class="required" >(Obrigatório *)</span></label>
                                <select name="genre_1" class="required" required>
                                    <option value="" ></option>
                                    <% for (String genre : genres) {
                                            String[] split = genre.split("#");
                                    %>       
                                    <option value="<%= split[0]%>" ><%= split[1]%></option>
                                    <%   }%>
                                </select>    
                            </p>

                            <p>
                                <label for="cf_name">Gênero <span class="required" >(Obrigatório *)</span></label>
                                <select name="genre_2" class="required" required >
                                    <option value="" ></option>
                                    <% for (String genre : genres) {
                                            String[] split = genre.split("#");
                                    %>       
                                    <option value="<%= split[0]%>" ><%= split[1]%></option>
                                    <% }%>
                                </select>    
                            </p>

                            <p>
                                <input type="hidden" name="action" value="search_advanced_2" />
                                <input type="submit" value="Buscar" />
                            </p>
                        </form>
                        <br/>        
                        <br/>        
                        <br/>
                        <h2 class="underlined-header">Pesquisa avançada 3</h2>
                        <h4 class="underlined-header">Busca de filmes do gênero drama com no mínimo 2 atores e cada ator só pode ter um personagem no filme.</h4>
                        <form method="post" action="ManageUser">
                            <p>
                                <input type="hidden" name="action" value="search_advanced_3" />
                                <input type="submit" value="Buscar" />
                            </p>
                        </form>
                    </div>
                    <!-- /Code -->


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