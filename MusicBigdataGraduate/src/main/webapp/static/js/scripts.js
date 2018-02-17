(function($) {
    $(document).ready(function() {

        // upload button
    	$('#top-links li.upload a').click(function(e) {
    	    e.preventDefault();
    	    if ($(this).attr('data-login') == 'true') {
    	        window.location.href = "/upload";
    	    } else {
    	        $('#login-modal').modal();
    	    }
    	});

        $("#contact-form").submit(function() {
        	var formData = new FormData($(this)[0]);
            var form = $(this);
            var script = $(this).attr("action");
            $.ajax({
                type: "POST",
                url: script,
                dataType: "html",
                data: formData,
                beforeSend: function() {
                    form.find('.loading').remove();
                    form.find('.dynamic').append('<div class="loading"><i class="fa fa-3x fa-spinner fa-spin"></i></div>')
                },
                success: function(response) {
                    form.find(".loading").remove();
                    form.find('.dynamic').append('<div class="result">' + response + '</div>')
                }
            });
            return false;
        });

    });

})(jQuery);