import logo from "../assets/logo.png";
import './Home.css';
import { Input } from "informed";
import {Button, Space, Table} from "antd";
import {useHistory} from "react-router";
import {useEffect, useState} from "react";
import axios from "axios";

function Home() {
    let history = useHistory();

    const [characters,setCharacters] =  useState(null);
    const [errorMessage, setErrorMessage] = useState();
    const [name, setName] = useState();
    const user = JSON.parse(localStorage.getItem('user'))

    useEffect(()=>{
            if(localStorage.getItem('user')==null)
            {
                history.push('/');
            }
            if(!characters && localStorage.getItem('user')!=null)
            {
                fetchDataCharacter();

            }
        }
    )

    const fetchDataCharacter = async () => {




        const response = await axios.get("/api/characters/"+user.id,
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            });
        setCharacters(response.data)
    }

    const handleSubmit = async e => {
        e.preventDefault();
        await axios.post("/api/characters/newCharacter/"+user.id,{name},
            {
                headers:
                    {
                        Authorization:'Bearer '+ user.accessToken
                    }
            }
            ).then((response) => {
            })

    }

    const columns = [
        {
            dataIndex: 'name',
            key: 'name'
        },
        {
            key:'action',
            render: (record) => (
                <Space>
                    <Button className="HomeButton" onClick={() => {
                        history.push("/characters/"+record.id)
                    }}>Open</Button>
                </Space>
            )
        }
    ]

    return (
        <div className="homeContainer">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="homeMain">
                <div className="homeCharacters">
                    <h1>Characters</h1>
                    <Table columns={columns} dataSource={characters} size="small" rowKey="name" pagination={false} />
                    <form>
                        <Input field="name" placeholder="name" className="input log-in-input"
                               onChange={e => setName(e.target.value)}/>
                        <Button type="submit" className="HomeButton" onClick={handleSubmit} >Create Character</Button>
                        <div className="error-message">
                            {errorMessage}
                        </div>
                    </form>

                </div>
                <div className="homeButtonContainer">
                    <div className="homeLinks">
                        <Button className="HomeButton" onClick={()=>history.push("/armor")} >Armors</Button>
                        <Button className="HomeButton" onClick={()=>history.push("/weapon")} >Weapons</Button>
                        <Button className="HomeButton" >Trapping</Button>
                        <Button className="HomeButton" onClick={()=>{
                            localStorage.removeItem('user')
                            history.push("/armor")
                        }} >Log out</Button>
                </div>
                </div>
            </div>
        </div>
    )

}
export default Home;