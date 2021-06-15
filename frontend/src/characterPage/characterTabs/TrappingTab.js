import React, {useState} from 'react'
import {Space,Button, Table} from "antd";
import "./Tabs.css"
import axios from "axios";


export default function TrappingTab(props){
    const user = JSON.parse(localStorage.getItem('user'))
    const [trappingSet,setTrappingSet] = useState(null)
    const [characterId,setCharacterId] = useState(null)
    if(props.trappingSet && trappingSet===null) {
        setTrappingSet(props.trappingSet)
        setCharacterId(props.characterId)
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

    return(
        <div>
            {trappingSet!=null ?
                <Table columns={columns} dataSource={trappingSet} size="small" rowKey="name" pagination={false}/>
                :
                <div/>
            }
        </div>
    )
}