"use strict";

addEventListener('DOMContentLoaded', () => {
    let cnt = sessionStorage.getItem("cnt");

    if (cnt == null) {
        sessionStorage.setItem("cnt", 1);
        const form_element = document.createElement('form');
        form_element.setAttribute('action', '/myproject/signup_servlet/home.go');
        form_element.setAttribute('method', 'post');
        document.querySelector('body').appendChild(form_element);
        form_element.submit();
    } else { setTimeout(sessionStorage.removeItem("cnt"), 1000) }

    
    contentLoad();
});

addEventListener('scroll',() => {
    let scrollHeight = Math.max(
        document.body.scrollHeight, document.documentElement.scrollHeight,
        document.body.offsetHeight, document.documentElement.offsetHeight,
        document.body.clientHeight, document.documentElement.clientHeight
    );
    
    if(scrollHeight/2 < scrollY) {
        contentLoad();
    }
        
});


const logout_btn = document.getElementById("logout");
const logo = document.getElementById("logo");
const home_tag = document.getElementById("home_tag");
const dm_tag = document.getElementById("dm_tag");
const nav_profile_img = document.getElementById("icon");
const layer = document.getElementById('layer');
const main = document.querySelector("main");


logout_btn.addEventListener("click", () => {
    const form_element = document.createElement('form');
    form_element.setAttribute('action', '/myproject/signup_servlet/logout.do');
    form_element.setAttribute('method', 'post');
    document.querySelector('body').appendChild(form_element);
    form_element.submit();
})

logo.addEventListener("click", MoveHomepage);
home_tag.addEventListener("click", MoveHomepage);


function MoveHomepage() {
    location.href="/home/index.jsp";
}


layer.style.display = 'none';
nav_profile_img.addEventListener('click', () => {
    if (layer.style.display == 'none') {
        layer.style.display = 'inline';
    } else {
        layer.style.display = 'none';
    }
});


function contentLoad() {
    const section = document.querySelectorAll("section");
    let section_cnt = section.length;
    let param = "from=" + section_cnt
                +"&till=" + (section_cnt + 6);

    fetch("/myproject/home_servlet/post.load", {
        method: "post",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: param
    })
    .then(response => response.text())
    .then(data => {
        const main = document.querySelector("main");
        const main_last_div = main.lastChild.previousSibling;
        main_last_div.innerHTML = data;
        const div_element = document.createElement('div');
        main.appendChild(div_element);
    })
    .catch(e => console.log(e));

}

main.addEventListener("click",()=>{
    const section = document.querySelectorAll("section");
    const textarea = document.querySelectorAll("textarea");
    const write_btn = document.getElementsByClassName("write_btn");
        
    textarea.forEach(v=>{
        v.addEventListener("input", () => {
            if(v.value.trim().length > 0) {
                v.nextElementSibling.disabled=false;
                v.nextElementSibling.style.cursor="pointer";
            } else {
                v.nextElementSibling.disabled=true;
                v.nextElementSibling.style.cursor="default";
            }
        });
    });

    Array.from(write_btn).forEach((v)=>{
        v.addEventListener("click",()=>{
            const post_img = v.parentElement.previousElementSibling.previousElementSibling.firstElementChild;
            const textarea = v.previousElementSibling;
            let comment = textarea.value;
            let num = post_img.alt;
            console.log(comment);
            uploadComment(comment, num);
            textarea.value="";
            location.reload();
        });
    });

});

function uploadComment(comment, num) {
    let param = "comment="+comment
                +"&num=" + num;
    fetch("/myproject/home_servlet/comment.upload", {
        method: "post",
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: param
    })
    .catch(e => console.log(e));
}

function sleep(ms) {
    const wakeUpTime = Date.now() + ms;
    while (Date.now() < wakeUpTime) {}
  }