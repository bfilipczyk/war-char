import {useHistory, useParams} from "react-router";
import logo from "../assets/logo.png";
import "./CharacterPage.css"
import {Button, Table} from "antd";
import axios from "axios";
import {useEffect, useState} from "react";
import Characteristics from "./characteristics/Characteristics";


function CharacterPage() {
    let history = useHistory();
    let characterId = useParams().characterId;


    let character = null;

    useEffect(()=> {
        if(localStorage.getItem('user')==null)
        {
            history.push('/');
        }
        if(character===null && localStorage.getItem('user')!=null)
        {
            fetchCharacter();
            console.log(1)

        }
    })
    const fetchCharacter= async () => {
        const user = JSON.parse(localStorage.getItem('user'))

        await axios.get("/api/characters/character/"+characterId,
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            }).then(response => {character=response});

    }

    return(
        <div className="characterContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="characterMain">
                <Button onClick={()=>{console.log(character.data)}} >Click</Button>
                <Characteristics characteristics={character} />
            </div>
        </div>
    )

}

export default CharacterPage