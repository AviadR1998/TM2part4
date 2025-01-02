import Message from "../message/Message";

/**
 * This component creates the final messages list html style, by getting a list of JSON messages and creating html tags from them
 * @param {messages} param0
 * @returns
 */
function MsgListResult({ user, messages, contactPic }) {
  const msgList = messages.slice(0).reverse().map((msg, key) => {
    //creating every message
    return <Message key={key} msg={msg} username={user.username} userProfPic={user.picture} contactProfPic={contactPic} />;
  });

  return <ul className="chatList">{msgList}</ul>;
}

export default MsgListResult;
