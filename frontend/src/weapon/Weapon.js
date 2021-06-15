import React, {useEffect, useState} from "react";
import logo from "../assets/logo.png";
import axios from "axios";
import "./Weapon.css";
import {Button, Table} from "antd";
import {useHistory} from "react-router";


export default function Weapon(){
    const user = localStorage.getItem('user');
    const [weapon,setWeapon] =  useState(null);
    const [quality,setQuality] =  useState(null);
    let history = useHistory();

    useEffect(()=>{
        if(user==null)
        {
            history.push('/');
        }
        if(!weapon && user!=null)
        {
            fetchDataWeapon();
        }
        if(!quality && user!=null)
        {
            fetchDataWeaponQuality();
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
        console.log(response.data)
    }

    const fetchDataWeaponQuality = async () => {
        const user = JSON.parse(localStorage.getItem('user'))
        const response = await axios.get("/api/weapon/quality",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setQuality(response.data)
        console.log(response.data)
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
                    {record.weaponQualitySet.map(quality => {
                        return (
                            <div id={quality.id}>
                                {quality.name}
                                <br />
                            </div>
                        );
                    })}
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
            title: 'Qualities',
            dataIndex: 'weaponQualitySet',
            key: "id",
            responsive: ["sm"],
            render: weaponQualitySet => (
                <>
                    {weaponQualitySet.map(quality => {
                        return (
                            <div key={quality.id}>
                                {quality.name}
                                <br />
                            </div>
                        );
                    })}
                </>
            )
        }

    ]
    
    const qualityColumns = [
        {
            title: 'Weapon Quality',
            render: (record) => (
                <React.Fragment>
                    {record.name}
                    <br/>
                    {record.description}
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
            title: 'Description',
            dataIndex: 'description',
            key: 'description',
            responsive: ["sm"]
        },
    ]
    
    return (
        <div className="weaponContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="weaponMain">
                <Table columns={columns} dataSource={weapon} size="small" rowKey="name" pagination={false} />
                <Table columns={qualityColumns} dataSource={quality} size="small" rowKey="name" pagination={false} />
                <Button className="armorButton" onClick={()=>history.push("/home")} >Home</Button>
            </div>
        </div>
    )
}