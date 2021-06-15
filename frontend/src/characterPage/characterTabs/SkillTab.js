import React, {useEffect, useState} from 'react'
import {Space, Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";
import {Hidden} from "@material-ui/core";


export default function SkillTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [characterSkillsSet,setCharacterSkillsSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    const [skills, setSkills] = useState()
    const [showAdd, setShowAdd] = useState(true)
    const [componentMounted,setComponentMounted] = useState(true);

    if(props.characterSkillsSet && characterSkillsSet===null) {
        setCharacterSkillsSet(props.characterSkillsSet)
        setCharacterId(props.characterId)
    }

    useEffect(()=> {
        if(!skills && user!=null)
        {
            fetchSkills();
        }
        return() => {
            setComponentMounted(false)
        }
    })

    const fetchSkills = async () => {
        const response = await axios.get("/api/skill",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        if(componentMounted) {
            setSkills(response.data)
        }
    }

    const remove = async (id)=> {
        await axios.delete("/api/skill/removeCharacterSkill/"+id,
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            }
        ).then(window.location.reload())
    }

    const update = async (id)=> {
        const value = parseInt(prompt("Enter advancements"))
        if(!isNaN(value))
        {
            await axios.patch("/api/skill/updateCharacterSkill",{id,value},
                {
                    headers:
                        {
                            Authorization:'Bearer '+ user.accessToken
                        }
                }
            ).then(window.location.reload())
        }
    }

    const columns = [
        {
            title: 'Skill',
            render: (record) => (
                <React.Fragment>
                    {record.skill.name}
                    <br/>
                    {record.skill.attribute}
                    <br/>
                    {record.advances}
                    <br/>
                    {record.skill.type}
                    <br/>
                </React.Fragment>
            ),
            responsive: ["xs"]
        },
        {
            title: 'Name',
            dataIndex: ['skill','name'],
            key: 'name',
            responsive: ["sm"]
        },
        {
            title: 'Attr',
            dataIndex: ['skill','attribute'],
            key: 'Atrr',
            responsive: ["sm"]
        },
        {
            title: 'Adv',
            dataIndex: 'advances',
            key: 'advances',
            responsive: ["sm"]
        },
        {
            title: 'Type',
            dataIndex: ['skill','type'],
            key: 'type',
            responsive: ["sm"]
        },
        {
            key:'action',
            render: (record) => (
                <Space>
                    <Button className="tabButton" onClick={()=>{
                        remove(record.id)
                    }}>Remove</Button>
                    <Button className="tabButton" onClick={()=>{
                        update(record.id)
                    }}>Update</Button>
                </Space>
            )
        }
    ]

    const columnsSkills = [
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name'
        },
        {
            key:'action',
            render: (record) => (
                <Space>
                    <Button className="tabButton" onClick={()=>{
                        add(record.id)
                    }}>Add</Button>
                </Space>
            )
        }]

    const add = async (id) => {
        let dataId = id
        await axios.post("/api/skill/addCharacterSkill",{characterId,dataId},
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            }
        ).then(window.location.reload())
    }

    return(
        <div>
            {characterSkillsSet!=null ?
                <div className={"tabDiv"}>
                    <Table columns={columns} dataSource={characterSkillsSet} size="small" rowKey="id" pagination={false}/>
                    <Button className="tabButton" onClick={()=>{
                        if(showAdd)
                            setShowAdd(false)
                        else
                            setShowAdd(true)}
                    }>Add</Button>
                    <Hidden lgDown={showAdd}>
                        {typeof skills != "undefined" ?
                            <Table columns={columnsSkills} dataSource={skills} size="small" rowKey="name" pagination={false}/>
                            :
                            <div/>
                        }
                    </Hidden>
                </div>
                :
                <div/>
            }
        </div>
    )
}