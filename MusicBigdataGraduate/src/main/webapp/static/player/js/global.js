jQuery(document).ready(function() {
    "use strict";

    // Call contact form
    new  Contact_Form();

    var $ = jQuery,
    html = $('html');

    // IE<8 Warning
    if (html.hasClass("ie6") || html.hasClass("ie7")) {
        $("body").empty().html('UPDATE YOUR BROWSER');
    }

    // Enable menu toggle for small screens.
    ( function() {
        var nav = $( '#primary-navigation' ), button, menu;
        if ( ! nav ) {
            return;
        }

        button = nav.find( '.menu-toggle' );
        if ( ! button ) {
            return;
        }

        // Hide button if menu is missing or empty.
        menu = nav.find( '.nav-menu' );
        if ( ! menu || ! menu.children().length ) {
            button.hide();
            return;
        }

        $( '.menu-toggle' ).on( 'click.base', function() {
            nav.toggleClass( 'toggled-on' );
        } );
    } )();

    // Search
    $('.search-toggle').click(function(e){
        e.preventDefault();

        if($('.search-box-wrapper').height() == "0"){
            $("html, body").animate({ scrollTop: 0 },{duration: 500, queue: false});
            $('.search-box-wrapper input[type="search"]').focus();
            $('.search-box-wrapper').animate({height:"52px"});
        } else {
            $('.search-box-wrapper').animate({height:"0"});
        }
        
    });

    // Popup
    $('.popup')
      .popup()
    ;

    // magnific popup
    $('.popup-vimeo').magnificPopup({
        type:'iframe'
    });

    $('.popup-image').magnificPopup({
        type:'image'
    });

    $('.ui.accordion')
      .accordion()
    ;

    // Event Countdown
    $('.event_countdown').countdown({ 
        until: new Date(2015, 1 - 1, 25), timezone: +10,
        labels: ['Years', 'Months', 'Weeks', 'Days', 'Hr', 'Min', 'Second']
    });

    // Isotope
    var $container = $('.gallery-filterable');
    $container.isotope({
        filter: '*'
    });
 
    $('.filterable-menu a').click(function(){
        $('.filterable-menu .colored').removeClass('colored');
        $(this).addClass('colored');
 
        var selector = $(this).attr('data-filter');
        $container.isotope({
            filter: selector
         });
         return false;
    }); 

});

// Contact Form
function Contact_Form(){

    "use strict";
    
    var error_report;
    jQuery("#contact_submit").bind("click",function(){

        // Hide notice message when submit
        jQuery("#contact_form .notice_ok").hide();
        jQuery("#contact_form .notice_error").hide();
        error_report = false;

        jQuery("#contact_form input, #contact_form select, #contact_form textarea, #contact_form radio").each(function(i){

            var form_element          = jQuery(this);
            var form_element_value    = jQuery(this).val();
            var form_element_id       = jQuery(this).attr("id");
            //var form_element_class    = jQuery(this).attr("class");
            var form_element_required = jQuery(this).hasClass("required");

            // Check email validation
            if(form_element_id === "contact_email"){
                form_element.removeClass("error valid");
                if(!form_element_value.match(/^\w[\w|\.|\-]+@\w[\w|\.|\-]+\.[a-zA-Z]{2,4}$/)){
                    form_element.addClass("error");
                    error_report = true;
                } else {
                    form_element.addClass("valid");
                }
            }

            // Check input required validation
            if(form_element_required && form_element_id !== "contact_email"){
                form_element.removeClass("error valid");
                if(form_element_value === ""){
                    form_element.addClass("error");
                    error_report = true;
                } else {
                    form_element.addClass("valid");
                }
            }

            if(jQuery("#contact_form input, #contact_form select, #contact_form textarea, #contact_form radio").length === i+1){
                if(error_report === false){
                    jQuery("#contact_form .loading").show();

                    var $string = "ajax=true";
                    jQuery("#contact_form input, #contact_form select, #contact_form textarea, #contact_form radio").each(function(){
                        var $form_element_name     = jQuery(this).attr("name");
                        var $form_element_value    = encodeURIComponent(jQuery(this).val());
                        $string = $string + "&" + $form_element_name + "=" + $form_element_value;
                    });

                    jQuery.ajax({
                        type: "POST",
                        url: "./page_contact_ajax.php",
                        data:$string,
                        success: function(response){
                            jQuery("#contact_form .loading").hide();
                            if(response === 'success'){
                                jQuery("#contact_form .notice_ok").show();
                                jQuery("#contact_form .field_submit").hide();
                            } else {
                                jQuery("#contact_form .notice_error").show();
                                jQuery("#contact_form .field_submit").hide();
                            }
                        }
                    });
                }
            }


        });
    return false;
    });

}
