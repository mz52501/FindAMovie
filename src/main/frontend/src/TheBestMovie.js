import {ListGroup, ListGroupItem, ListGroupItemHeading, ListGroupItemText} from "reactstrap";
import {AiFillStar} from "react-icons/ai";
import {useEffect, useState} from "react";
import axios from "axios";

export default function TheBestMovie() {

    const[theBestMovies, setTheBestMovies] = useState([]);

    const fetchMovies = () => {
        axios.get("http://localhost:8080/thebest").then(res => {
            setTheBestMovies(res.data);
        });
    };

    useEffect(() => {
        fetchMovies();
    }, []);

    return (
        <div style={{display: "flex", justifyContent: "center", backgroundColor: "#202020", minHeight: "100vh"}}>
            <div style={{width: "60%"}}>
                <ListGroup>
                <ListGroupItem>
                    <ListGroupItemHeading>
                        <h2>The best rated movies</h2>
                    </ListGroupItemHeading>
                </ListGroupItem>
                </ListGroup>
                {theBestMovies.map(theBestMovie => (
                    <ListGroup>
                        <ListGroupItem style={{display: "flex", alignItems: "center"}}>
                            <div style={{maxWidth: "120px"}}>
                                <img src={theBestMovie.image} style={{objectFit: "cover",width: "100%",borderRadius: "3%"}} />
                            </div>
                            <div style={{paddingLeft: "15px"}}>
                            <ListGroupItemHeading>
                                <div style={{display: "flex"}}>
                                <AiFillStar size={24} style={{color: "#efcc00", marginTop: "10px"}}/>
                                <h4 style={{paddingTop: "6px", paddingLeft: "5px"}}>{theBestMovie.avgRating}</h4>
                                </div>
                            </ListGroupItemHeading>
                            <ListGroupItemText>
                                <h4>{theBestMovie.title}</h4>
                                {theBestMovie.description}
                            </ListGroupItemText>
                            </div>
                        </ListGroupItem>
                    </ListGroup>
                ))}
            </div>
        </div>
    )
}