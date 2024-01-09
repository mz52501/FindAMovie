
import {Button, Form, FormGroup, Input, Label} from "reactstrap";
import {useEffect, useState} from "react";
import axios from "axios";

export default function ConnectMovieActors() {

    const[allActors, setAllActors] = useState([]);
    const[allMovies, setAllMovies] = useState([]);
    const[movieid, setMovieid] = useState(0);
    const[actorid, setActorid] = useState(0);
    const[role, setRole] = useState("");

    const fetchActorsMovies = () => {
        axios.get("http://localhost:8080/connectMovieActor").then(res => {
            setAllMovies(res.data.movies);
            setAllActors(res.data.actors);
        });
    };

    useEffect(() => {
        fetchActorsMovies();
    }, []);

    allActors.sort(function(a, b) {
        const nameA = a.name.toUpperCase() + " " + a.surname.toUpperCase(); // ignore upper and lowercase
        const nameB = b.name.toUpperCase() + " " + b.surname.toUpperCase(); // ignore upper and lowercase
        if (nameA < nameB) {
            return -1;
        }
        if (nameA > nameB) {
            return 1;
        }
    });

    allMovies.sort(function (a, b) {
        const titleA = a.title.toUpperCase();
        const titleB = b.title.toUpperCase();
        if (titleA < titleB) {
            return -1;
        }
        if (titleA > titleB) {
            return 1;
        }
    });

    function handleChangeMovies(e) {
        const movie = parseInt(e.target.value, 10);
        console.log(movie);
        setMovieid(movie);
    }

    function handleChangeActor(e) {
        const actor = parseInt(e.target.value, 10);
        console.log(actor);
        setActorid(actor);
    }

    function handleChangeRole(e) {
        const role1 = e.target.value;
        console.log(role1);
        setRole(role1);
    }


    function submit(e) {
        e.preventDefault();
        axios.post("http://localhost:8080/connectMovieActor", {
            movieid: movieid,
            personid: actorid,
            role: role
        }).then(res => {
            console.log(res.data)
            window.location.href = '/admin';
        })
    }

    return(
        <div style={{width: 800, marginTop: 20,marginLeft: 100}}>
            <h2>Connect movie and actor</h2>
            <Form onSubmit={((e) => submit(e))}>
                <Label for="movies" style={{marginTop: "10px"}}>
                    Select movies
                </Label>
                <Input
                    id="movies"
                    name="movies"
                    type="select"
                    onClick={(e) => handleChangeMovies(e)}
                >
                    {allMovies.map(movie => (
                        <option value={movie.id}>{movie.title}</option>
                    ))}
                </Input>
                <Label for="actor" style={{marginTop: "10px"}}>
                    Select actor
                </Label>
                <Input
                    id="actor"
                    name="actor"
                    type="select"
                    onClick={(e) => handleChangeActor(e)}
                >
                    {allActors.map(actor => (
                        <option value={actor.id}>{actor.name + " " + actor.surname}</option>
                    ))}
                </Input>
                    <Label
                        className="me-sm-2"
                        for="role"
                        style={{marginTop: "10px"}}
                    >
                        Role
                    </Label>
                    <Input
                        id="role"
                        name="role"
                        type="role"
                        value={role}
                        onChange={(e) => handleChangeRole(e)}
                    />
                <Button style={{marginTop: "20px"}}>Submit</Button>
            </Form>
        </div>
    )
}