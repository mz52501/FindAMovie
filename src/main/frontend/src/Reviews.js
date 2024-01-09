import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import axios from "axios";
import {ListGroup, ListGroupItem, ListGroupItemHeading, ListGroupItemText} from "reactstrap";
import {AiFillStar} from "react-icons/ai";

export default function Reviews() {

    const[movie, setMovie] = useState([]);
    const[reviews, setReviews] = useState([]);

    const {id} = useParams();

    const fetchReviews = () => {
        axios.get(`http://localhost:8080/reviews${id}`).then(res => {
            setMovie(res.data);
            setReviews(res.data.array)
        });
    };

    useEffect(() => {
        fetchReviews();
    }, []);

    return(
        <div style={{display: "flex", justifyContent: "center", backgroundColor: "#D3D3D3", minHeight: "100vh"}}>
        <div style={{width: "60%"}}>
            <ListGroup>
                <ListGroupItem>
                    <ListGroupItemHeading>
                        <h2>The reviews of the movie: {movie.title}</h2>
                    </ListGroupItemHeading>
                </ListGroupItem>
            </ListGroup>
            {reviews.map(review => (
        <ListGroup>
            <ListGroupItem>
                <ListGroupItemHeading style={{display: "flex"}}>
                    <AiFillStar size={24} style={{color: "#efcc00", marginTop: "10px"}}/>
                    <h4 style={{paddingTop: "6px", paddingLeft: "5px"}}>{review.rating}/10</h4>
                </ListGroupItemHeading>
                <ListGroupItemText>
                    {review.comment}
                </ListGroupItemText>
                <h5>User: {review.username}</h5>
            </ListGroupItem>
        </ListGroup>
            ))}
        </div>
        </div>
    )
}