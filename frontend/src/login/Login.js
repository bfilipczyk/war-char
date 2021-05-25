import './Login.css';
import logo from '../assets/logo.png';
import { Input } from "informed";
import {useState} from "react";
import {useHistory} from "react-router";
import {Link} from "react-router-dom";
import axios from "axios";





function Login() {
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [errorMessage, setErrorMessage] = useState();
    let history = useHistory();

    const handleSubmit = async e => {
        e.preventDefault()
        console.log("pies");
        await axios.post(`api/auth/login`, {email,password})
            .then((response) => {
                console.log(response);
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                history.push('/armor');
            })
            .catch(() => {
                setErrorMessage(() => ({ message: 'No account found with provided credentials' }));
            });

    }




    return (
        <div className="login-container">
            <div className="Logo">
                <img src={logo} alt="error"/>
            </div>
            <div className="Main">
                <form onSubmit={handleSubmit} className="login-form">
                    <Input field="email" type="email" placeholder="email" className="input log-in-input"
                           onChange={e => setEmail(e.target.value)} />
                    <Input field="password" type="password" placeholder="password" className={`input log-in-input`}
                           onChange={e => setPassword(e.target.value)}  />
                    <button type="submit" className="button sign-in-button">Sign in</button>
                    <Link to="/register" className="button register-button">Create account</Link>
                    <div className="error-message">
                        {errorMessage}
                    </div>
                </form>

            </div>
        </div>
    )
}
export default Login;