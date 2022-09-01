"use strict";

const form = document.querySelector("form");
const input = document.querySelectorAll("input");
const login_btn = document.getElementById("login_btn");
const sign_up_btn = document.getElementById("sign_up_btn");

input.forEach((v) => {
    v.addEventListener("input", () => {
        if (input[0].value != "" && input[1].value.length >= 6) {
            login_btn.disabled = false;
        } else {
            login_btn.disabled = true;
        }
    });
});

sign_up_btn.addEventListener("click", signUp);

function signUp() {
    location.href = "../signup/index.jsp";
}

login_btn.addEventListener("click", () => {

    let info = {
        id : input[0].value,
        pw : input[1].value
    }

    fetch("/myproject/signup_servlet/login.do", {
        method: "post",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(info)
    })
    .then(request => request.json())
    .then(data => {
        if( data.result ) {
            form.submit();
        } else {
            window.alert("잘못된 아이디 or 비밀번호");

        }
    })
    .catch(e => console.log(e));
});