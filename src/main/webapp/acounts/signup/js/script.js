"use strict";

const section = document.getElementsByClassName("section");
const input = document.querySelectorAll("input");

const email_false_img = document.getElementById("email_false_img");
const email_pass_img = document.getElementById("email_pass_img");
const full_pass_img = document.getElementById("full_pass_img");
const user_false_img = document.getElementById("user_false_img");
const user_pass_img = document.getElementById("user_pass_img");
const pw_false_img = document.getElementById("pw_false_img");
const pw_pass_img = document.getElementById("pw_pass_img");

const sign_up_btn = document.getElementById("sign_up_btn");


input.forEach((v) => {
    v.addEventListener("change", () => {
        emailCheck(input[0].value);
        fullNameCheck(input[1].value);
        userNameCheck(input[2].value);
        pwCheck(input[3].value);
        signUpBtn();
    });
});

sign_up_btn.addEventListener("click", () => {

    let info = {
        email : input[0].value,
        fullname : input[1].value,
        userid : input[2].value,
        pw : input[3].value,
        birth : "",
    };

    section[0].innerHTML =
        "<article class=\"logo\"><img id=\"logo\" src=\"../../img/cat-birth.svg\" alt=\"birth_cat\" width=\"150px\"></article>"
        + "<article class=\"birth\">"
        + "<span><h3>생일 추가</h3></span>"
        + "<div class=\"birth_select\">"
        + "<select class=\"h144Z\" title=\"연도:\"><option title=\"2022\" value=\"2022\">2022</option><option title=\"2021\" value=\"2021\">2021</option><option title=\"2020\" value=\"2020\">2020</option><option title=\"2019\" value=\"2019\">2019</option><option title=\"2018\" value=\"2018\">2018</option><option title=\"2017\" value=\"2017\">2017</option><option title=\"2016\" value=\"2016\">2016</option><option title=\"2015\" value=\"2015\">2015</option><option title=\"2014\" value=\"2014\">2014</option><option title=\"2013\" value=\"2013\">2013</option><option title=\"2012\" value=\"2012\">2012</option><option title=\"2011\" value=\"2011\">2011</option><option title=\"2010\" value=\"2010\">2010</option><option title=\"2009\" value=\"2009\">2009</option><option title=\"2008\" value=\"2008\">2008</option><option title=\"2007\" value=\"2007\">2007</option><option title=\"2006\" value=\"2006\">2006</option><option title=\"2005\" value=\"2005\">2005</option><option title=\"2004\" value=\"2004\">2004</option><option title=\"2003\" value=\"2003\">2003</option><option title=\"2002\" value=\"2002\">2002</option><option title=\"2001\" value=\"2001\">2001</option><option title=\"2000\" value=\"2000\">2000</option><option title=\"1999\" value=\"1999\">1999</option><option title=\"1998\" value=\"1998\">1998</option><option title=\"1997\" value=\"1997\">1997</option><option title=\"1996\" value=\"1996\">1996</option><option title=\"1995\" value=\"1995\">1995</option><option title=\"1994\" value=\"1994\">1994</option><option title=\"1993\" value=\"1993\">1993</option><option title=\"1992\" value=\"1992\">1992</option><option title=\"1991\" value=\"1991\">1991</option><option title=\"1990\" value=\"1990\">1990</option><option title=\"1989\" value=\"1989\">1989</option><option title=\"1988\" value=\"1988\">1988</option><option title=\"1987\" value=\"1987\">1987</option><option title=\"1986\" value=\"1986\">1986</option><option title=\"1985\" value=\"1985\">1985</option><option title=\"1984\" value=\"1984\">1984</option><option title=\"1983\" value=\"1983\">1983</option><option title=\"1982\" value=\"1982\">1982</option><option title=\"1981\" value=\"1981\">1981</option><option title=\"1980\" value=\"1980\">1980</option><option title=\"1979\" value=\"1979\">1979</option><option title=\"1978\" value=\"1978\">1978</option><option title=\"1977\" value=\"1977\">1977</option><option title=\"1976\" value=\"1976\">1976</option><option title=\"1975\" value=\"1975\">1975</option><option title=\"1974\" value=\"1974\">1974</option><option title=\"1973\" value=\"1973\">1973</option><option title=\"1972\" value=\"1972\">1972</option><option title=\"1971\" value=\"1971\">1971</option><option title=\"1970\" value=\"1970\">1970</option><option title=\"1969\" value=\"1969\">1969</option><option title=\"1968\" value=\"1968\">1968</option><option title=\"1967\" value=\"1967\">1967</option><option title=\"1966\" value=\"1966\">1966</option><option title=\"1965\" value=\"1965\">1965</option><option title=\"1964\" value=\"1964\">1964</option><option title=\"1963\" value=\"1963\">1963</option><option title=\"1962\" value=\"1962\">1962</option><option title=\"1961\" value=\"1961\">1961</option><option title=\"1960\" value=\"1960\">1960</option><option title=\"1959\" value=\"1959\">1959</option><option title=\"1958\" value=\"1958\">1958</option><option title=\"1957\" value=\"1957\">1957</option><option title=\"1956\" value=\"1956\">1956</option><option title=\"1955\" value=\"1955\">1955</option><option title=\"1954\" value=\"1954\">1954</option><option title=\"1953\" value=\"1953\">1953</option><option title=\"1952\" value=\"1952\">1952</option><option title=\"1951\" value=\"1951\">1951</option><option title=\"1950\" value=\"1950\">1950</option><option title=\"1949\" value=\"1949\">1949</option><option title=\"1948\" value=\"1948\">1948</option><option title=\"1947\" value=\"1947\">1947</option><option title=\"1946\" value=\"1946\">1946</option><option title=\"1945\" value=\"1945\">1945</option><option title=\"1944\" value=\"1944\">1944</option><option title=\"1943\" value=\"1943\">1943</option><option title=\"1942\" value=\"1942\">1942</option><option title=\"1941\" value=\"1941\">1941</option><option title=\"1940\" value=\"1940\">1940</option><option title=\"1939\" value=\"1939\">1939</option><option title=\"1938\" value=\"1938\">1938</option><option title=\"1937\" value=\"1937\">1937</option><option title=\"1936\" value=\"1936\">1936</option><option title=\"1935\" value=\"1935\">1935</option><option title=\"1934\" value=\"1934\">1934</option><option title=\"1933\" value=\"1933\">1933</option><option title=\"1932\" value=\"1932\">1932</option><option title=\"1931\" value=\"1931\">1931</option><option title=\"1930\" value=\"1930\">1930</option><option title=\"1929\" value=\"1929\">1929</option><option title=\"1928\" value=\"1928\">1928</option><option title=\"1927\" value=\"1927\">1927</option><option title=\"1926\" value=\"1926\">1926</option><option title=\"1925\" value=\"1925\">1925</option><option title=\"1924\" value=\"1924\">1924</option><option title=\"1923\" value=\"1923\">1923</option><option title=\"1922\" value=\"1922\">1922</option><option title=\"1921\" value=\"1921\">1921</option><option title=\"1920\" value=\"1920\">1920</option><option title=\"1919\" value=\"1919\">1919</option></select>"
        + "<select class=\"h144Z\" title=\"월:\"><option title=\"1월\" value=\"01\">1월</option><option title=\"2월\" value=\"02\">2월</option><option title=\"3월\" value=\"03\">3월</option><option title=\"4월\" value=\"04\">4월</option><option title=\"5월\" value=\"05\">5월</option><option title=\"6월\" value=\"06\">6월</option><option title=\"7월\" value=\"07\">7월</option><option title=\"8월\" value=\"08\">8월</option><option title=\"9월\" value=\"09\">9월</option><option title=\"10월\" value=\"10\">10월</option><option title=\"11월\" value=\"11\">11월</option><option title=\"12월\" value=\"12\">12월</option></select>"
        + "<select class=\"h144Z\" title=\"일:\"><option title=\"01\" value=\"01\">1</option><option title=\"02\" value=\"02\">2</option><option title=\"03\" value=\"03\">3</option><option title=\"04\" value=\"04\">4</option><option title=\"05\" value=\"05\">5</option><option title=\"06\" value=\"06\">6</option><option title=\"07\" value=\"07\">7</option><option title=\"08\" value=\"08\">8</option><option title=\"09\" value=\"09\">9</option><option title=\"10\" value=\"10\">10</option><option title=\"11\" value=\"11\">11</option><option title=\"12\" value=\"12\">12</option><option title=\"13\" value=\"13\">13</option><option title=\"14\" value=\"14\">14</option><option title=\"15\" value=\"15\">15</option><option title=\"16\" value=\"16\">16</option><option title=\"17\" value=\"17\">17</option><option title=\"18\" value=\"18\">18</option><option title=\"19\" value=\"19\">19</option><option title=\"20\" value=\"20\">20</option><option title=\"21\" value=\"21\">21</option><option title=\"22\" value=\"22\">22</option><option title=\"23\" value=\"23\">23</option><option title=\"24\" value=\"24\">24</option><option title=\"25\" value=\"25\">25</option><option title=\"26\" value=\"26\">26</option><option title=\"27\" value=\"27\">27</option><option title=\"28\" value=\"28\">28</option><option title=\"29\" value=\"29\">29</option><option title=\"30\" value=\"30\">30</option><option title=\"31\" value=\"31\">31</option></select></div>"
        + "<br>"
        + "<button type=\"button\" id=\"next_btn\" disabled>다음</button>"
        + "</article>";

    const birth = document.getElementsByClassName("h144Z");
    const next_btn = document.getElementById("next_btn");
    
    let isLeap = false;
    let year = 2022;
    let month = 1;
    let day = 1;

    Array.from(birth).forEach((v) => {
        v.addEventListener("change", () => {
            if (day == birth[2].value) {
                year = birth[0].value;
                month = birth[1].value;
                day = birth[2].value;
                isLeap = (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
                if (month == 2) {
                    if (isLeap) {
                        birth[2].innerHTML = "<option title=\"01\" value=\"01\">1</option><option title=\"02\" value=\"02\">2</option><option title=\"03\" value=\"03\">3</option><option title=\"04\" value=\"04\">4</option><option title=\"05\" value=\"05\">5</option><option title=\"06\" value=\"06\">6</option><option title=\"07\" value=\"07\">7</option><option title=\"08\" value=\"08\">8</option><option title=\"09\" value=\"09\">9</option><option title=\"10\" value=\"10\">10</option><option title=\"11\" value=\"11\">11</option><option title=\"12\" value=\"12\">12</option><option title=\"13\" value=\"13\">13</option><option title=\"14\" value=\"14\">14</option><option title=\"15\" value=\"15\">15</option><option title=\"16\" value=\"16\">16</option><option title=\"17\" value=\"17\">17</option><option title=\"18\" value=\"18\">18</option><option title=\"19\" value=\"19\">19</option><option title=\"20\" value=\"20\">20</option><option title=\"21\" value=\"21\">21</option><option title=\"22\" value=\"22\">22</option><option title=\"23\" value=\"23\">23</option><option title=\"24\" value=\"24\">24</option><option title=\"25\" value=\"25\">25</option><option title=\"26\" value=\"26\">26</option><option title=\"27\" value=\"27\">27</option><option title=\"28\" value=\"28\">28</option><option title=\"29\" value=\"29\">29</option>"
                    } else {
                        birth[2].innerHTML = "<option title=\"01\" value=\"01\">1</option><option title=\"02\" value=\"02\">2</option><option title=\"03\" value=\"03\">3</option><option title=\"04\" value=\"04\">4</option><option title=\"05\" value=\"05\">5</option><option title=\"06\" value=\"06\">6</option><option title=\"07\" value=\"07\">7</option><option title=\"08\" value=\"08\">8</option><option title=\"09\" value=\"09\">9</option><option title=\"10\" value=\"10\">10</option><option title=\"11\" value=\"11\">11</option><option title=\"12\" value=\"12\">12</option><option title=\"13\" value=\"13\">13</option><option title=\"14\" value=\"14\">14</option><option title=\"15\" value=\"15\">15</option><option title=\"16\" value=\"16\">16</option><option title=\"17\" value=\"17\">17</option><option title=\"18\" value=\"18\">18</option><option title=\"19\" value=\"19\">19</option><option title=\"20\" value=\"20\">20</option><option title=\"21\" value=\"21\">21</option><option title=\"22\" value=\"22\">22</option><option title=\"23\" value=\"23\">23</option><option title=\"24\" value=\"24\">24</option><option title=\"25\" value=\"25\">25</option><option title=\"26\" value=\"26\">26</option><option title=\"27\" value=\"27\">27</option><option title=\"28\" value=\"28\">28</option>"
                    }
                } else {
                    switch (month) {
                        case '1':
                        case '3':
                        case '5':
                        case '7':
                        case '8':
                        case '10':
                        case '12':
                            birth[2].innerHTML = "<option title=\"01\" value=\"01\">1</option><option title=\"02\" value=\"02\">2</option><option title=\"03\" value=\"03\">3</option><option title=\"04\" value=\"04\">4</option><option title=\"05\" value=\"05\">5</option><option title=\"06\" value=\"06\">6</option><option title=\"07\" value=\"07\">7</option><option title=\"08\" value=\"08\">8</option><option title=\"09\" value=\"09\">9</option><option title=\"10\" value=\"10\">10</option><option title=\"11\" value=\"11\">11</option><option title=\"12\" value=\"12\">12</option><option title=\"13\" value=\"13\">13</option><option title=\"14\" value=\"14\">14</option><option title=\"15\" value=\"15\">15</option><option title=\"16\" value=\"16\">16</option><option title=\"17\" value=\"17\">17</option><option title=\"18\" value=\"18\">18</option><option title=\"19\" value=\"19\">19</option><option title=\"20\" value=\"20\">20</option><option title=\"21\" value=\"21\">21</option><option title=\"22\" value=\"22\">22</option><option title=\"23\" value=\"23\">23</option><option title=\"24\" value=\"24\">24</option><option title=\"25\" value=\"25\">25</option><option title=\"26\" value=\"26\">26</option><option title=\"27\" value=\"27\">27</option><option title=\"28\" value=\"28\">28</option><option title=\"29\" value=\"29\">29</option><option title=\"30\" value=\"30\">30</option><option title=\"31\" value=\"31\">31</option>";
                            break;
                        case '4':
                        case '6':
                        case '9':
                        case '11':
                            birth[2].innerHTML = "<option title=\"01\" value=\"01\">1</option><option title=\"02\" value=\"02\">2</option><option title=\"03\" value=\"03\">3</option><option title=\"04\" value=\"04\">4</option><option title=\"05\" value=\"05\">5</option><option title=\"06\" value=\"06\">6</option><option title=\"07\" value=\"07\">7</option><option title=\"08\" value=\"08\">8</option><option title=\"09\" value=\"09\">9</option><option title=\"10\" value=\"10\">10</option><option title=\"11\" value=\"11\">11</option><option title=\"12\" value=\"12\">12</option><option title=\"13\" value=\"13\">13</option><option title=\"14\" value=\"14\">14</option><option title=\"15\" value=\"15\">15</option><option title=\"16\" value=\"16\">16</option><option title=\"17\" value=\"17\">17</option><option title=\"18\" value=\"18\">18</option><option title=\"19\" value=\"19\">19</option><option title=\"20\" value=\"20\">20</option><option title=\"21\" value=\"21\">21</option><option title=\"22\" value=\"22\">22</option><option title=\"23\" value=\"23\">23</option><option title=\"24\" value=\"24\">24</option><option title=\"25\" value=\"25\">25</option><option title=\"26\" value=\"26\">26</option><option title=\"27\" value=\"27\">27</option><option title=\"28\" value=\"28\">28</option><option title=\"29\" value=\"29\">29</option><option title=\"30\" value=\"30\">30</option>";
                            break;
                    }
                }
            } else {
                year = birth[0].value;
                month = birth[1].value;
                day = birth[2].value;
            }

            if (year < 2015) next_btn.disabled = false;
            else next_btn.disabled = true;

        });
    });
    next_btn.addEventListener("click", () => {
        info.birth = year + month + day;

        fetch("/myproject/signup_servlet/insert.do", {
            method: "post",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(info)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if(data.pw == 1) {
                    section[0].innerHTML =
                    "<article class=\"logo\"><img id=\"logo\" src=\"../../img/cat-welcom.png\" alt=\"birth_cat\" width=\"150px\"></article>"
                    + "<article class=\"welcom\">"
                    + "<span><h3>환영합니다" + data.userid + "님</h3></span>";
                    section[1].innerHTML = 
                    "<div>ShaMP 구경하러 <a href=\"../login/index\">가기</a></div>"
                } else {
                    alert("잘못된 입력!");
                }
            })
            .catch(e => console.log(e));
    });
});

function emailCheck(email) {
    let regex = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]+$/;
    let overlap = "";

    fetch("/myproject/signup_servlet/emailchk.do", {
        method: "post",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            email: email
        })
    })
    .then(response => response.json())
    .then(data => {
        overlap = data.result;
    
        if(email == '') {
            email_pass_img.hidden=true;
            email_false_img.hidden=true;
        } else if(overlap == 1) {
            email_pass_img.hidden=true;
            email_false_img.hidden=false;
        }
        else if(regex.test(email)){
            email_pass_img.hidden=false;
            email_false_img.hidden=true;
        } else {
            email_pass_img.hidden=true;
            email_false_img.hidden=false;
        }
    })
    .catch(e => console.log(e));
}

function fullNameCheck(name) {
    if(name == '') {
        full_pass_img.hidden=true;
    }
    else {
        full_pass_img.hidden=false;
    }
}

function userNameCheck(name) {
    let regex = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*/;
    let overlap = "";

    fetch("/myproject/signup_servlet/userNameChk.do", {
        method: "post",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            name: name
        })
    })
        .then(response => response.json())
        .then(data => {
            overlap = data.result;
            if (name == '') {
                user_pass_img.hidden = true;
                user_false_img.hidden = true;
            } else if(overlap == 1) {
                user_pass_img.hidden=true;
                user_false_img.hidden=false;
            } else if (regex.test(name)) {
                user_pass_img.hidden = false;
                user_false_img.hidden = true;
            } else {
                user_pass_img.hidden = true;
                user_false_img.hidden = false;
            }
        })
}

function pwCheck(pw) {
    let regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@!%*#?&]{6,}$/;
    
    if(pw == '') {
        pw_pass_img.hidden=true;
        pw_false_img.hidden=true;
    }
    else if(regex.test(pw)){
        pw_pass_img.hidden=false;
        pw_false_img.hidden=true;
    } else {
        pw_pass_img.hidden=true;
        pw_false_img.hidden=false;
    }
}

function signUpBtn() {
    if(!email_pass_img.hidden && !full_pass_img.hidden && !user_pass_img.hidden && !pw_pass_img.hidden) sign_up_btn.disabled=false;
    else sign_up_btn.disabled=true;
}