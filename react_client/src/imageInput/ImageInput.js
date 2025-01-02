/*
this copmponent represant the image input
props - the props the component get
*/
function ImageInput(props) {
    var myInputs = document.getElementsByClassName('valids')

    if (props.brt === 1) {
        myInputs = document.getElementsByClassName('validsD')
    }
    /*
    write a note when the cursor is over an icon
    */
    function myOver () {
        if (!props.reg) {
            myInputs[props.acc].innerText = props.tt +'.'
        }
    }

    /*
    delete a note when the cursor is out an icon
    */
    function myOut () {
        if (!props.reg){
            myInputs[props.acc].innerText = ""
        }
    }
    return(
        <div className="input-group mb-3 marg cenDiv">
            <span className="input-group-text icon"  onMouseOver={myOver} onMouseOut={myOut}>
                <i className={props.icon}></i>
            </span>
            <input type={props.type} placeholder={props.hint} className="fields form-control" 
            accept ="image/png, image/jpeg" onChange={props.chg} required/>
        </div>
    );
}

export default ImageInput;