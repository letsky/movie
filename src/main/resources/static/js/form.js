"use strict";

//Plaeholder handler
$(function () {

    if (!Modernizr.input.placeholder) {             //placeholder for old brousers and IE

        $('[placeholder]').focus(function () {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function () {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
        $('[placeholder]').parents('form').submit(function () {
            $(this).find('[placeholder]').each(function () {
                var input = $(this);
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                }
            })
        });
    }

    $('.login').submit(function (e) {
        e.preventDefault();
        var error = 0;
        var self = $(this);

        var $email = self.find('[type=email]');
        var $pass = self.find('[type=password]');

        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailRegex.test($email.val())) {
            createErrTult("错误的邮箱格式!", $email);
            error++;
        }

        if ($pass.val().length > 1 && $pass.val() != $pass.attr('placeholder')) {
            $pass.removeClass('invalid_field');
        } else {
            createErrTult('无效的密码!', $pass);
            error++;
        }

        var data = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            url: '/api/users/login',
            method: 'POST',
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (data) {
                console.log(data);
            },
            statusCode: {
                400: function () {
                    alert("账号或密码错误");
                },
                404: function () {
                    alert("账号或密码错误");
                },
                200: function () {
                    location.reload();
                }
            }
        })
        // end post
    }); // end submit

    $('#logout').click(function () {
        $.ajax({
            url: "/api/users/logout",
            method: 'POST',
            contentType: 'application/json;charset=utf-8',
            success: function () {
                alert("退出登录");
                window.location.href = "/index";
            }
        })
    });

    $('.comment-form__btn').on('click', function (e) {
        e.preventDefault();

        var form = $('.comment-form').serializeObject();
        console.log(form);

        $.ajax({
            url: '/api/review',
            method: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(form),
            success: function (e) {
                console.log("success");
                location.reload();
            },
            statusCode: {
                400: function (e) {
                    console.log("400")
                },
                404: function (e) {
                    console.log("404")
                },
            }
        })
    });

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
});
