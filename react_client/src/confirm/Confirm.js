/*
this copmponent represant the confirm part and a button and a link to the register/login page 
props - the props the component get
*/
function Confirm(props) {
    var Link = require('react-router-dom').Link

    /*
    change the color of the text of the button
    */
    function mouseDown (e) {
        if (props.brt === 0) {
            e.target.style.color = 'black'
        } else {
            e.target.style.color = 'white'
        }
    }

    return(
        <div className="last cenDiv">
            <button type="button" onMouseDown={mouseDown} className={props.brt ? "btn btn-primary submitButtonD textColorsD" 
            : "btn btn-primary submitButton textColors"} onMouseOver={props.over} onMouseOut={props.out} onClick={props.subFun}>
                {props.sub}
            </button>
            <br></br>
            <nav>
                <div className={props.brt ? "textColorsD fw-light" : "textColors fw-light"}>
                    {props.dis} 
                    <Link to={props.link}>{props.hint}</Link>
                </div>
            </nav>
        </div>
    );
}

export default Confirm;