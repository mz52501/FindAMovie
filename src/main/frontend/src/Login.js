import {Button, Form, Input, Label, FormGroup} from 'reactstrap';
import {Link} from "react-router-dom";
import NavigatingBar from "./NavigatingBar";
import {useContext, useEffect, useState} from "react";
import {UserContext} from "./UserContext";
import axios from "axios";

function Login() {

    const {user, setUser} = useContext(UserContext);
    const[users, setUsers] = useState([]);
    const[formValue, setFormValue] = useState({
        username: "",
        password: ""
    });

    const fetchUsers = () => {
        axios.get("http://localhost:8080/admin").then(res => {
            setUsers(res.data);
        });
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    function handleChange(e) {
        const newData = {...formValue}
        newData[e.target.id] = e.target.value
        setFormValue(newData)
        console.log(newData)
    }

    function handleClick(e) {
        for(const currentUser of users) {
            if(currentUser.username === formValue.username) {
                if(currentUser.password === formValue.password) {
                    setUser({
                        username: formValue.username,
                        password: formValue.password,
                        id: currentUser.id,
                        role: currentUser.role
                    });
                }
            }
        }
    }

    return (
        <div style={{width: 800, marginTop: 20,marginLeft: 100}}>
            <h2>Login</h2>
        <Form>
            <FormGroup className="mb-2 me-sm-2 mb-sm-0">
                <Label
                    className="me-sm-2"
                    for="username"
                >
                    Username
                </Label>
                <Input
                    id="username"
                    name="username"
                    type="text"
                    value={formValue.username}
                    onChange={(e) => handleChange(e)}
                />
            </FormGroup>
            <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                <Label
                    className="me-sm-2"
                    for="password"
                >
                    Password
                </Label>
                <Input
                    id="password"
                    name="password"
                    type="password"
                    value={formValue.password}
                    onChange={(e) => handleChange(e)}
                />
            </FormGroup>
            <Link to="/">
                <Button type="button" style={{marginTop: "10px"}} onClick={(e) => handleClick(e)}>
                    Submit
                </Button>
            </Link>
        </Form>
        </div>
);
}

export default Login;