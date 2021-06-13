import {useState} from "react";
import axios from "axios";
import {Button, Form} from "antd";


export default function Characteristics(props){
    let characterId = props

    let characteristics

    const fetchDataCharacteristics = async () => {

        const user = JSON.parse(localStorage.getItem('user'))


        await axios.get("/api/characters/"+user.id,
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            }).then(response => {characteristics=response.data});
    }

    return (
        <div>
            <Button onClick={()=>{console.log(characterId)}} >Click</Button>
        </div>
    )
}