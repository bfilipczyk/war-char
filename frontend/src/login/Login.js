import './Login.css';
import logo from '../assets/logo.png';
import { Form, Input, Button, Checkbox } from 'antd';

function Login() {
    return (
        <div className="container">
            <div className="Logo">
                <img src={logo}/>
            </div>
            <div className="Main">
                <Form name="basic">
                    <Form.Item
                        label="username"
                        name="username"
                        rules={[{required: true},]}>
                        <Input />
                    </Form.Item>
                    <Form.Item
                        label="password"
                        name="password"
                        rules={[{required: true},]}>
                        <Input />
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            Log in
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    )
}
export default Login;