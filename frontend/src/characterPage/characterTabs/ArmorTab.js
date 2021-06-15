import React, {useState} from 'react'
import {Space,Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";


export default function ArmorTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [armorSet,setArmorSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    if(props.armorSet && armorSet===null) {
        setArmorSet(props.armorSet)
        setCharacterId(props.characterId)
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
            title: 'Name',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: 'Location',
            dataIndex: 'location',
            key: 'location'
        },
        {
            title: 'Armor Points',
            dataIndex: 'armorPoints',
            key: 'armorPoints'
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
            {armorSet!=null ?
                <Table columns={columns} dataSource={armorSet} size="small" rowKey="name" pagination={false}/>
                :
                <div/>
            }
        </div>
    )
}