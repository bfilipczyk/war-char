import {useHistory, useParams} from "react-router";
import logo from "../assets/logo.png";
import "./CharacterPage.css"
import axios from "axios";
import React, {useEffect, useState} from "react";
import Characteristics from "./characteristics/Characteristics";
import ArmorPoints from "./armorPoints/ArmorPoints";
import {AppBar, Tab, Tabs, Typography} from "@material-ui/core";
import WeaponTab from "./characterTabs/WeaponTab";
import ArmorTab from "./characterTabs/ArmorTab";
import TrappingTab from "./characterTabs/TrappingTab";
import SkillTab from "./characterTabs/SkillTab";
import PropTypes from "prop-types";
import TalentTab from "./characterTabs/TalentTab";


function TabPanel(props) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`scrollable-force-tabpanel-${index}`}
            aria-labelledby={`scrollable-force-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Typography component={"span"}>{children}</Typography>
            )}
        </div>
    );
}

TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.any.isRequired,
    value: PropTypes.any.isRequired,
};

function a11yProps(index) {
    return {
        id: `scrollable-force-tab-${index}`,
        'aria-controls': `scrollable-force-tabpanel-${index}`,
    };
}

function CharacterPage() {
    let history = useHistory();
    let characterId = useParams().characterId;
    const user = localStorage.getItem('user');
    const [character,setCharacter] = useState()
    const [loading, setLoading]= useState(true)
    const [value, setValue] = React.useState(0);
    const [value2, setValue2] = React.useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };
    const handleChange2 = (event, newValue) => {
        setValue2(newValue);
    };


    useEffect(()=> {
        if(user==null)
        {
            history.push('/');
        }
        if(!character && user!=null)
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
                    <div className="characterDiv">
                        {typeof character != "undefined"?
                        <div>
                            <h1 align={"center"}>{character.name}</h1>
                        </div>
                        :
                        <div/>}
                        <Characteristics character={character} />
                        <ArmorPoints/>
                        <div>
                                <AppBar position="static">
                                    <Tabs
                                        value={value}
                                        onChange={handleChange}
                                        variant="scrollable"
                                        scrollButtons="on"
                                        indicatorColor="secondary"
                                        color="secondary"
                                        aria-label="scrollable force tabs example"
                                    >
                                        <Tab label="Items" {...a11yProps(0)} />
                                        <Tab label="Skills" {...a11yProps(1)}/>
                                        <Tab label="Talents" {...a11yProps(2)}/>
                                    </Tabs>
                                </AppBar>
                                <TabPanel  value={value} index={0}>
                                        <AppBar position="static">
                                            <Tabs
                                                value={value2}
                                                onChange={handleChange2}
                                                variant="scrollable"
                                                scrollButtons="on"
                                                indicatorColor="secondary"
                                                aria-label="scrollable force tabs example"
                                            >
                                                <Tab label="Weapons" {...a11yProps(0)} />
                                                <Tab label="Armors" {...a11yProps(1)}/>
                                                <Tab label="Trapping" {...a11yProps(2)}/>
                                            </Tabs>
                                        </AppBar>
                                        <TabPanel value={value2} index={0}>
                                            {typeof character != "undefined" ?
                                                <WeaponTab weaponSet={character.weaponSet} characterId={characterId}/>
                                                :
                                                <div/>
                                            }
                                        </TabPanel>
                                        <TabPanel value={value2} index={1}>
                                            {typeof character != "undefined" ?
                                                <ArmorTab armorSet={character.armorSet} characterId={characterId}/>
                                                :
                                                <div/>
                                            }
                                        </TabPanel>
                                        <TabPanel value={value2} index={2}>
                                            {typeof character != "undefined" ?
                                                <TrappingTab trappingSet={character.trappingSet} characterId={characterId}/>
                                                :
                                                <div/>
                                            }
                                        </TabPanel>
                                </TabPanel>
                                <TabPanel value={value} index={1}>
                                    {typeof character != "undefined" ?
                                        <SkillTab characterSkillsSet={character.characterSkillsSet}/>
                                        :
                                        <div/>
                                    }
                                </TabPanel>
                                <TabPanel value={value} index={2}>
                                    {typeof character != "undefined" ?
                                        <TalentTab characterTalentsSet={character.characterTalentsSet}/>
                                        :
                                        <div/>
                                    }
                                </TabPanel>
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