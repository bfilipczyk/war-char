import React,{useState} from "react";
import { Form, Text } from 'informed';
import axios from "axios";
import "./Characteristics.css"


export default function Characteristics(props){
    const [characteristics,setCharacteristics] = useState(null)
    if(props.character && characteristics===null) {
        setCharacteristics(props.character.characteristics)
    }

    const update = async (values) => {
        const user = JSON.parse(localStorage.getItem('user'))
        let id = characteristics.id
        await axios.put("/api/characters/updateCharacteristics/"+id,values, {
            headers:
                {
                    Authorization:'Bearer '+ user.accessToken
                }
        }).then(window.location.reload())
    }

    return (
        <div>
            {characteristics!=null?
                <div>
                <h3 align={"center"}>Characteristics:</h3>
                <Form className="characteristicsForm" onSubmit={update}>
                    <div className="characteristicsFormDiv">
                        <div className="characteristicsFormDivCtrl">
                    <label className="characteristicsLabel">
                        WS
                        <Text className="characteristicsText" field={"weaponSkill"} initialValue={characteristics.weaponSkill}/>
                    </label>
                    <label className="characteristicsLabel">
                        BS
                        <Text className="characteristicsText" field={"ballisticSkill"} initialValue={characteristics.ballisticSkill}/>
                    </label>
                    <label className="characteristicsLabel">
                        S
                        <Text className="characteristicsText" field={"strength"} initialValue={characteristics.strength}/>
                    </label>
                    <label className="characteristicsLabel">
                        T
                        <Text className="characteristicsText" field={"toughness"} initialValue={characteristics.toughness}/>
                    </label>
                    <label className="characteristicsLabel">
                        I
                        <Text className="characteristicsText" field={"initiative"} initialValue={characteristics.initiative}/>
                    </label>
                    </div>
                    <div className="characteristicsFormDivCtrl">
                    <label className="characteristicsLabel">
                        Ag
                        <Text className="characteristicsText" field={"agility"} initialValue={characteristics.agility}/>
                    </label>
                    <label className="characteristicsLabel">
                        Dex
                        <Text className="characteristicsText" field={"dexterity"} initialValue={characteristics.dexterity}/>
                    </label>
                    <label className="characteristicsLabel">
                        Int
                        <Text className="characteristicsText" field={"intelligence"} initialValue={characteristics.intelligence}/>
                    </label>
                    <label className="characteristicsLabel">
                        WP
                        <Text className="characteristicsText" field={"willpower"} initialValue={characteristics.willpower}/>
                    </label>
                    <label className="characteristicsLabel">
                        Fel
                        <Text className="characteristicsText" field={"fellowship"} initialValue={characteristics.fellowship}/>
                    </label>
                    </div>
                    </div>
                    <button type="submit" className="characteristicsButton" >Save Changes</button>
                </Form>
                </div>
                :
                <div />
            }
        </div>
    )
}