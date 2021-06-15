import {Form, Text} from "informed";
import React from "react";
import "./Tabs.css";
import axios from "axios";


export default function CharacterStats(props) {
    const data = props.stats;
    const characterId = props.characterId;

    const update = async (values)=>{
        const user = JSON.parse(localStorage.getItem('user'))
        await axios.patch("/api/characters/updateCharacterStats/"+characterId,values,{
            headers:
                {
                    Authorization:'Bearer '+ user.accessToken
                }
        }).then(window.location.reload())
    }

    return (
        <div>
            <Form className={"armorPointsForm"} onSubmit={update} >
                <label className={"armorPointsLabel"}>
                    Race
                    <Text className={"statsText"} field={"race"} initialValue={data.race} />
                </label>
                <label className={"armorPointsLabel"}>
                    Career
                    <Text className={"statsText"} field={"career"} initialValue={data.career} />
                </label>
                <label className={"armorPointsLabel"}>
                    Exp
                    <Text className={"statsText"} field={"experience"} type={"number"} initialValue={data.experience} />
                </label>
                <label className={"armorPointsLabel"}>
                    TotalWounds
                    <Text className={"armorPointsText"} field={"totalWounds"} type="number" initialValue={data.totalWounds} />
                </label>
                <label className={"armorPointsLabel"}>
                    CurrentWounds
                    <Text className={"armorPointsText"} field={"currentWounds"} type={"number"} initialValue={data.currentWounds} />
                </label>
                <label className={"armorPointsLabel"}>
                    Fate
                    <Text className={"armorPointsText"} field={"fate"} type="number" initialValue={data.fate} />
                </label>
                <label className={"armorPointsLabel"}>
                    Resilience
                    <Text className={"armorPointsText"} field={"resilience"} type="number" initialValue={data.resilience} />
                </label>
                <button className="tabButton" type="submit" >Update</button>
            </Form>
        </div>
    )

}