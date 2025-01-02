import { useRef } from "react";
import currentTimeAndDateString from "../currentTimeAndDate/currentTimeAndDate";

/**
 * This component creates the add new contact button and modal, when the user clicks the button this modal pops up
 * @param {addNewCon, setChatsHeader, numOfChats} param0
 * @returns
 */
function NewContactModal({ addNewCon, numOfChats }) {
  const inputVal = useRef(null);
  var currDateAndTime = currentTimeAndDateString();
  const addAnotherChat = function () {
    if (inputVal.current.value !== "") {
      addNewCon(inputVal.current.value, "New contact", currDateAndTime);
      inputVal.current.value = "";
    }
  };

  return (
    <div
      className="modal fade"
      id="exampleModal"
      tabIndex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h2 className="modal-title fs-5" id="exampleModalLabel">
              Add new contact
            </h2>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div className="modal-body">
            <input
              ref={inputVal}
              placeholder="Contact Identifier"
              className="msgItself"
            ></input>
          </div>
          <div className="modal-footer">
            <button
              onClick={addAnotherChat}
              type="button"
              className="btn btn-primary"
              id="modalSaveBtn"
            >
              Save
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default NewContactModal;
