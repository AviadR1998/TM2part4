import FieldInput from "../fieldInput/FieldInput";
import ImageInput from "../imageInput/ImageInput";

/*
this copmponent represant a complete field with input and his title 
props - the props the component get
*/
function Field(props) {
    return(
        <div>
            <div className={props.brt ? "fieldName textColorsD" : "fieldName textColors"}>
                {props.name}
            </div>
            {props.acc !== 4 ? <FieldInput icon={props.icon} type={props.type} hint={props.hint} 
            acc={props.acc} tt={props.tt} reg={props.reg} brt={props.brt}/> 
            : <ImageInput icon={props.icon} type={props.type} hint={props.hint} chg={props.chg} 
            tt={props.tt} reg={props.reg} brt={props.brt} acc={props.acc}/>}
            <div className={props.brt ? "validsD" : "valids"}></div>
        </div>
    );
}

export default Field;