import React, {useState} from 'react'
import {Space, Button, Table, Input, Form} from "antd";
import "./Tabs.css"
import axios from "axios";



export default function SkillTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [characterSkillsSet,setCharacterSkillsSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    if(props.characterSkillsSet && characterSkillsSet===null) {
        setCharacterSkillsSet(props.characterSkillsSet)
        setCharacterId(props.characterId)
    }



    const remove = async (id)=> {
        let dataId = id
        // await axios.patch("/api/armor/removeCharacterArmor",{characterId,dataId},
        //     {
        //         headers:
        //             {
        //                 Authorization:'Bearer '+ user.accessToken
        //             }
        //     }
        // ).then(window.location.reload())
    }
    const update = async (id)=> {
        const adv = parseInt(prompt("Enter advancements"))
        console.log(adv)

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
                        // remove(record.id)
                        console.log(record)
                    }}>Remove</Button>
                    <Button className="tabButton" onClick={()=>{
                        update(record.id)
                    }}>Update</Button>
                </Space>
            )
        }
    ]
    if(characterSkillsSet!=null){
        console.log(characterSkillsSet)
    }
    return(
        <div>
            {characterSkillsSet!=null ?
                <Table columns={columns} dataSource={characterSkillsSet} size="small" rowKey="id" pagination={false}/>
                :
                <div/>
            }
        </div>
    )
}