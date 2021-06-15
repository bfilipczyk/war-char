import React, {useEffect, useState} from 'react'
import {Space,Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";
import {Hidden} from "@material-ui/core";


export default function TrappingTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [trappingSet,setTrappingSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    const [trapping, setTrapping] = useState()
    const [showAdd, setShowAdd] = useState(true)

    if(props.trappingSet && trappingSet===null) {
        setTrappingSet(props.trappingSet)
        setCharacterId(props.characterId)
    }

    useEffect(()=> {
        if(!trapping && user!=null)
        {
            fetchTrapping();
        }
    })

    const fetchTrapping= async () => {
        const response = await axios.get("/api/trapping",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setTrapping(response.data)
    }

    const remove = async (id)=> {
        let dataId = id
        await axios.patch("/api/trapping/removeCharacterTrapping",{characterId,dataId},
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
            title: 'Name',
            dataIndex: 'name',
            key: 'name'
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

    const columnsTrapping = [
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
        await axios.post("/api/trapping/addCharacterTrapping",{characterId,dataId},
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
            {trappingSet!=null ?
                <div className={"tabDiv"}>
                <Table columns={columns} dataSource={trappingSet} size="small" rowKey="name" pagination={false}/>
                    <Button className="tabButton" onClick={()=>{
                        if(showAdd)
                            setShowAdd(false)
                        else
                            setShowAdd(true)}
                    }>Add</Button>
                    <Hidden lgDown={showAdd}>
                        {typeof trapping != "undefined" ?
                            <Table columns={columnsTrapping} dataSource={trapping} size="small" rowKey="name" pagination={false}/>
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