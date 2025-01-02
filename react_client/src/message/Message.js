/**
 * This component creats the message in the chat screen, by getting the message string and return a li div
 * @param {msg} param0
 * @returns
 */
function Message({ msg, key, username, userProfPic, contactProfPic }) {
  if (msg.sender.username !== username) {
    return (
      <li key={key} className="liLeft" >
        <div className="chatRow">
          <img
            src={contactProfPic}
            alt="Avatar"
            className="avatar leftAvatar"
          ></img>
          <span className="leftMsg first-color border-second-color">
            {msg.content}
          </span>
        </div>
      </li>
    );
  } else {
    return (
      <li key={key} className="liRight">
        <div className="chatRow">
          <div className="rightMsg first-color darkModeRightMsg">
            {msg.content}
          </div>
          <img
            src={userProfPic}
            alt="Avatar"
            className="avatar rightAvatar"
          ></img>
        </div>
      </li>
    );
  }
}

export default Message;
