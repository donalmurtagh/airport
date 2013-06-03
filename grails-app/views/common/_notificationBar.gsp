%{--I put this JQuery code in a GSP, so I can use flash.msgBody to display the message--}%

<r:script>
    (function($) {

        var _options = null;

        $.fn.MyTopMessageBar = function(options) {
            _options = $.extend({}, $.fn.MyTopMessageBar.defaults, options);

            $("#topMessageBar").remove();

            $("body").prepend("<div id='topMessageBar'><div id='msgContainer'><span id='notificationMessage'>"
                    + _options.message + "</span><span id='closeMsg'>[click to close]</span></div></div>");

            $("#topMessageBar").addClass(_options.cssClass);

            if (_options.bFading) {
                $("#topMessageBar").fadeIn();
                $("#messageBarCloseBtn").fadeIn();

            } else {
                $("#topMessageBar").slideDown();
                $("#messageBarCloseBtn").slideDown();
            }

            $("#topMessageBar").click(function(){
                if (_options.bFading) {
                    $("#messageBarCloseBtn").fadeOut();
                    $("#topMessageBar").fadeOut();

                } else {
                    $("#messageBarCloseBtn").hide();
                    $("#topMessageBar").slideUp();
                }
            });
        };

        $.fn.MyTopMessageBar.defaults = { bFading: false };

        <g:if test="${flash.info}">
            $().MyTopMessageBar({message: '${flashMsg.msg(key: "info", remove: "true")}', cssClass: 'messageBarCommon messageBarOk'});
        </g:if>

        <g:if test="${flash.warn}">
            $().MyTopMessageBar({message: '${flashMsg.msg(key: "warn", remove: "true")}', cssClass: 'messageBarCommon messageBarWarning'});
        </g:if>

    })(jQuery);
</r:script>