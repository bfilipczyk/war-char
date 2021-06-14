import {useHistory, useParams} from "react-router";
import logo from "../assets/logo.png";
import "./CharacterPage.css"
import axios from "axios";
import React, {useEffect, useState} from "react";
import Characteristics from "./characteristics/Characteristics";
import ArmorPoints from "./armorPoints/ArmorPoints";
import {AppBar, Box, Button, Tab, Tabs, Typography} from "@material-ui/core";
import TabContext from '@material-ui/lab/TabContext';
import TabList from '@material-ui/lab/TabList';
import TabPanel from '@material-ui/lab/TabPanel';
import {Table} from "antd";
import WeaponTab from "./characterTabs/WeaponTab";


function CharacterPage() {
    let history = useHistory();
    let characterId = useParams().characterId;
    const [character,setCharacter] = useState()
    const [loading, setLoading]= useState(true)
    const [value, setValue] = React.useState('1');
    const [value2, setValue2] = React.useState('1');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };
    const handleChange2 = (event, newValue) => {
        setValue2(newValue);
    };


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
                            <h1 align={"center"}>{character.name}</h1>
                        </div>
                        :
                        <div/>}
                        <Characteristics character={character} />
                        <ArmorPoints/>
                        <div>
                            <TabContext value={value}>
                                <AppBar position="static">
                                    <TabList onChange={handleChange}>
                                        <Tab label="Items" value="1" />
                                        <Tab label="Skills" value="2" />
                                        <Tab label="Talents" value="3" />
                                    </TabList>
                                </AppBar>
                                <TabPanel  value="1">
                                    <TabContext value={value2}>
                                        <AppBar position="static">
                                            <TabList onChange={handleChange2}>
                                                <Tab label="Weapons" value="1" />
                                                <Tab label="Armors" value="2" />
                                                <Tab label="Trapping" value="3" />
                                            </TabList>
                                        </AppBar>
                                        <TabPanel value="1">

                                            {typeof character != "undefined" ?
                                                <WeaponTab weaponSet={character.weaponSet} characterId={characterId}/>
                                                :
                                                <div/>
                                            }
                                        </TabPanel>
                                        <TabPanel value="2">
                                            Armors
                                        </TabPanel>
                                        <TabPanel value="3">
                                            Trapping
                                        </TabPanel>
                                    </TabContext>
                                </TabPanel>
                                <TabPanel value="2">
                                    <Button onClick={console.log(character)}>
                                        click
                                    </Button>
                                </TabPanel>
                                <TabPanel value="3">
                                </TabPanel>
                            </TabContext>
                        </div>
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