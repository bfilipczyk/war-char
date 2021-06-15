import React, {useEffect, useState} from "react";
import logo from "../assets/logo.png";
import axios from "axios";
import "./Armor.css";
import {Button, Table} from "antd";
import {useHistory} from "react-router";


export default function Armor(){
    const user = localStorage.getItem('user');
    const [armor,setArmor] =  useState(null);
    const [quality,setQuality] =  useState(null);
    let history = useHistory();

    useEffect(()=>{
        if(user==null)
        {
            history.push('/');
        }
        if(!armor && user!=null)
        {
            fetchDataArmor();
        }
        if(!quality && user!=null)
        {
            fetchDataArmorQuality();
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

    const fetchDataArmorQuality = async () => {
        const user = JSON.parse(localStorage.getItem('user'))
        const response = await axios.get("/api/armor/quality",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setQuality(response.data)
    }

    const columns = [
        {
            title: 'Armor',
            render: (record) => (
                <React.Fragment>
                    {record.name}
                    <br/>
                    <br/>
                    {record.location}
                    <br/>
                    {record.armorPoints}
                    <br/>
                    {record.armorQualitySet.map(quality => {
                        return (
                            <div id={quality.id}>
                                {quality.name}
                                <br />
                            </div>
                        );
                    })}
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
            title: 'Qualities',
            dataIndex: 'armorQualitySet',
            key: "id",
            responsive: ["sm"],
            render: armorQualitySet => (
                <>
                    {armorQualitySet.map(quality => {
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
            title: 'Armor Quality',
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
        <div className="armorContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="armorMain">
                <Table columns={columns} dataSource={armor} size="small" rowKey="name"  pagination={false} />
                <Table columns={qualityColumns} dataSource={quality} size="small" rowKey="name" pagination={false} />
                <Button className="armorButton" onClick={()=>history.push("/home")} >Home</Button>
            </div>
        </div>
    )
}