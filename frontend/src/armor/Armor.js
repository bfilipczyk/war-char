import React, {useEffect, useState} from "react";
import logo from "../assets/logo.png";
import axios from "axios";
import "./Armor.css";
import {Button, Table} from "antd";
import {useHistory} from "react-router";


export default function Armor(){

    const [armor,setArmor] =  useState(null);
    let history = useHistory();

    useEffect(()=>{
        if(localStorage.getItem('user')==null)
        {
            history.push('/');
        }
        if(!armor && localStorage.getItem('user')!=null)
        {
            fetchDataArmor();
        }
        }
    )
    const fetchDataArmor = async () => {

        const user = JSON.parse(localStorage.getItem('user'))


        const response = await axios.get("/api/armor",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setArmor(response.data)
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
    ]
    return (
        <div className="armorContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="armorMain">
                <Table columns={columns} dataSource={armor} size="small" rowKey="name"  pagination={false} />
                <Button className="armorButton" onClick={()=>history.push("/home")} >Home</Button>
            </div>
        </div>
    )
}