"use strict";

//General function for all pages

//Modernizr touch detect
Modernizr.load({
    test: Modernizr.touch,
    yep: ['css/touch.css?v=1'],
    nope: []
});

//1. Scroll to top arrow
// Scroll to top
var $block = $('<div/>', {'class': 'top-scroll'}).append('<a href="#"/>').hide().appendTo('body').click(function () {
    $('body,html').animate({scrollTop: 0}, 800);
    return false;
});

//2. Mobile menu
//Init mobile menu
$('#navigation').mobileMenu({
    triggerMenu: '#navigation-toggle',
    subMenuTrigger: ".sub-nav-toggle",
    animationSpeed: 500
});

//3. Search bar dropdown
//search bar
$("#search-sort").selectbox({
    onChange: function (val, inst) {

        $(inst.input[0]).children().each(function (item) {
            $(this).removeAttr('selected');
        });
        $(inst.input[0]).find('[value="' + val + '"]').attr('selected', 'selected');
    }
});

//4. Login window pop up
//Login pop up
$('.login-window').click(function (e) {
    e.preventDefault();
    $('.overlay').removeClass('close').addClass('open');
});

$('.overlay-close').click(function (e) {
    e.preventDefault;
    $('.overlay').removeClass('open').addClass('close');

    setTimeout(function () {
        $('.overlay').removeClass('close');
    }, 500);
});


function user_list() {
    //user list option
    $('.auth__show').click(function (e) {
        e.preventDefault();
        $('.auth__function').toggleClass('open-function')
    });

    $('.btn--singin').click(function (e) {
        e.preventDefault();
        $('.auth__function').toggleClass('open-function')
    });
}

//2. Dropdown for authorize button
//user list option
user_list();

function init_Home() {
    "use strict";

    //4. Rating score init
    //Rating star
    $('.score').raty({
        width: 130,
        score: 0,
        path: 'images/rate/',
        starOff: 'star-off.svg',
        starOn: 'star-on.svg'
    });
}

function init_OrderSeats() {
    "use strict";

    //2. Init vars for order data
    // var for booking;
    var sumTicket = $('.choosen-cost'),
        sits = $('.choosen-sits');

    //data elements init
    var sum = 0;

    $('.sits__place').click(function (e) {
        e.preventDefault();
        var place = $(this).attr('data-place');
        var ticketPrice = $(this).attr('data-price');
        var price = parseInt(ticketPrice);

        if (!$(e.target).hasClass('sits-state--your')) {

            if (!$(this).hasClass('sits-state--not')) {
                $(this).addClass('sits-state--your');
                $('.checked-place').prepend('<span class="choosen-place ' + place + '">' + place + '</span>');
                sum += price;
                $('.checked-result').text('￥' + sum);
            }
        } else {
            $(this).removeClass('sits-state--your');
            $('.' + place + '').remove();
            sum -= price;
            $('.checked-result').text('￥' + sum)
        }

        //data element set
        sumTicket.val(sum);

        //data element init
        var chooseSits = '';
        $('.choosen-place').each(function () {
            chooseSits += $(this).text() + ',';
        });

        //data element set
        sits.val(chooseSits);
    });

    //--- Step for data  ---//
    //Get data from pvevius page
    var url = decodeURIComponent(document.URL);
    var prevDate = url.substr(url.indexOf('?') + 1);

    //Serialize, add new data and send to next page
    $('.booking-form').submit(function (e) {
        e.preventDefault();
        var data = $('.choosen-sits').val();
        var index = data.lastIndexOf(",");
        var params = data.substr(0, index).split(",");
        if (params.length <= 0 || params[0] == "") {
            alert("请选择座位");
            return;
        }
        var newData = '';
        for (var i = 0; i < params.length; i++) {
            newData += "&" + "sits=" + params[i];
        }
        var fullData = prevDate + newData;
        console.log(fullData);
        window.location.href = "/order?" + fullData;
    });


}

function init_MovieList() {
    "use strict";

    //1. Dropdown init
    //select
    $(".select__sort").selectbox({
        onChange: function (val, inst) {

            $(inst.input[0]).children().each(function (item) {
                $(this).removeAttr('selected');
            });
            $(inst.input[0]).find('[value="' + val + '"]').attr('selected', 'selected');
        }

    });

    //3. Rating scrore init
    //Rating star
    $('.score').raty({
        width: 130,
        score: 0,
        number: 5,
        hintList: ['1分', '2分', '3分', '4分', '5分'],
        half: true,
        readOnly: true,
        noRatedMsg: '您还没评分！',
        path: 'images/rate/',
        starOff: 'star-off.svg',
        starOn: 'star-on.svg'
    });

    //5. Toggle function for additional content
    //toggle timetable show
    $('.movie__show-btn').click(function (ev) {
        ev.preventDefault();

        $(this).parents('.movie--preview').find('.time-select').slideToggle(500);
    });

    $('.time-select__item').click(function () {
        $('.time-select__item').removeClass('active');
        $(this).addClass('active');
    });
}

function init_MoviePage() {
    "use strict";

    //6. Reply comment form
    //reply comment function
    $('.comment__reply').click(function (e) {
        e.preventDefault();

        $('.comment').find('.comment-form').remove();
        $(this).parent().append("<form id='comment-form' class='comment-form' method='post'>\
                            <textarea class='comment-form__text' placeholder='发表您的评论吧'></textarea>\
                            <button type='submit' class='btn btn-md btn--danger comment-form__btn'>发表评论</button>\
                        </form>");
    });
}

function init_SinglePage() {
    "use strict";

    //reply comment function
    $('.comment__reply').click(function (e) {
        e.preventDefault();

        $('.comment').find('.comment-form').remove();


        $(this).parent().append("<form id='comment-form' class='comment-form' method='post'>\
                            <textarea class='comment-form__text' placeholder='发表您的评论吧'></textarea>\
                            <button type='submit' class='btn btn-md btn--danger comment-form__btn'>发表评论</button>\
                        </form>");
    });
}