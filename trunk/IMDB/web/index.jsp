<%-- 
    Document   : index
    Created on : 12/04/2011, 13:39:47
    Author     : Valter
--%>
<html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="head.jsp"%>
<body class="page-frontpage">
    <%@include file="header.jsp" %>    

    <!-- Slider nivo -->
    <div class="slider-wrapper slider-nivo">
        <div class="slider-content">
            <a href="#" title="Some title"><img class="slide-item" src="images/movies/1.jpg" alt="X-Men First Class" title="<p>X-Men First Class <a href='#'> the movie</a></p>" /></a>
            <a href="#" title="Some title"><img class="slide-item" src="images/movies/2.jpg" alt="Green Lantern" title="<p>Green Lantern<a href='#'> the movie</a></p>" /></a>
            <a href="#" title="Some title"><img class="slide-item" src="images/movies/3.jpg" alt="Transformers" title="<p>Transformers <a href='#'> the movie</a></p>" /></a>
            <a href="#" title="Some title"><img class="slide-item" src="images/movies/4.jpg" alt="The Hangover 2" title="<p>The Hangover <a href='#'> the movie</a></p>" /></a>
        </div>
    </div>
    <!-- /Slider nivo -->

    <!-- Page body -->
    <div id="page-body" class="container_12 clearfix">
        <div class="page-content">

            <!-- Front features -->
            <div class="clearfix">
                <!-- Theme features rows -->
                <section class="grid_4">
                    <a href="#" class="img-holder round-3">
                        <img src="assets/portfolio_3-2.jpg" />
                    </a>

                    <h4 class="underlined-header">Procure por seus filmes</h4>
                    <p>Procure aqui pelos seus filmes favoritos, na maior base de dados para filmes do mundo.</p>
                </section>
                <!-- /Theme features rows -->

                <!-- Theme features rows -->
                <section class="grid_4">
                    <a href="#" class="img-holder round-3">
                        <img src="assets/portfolio_3-5.jpg" />
                    </a>
                    <h4 class="underlined-header">Saiba mais</h4>
                    <p>Aqui você encontra não só informações sobre os seus filmes, mas sobre os diretores, atores também, pesquise agora mesmo.</p>
                </section>
                <!-- /Theme features rows -->

                <!-- Theme features rows -->
                <section class="grid_4">
                    <a href="#" class="img-holder round-3">
                        <img src="assets/portfolio_3-8.jpg" />
                    </a>
                    <h4 class="underlined-header">Contribua</h4>
                    <p>Faça o cadastro dos filmes, é bem simples, faça agora mesmo o cadastro do seu filme favorito!</p>
                </section>
                <!-- /Theme features rows -->
            </div>
            <!-- /Front features -->

            <hr /> <!-- /Separator -->

            <!-- Front content -->
            <div class="clearfix">
                <!-- Front text -->
                <article class="grid_8">
                    <h4 class="underlined-header">Vantagens</h4>
                    <p>Algumas vantagens de usar o IMDB:</p>
                    <ul class="featured-list">
                        <li>Agora você saberá tudo sobre o seu filme favorito</li>
                        <li>É a maior base de dados do mundo sobre filmes</li>
                        <li>Qualquer um pode participar, qualquer um pode contribuir para aumentar cada vez mais nossa base.</li>
                        <li>Fique antenado no mundo do cinema, saiba quem produzu, dirigiu, participou do seu filme favorito!</li>
                        <li>Faça parte desta grande rede de filmes!</li>
                    </ul>
                </article>
                <!-- /Front text -->

                <!-- Front aside -->
                <section class="grid_4">
                    <h4 class="underlined-header">Lançamento</h4>
                    <p class="round-3 img-holder">
                        <iframe width="290" height="195" src="http://www.youtube.com/embed/o8ccSiH4olo?rel=0" frameborder="0" allowfullscreen></iframe>
                        <span class="caption">X-Men First Class</span>
                    </p>
                </section>
                <!-- /Front aside -->
            </div>
            <!-- Front content -->
        </div>
    </div>
    <!-- /Page body -->

    <%@include file="bottom.jsp" %>

    <%@include  file="footer.jsp" %>

    <%@include file="scripts.jsp" %>

</body>

</html>

