import './Register.css';
import logo from '../assets/logo.png';
import { Input } from "informed";
import {useState} from "react";
import {useHistory} from "react-router";
import axios from "axios";


function Register() {
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [confirmPassword, setConfirmPassword] = useState();
    const [errorMessage, setErrorMessage] = useState();

    let history = useHistory();

    const sendForm = async e => {
        axios.post('api/auth/register', {email,password})
            .then((response) => {
                history.push('/');
            }).catch((e) => {
                setErrorMessage("error");
        })
    }

    const validatePassword = value => {
        let error = "";
        if(!password || ! confirmPassword){
            error = 'Provide passwords';
        }else if(password !== confirmPassword) {
            error = 'Provided passwords do not match';
        }else if (!(password.length >= 6 && password.length<= 30)) {
            error = 'Password must contain between 6 to 30 characters';
        }

        if (error!=="") {
            setErrorMessage(error);
            return false;
        }
        return true;
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (validatePassword()) {
            sendForm();
        }
    };

    return (
        <div className="register-container">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="Main">
                <form onSubmit={handleSubmit} className="register-form">
                    <Input field="email" type="email" placeholder="email" className="input register-input"
                           onChange={e => setEmail(e.target.value)} />
                    <Input field="password" type="password" placeholder="password" className={`input register-input`}
                           onChange={e => setPassword(e.target.value)}  />
                    <Input field="confirm password" type="password" placeholder="password" className={`input register-input`}
                           onChange={e => setConfirmPassword(e.target.value)}  />
                    <button type="submit" className="button register-button">Register</button>
                    <div className="error-message">
                        {errorMessage}
                    </div>
                </form>
            </div>
        </div>
    )
}
export default Register;