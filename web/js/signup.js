function refreshCaptcha() {
    var captchaImage = document.querySelector('.captcha img');
    captchaImage.src = 'captcha?' + new Date().getTime();
}