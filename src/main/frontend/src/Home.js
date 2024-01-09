import NavigatingBar from "./NavigatingBar";
import axios from "axios";
import {useContext, useEffect, useState} from "react";
import {UserContext} from "./UserContext";
import NavigatingBarLoggedIn from "./NavigatingBarLoggedIn";
import {BiCameraMovie} from "react-icons/bi"
import NavBarAdmin from "./NavBarAdmin";
import NavBarForAdmin from "./NavBarForAdmin";

function Home() {

    const{user, setUser} = useContext(UserContext);

    const[movie, setMovie] = useState([]);
    const[actors, setActors] = useState([]);
    const[directos, setDirectors] = useState([]);
    const[genres, setGenres] = useState([]);

    const fetchMovie = () => {
        axios.get("http://localhost:8080/home").then(res => {
            setMovie(res.data);
            setActors(res.data.actors);
            setDirectors(res.data.directors);
            setGenres(res.data.genres);
        });
    };

    useEffect(() => {
        fetchMovie();
    }, []);

    const date = new Date(movie.relDate);

    return(
        <div style={{height: "100vh", width: "100vw", backgroundColor: "#202020"}}>
            {
               user ? (
                   user.role === "ROLE_USER" ? (
                       <NavigatingBarLoggedIn />
                   ) : (
                       <NavBarForAdmin />
                   )
                   ) : (
                       <NavigatingBar/>
               )}
            <div style={{width: "65%", height: "92%", marginTop: "7px", display: "flex"}}>
                <div style={{display: "flex", flexDirection: "column", alignItems: "center", marginTop: "200px", minWidth:"53%"}}>
                    <h2 style={{margin: " 10px 10px 10px 10px", color: "#FFD700"}}>{movie.title}</h2>
                    <p style={{margin: " 15px 10px 10px 10px", color: "white", fontSize: "20px"}}>Release date: {date.toLocaleDateString()}</p>
                    <div style={{display: "flex"}}>
                        <BiCameraMovie size={50} style={{margin: "10px 0px 15px 0px", color: "#FFD700"}}/>
                        <h3 style={{margin: " 20px 10px 20px 20px", color: "#FFD700"}}>Coming soon</h3>
                    </div>
                </div>
                <img src={movie.image} style={{maxHeight: "100%", maxWidth: "100%", borderRadius: "2%"}}/>
                <div style={{minWidth: "52%", marginLeft: "15px", display: "flex", flexDirection: "column", alignItems: "center", marginTop: "200px"}}>
                    <h3 style={{color:"#FFD700"}}>Short description</h3>
                    <p style={{color: "white", fontSize:"21px", width: "430px"}}>{movie.description}</p>
                    <h4 style={{color: "#FFD700"}}>Stars:</h4>
                    {actors.map(actor => (
                        <h5 style={{color: "white"}}>{actor.name} {actor.surname}</h5>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default Home;