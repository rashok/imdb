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
                    <h2 class="underlined-header">Mensagem</h2>
                    <% String status = (String) session.getAttribute("status");
                        String message = (String) session.getAttribute("message");
                        if (status != null && message != null) {
                    %>
                    <!-- <div class="[box_info, box_success, box_warning, box_error] round-3"> -->
                            <div class="layout-1-2">
                                <h4>Informação</h4>
                                <div class="box-info round-3">
                                    <div class="<%= status + " round-3"%>">
                                        <div class="box-content">
                                            <p><% out.print(message);%></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                
                    <%      session.removeAttribute("status");  
                            session.removeAttribute("message");
                        } else { %>
                    <div class="layout-1-2">
                        <h4>Informação</h4>
                        <div class="box-info round-3">
                            <div class="box-info round-3">
                                <div class="box-content">
                                    <p>Não foi realizada nenhuma operação para ter alguma informação.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                <% }%>
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