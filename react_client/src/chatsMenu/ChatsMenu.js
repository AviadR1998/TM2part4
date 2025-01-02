import NewContactModal from "../newContactModal/NewContactModal";
import ChatsMenuResult from "../chatsMenuResult/ChatsMenuResult";
import DarkModeBtn from "../darkModeBtn/DarkModeBtn";

/**
 * This component acts as the left side of the chat - means, the chat menu
 * @param "{user,setUserMessages,myMessages,setCurrChat,setChatsHeader,handleOnChange,setNumOfChats,numOfChats,setCurrentChats,currentChats,mod,}" param0
 * @returns
 */
function ChatsMenu({
  user,
  setUserMessages,
  myMessages,
  setCurrChat,
  currChat,
  setChatsHeader,
  handleOnChange,
  setNumOfChats,
  numOfChats,
  setCurrentChats,
  currentChats,
  mod,
  isClicked,
  setIsClicked
}) {

  async function addNewCon(userName, userLastMsg, userTimeAndDate) {
    const data = { "username": userName };

    const res = await fetch("http://localhost:5000/api/Chats", {
      "method": "post",
      "headers": {
        "authorization": "Bearer " + user.tokken,
        "Content-Type": "application/json",
      },
      "body": JSON.stringify(data)
    });

    if (res.ok) {
      const resJson = await res.json();
      setCurrentChats((prevChats) => [...prevChats, resJson]);
      setNumOfChats((prevNumOfChats) => prevNumOfChats + 1);
    }
  }


  return (
    <div className="col-4 black2Back" id="usersList">
      <div className="lead" id="chatBar">
        <img src={user.picture} alt="Avatar" className="avatar"></img>
        <span className="whiteText">{user.displayName}'s chats</span>
        <button
          type="button"
          className="btn btn-outline-info black2Back"
          id="addCon"
          data-bs-toggle="modal"
          data-bs-target="#exampleModal"
        >
          <i className="bi bi-person-plus-fill"></i>
        </button>
        <NewContactModal
          addNewCon={addNewCon}
          numOfChats={numOfChats}
        />
      </div>
      <ul className="list-group list-group-flush">
        <ChatsMenuResult
          user={user}
          chats={currentChats}
          setCurrentChats={setCurrentChats}
          setChatsHeader={setChatsHeader}
          setCurrChat={setCurrChat}
          setUserMessages={setUserMessages}
          currChat={currChat}
          isClicked={isClicked}
          setIsClicked={setIsClicked}
        />
      </ul>
      <DarkModeBtn handleDarkChange={handleOnChange} mod={mod} />
    </div>
  );
}

export default ChatsMenu;
