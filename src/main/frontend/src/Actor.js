import React, {useEffect, useState} from "react";
import axios from "axios";
import {Button} from "reactstrap";
import {useParams, Link} from "react-router-dom";
import "./Movie.css";

function Actor() {

    const [actor, setActor] = useState([]);
    const [movies, setMovies] = useState([]);
    const {id} = useParams();

    const fetchActor = () => {
        axios.get(`http://localhost:8080/actor${id}`).then(res => {
            setActor(res.data);
            setMovies(res.data.array)
        });
    };

    useEffect(() => {
        fetchActor();
    }, []);

    let a = 1;
    function handleClick(e) {
        if(a === 1) {
            document.getElementById("biography").style.display="inline";
            return a = 0;
        } else {
            document.getElementById("biography").style.display="none";
            return a = 1;
        }
    }

    return (
        <div style={{minHeight: "100vh", width: "100vw", background: "#202020"}}>
        <div style={{display: "flex", padding: "20px"}}>
            <div style={{width: "460px", display: "flex", flexDirection: "column", alignItems: "center"}}>
                <h2 style={{color: "#FFD700"}}>{actor.name} {actor.surname}</h2>
                <img src={actor.image} style={{objectFit: "cover", maxHeight: "600px", maxWidth: "460px", borderRadius: "2%"}} />
            </div>
            <div style={{padding: "40px 0px 0px 50px"}}>
                <h3 style={{color: "#FFD700", marginBottom: "20px"}}>Movies {actor.name} {actor.surname} played in: </h3>
                {movies.map(movie => (
                    <Link to={"/movie" + movie.id} className="movie">
                        <p style={{marginLeft: "40px",fontSize: "20px", color: "#FFD700", fontWeight: 700}}>{movie.title}</p>
                    </Link>
                ))}
                <Button onClick={(e) => handleClick(e)} style={{backgroundColor: "#FFD700", color: "#202020", marginTop: "40px"}}>Biography</Button>
                <div style={{display: "none"}} id="biography"><p style={{width: "950px", marginTop: "30px", color: "white"}}>{actor.description}</p></div>
            </div>
        </div>
        </div>
    )
}

export default Actor;