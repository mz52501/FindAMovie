import {Button, Form, Input, Label} from "reactstrap";
import {useEffect, useState} from "react";
import axios from "axios";

export default function ConnectMovieDirector() {

    const[allDirectors, setAllDirectors] = useState([]);
    const[allMovies, setAllMovies] = useState([]);
    const[movieid, setMovieid] = useState(0);
    const[directorid, setDidrectorid] = useState(0);

    const fetchActorsMovies = () => {
        axios.get("http://localhost:8080/connectMovieDirector").then(res => {
            setAllMovies(res.data.movies);
            setAllDirectors(res.data.directors);
        });
    };

    useEffect(() => {
        fetchActorsMovies();
    }, []);

    allDirectors.sort(function(a, b) {
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

    function handleChangeDirector(e) {
        const director = parseInt(e.target.value, 10);
        console.log(director);
        setDidrectorid(director);
    }


    function submit(e) {
        e.preventDefault();
        axios.post("http://localhost:8080/connectMovieDirector", {
            movieid: movieid,
            personid: directorid
        }).then(res => {
            console.log(res.data)
            window.location.href = '/admin';
        })
    }

    return(
        <div style={{width: 800, marginTop: 20,marginLeft: 100}}>
            <h2>Connect movie and director</h2>
            <Form onSubmit={((e) => submit(e))}>
                <Label for="movies">
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
                <Label for="director">
                    Select director
                </Label>
                <Input
                    id="director"
                    name="director"
                    type="select"
                    onClick={(e) => handleChangeDirector(e)}
                >
                    {allDirectors.map(director => (
                        <option value={director.id}>{director.name + " " + director.surname}</option>
                    ))}
                </Input>
                <Button style={{marginTop: "20px"}}>Submit</Button>
            </Form>
        </div>
    )
}