/**
 * This component acts as a chat item- create a li tag that is a functionaning chat item in the chat menu
 * @param {user, userKey, onClickFun} param0
 * @returns
 */
function ChatItem({ user, userKey, onClickFun, lastMsg, date, time, isActive, isClicked }) {
  const isActiveFun = function () {
    var currClassName =
      "list-group-item btn btn-primary btn-lg btn-block text-left";
    if (isActive === true && isClicked === true) {
      currClassName = currClassName + " active";
    }
    return currClassName;
  };

  let tempMsg;
  if (lastMsg.length > 15) {
    tempMsg = lastMsg.slice(0, 15);
    tempMsg = tempMsg + "..";
  } else {
    tempMsg = lastMsg;
  }
  return (
    <li
      key={userKey}
      onClick={() => onClickFun(userKey)}
      type="button"
      className={isActiveFun()}
    >
      <div className="container whiteText">
        <div className="row">
          <div className="col-lg-4 iconAndName">
            <img src={user.profilePic} alt="Avatar" className="avatar"></img>
            <span className="chatHead"> {user.displayName}</span>
          </div>
          <div className="col-lg-4 align-center">
            <span>{tempMsg}</span>
          </div>
          <div className="col-lg-4">
            <span className="dateAndTime">{time} {date}</span>
          </div>
        </div>
      </div>
    </li>
  );
}

export default ChatItem;
