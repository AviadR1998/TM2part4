
import Field from "../field/Field";
import Title from "../title/Title";
import Confirm from "../confirm/Confirm";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

/*
this is the Register page component
props - the props the component get
*/
function Register(props) {
  var currentHover = "#bea3e6",
    currentSubmitColor = "#7868e6";
  var submit = document.getElementsByClassName("submitButton");
  var texts = document.getElementsByClassName("textColors");
  var myFrame = document.getElementsByClassName("frame");
  var inputs = document.getElementsByTagName("input");
  var valTxt = document.getElementsByClassName("valids");
  var ttArr = [
    "Enter letters or numbers",
    "Enter at least 8 letters or numbers (1 capital and 1 number)",
    "Repeat your password",
    "Enter up to 10 letters or numbers",
    "Upload Image",
  ];
  const [im, setIm] = useState(null);
  const [clickReg, setReg] = useState(false);
  const nav = useNavigate();

  if (props.mod.mod === 1) {
    document.body.style.backgroundImage = "url('dark-bckg.jpg')";
    submit = document.getElementsByClassName("submitButtonD");
    texts = document.getElementsByClassName("textColorsD");
    myFrame = document.getElementsByClassName("frameD");
    valTxt = document.getElementsByClassName("validsD");
    currentHover = "#d8bfd8";
    currentSubmitColor = "#ce7777";
  } else {
    document.body.style.backgroundImage = "url('light-bckg.jpg')";
  }



  /*
  the function change the css to dark mode and to light mode
  */
  function useChMod() {
    props.modFun(props.mod.mod ? { mod: 0 } : { mod: 1 });
    if (props.mod.mod === 0) {
      document.body.style.backgroundImage = "url('dark-bckg.jpg')";
      for (let i = 0; i < texts.length; i++) {
        texts[i].style.color = "white";
      }
      if (!clickReg) {
        for (let i = 0; i < valTxt.length; i++) {
          valTxt[i].style.color = "white";
        }
      }
      submit[0].style.backgroundColor = "#ce7777";
      submit[0].style.borderColor = "#ce7777";
      currentHover = "#d8bfd8";
      currentSubmitColor = "#ce7777";
      myFrame[0].style.backgroundColor = "rgb(90, 90, 90, 0.3)";
    } else {
      document.body.style.backgroundImage = "url('light-bckg.jpg')";
      for (let i = 0; i < texts.length; i++) {
        texts[i].style.color = "black";
      }
      if (!clickReg) {
        for (let i = 0; i < valTxt.length; i++) {
          valTxt[i].style.color = "black";
        }
      }
      submit[0].style.backgroundColor = "#7868e6";
      submit[0].style.borderColor = "#7868e6";
      currentHover = "#bea3e6";
      currentSubmitColor = "#7868e6";
      myFrame[0].style.backgroundColor = "rgb(240, 240, 240, 0.3)";
    }
  }

  /*
  change the color of the Register button when the cursor is over on the button
  */
  function over() {
    submit[0].style.backgroundColor = currentHover;
    submit[0].style.borderColor = currentHover;
  }

  /*
  change the color of the Register button when the cursor is out on the button
  */
  function out() {
    submit[0].style.backgroundColor = currentSubmitColor;
    submit[0].style.borderColor = currentSubmitColor;
  }

  /*
  the function check if str is at least 8 letters and contain 1 letter and 1 number
  str - the string of the field
  pre - the last of the validation value
  */
  function checkPass(str, pre) {
    if (str.length < 8) {
      return 1;
    } else {
      let cap = false,
        num = false;
      if (!/^[a-z0-9A-Z]+$/.test(str)) {
        return 1;
      }
      for (let i = 0; i < str.length; i++) {
        if (str[i] >= "A" && str[i] <= "Z") {
          cap = true;
        }
        if (str[i] >= "0" && str[i] <= "9") {
          num = true;
        }
      }
      if (!(cap && num)) {
        return 1;
      }
    }
    return pre;
  }

  async function sendDetails(newUser) {
    const data = {
      "username": newUser[0],
      "password": newUser[1],
      "displayName": newUser[3],
      "profilePic": im
    }
    const res = await fetch("http://localhost:5000/api/Users", {
      "method": "post",
      "headers": {
        "Content-Type": "application/json",
      },
      "body": JSON.stringify(data)
    })

    if (res.status === 200) {
      nav("/");
    }
    valTxt[0].innerText = "User already exsist.";
    inputs[1].classList.remove("is-valid");
    inputs[1].classList.add("is-invalid");
    valTxt[0].style.color = "red";
  }

  /*
  the function activate when Register button press and move to the login screen if the user set correct values
  else write an error message
  */
  function subFun() {
    let someWrong = [0, 0, 0, 0, 0];
    let margs = document.getElementsByClassName("marg");
    setReg(true);
    if (!/^[a-z0-9A-Z]+$/.test(inputs[1].value)) {
      someWrong[0] = 1;
    }
    /*for (let i = 0; i < props.users.length; i++) {
      if (props.users[i][0] === inputs[1].value) {
        someWrong[0] = 2;
      }
    }*/
    someWrong[1] = checkPass(inputs[2].value, someWrong[1]);
    if (inputs[2].value !== inputs[3].value) {
      someWrong[2] = 3;
    }
    someWrong[2] = checkPass(inputs[3].value, someWrong[2]);
    if (
      !/^[a-z0-9A-Z]+$/.test(inputs[4].value) ||
      inputs[4].value.length > 10
    ) {
      someWrong[3] = 1;
    }
    if (inputs[5].value === "") {
      someWrong[4] = 1;
    }
    for (let i = 0; i < 5; i++) {
      margs[i].classList.remove("mb-3");
      if (someWrong[i] === 0) {
        inputs[i + 1].classList.remove("is-invalid");
        inputs[i + 1].classList.add("is-valid");
        valTxt[i].style.color = "green";
        valTxt[i].innerText = "Excellent.";
      } else {
        if (someWrong[i] === 1) {
          if (i !== 2) {
            valTxt[i].innerText = ttArr[i] + ".";
          } else {
            valTxt[2].innerText = ttArr[1] + ".";
          }
        } else {
          valTxt[i].innerText = ttArr[2] + ".";
        }
        inputs[i + 1].classList.remove("is-valid");
        inputs[i + 1].classList.add("is-invalid");
        valTxt[i].style.color = "red";
      }
    }
    for (let i = 0; i < 5; i++) {
      if (someWrong[i] !== 0) {
        return;
      }
    }
    var newUser = [];
    for (let i = 0; i < inputs.length - 1; i++) {
      newUser[i] = inputs[i + 1].value;
    }
    sendDetails(newUser)
  }

  /*
  the function set the value of the image in a var
  */
  function fileOnChange(e) {
    if (e.target.value !== "") {
      const reader = new FileReader();
      reader.onloadend = () => {
        setIm(reader.result.toString());
      }
      reader.readAsDataURL(e.target.files[0]);
    }
  }

  return (
    <form
      className={
        props.mod.mod
          ? "card needs-validation frameD"
          : "card needs-validation frame"
      }
      noValidate
    >
      <div className="form-check form-switch toggle">
        <input
          className="form-check-input"
          type="checkbox"
          role="switch"
          onClick={useChMod}
          placeholder="toggle"
          defaultChecked={props.mod.mod ? true : false}
        />
      </div>
      <Title
        main="Register"
        sub="Please fill your details"
        brt={props.mod.mod}
      ></Title>
      <Field
        name="Username"
        icon="bi bi-person-fill"
        type="text"
        hint="Username"
        acc={0}
        brt={props.mod.mod}
        tt={ttArr[0]}
        reg={clickReg}
      ></Field>
      <Field
        name="Password"
        icon="bi bi-person-fill-lock"
        type="password"
        hint="password"
        acc={1}
        brt={props.mod.mod}
        tt={ttArr[1]}
        reg={clickReg}
      ></Field>
      <Field
        name="Confirm Password"
        icon="bi bi-person-fill-check"
        type="password"
        hint="Confirm password"
        acc={2}
        brt={props.mod.mod}
        tt={ttArr[2]}
        reg={clickReg}
      ></Field>
      <Field
        name="Display name"
        icon="bi bi-person-vcard-fill"
        type="text"
        hint="Display name"
        acc={3}
        brt={props.mod.mod}
        tt={ttArr[3]}
        reg={clickReg}
      ></Field>
      <Field
        name="Display picture"
        icon="bi bi-person-bounding-box"
        type="file"
        hint="Picture"
        acc={4}
        brt={props.mod.mod}
        chg={fileOnChange}
        tt={ttArr[4]}
        reg={clickReg}
      ></Field>
      <img alt="" src={im === null ? "empty-profile.png" : im}></img>
      <Confirm
        over={over}
        out={out}
        sub="Register "
        dis="Already got a user? "
        link="/"
        hint="Login here!"
        brt={props.mod.mod}
        subFun={subFun}
      ></Confirm>
    </form>
  );
}

export default Register;
