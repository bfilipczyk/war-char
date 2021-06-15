import React, {useEffect, useState} from 'react'
import {Space,Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";
import {Hidden} from "@material-ui/core";


export default function ArmorTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [armorSet,setArmorSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    const [armor, setArmor] = useState()
    const [showAdd, setShowAdd] = useState(true)

    if(props.armorSet && armorSet===null) {
        setArmorSet(props.armorSet)
        setCharacterId(props.characterId)
    }

    useEffect(()=> {
        if(!armor && user!=null)
        {
            fetchArmor();

        }

    })

    const fetchArmor= async () => {
        const response = await axios.get("/api/armor",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setArmor(response.data)
    }

    const remove = async (id)=> {
        let dataId = id
        await axios.patch("/api/armor/removeCharacterArmor",{characterId,dataId},
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            }
        ).then(window.location.reload())


    }

    const columns = [
        {
            title: 'Armor',
            render: (record) => (
                <React.Fragment>
                    {record.name}
                    <br/>
                    {record.location}
                    <br/>
                    {record.armorPoints}
                    <br/>
                </React.Fragment>
            ),
            responsive: ["xs"]
        },
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
            responsive: ["sm"]
        },
        {
            title: 'Location',
            dataIndex: 'location',
            key: 'location',
            responsive: ["sm"]
        },
        {
            title: 'Armor Points',
            dataIndex: 'armorPoints',
            key: 'armorPoints',
            responsive: ["sm"]
        },
        {
            key:'action',
            render: (record) => (
                <Space>
                    <Button className="tabButton" onClick={()=>{
                        remove(record.id)
                    }}>Remove</Button>
                </Space>
            )
        }
    ]

    const columnsArmor = [
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
        await axios.post("/api/armor/addCharacterArmor",{characterId,dataId},
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
            {armorSet!=null ?
                <div className={"tabDiv"}>
                <Table columns={columns} dataSource={armorSet} size="small" rowKey="name" pagination={false}/>
                    <Button className="tabButton" onClick={()=>{
                        if(showAdd)
                            setShowAdd(false)
                        else
                            setShowAdd(true)}
                    }>Add</Button>
                    <Hidden lgDown={showAdd}>
                        {typeof armor != "undefined" ?
                            <Table columns={columnsArmor} dataSource={armor} size="small" rowKey="name" pagination={false}/>
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