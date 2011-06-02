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
                    <h2>Cadastro</h2>
                    <h3>Cadastre atores, diretores, filmes, ajude o IMDB.</h3>
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
                    <h2 class="underlined-header">Cadastrar</h2>

                    <!-- Code -->
                    <div class="toggle-block">
                        <div class="toggle-trigger"><a href="#">Artista</a></div>
                        <div class="toggle-content">
                            <form method="post" action="ManageArtist">
                                <p>
                                    <label for="cf_name">Nome <span class="required">(Obrigatório *)</span></label>
                                    <input type="text" name="name" id="name" class="required" required />
                                </p>

                                <p>
                                    <label for="cf_name">Gênero <span class="required">(Obrigatório *)</span></label>
                                    <select name="gender" class="required" required>
                                        <option value="" class="" ></option>
                                        <option value="F" >Feminino</option>
                                        <option value="M" >Masculino</option>
                                    </select>
                                </p>

                                <p>
                                    <input type="hidden" name="action" value="selectMovies" />
                                    <input type="submit" value="Selecionar filme(s)" />
                                </p>
                            </form>
                        </div>
                    </div>
                    <!-- /Code -->

                    <!-- Code -->
                    <div class="toggle-block">
                        <div class="toggle-trigger"><a href="#">Diretores</a></div>
                        <div class="toggle-content">
                            <form method="post" action="ManageArtist">
                                <p>
                                    <label for="cf_name">Nome <span class="required">(Obrigatório *)</span></label>
                                    <input type="text" name="name" id="name" class="required" required />
                                </p>

                                <p>
                                    <input type="hidden" name="action" value="selectMovies" />
                                    <input type="submit" value="Selecionar filme(s)" />
                                </p>
                            </form>
                        </div>
                    </div>
                    <!-- /Code -->

                    <!-- Code -->
                    <div class="toggle-block">
                        <div class="toggle-trigger"><a href="#">Filme</a></div>
                        <div class="toggle-content">
                            <form method="post" action="ManageMovie">
                                <p>
                                    <label for="cf_name">Título <span class="required">(Obrigatório *)</span></label>
                                    <input type="text" name="title" id="title" class="required" required />

                                </p>

                                <p>
                                    <label for="cf_name">Ano <span class="required">(Obrigatório *)</span></label>
                                    <select name="year" >
                                        <% for (int i = 2011; i >= 1900; i--) {%>
                                        <option value="<%= i%>" ><%= i%></option>
                                        <%
                                            }
                                        %>
                                    </select>    
                                </p>

                                <p>
                                    <label for="cf_name">Gênero <span class="required">(Obrigatório *)</span></label>
                                    <select name="genre" >
                                        <% for (String genre : genres) {
                                                String[] split = genre.split("#");
                                        %>       
                                        <option value="<%= split[0]%>" ><%= split[1]%></option>
                                        <%   }%>
                                    </select>    
                                </p>

                                <p>
                                    <label for="cf_name">Língua <span class="required">(Obrigatório *)</span></label>
                                    <select name="language" >
                                        <% for (String language : languages) {
                                                String[] split = language.split("#");
                                        %>       
                                        <option value="<%= split[0]%>" ><%= split[1]%></option>
                                        <%   }%>
                                    </select>    

                                </p>
                                
                                <p>
                                    <label for="cf_name">Info. sobre língua <span class="required">(Opcional *)</span></label>
                                    <input type="text" name="info" id="info" class="required" />
                                </p>
                                
                                <p> 
                                    <input type="hidden" name="action" value="register" />
                                    <input type="submit" value="Cadastrar" />
                                </p>
                            </form>
                        </div>
                    </div>
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