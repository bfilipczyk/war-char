import './Register.css';
import logo from '../assets/logo.png';
import { Form, Input, Button, Checkbox } from 'antd';

function Register() {
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
                    <Form.Item
                        label="confirm password"
                        name="confirmPassword"
                        rules={[{required: true},]}>
                        <Input />
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            Register
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        </div>
    )
}
export default Register;