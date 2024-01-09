import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import {useContext, useState} from "react";
import axios from "axios";
import {UserContext} from "./UserContext";

function Registration() {

    const {user, setUser} = useContext(UserContext);
    const[formValue, setFormValue] = useState({
        email: '',
        username: '',
        password: '',
        repPassword: '',
    });
    function changeHandler(e) {
        const newData = {...formValue}
        newData[e.target.id] = e.target.value
        setFormValue(newData)
        console.log(newData)
    }

    function submit(e) {
        e.preventDefault();
        axios.post("http://localhost:8080/registration", {
            email: formValue.email,
            username: formValue.username,
            password: formValue.password,
            repeatedPassword: formValue.repPassword
        }).then(res => {
            console.log(res.data)
            window.location.href = '/login';
        })
    }

    return(
        <div style={{marginTop: 20, width: 800,marginLeft: 100, borderRadius: "5px"}}>
            <h2>Registration</h2>
            <Form onSubmit={(e) => submit(e)}>
                <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                    <Label
                        className="me-sm-2"
                        for="email"
                    >
                        Email
                    </Label>
                    <Input
                        id="email"
                        name="email"
                        placeholder="something@idk.cool"
                        type="email"
                        value={formValue.email}
                        onChange={(e) => changeHandler(e)}
                    />
                </FormGroup>
                <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
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
                        onChange={(e) => changeHandler(e)}
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
                        placeholder="****"
                        type="password"
                        value={formValue.password}
                        onChange={(e) => changeHandler(e)}
                    />
                </FormGroup>
                <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                    <Label
                        className="me-sm-2"
                        for="repPassword"
                    >
                        Repeat password
                    </Label>
                    <Input
                        id="repPassword"
                        name="repPassword"
                        placeholder="****"
                        type="password"
                        value={formValue.repPassword}
                        onChange={(e) => changeHandler(e)}
                    />
                </FormGroup>
                <Button style={{marginTop: "10px"}}>
                    Submit
                </Button>
            </Form>
        </div>
    )

}

export default Registration;