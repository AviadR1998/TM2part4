import { useRef } from "react";

/**
 * This component acts as the message input and the send message button in the the right side of the chat
 * @param {addMsg, numberOfChats} param0
 * @returns
 */
function MessageInput({ addMsg, numberOfChats, isClicked }) {
  const inputVal = useRef(null);

  const makeSubmit = function () {
    if (inputVal.current.value !== "" && isClicked) {
      addMsg(inputVal.current.value);
      inputVal.current.value = "";
    }
  };

  return (
    <div className="msgPart black2Back">
      <input
        placeholder="Write something"
        className="msgItself whiteText"
        ref={inputVal}
      ></input>
      <button
        onClick={makeSubmit}
        type="button"
        className="btn btn-outline-info"
        id="sendBtn"
      >
        <i className="bi bi-send"></i>
      </button>
    </div>
  );
}

export default MessageInput;
