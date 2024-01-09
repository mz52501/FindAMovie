import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import {useState} from "react";
import axios from "axios";

function AddDirector() {

    const[formValue, setFormValue] = useState({
        name: '',
        surname: '',
        description: '',
        image: ''
    });

    function changeHandler(e) {
        const newData = {...formValue}
        newData[e.target.id] = e.target.value
        setFormValue(newData)
        console.log(newData)
    }

    function fileSelectHandler(e) {
        const newData = {...formValue}
        newData[e.target.id] = e.target.files[0].name
        setFormValue(newData)
        console.log(newData)
    }

    function submit(e) {
        e.preventDefault();
        axios.post("http://localhost:8080/addDirector", {
            name: formValue.name,
            surname: formValue.surname,
            description: formValue.description,
            image: formValue.image
        }).then(res => {
            console.log(res.data)
            window.location.href = '/admin';
        })
    }

    /*function redirect () {
        window.location.href = '/addMovie';
    }*/

    return(
        <div style={{width: 800, marginTop: 20,marginLeft: 100}}>
            <h2>Add new director</h2>
            <Form onSubmit={(e) => submit(e)}>
                <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                    <Label
                        className="me-sm-2"
                        for="name"
                    >
                        Name
                    </Label>
                    <Input
                        id="name"
                        name="name"
                        type="text"
                        value={formValue.name}
                        onChange={(e) => changeHandler(e)}
                    />
                </FormGroup>
                <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                    <Label
                        className="me-sm-2"
                        for="surname"
                    >
                        surname
                    </Label>
                    <Input
                        id="surname"
                        name="surname"
                        type="text"
                        value={formValue.surname}
                        onChange={(e) => changeHandler(e)}
                    />
                </FormGroup>
                <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                    <Label
                        className="me-sm-2"
                        for="description"
                    >
                        Description
                    </Label>
                    <Input
                        id="description"
                        name="description"
                        type="textarea"
                        value={formValue.description}
                        onChange={(e) => changeHandler(e)}
                    />
                </FormGroup>
                <FormGroup style={{marginTop: "10px"}}>
                    <Label for="image">
                        Image
                    </Label>
                    <Input
                        id="image"
                        name="image"
                        type="file"
                        onChange={(e) => fileSelectHandler(e)}
                    />
                </FormGroup>
                <div style={{display: "flex"}}>
                    <Button>Submit</Button>
                </div>
            </Form>
        </div>
    )
}
export default AddDirector;