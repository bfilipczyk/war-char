import React, {useEffect, useState} from "react";
import logo from "../assets/logo.png";
import axios from "axios";
import "./Armor.css";
import {Table} from "antd";


export default function Armor(){

    const [armor,setArmor] =  useState(null);

    useEffect(()=>{
        if(!armor)
        {
            fetchDataArmor();
        }
        }
    )
    const fetchDataArmor = async () => {
        const response = await axios.get("/api/armor");
        console.log(response.data)
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
                <img src={logo}/>
            </div>
            <div className="armorMain">
                <Table columns={columns} dataSource={armor} size="small" pagination={false} />
            </div>
        </div>
    )
}