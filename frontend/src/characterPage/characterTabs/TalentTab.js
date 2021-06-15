import React, {useEffect, useState} from 'react'
import {Space, Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";
import {Hidden} from "@material-ui/core";


export default function TalentTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [characterTalentsSet,setCharacterTalentsSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    const [talents, setTalents] = useState()
    const [showAdd, setShowAdd] = useState(true)
    const [componentMounted,setComponentMounted] = useState(true);

    if(props.characterTalentsSet && characterTalentsSet===null) {
        setCharacterTalentsSet(props.characterTalentsSet)
        setCharacterId(props.characterId)
    }

    useEffect(()=> {
        if(!talents && user!=null)
        {
            fetchTalents();
        }
        return() => {
            setComponentMounted(false)
        }
    })

    const fetchTalents = async () => {
        const response = await axios.get("/api/talent",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        if(componentMounted) {
            setTalents(response.data)
        }
    }


    const remove = async (id)=> {
        await axios.delete("/api/talent/removeCharacterTalent/"+id,
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
            await axios.patch("/api/talent/updateCharacterTalent",{id,value},
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
            title: 'Talent',
            render: (record) => (
                <React.Fragment>
                    {record.talent.name}
                    <br/>
                    {record.advances}
                    <br/>
                </React.Fragment>
            ),
            responsive: ["xs"]
        },
        {
            title: 'Name',
            dataIndex: ['talent','name'],
            key: 'name',
            responsive: ["sm"]
        },
        {
            title: 'Adv',
            dataIndex: 'advances',
            key: 'advances',
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

    const columnsTalents = [
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
        await axios.post("/api/talent/addCharacterTalent",{characterId,dataId},
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
            {characterTalentsSet!=null ?
                <div className={"tabDiv"}>
                <Table columns={columns} dataSource={characterTalentsSet} size="small" rowKey="id" pagination={false}/>
                    <Button className="tabButton" onClick={()=>{
                        if(showAdd)
                            setShowAdd(false)
                        else
                            setShowAdd(true)}
                    }>Add</Button>
                    <Hidden lgDown={showAdd}>
                        {typeof talents != "undefined" ?
                            <Table columns={columnsTalents} dataSource={talents} size="small" rowKey="name" pagination={false}/>
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