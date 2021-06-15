import React, {useEffect, useState} from "react";
import logo from "../assets/logo.png";
import axios from "axios";
import "./Skill.css";
import {Button, Table} from "antd";
import {useHistory} from "react-router";


export default function Skill(){
    const user = localStorage.getItem('user');
    const [skills,setSkills] =  useState(null);
    let history = useHistory();

    useEffect(()=>{
            if(user==null)
            {
                history.push('/');
            }
            if(!skills && user!=null)
            {
                fetchSkills();
            }
        }
    )

    const fetchSkills = async () => {
        const user = JSON.parse(localStorage.getItem('user'))
        const response = await axios.get("/api/skill",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setSkills(response.data)
    }

    const columns = [
        {
            title: 'Armor',
            render: (record) => (
                <React.Fragment>
                    {record.name}
                    <br/>
                    {record.type}
                    <br/>
                    {record.attribute}
                    <br/>
                    {record.description}
                    <br/>
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
            title: 'Type',
            dataIndex: 'type',
            key: 'type',
            responsive: ["sm"]
        },
        {
            title: 'Attr',
            dataIndex: 'attribute',
            key: 'attribute',
            responsive: ["sm"]
        },
        {
            title: 'Description',
            dataIndex: 'description',
            key: 'description',
            responsive: ["sm"]
        }
    ]

    return (
        <div className="skillContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="skillMain">
                <Table columns={columns} dataSource={skills} size="small" rowKey="name"  pagination={false} />
                <Button className="skillButton" onClick={()=>history.push("/home")} >Home</Button>
            </div>
        </div>
    )
}