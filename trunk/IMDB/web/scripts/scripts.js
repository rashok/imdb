jQuery.noConflict();

jQuery(document).ready(function($) {
    /* Tweet widgets -------------------------------------------------------- */
    $(".widget-tweets .widget-content").tweet({
        username: "envato",
        count: 3
    });

    if($(".widget-last-tweet").length){
        $(".widget-last-tweet .widget-content").tweet({
            username: "envato",
            count: 1
        });
    }


    /* Forms validation ----------------------------------------------------- */
    //Validate form and send data to defined url file
        $("#contact-form, #contact-form-widget").each(function () {
            var url = $(this).attr('action'); /* Define server side script witch sends mail */
            var formItem = $(this);

            formItem.validate({
                submitHandler: function() {
                    var datastring = formItem.serialize();

                    $.ajax({
                        type: "POST",
                        url: url,
                        data: datastring,
                        success: function(data){
                            formItem.slideUp();
                            formItem.next('.form-sent').fadeIn().html(data);
                        }
                    });
                }
            });
        });

    /* Widget contact form */
    $('#contact-form-widget p').each(function() {
        labelText = $.trim($(this).find('label').text().replace('(Required *)', ''));
        myInput = $(this).find('input:not(:submit), textarea');

        // Hide label
        $(this).find('label').remove();

        $(myInput).val(labelText);

        // Remove placeholders
        myInput.bind('focusin', {label: labelText}, function(event) {
            if($(this).val() == event.data.label) {
                $(this).val('');
            }
        });

        // Add placeholders
        myInput.bind('focusout', {label: labelText}, function(event) {
            if($(this).val() == '') {
                $(this).val(event.data.label);
            }
        });
    });

    /* Comment form validation */
    $('#comment-reply').validate();


    /* Remove no-js class from html attribute if JavaScript is enabled */
    $('html').removeClass('no-js');


    /* HTML5 Video player --------------------------------------------------- */
    VideoJS.setupAllWhenReady();
    

    /* Top navigation ------------------------------------------------------- */
    $("#header-navigation ul ul").css({display: "none"});
    $("#header-navigation a").removeAttr('title');
    
    $("#header-navigation li:last").addClass('last');
    $("#header-navigation li li ul").parent().children('a').addClass('with-subnav');
    
    // Background shift (background image offset -60px) + (link margin 21px)
    $("#header-navigation > ul > li").each(function() {
        shift = -60 + 21 + ($(this).children('a').width() / 2);
        $(this).children('ul').css({backgroundPosition: shift + 'px -430px'});
    });

    // Top level color animation
    $("#header-navigation li").hover(function(){
        // Get color from page link element
        linkColor = $('a').css('color');
        $(this).children('a').stop(true, true).animate({color: linkColor});
    }, function() {
        $(this).children('a').stop(true, true).animate({color: '#fff'});
    });

    // Dropdown li a background
    $("#header-navigation ul ul a").hover(function(){
        // Get color from navigation ul element
        ddColor = $('#header-navigation').css('backgroundColor');
        $(this).stop().animate({backgroundColor: "#222"}, 'normal');
    }, function() {
        $(this).stop().animate({backgroundColor: "#333"}, 'normal');
    });

    // Dropdown animation
    $("#header-navigation li").hover(function(){
        $(this).children('ul:first').css({visibility: "visible"}).stop(true, true).slideDown(400);
    },function(){
        $(this).children('ul:first').stop(true, true).delay(100).fadeOut('fast');
    });


    /* Nivo slider ---------------------------------------------------------- */
    if($('.slider-nivo .slider-content, .gallery-inner').length) {
        $('.slider-nivo .slider-content, .gallery-inner').nivoSlider({
            effect: 'fold',
            directionNav:true,
            pauseTime: 5000
        });
    }


    /* Prettyphoto ---------------------------------------------------------- */
    if($("a[rel^='prettyPhoto']").length) {
        $("a[rel^='prettyPhoto']").prettyPhoto({overlay_gallery: false});

        $("a[rel^='prettyPhoto']").each(function(){
            // Get image sizes
            if($(this).find('.portfolio-image-inner, .gallery-image-inner').length) {
                width = $(this).find('.portfolio-image-inner, .gallery-image-inner').width();
                height = $(this).find('.portfolio-image-inner, .gallery-image-inner').height();
            } else {
                width = $(this).find('img').width();
                height = $(this).find('img').height();
            }

            // Add "prettyPhoto" class and add a hidden span
            $(this).addClass('prettyPhoto').append('<span class="zoom-frame" />');
            $(this).find('.zoom-frame').width(width).height(height);

            // Show "frame" on hover
            $(this).hover(function() {
                $(this).find('.zoom-frame').stop(true, true).fadeIn();
            }, function() {
                $(this).find('.zoom-frame').stop(true, true).fadeOut();
            });
        });
    }


    /* Search top ----------------------------------------------------------- */
    if($.browser.webkit) {
        $('#title-search #seachField').css({height: '30px'});
    }
    
    $('#title-search #seachField')
        .animate({width: '50px'})
        .blur(function(){
            if($('#title-search #seachField').val() == '') {
                $(this).animate({width: '50px'});
            }
        });
    
    $('#title-search').hover(function(){
        $('#title-search #seachField').stop(true, true).animate({width: '180px'});
    }, function() {
        if(($('#title-search #seachField').val() == '') && !($('#title-search #seachField').is(":focus"))) {
            $('#title-search #seachField').stop(true, true).animate({width: '50px'}, 'slow');
        }
    });


    /* Images preloader ----------------------------------------------------- */
    if($.browser.msie != true) {
        $("img").hide();
        $("img").fadeIn("normal");
    }

    /* Widgets hover hint --------------------------------------------------- */
    $('.widget-hint').css({display: 'none'});
    $('.widget-hint').parent().css({position: "relative"}).hover(function() {
        $(this).find('.widget-hint').stop(true, true).fadeIn();
    }, function() {
        $(this).find('.widget-hint').stop(true, true).fadeOut();
    });


    /* Rounded corners for member's avatar - opera and firefox < v.4 fix ---- */
    if($.browser.opera || parseInt($.browser.mozilla) < 2){
        $('.team-member-img').each(function() {
            background = $(this).children('img').attr('src');
            $(this).children('img').remove();
            
            $('<div style="width: 130px; height: 130px; margin: 5px; border-radius: 70px; -moz-border-radius: 70px; overflow: hidden; background: url(' + background + ')" />').appendTo(this);
        });
    };


    /* Tipsy ---------------------------------------------------------------- */
    $('.social a, .social-dark a, .pager-first a, .pager-last a, .postlist-footer a').tipsy({fade: true, gravity: 'n'});


    /* Tabs ----------------------------------------------------------------- */
    if($(".tabs-block").length) {
        $('.tabs-block').each(function() {
            // Hide all tabs content
            $(this).find(".tabs-content").hide();

            // Activate first tab
            $(this).find(".tabs-controller li:first").addClass("active").show();
            $(this).find(".tabs-content:first").show();

            // Click tab and make magic
            $(this).find(".tabs-controller li").click(function() {
                // Add "active" class to the current tab and remove it from others
                $(this).addClass("active").siblings().removeClass("active");
                $(".tabs-content").hide();

                // Get an ID of selected tab and show it
                var activeTab = $(this).find("a").attr("href");
                $(activeTab).fadeIn();

                // Disable link click
                return false;
            });
        });
    }


    /* Toggles -------------------------------------------------------------- */
    if($('.toggle-block').length) {
        $('.toggle-block').each(function() {
            // Hide all toggles content
            $(this).find(".toggle-content").hide();

            // Click on toggle trigger and show the content
            $(this).find(".toggle-trigger").click(function() {
                $(this).toggleClass("active").next().stop(true, true).slideToggle('normal');

                // Disable link click
                return false;
            });
        });
    }


    /* Accordions ----------------------------------------------------------- */
    if($('.toggle-block').length) {
        $('.accordion-block').each(function() {
            // Hide all accordions content
            $(this).find('.accordion-content').hide();

            // Click on accordion item trigger and show the content
            $(this).find('.accordion-trigger').click(function() {
                // On click we close all slides and removes "on" class
                $('.accordion-trigger').removeClass('active');
                $('.accordion-content').slideUp('normal');

                // Open next to this trigger slider
                if($(this).next().is(':hidden') == true) {
                    $(this).addClass('active');
                    $(this).next().slideDown('normal');
                }

                // Disable link click
                return false;
            });
        });
    }
});