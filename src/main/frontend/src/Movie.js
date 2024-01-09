import {useContext, useEffect, useState} from "react";
import {useParams, Link} from "react-router-dom";
import axios from "axios";
import {
    Button,
    Card,
    CardBody,
    CardImg,
    CardSubtitle,
    CardTitle, ListGroup,
    ListGroupItem,
    ListGroupItemHeading
} from "reactstrap";
import {HiCursorClick} from "react-icons/hi";
import {UserContext} from "./UserContext";
import "./Movie.css";
import {FiStar} from "react-icons/fi";

export default function Movie() {

    const {user, setUser} = useContext(UserContext);
    const[movie, setMovie] = useState([]);
    const[actors, setActors] = useState([]);
    const[directors, setDirectors] = useState([]);
    const[genres, setGenres] = useState([]);
    const[reviews, setReviews] = useState([]);
    const {id} = useParams();

    const fetchMovie = () => {
        axios.get(`http://localhost:8080/movie${id}`).then(res => {
            setMovie(res.data);
            setActors(res.data.actors)
            setDirectors(res.data.directors)
            setGenres(res.data.genres)
            setReviews(res.data.reviews)
        });
    };

    useEffect(() => {
        fetchMovie();
    }, []);

    actors.sort(function (a, b) {
        const idA = a.id;
        const idB = b.id;
        if(idA > idB) {
            return 1;
        }
        if(idA < idB) {
            return -1;
        }
    });

    function check() {
        if(user != null) {
            setUser({
                username: user.username,
                password: user.password,
                id: user.id,
                role: user.role,
                movieid: id
            })
        }
    }


    let check_var = false;
    if(user != null) {
        for (const review of reviews) {
            if (review.userid === user.id) {
                check_var = true;
                break;
            }
        }
    }


    return(
        <div style={{display: "flex", justifyContent: "center", backgroundColor: "#202020", minHeight: "100vh"}}>
            <div style={{width: "60%", margin: "auto"}}>
                <ListGroup>
                    <ListGroupItem style={{height: "100px", backgroundColor: "#FAFAD2"}}>
                        <ListGroupItemHeading>
                            <div style={{display: "flex"}}>
                                <h2 style={{marginRight: "40px"}}>{movie.title}</h2>
                                <div style={{display: "flex", maxWidth: "100%"}}>
                                    {user ? ( check_var ? ( <p></p> ) : (
                                            <Link
                                                to="/rateComment"
                                                style={{textDecoration: "none", color: "#202020"}}
                                                onMouseEnter={() => check()}
                                            >
                                                <div style={{display: "flex"}}>
                                                    <FiStar
                                                        size={50}
                                                        /*onMouseEnter={() => check()}*/
                                                    />
                                                    <p style={{
                                                        marginLeft: "5px",
                                                        marginTop: "12px",
                                                        fontWeight: "900",
                                                        fontSize: "20px"
                                                    }}>Rate</p>
                                                </div>
                                            </Link>
                                        )
                                        ) : (
                                        <p></p>
                                    )
                                    }
                                </div>
                            </div>
                            <div style={{display: "flex"}}>
                                {movie.duration === null ? <p></p> : <p>Duration: {movie.hours}h {movie.minutes}m</p>}
                            <p style={{marginLeft: "15px"}}>Release date: {(new Date(movie.relDate)).toLocaleDateString()}</p>
                            </div>
                        </ListGroupItemHeading>
                    </ListGroupItem>
                </ListGroup>
                <ListGroup>
                    <ListGroupItem style={{backgroundColor: "#FAFAD2"}}>
                        <ListGroupItemHeading style={{display: "flex", height: "500px"}}>
                            <img src={movie.image} style={{height: "100%"}} />
                            <div style={{marginLeft: "20px", display: "flex", flexDirection: "column"}}>
                                <h3 style={{marginBottom: "20px"}}>Actors who played in the movie: </h3>
                            {actors.map(actor => (
                                <div style={{display: "flex"}}>
                                    <div style={{marginBottom: "20px"}}>
                                    <Link to={"/actor" + actor.id}
                                    style={{textDecoration: "none"}}>
                                        <li className="actor">{actor.name} {actor.surname}</li>
                                    </Link>
                                    </div>
                                    <p style={{fontSize: "18px", marginLeft: "5px"}}> as {actor.role}</p>
                                </div>
                            ))}
                                {directors.length === 1 ?
                                    <h3>Director of the movie:</h3> : <h3>Directors of the movie:</h3>
                                }
                                {directors.map(director => (
                                    <div style={{marginTop: "11px"}}>
                                        <Link to={"/director" + director.id}
                                           style={{textDecoration: "none"}}>
                                            <li className="actor">{director.name} {director.surname}</li>
                                        </Link>
                                    </div>
                                ))}
                                <div style={{marginTop: "18px", maxWidth: "500px"}}>
                                    <h3>Description</h3>
                                    <p style={{fontSize: "18px", lineHeight: "1.6"}}>{movie.description}</p>
                                </div>
                            </div>
                        </ListGroupItemHeading>
                    </ListGroupItem>
                </ListGroup>
                <ListGroup>
                    <ListGroupItem style={{maxHeight: "80px", backgroundColor: "#FAFAD2"}}>
                        <ListGroupItemHeading style={{display: "flex", alignItems: "center", width: "100%"}}>
                            <div style={{display: "flex", alignItems: "baseline", width: "100%"}}>
                                <ul style={{marginRight: "30px"}}>Genres: </ul>
                                {genres.map(genre => (
                                    <li style={{marginRight: "30px", fontSize: "18px"}}>{genre.genreName}</li>
                                ))}
                            </div>
                            <div style={{maxWidth: "100%", display: "flex", justifyContent: "flex-end"}}>
                            <div style={{width: "240px"}}>
                                <Link to={"/reviews" + movie.id} style={{textDecoration: "none"}}>
                                    <div style={{display: "flex", alignItems: "center"}}>
                                        <h5 className="click">Reviews given by users</h5>
                                        <HiCursorClick className="click" />
                                    </div>
                                </Link>
                            </div>
                            </div>
                        </ListGroupItemHeading>
                    </ListGroupItem>
                </ListGroup>
            </div>
        </div>
    )

}