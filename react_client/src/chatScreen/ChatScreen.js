import MessageInput from "../messageInput/MessageInput";
import MsgListResult from "../msgListResult/MsgListResult";

/**
 * This component acts as the whole right side of the chat - the chat screen
 * @param "user, setUserMessages,messages,userKey,numOfChats,chatHeader,setCurrentChats" param0
 * @returns
 */
function ChatScreen({
  user,
  setUserMessages,
  messages,
  userKey,
  numOfChats,
  chatHeader,
  currentChats,
  setCurrentChats,
  isClicked,
  setIsClicked,
  mySocket
}) {

  let chatId;
  let contactPic;
  if (numOfChats !== 0) {
    chatId = currentChats[userKey].id;
    contactPic = currentChats[userKey].user.profilePic;
  }

  async function addMsg(msg) {
    const data = { "msg": msg }
    const addMsgRes = await fetch("http://localhost:5000/api/Chats/" + chatId + "/Messages", {
      method: "post",
      headers: {
        "authorization": "Bearer " + user.tokken, // attach the token
        "Content-Type": "application/json",
      },
      "body": JSON.stringify(data)
    });

    if (addMsgRes.ok) {
      const chatRes = await fetch("http://localhost:5000/api/Chats/" + chatId + "/Messages", {
        method: "get",
        headers: {
          "authorization": "Bearer " + user.tokken, // attach the token
          "Content-Type": "application/json",
        },
      });

      if (chatRes.ok) {
        const chatJson = await chatRes.json();
        setUserMessages(chatJson);

        const getAllChatsRes = await fetch("http://localhost:5000/api/Chats", {
          method: "get",
          headers: {
            "authorization": "Bearer " + user.tokken, // attach the token
            "Content-Type": "application/json",
          },
        });


        if (getAllChatsRes.ok) {
          const allChatsJson = await getAllChatsRes.json();
          console.log(allChatsJson);
          setCurrentChats(allChatsJson);
          mySocket.emit('clientSentMessage', { chatId: chatId, chats: allChatsJson, messages: chatJson });
        }
      }
    }
  };

  return (
    <div className="col-8" id="theChat">
      <div className="second-color" id="topChat">
        <div id="chatHeader">{chatHeader}</div>
      </div>
      <MsgListResult user={user} messages={messages} contactPic={contactPic} />
      <MessageInput addMsg={addMsg} numberOfChats={numOfChats} isClicked={isClicked} />
    </div>
  );
}

export default ChatScreen;
