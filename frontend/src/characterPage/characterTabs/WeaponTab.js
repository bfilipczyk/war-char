import React, {useState} from 'react'
import {Space,Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";


export default function WeaponTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [weaponSet,setWeaponSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    if(props.weaponSet && weaponSet===null) {
        setWeaponSet(props.weaponSet)
        setCharacterId(props.characterId)
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

    return(
        <div>
            {weaponSet!=null ?
                <Table columns={columns} dataSource={weaponSet} size="small" rowKey="name" pagination={false}/>
                :
                <div/>
            }
        </div>
    )
}