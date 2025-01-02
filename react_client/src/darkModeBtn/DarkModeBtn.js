/**
 * This component is a dark mode switch - when it on the app css will change to dark, when its off to light.
 * @param {handleDarkChange, mod} param0
 * @returns
 */

const DarkModeBtn = ({ handleDarkChange, mod }) => {
  return (
    <div className="form-check form-switch darkSwitch" id="darkModeSwitch">
      <input
        className="form-check-input"
        type="checkbox"
        role="switch"
        onChange={handleDarkChange}
        id="flexSwitchCheckDefault"
        defaultChecked={mod.mod ? true : false}
      ></input>
      <label className="form-check-label" htmlFor="flexSwitchCheckDefault">
        Dark mode
      </label>
    </div>
  );
};

export default DarkModeBtn;
