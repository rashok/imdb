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
                    <h2>Login</h2>
                    <h3>Acesse sua conta IMDB</h3>
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
                    <h2 class="underlined-header">Acessar</h2>

                    <!-- Code -->
                        <div class="toggle-content">
                            <form method="post" action="ManageUser">
                                <p>
                                    <label for="cf_name">Email <span class="required">(Obrigatório *)</span></label>
                                    <input type="text" name="email" id="email" class="required" required />
                                </p>

                                <p>
                                    <label for="cf_name">Senha <span class="required">(Obrigatório *)</span></label>
                                    <input type="password" name="password" id="password" class="required" required />
                                </p>

                                <p>
                                    <input type="hidden" name="action" value="login" />
                                    <input type="submit" value="Acessar" />
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