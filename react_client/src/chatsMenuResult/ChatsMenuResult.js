import ChatItem from "../chatItem/ChatItem";

/**
 *This component creates the final chats for the chats menu
 * @param "{chats,setCurrentChats,setChatsHeader,setCurrChat}" param0
 * @returns
 */
function ChatsMenuResult({
  user,
  chats,
  setCurrentChats,
  setChatsHeader,
  setCurrChat,
  setUserMessages,
  currChat,
  isClicked,
  setIsClicked,
}) {
  async function handleChatClick(userKey) {
    const chatId = chats[userKey].id;
    const chatRes = await fetch("http://localhost:5000/api/Chats/" + chatId + "/Messages", {
      method: "get",
      headers: {
        "authorization": "Bearer " + user.tokken, // attach the token
        "Content-Type": "application/json",
      },
    });


    if (chatRes.ok) {
      const chatJson = await chatRes.json();
      setChatsHeader(chats[userKey].user.displayName);
      setUserMessages(chatJson);
      setCurrChat(userKey);
      setIsClicked(true);
    }


  };

  function subDate(chat) {
    if (chat.lastMessage == null) {
      return "";
    }
    else {
      let tempStr = chat.lastMessage.created.substring(0, 10);
      let newStr = tempStr.substring(8, 10) + "." + tempStr.substring(5, 7) + "." + tempStr.substring(2, 4);
      return newStr;
    }
  }

  const currentChats = chats.map((chat, key) => {
    //creating every chat list component
    let isActive = false;
    if (key === currChat) {
      isActive = true;
    }
    return <ChatItem user={chat.user} key={key} userKey={key} onClickFun={handleChatClick} lastMsg={chat.lastMessage != null ? chat.lastMessage.content : ""}
      date={subDate(chat)} time={chat.lastMessage != null ? chat.lastMessage.created.substring(11, 16) : ""} isActive={isActive} isClicked={isClicked} />;
  });

  return <>{currentChats}</>;
}

export default ChatsMenuResult;
