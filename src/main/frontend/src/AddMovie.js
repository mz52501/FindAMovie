import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import {useEffect, useState} from "react";
import axios from "axios";

function AddMovie() {

    const[formValue, setFormValue] = useState({
        title: '',
        relDate: '',
        description: '',
        image: '',
        duration: ''
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



    function submit(event) {
        event.preventDefault();
        axios.post("http://localhost:8080/addMovie", {
            title: formValue.title,
            description: formValue.description,
            image: formValue.image,
            duration: formValue.duration,
            relDate: formValue.relDate
        }).then(res => {
            console.log(res.data)
            window.location.href = '/admin';
        })
    }

    return(
    <div style={{width: 800, marginTop: 20,marginLeft: 100}}>
        <h2>Add new movie</h2>
        <Form onSubmit={(event => submit(event))}>
            <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                <Label
                    className="me-sm-2"
                    for="title"
                >
                    Title
                </Label>
                <Input
                    id="title"
                    name="title"
                    type="text"
                    value={formValue.title}
                    onChange={(e) => changeHandler(e)}
                />
            </FormGroup>
            <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                <Label
                    className="me-sm-2"
                    for="duration"
                >
                    Duration
                </Label>
                <Input
                    id="duration"
                    name="duration"
                    type="time"
                    value={formValue.duration}
                    onChange={(e) => changeHandler(e)}
                />
            </FormGroup>
            <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                <Label
                    className="me-sm-2"
                    for="relDate"
                >
                    Release date
                </Label>
                <Input
                    id="relDate"
                    name="relDate"
                    type="date"
                    value={formValue.relDate}
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
            <Button type="submit" style={{marginTop: "10px"}}>
                Submit
            </Button>
        </Form>
    </div>
    )
}

export default AddMovie;