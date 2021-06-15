import React, {useState} from 'react'
import {Space, Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";



export default function TalentTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [characterTalentsSet,setCharacterTalentsSet] = useState(null)
    if(props.characterTalentsSet && characterTalentsSet===null) {
        setCharacterTalentsSet(props.characterTalentsSet)
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
    if(characterTalentsSet!=null){
        console.log(characterTalentsSet)
    }
    return(
        <div>
            {characterTalentsSet!=null ?
                <Table columns={columns} dataSource={characterTalentsSet} size="small" rowKey="id" pagination={false}/>
                :
                <div/>
            }
        </div>
    )
}