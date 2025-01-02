
import Field from "../field/Field";
import Title from "../title/Title";
import Confirm from "../confirm/Confirm";
import { useNavigate } from "react-router-dom";
import { io } from 'socket.io-client';

/*
this is the Login componenet in my project
props - the properties that send to this component
*/
function Login(props) {
  const navigate = useNavigate();
  var currentHover = "#bea3e6",
    currentSubmitColor = "#7868e6";
  var submit = document.getElementsByClassName("submitButton");
  var texts = document.getElementsByClassName("textColors");
  var myFrame = document.getElementsByClassName("frame");
  var inputs = document.getElementsByTagName("input");
  var valTxt = document.getElementsByClassName("valids");

  if (document.getElementById("cssLink").href !== "log-reg.css") {
    document.getElementById("cssLink").href = "log-reg.css";
  }


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
  function chMod() {
    if (props.mod.mod === 0) {
      props.modFun({ mod: 1 });
      document.body.style.backgroundImage = "url('dark-bckg.jpg')";
      for (let i = 0; i < texts.length; i++) {
        texts[i].style.color = "white";
      }
      submit[0].style.backgroundColor = "#ce7777";
      submit[0].style.borderColor = "#ce7777";
      currentHover = "#d8bfd8";
      currentSubmitColor = "#ce7777";
      myFrame[0].style.backgroundColor = "rgb(90, 90, 90, 0.3)";
    } else {
      props.modFun({ mod: 0 });
      document.body.style.backgroundImage = "url('light-bckg.jpg')";
      for (let i = 0; i < texts.length; i++) {
        texts[i].style.color = "black";
      }
      submit[0].style.backgroundColor = "#7868e6";
      submit[0].style.borderColor = "#7868e6";
      currentHover = "#bea3e6";
      currentSubmitColor = "#7868e6";
      myFrame[0].style.backgroundColor = "rgb(240, 240, 240, 0.3)";
    }
  }

  /*
  change the color of the Login button when the cursor is over on the button
  */
  function over() {
    submit[0].style.backgroundColor = currentHover;
    submit[0].style.borderColor = currentHover;
  }

  /*
  change the color of the Login button when the cursor is out on the button
  */
  function out() {
    submit[0].style.backgroundColor = currentSubmitColor;
    submit[0].style.borderColor = currentSubmitColor;
  }

  async function getDetails(username, tokken) {
    const res = await fetch("http://localhost:5000/api/Users/" + username, {
      "method": "get",
      "headers": {
        "authorization": "Bearer " + tokken,
        "Content-Type": "application/json",
      },
    })

    const data = await res.json();
    props.setUser({
      username: username,
      password: data.password,
      displayName: data.displayName,
      picture: data.profilePic,
      tokken: tokken,
    });

    const chatsRes = await fetch("http://localhost:5000/api/Chats", {
      "method": "get",
      "headers": {
        "authorization": "Bearer " + tokken,
        "Content-Type": "application/json",
      },
    })

    if (chatsRes.ok) {
      const currChatsRes = await chatsRes.json();
      props.setCurrentChats(currChatsRes);
      props.setNumOfChats(currChatsRes.length);
      props.setUserMessages([]);
      props.setIsClicked(false);
      var socket = io('http://localhost:5000');
      props.setMySocket(socket);
      socket.emit('username', username);
      document.getElementById("cssLink").href = "./index.css";
      let str;
      if (props.mod.mod) {
        str = "./chats_dark_mode.css";
      } else {
        str = "./index.css";
      }
      document.getElementById("cssLink").href = str;
      navigate("/chat");
    }

  }

  async function checkDetails(username, password) {
    const data = {
      "username": username,
      "password": password
    }

    const res = await fetch("http://localhost:5000/api/Tokens", {
      "method": "post",
      "headers": {
        "Content-Type": "application/json",
      },
      "body": JSON.stringify(data)
    })
    let resText = await res.text();
    if (res.status === 200) {
      props.setIsSignIn(true);
      getDetails(username, resText);
    } else {
      valTxt[1].style.color = "red";
      valTxt[1].innerText =
        "Username or Password are incorrect.\nplease try again.";
    }

  }

  /*
  the function activate when Login button press and move to the chat screen if the user set correct values
  else write an error message
  */
  function subFun() {
    checkDetails(inputs[1].value, inputs[2].value)
  }



  return (
    <div className={props.mod.mod ? "card frameD" : "card frame"}>
      <div className="form-check form-switch toggle">
        <input
          className="form-check-input"
          type="checkbox"
          role="switch"
          onClick={chMod}
          placeholder="toggle"
          defaultChecked={props.mod.mod ? true : false}
        ></input>
      </div>
      <Title
        main="Login"
        sub="Login with your username and password"
        brt={props.mod.mod}
      ></Title>
      <Field
        name="Your Username"
        icon="bi bi-person-fill"
        type="text"
        hint="Enter Username"
        acc={0}
        brt={props.mod.mod}
        reg={true}
      ></Field>
      <Field
        name="Your Password"
        icon="bi-person-fill-lock"
        type="password"
        hint="Enter Password"
        acc={0}
        brt={props.mod.mod}
        reg={true}
      ></Field>
      <Confirm
        over={over}
        out={out}
        sub="Login"
        dis="Not a member? "
        link="/register"
        hint="Register here!"
        brt={props.mod.mod}
        subFun={subFun}
      ></Confirm>
    </div>
  );
}

export default Login;
