/*
in this componnent i will represent the title in the Login and the Register page
*/
function Title(props) {
    return(
        <div className="cenDiv">
            <div className={props.brt ? "textColorsD" : "textColors"}>
                <b>
                    {props.main}
                </b>
            </div>
            <div className={props.brt ? "fw-light subTitle textColorsD" : "fw-light subTitle textColors"}>
                {props.sub}
            </div>
        </div>
    );
}

export default Title;