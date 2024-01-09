import {useState, useEffect} from "react";
import axios from "axios";

function Genres() {

    const[genres, setGenres] = useState([]);

    const fetchGenres = () => {
        axios.get("http://localhost:8080/genres").then(res => {
            setGenres(res.data);
        });
    };

    useEffect(() => {
        fetchGenres();
    }, []);

    return(
        <div>
            {genres.map((genre) => (
                <div>
                    <h1>ID---->: {genre.id}</h1>
                    <h1>Genre's name---->: {genre.genrename}</h1>
                    <img src="shawshank.jpg" />
                </div>
            ))}
        </div>
    );
}

export default Genres;