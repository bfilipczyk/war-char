import React, {useEffect, useState} from "react";
import logo from "../assets/logo.png";
import axios from "axios";
import "./Weapon.css";
import {Table} from "antd";
import {useHistory} from "react-router";


export default function Weapon(){

    const [weapon,setWeapon] =  useState(null);
    let history = useHistory();

    useEffect(()=>{
            if(localStorage.getItem('user')==null)
            {
                history.push('/');
            }
            if(!weapon && localStorage.getItem('user')!=null)
            {
                fetchDataWeapon();
            }
        }
    )
    const fetchDataWeapon = async () => {

        const user = JSON.parse(localStorage.getItem('user'))


        const response = await axios.get("/api/weapon",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setWeapon(response.data)
    }
    const columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: 'Dmg',
            dataIndex: 'dmg',
            key: 'dmg'
        },
        {
            title: 'Weapon Group',
            dataIndex: 'weaponGroup',
            key: 'weaponGroup'
        },
    ]
    return (
        <div className="weaponContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="weaponMain">
                <Table columns={columns} dataSource={weapon} size="small" rowKey="name" pagination={false} />
            </div>
        </div>
    )
}