import React, {useEffect, useState} from "react";
import axios from "axios";
import {useParams, Link} from "react-router-dom";
import {Button} from "reactstrap";

function Director() {

    const [director, setDirector] = useState([]);
    const [movies, setMovies] = useState([]);
    const {id} = useParams();

    const fetchDirector = () => {
        axios.get(`http://localhost:8080/director${id}`).then(res => {
            setDirector(res.data);
            setMovies(res.data.array);
        });
    };

    useEffect(() => {
        fetchDirector();
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
                    <h2 style={{color: "#FFD700"}}>{director.name} {director.surname}</h2>
                    <img src={director.image} style={{objectFit: "cover", maxWidth: "100%", borderRadius: "2%"}} />
                </div>
                <div style={{padding: "40px 0px 0px 50px"}}>
                        <h3 style={{color: "#FFD700"}}>Movies {director.name} {director.surname} directed: </h3>
                        {movies.map(movie => (
                            <Link to={"/movie" + movie.id} className="movie">
                                <p style={{marginLeft: "40px",fontSize: "20px", color: "#FFD700", fontWeight: 700}}>{movie.title}</p>
                            </Link>
                        ))}
                    <Button onClick={(e) => handleClick(e)} style={{backgroundColor: "#FFD700", color: "#202020", marginTop: "40px"}}>Biography</Button>
                    <div style={{display: "none"}} id="biography"><p style={{width: "950px", marginTop: "30px", color: "white"}}>{director.description}</p></div>
                </div>
            </div>
        </div>
    )
}

export default Director;