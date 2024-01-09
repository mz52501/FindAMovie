import {useEffect, useState} from "react";
import axios from "axios";
import {Button, Form, Input, Label} from "reactstrap";

export default function ConnectMovieGenres() {

    const[genres, setGenres] = useState([]);
    const[allMovies, setAllMovies] = useState([]);
    const[movieid, setMovieid] = useState(0);
    const[genresid, setGenresid] = useState([]);

    const fetchGenresMovies = () => {
        axios.get("http://localhost:8080/connectMovieGenre").then(res => {
            setAllMovies(res.data.movies);
            setGenres(res.data.genres);
        });
    };

    useEffect(() => {
        fetchGenresMovies();
    }, []);

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

    genres.sort(function (a, b) {
        const genreNameA = a.genreName.toUpperCase();
        const genreNameB = b.genreName.toUpperCase();
        if (genreNameA < genreNameB) {
            return -1;
        }
        if (genreNameA > genreNameB) {
            return 1;
        }
    });

    function handleChangeMovies(e) {
        const movie = parseInt(e.target.value, 10);
        console.log(movie);
        setMovieid(movie);
    }

    function handleChangeGenres(e) {
        let value = Array.from(e.target.selectedOptions, option => parseInt(option.value, 10));
        console.log(value);
        setGenresid(value);
    }

    function submit(e) {
        e.preventDefault();
        axios.post("http://localhost:8080/connectMovieGenre", {
            movieid: movieid,
            genresid: genresid,
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
                <div style={{display: "flex", flexDirection: "column"}}>
                <Label for="genres" style={{marginTop: "10px"}}>
                    Select genres
                </Label>
                <select name="selectOptions" size="5" multiple={true} onChange={(e) => handleChangeGenres(e)}>
                    {genres.map(genre => (
                        <option value={genre.id}>{genre.genreName}</option>
                    ))}
                </select>
                </div>
                <Button style={{marginTop: "20px"}}>Submit</Button>
            </Form>
        </div>
    )
}