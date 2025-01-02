import ChatScreen from "../chatScreen/ChatScreen";
import { useState } from "react";
import React from "react";
import LogoutBtn from "../logoutBtn/LogoutBtn";
import ChatsMenu from "../chatsMenu/ChatsMenu.js";
import { io } from 'socket.io-client';

/**
 * This component manage the total chat screen, creates a chat menu and a chat screen
 * @param {mod,modFun,user,setUserMessages,UserMessages,currentChats,setCurrentChats,numOfChats,setNumOfChats,}
 * @returns
 */
const TotalChat = ({
  mod,
  modFun,
  user,
  setUserMessages,
  UserMessages,
  currentChats,
  setCurrentChats,
  numOfChats,
  setNumOfChats,
  currChat,
  setCurrChat,
  isClicked,
  setIsClicked,
  mySocket,
}) => {
  document.body.style.backgroundImage = 'none';

  mySocket.off('newMsg');
  mySocket.on('newMsg', function (response) {
    if (isClicked && currentChats[currChat].id === response.chat.id) {
      setUserMessages((prevMsgs) => {
        let tempMsgs = [...prevMsgs];
        tempMsgs.unshift(response.msg);
        return tempMsgs;
      });
    }
    let tempChats = [...currentChats];
    let flag = false;
    let updatedChats = tempChats.map((chat) => {
      if (chat.id === response.chat.id) {
        chat.lastMessage = { id: response.msg.id, created: response.msg.created, content: response.msg.content };
        flag = true;
      }
      return chat;
    })
    if (flag) {
      setCurrentChats(updatedChats);
    } else {
      setCurrentChats((prevChats) => [...prevChats, response.chat]);
      setNumOfChats((prevNumOfChats) => (prevNumOfChats + 1));
    }
  });

  mySocket.off('updateChat');
  mySocket.on('updateChat', function (response) {
    if (isClicked && currentChats[currChat].id === response.chatId) {
      setUserMessages(response.messages);
    }
    setCurrentChats(response.chats);
    setNumOfChats((prevNumOfChats) => (prevNumOfChats + 1));
  })


  const [currChatUserName, setCurrChatUserName] = useState("chat screen");
  if (mod.mod === 1) {
    document.body.style.backgroundColor = "black";
  } else {
    document.body.style.backgroundColor = "#F3E8FF";
  }

  /**
   * This function handle the change of screen mode - light or dark and changes the css file accordingly
   */
  function handleOnChange() {
    if (mod.mod === 0) {
      document.body.style.backgroundColor = "black";
    } else {
      document.body.style.backgroundColor = "#F3E8FF";
    }
    modFun(mod.mod === 0 ? { mod: 1 } : { mod: 0 });
    updateCssLink();
  }

  /**
   * This function update the css link of this document according to the screen mode(light or dark)
   */
  function updateCssLink() {
    const link = document.getElementById("cssLink");
    if (mod.mod) {
      link.href = "./index.css";
    } else {
      link.href = "./chats_dark_mode.css";
    }
  }
  return (
    <>
      <LogoutBtn />
      <div className="container" id="main-container">
        <div className="row border-fourth-color" id="mainRow">
          <ChatsMenu
            user={user}
            setUserMessages={setUserMessages}
            myMessages={UserMessages}
            setCurrChat={setCurrChat}
            currChat={currChat}
            setChatsHeader={setCurrChatUserName}
            handleOnChange={handleOnChange}
            setNumOfChats={setNumOfChats}
            numOfChats={numOfChats}
            setCurrentChats={setCurrentChats}
            currentChats={currentChats}
            mod={mod}
            isClicked={isClicked}
            setIsClicked={setIsClicked}
          />
          <ChatScreen
            user={user}
            setUserMessages={setUserMessages}
            messages={UserMessages}
            userKey={currChat}
            numOfChats={numOfChats}
            chatHeader={currChatUserName}
            currentChats={currentChats}
            setCurrentChats={setCurrentChats}
            isClicked={isClicked}
            setIsClicked={setIsClicked}
            mySocket={mySocket}
          />
        </div>
      </div>
    </>
  );
};

export default TotalChat;
