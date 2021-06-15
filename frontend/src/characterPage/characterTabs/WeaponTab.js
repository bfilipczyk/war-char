import React, {useEffect, useState} from 'react'
import {Space,Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";
import { Hidden} from "@material-ui/core";


export default function WeaponTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [weaponSet,setWeaponSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    const [weapons, setWeapons] = useState()
    const [showAdd, setShowAdd] = useState(true)
    const [componentMounted,setComponentMounted] = useState(true);

    if(props.weaponSet && weaponSet===null) {
        setWeaponSet(props.weaponSet)
        setCharacterId(props.characterId)
    }

    useEffect(()=> {
        if(!weapons && user!=null)
        {
            fetchWeapons();
        }
        return() => {
            setComponentMounted(false)
        }
    })

    const fetchWeapons= async () => {
        const response = await axios.get("/api/weapon",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        if(componentMounted) {
            setWeapons(response.data)
        }
    }

    const remove = async (id)=> {
        let dataId = id
        await axios.patch("/api/weapon/removeCharacterWeapon",{characterId,dataId},
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
            title: 'Weapon',
            render: (record) => (
                <React.Fragment>
                    {record.name}
                    <br/>
                    {record.dmg}
                    <br/>
                    {record.weaponGroup}
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
            title: 'Dmg',
            dataIndex: 'dmg',
            key: 'dmg',
            responsive: ["sm"]
        },
        {
            title: 'Weapon Group',
            dataIndex: 'weaponGroup',
            key: 'weaponGroup',
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

    const columnsWeapons = [
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
        await axios.post("/api/weapon/addCharacterWeapon",{characterId,dataId},
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
            {weaponSet!=null ?
                <div className={"tabDiv"}>
                <Table columns={columns} dataSource={weaponSet} size="small" rowKey="name" pagination={false}/>
                <Button className="tabButton" onClick={()=>{
                if(showAdd)
                    setShowAdd(false)
                else
                    setShowAdd(true)}
                }>Add</Button>
                    <Hidden lgDown={showAdd}>
                        {typeof weapons != "undefined" ?
                            <Table columns={columnsWeapons} dataSource={weapons} size="small" rowKey="name" pagination={false}/>
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