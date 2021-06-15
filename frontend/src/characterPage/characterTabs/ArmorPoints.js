import React from 'react'
import {Form, Text} from "informed";
import "./Tabs.css"


export default function ArmorPoints() {
    const spots = ["Head","PrimaryArm","SecondaryArm","Shield","Body","RightLeg","LeftLeg"]

    return (
        <div>
        <Form className={"armorPointsForm"} >
            {spots.map((spot) =>
                <label className={"armorPointsLabel"} key={spot}>
                    {spot}
                <Text className={"armorPointsText"} field={spot} initialValue={0}/>
                </label>
            )}
        </Form>
        </div>
    )

}