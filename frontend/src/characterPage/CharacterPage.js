import {useHistory, useParams} from "react-router";
import logo from "../assets/logo.png";
import "./CharacterPage.css"
import {Button, Table} from "antd";
import axios from "axios";
import React, {useEffect, useState} from "react";
import Characteristics from "./characteristics/Characteristics";


function CharacterPage() {
    let history = useHistory();
    let characterId = useParams().characterId;
    const [character,setCharacter] = useState()
    const [loading, setLoading]= useState(true)



    useEffect(()=> {
        if(localStorage.getItem('user')==null)
        {
            history.push('/');
        }
        if(!character && localStorage.getItem('user')!=null)
        {
            setLoading(true)
            fetchCharacter();

        }
    })


    const fetchCharacter= async () => {
        const user = JSON.parse(localStorage.getItem('user'))

        const response = await axios.get("/api/characters/character/"+characterId,
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            })
        setLoading(false)
        setCharacter(response.data)



    }


    return(
        <div className="characterContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="characterMain">
                {!loading?
                    <div>
                        {typeof character != "undefined"?
                        <div>
                            <h1>{character.name}</h1>
                        </div>
                        :
                        <div/>}
                        <Characteristics character={character} />
                    </div>
                    :
                    <div>
                        Loading
                    </div>
                }
            </div>
        </div>
    )


}

export default CharacterPage