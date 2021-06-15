import React, {useEffect, useState} from "react";
import logo from "../assets/logo.png";
import axios from "axios";
import "./Talent.css";
import {Button, Table} from "antd";
import {useHistory} from "react-router";


export default function Talent(){
    const user = localStorage.getItem('user');
    const [talents,setTalents] =  useState(null);
    let history = useHistory();

    useEffect(()=>{
            if(user==null)
            {
                history.push('/');
            }
            if(!talents && user!=null)
            {
                fetchSkills();
            }
        }
    )

    const fetchSkills = async () => {

        const user = JSON.parse(localStorage.getItem('user'))


        const response = await axios.get("/api/talent",
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setTalents(response.data)
    }

    const columns = [
        {
            title: 'Armor',
            render: (record) => (
                <React.Fragment>
                    {record.name}
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
            title: 'Description',
            dataIndex: 'description',
            key: 'description',
            responsive: ["sm"]
        }
    ]

    return (
        <div className="talentContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="talentMain">
                <Table columns={columns} dataSource={talents} size="small" rowKey="name"  pagination={false} />
                <Button className="talentButton" onClick={()=>history.push("/home")} >Home</Button>
            </div>
        </div>
    )
}