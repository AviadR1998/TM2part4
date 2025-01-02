import React, { useState } from "react";
import Login from "./login/Login";
import Register from "./register/Register";
import { Navigate, Route, Routes } from "react-router-dom";
import TotalChat from "./totalChat/TotalChat";

function App() {
  const [modeObg, setMod] = useState({ mod: 0 });
  const [currentChats, setCurrentChats] = useState([]);
  const [numOfChats, setNumOfChats] = useState(0);
  const [UserMessages, setUserMessages] = useState([]);
  const [users, setUsers] = useState([]);
  const [user, setUser] = useState({});
  const [isSignIn, setIsSignIn] = useState(false);
  const [currChat, setCurrChat] = useState(0);
  const [isClicked, setIsClicked] = useState(false);
  const [mySocket, setMySocket] = useState(null);

  return (
    <Routes>
      <Route
        path="/register"
        element={
          <Register
            mod={modeObg}
            modFun={setMod}
            users={users}
            setUsers={setUsers}
            setIsSignIn={setIsSignIn}
          />
        }
      />
      <Route
        path="/"
        element={
          <Login
            mod={modeObg}
            modFun={setMod}
            users={users}
            setUsers={setUsers}
            user={user}
            setUser={setUser}
            setIsSignIn={setIsSignIn}
            setCurrentChats={setCurrentChats}
            setNumOfChats={setNumOfChats}
            setUserMessages={setUserMessages}
            setCurrChat={setCurrChat}
            setIsClicked={setIsClicked}
            setMySocket={setMySocket}
          />
        }
      />
      <Route
        path="/chat"
        element={
          isSignIn ? (
            <TotalChat
              mod={modeObg}
              modFun={setMod}
              user={user}
              UserMessages={UserMessages}
              setUserMessages={setUserMessages}
              currentChats={currentChats}
              setCurrentChats={setCurrentChats}
              numOfChats={numOfChats}
              setNumOfChats={setNumOfChats}
              currChat={currChat}
              setCurrChat={setCurrChat}
              isClicked={isClicked}
              setIsClicked={setIsClicked}
              mySocket={mySocket}
            />
          ) : (
            <Navigate to="/" />
          )
        }
      />
    </Routes>
  );
}

export default App;
