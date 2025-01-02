import React from "react";
import { useNavigate } from "react-router-dom";

/**
 * This component acts as a logout button, when the user clicks on this button the path will change to login screen
 * @returns
 */
const LogoutBtn = () => {
  const navigate = useNavigate();
  const toLogIn = function () {
    const link = document.getElementById("cssLink");
    link.href = "./log-reg.css";
    //moving to login screen
    navigate("/");
  };
  return (
    <button
      type="button"
      className="btn btn-danger"
      id="logoutBtn"
      onClick={toLogIn}
    >
      Logout
    </button>
  );
};

export default LogoutBtn;
