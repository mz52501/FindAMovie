import {Form, FormGroup, Input, Label, Button} from "reactstrap";
import {Link} from "react-router-dom";
import {FaStar} from "react-icons/fa";
import React, {useContext, useState} from "react";
import {UserContext} from "./UserContext";
import axios from "axios";


export default function RateComment() {

    const {user, setUser} = useContext(UserContext);
    const[rating, setRating] = useState(null);
    const[hover, setHover] = useState(null);
    const[comment, setComment] = useState(null);

    function handleChange(e) {
        let comm = e.target.value;
        setComment(comm);
        console.log(comm);
    }

    function submit(e) {
        e.preventDefault();
        axios.post("http://localhost:8080/rateComment", {
            userid: user.id,
            movieid: user.movieid,
            comment: comment,
            rating: rating
        }).then(res => {
            console.log(res.data)
        })
    }

    return(
        <div style={{width: 800, marginTop: 20,marginLeft: 100}}>
            <h2>Rate and Comment</h2>
            <Form onSubmit={(e) => submit(e)}>
                <label style={{fontSize: "18px"}}>Rating</label>
                <div>
                    {[...Array(10)].map((star, i) => {
                        const ratingValue = i + 1;
                        return <label>
                            <input
                                type="radio"
                                name="rating"
                                value={ratingValue}
                                onClick={() => setRating(ratingValue)}
                                style={{display: "none"}}
                            />
                            <FaStar
                                style={{cursor: "pointer", transition: "color 200ms"}}
                                color={ratingValue <= (hover || rating) ? "#ffc107" : "#e4e5e9"}
                                size={75}
                                onMouseEnter={() => setHover(ratingValue)}
                                onMouseLeave={() => setHover(null)}
                            />
                        </label>
                    })}
                </div>
                <FormGroup className="mb-2 me-sm-2 mb-sm-0" style={{marginTop: "10px"}}>
                    <Label
                        style={{fontSize: "18px"}}
                        className="me-sm-2"
                        for="comment"
                    >
                        Comment
                    </Label>
                    <Input
                        id="comment"
                        name="comment"
                        type="textarea"
                        value={comment}
                        onChange={(e) => handleChange(e)}
                    />
                </FormGroup>
                    <Button type="submit" style={{marginTop: "10px"}}>
                        Submit
                    </Button>
            </Form>
        </div>
    )
}