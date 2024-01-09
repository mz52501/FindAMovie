import "./SearchBar.css"
import { GoSearch } from "react-icons/go";
import { MdClose } from "react-icons/md";
import React, {useEffect, useState} from "react";
import axios from "axios";

export default function SearchBar() {

    const[movies,setMovies] = useState([]);
    const[filteredData, setFilteredData] = useState([]);
    const[wordEntered, setWordEntered] = useState("");

    const fetchMovies = () => {
        axios.get("http://localhost:8080/movies").then(res => {
            setMovies(res.data);
        });
    };

    useEffect(() => {
        fetchMovies();
    }, []);

    const handleFilter = (event) => {
        const searchWord = event.target.value;
        setWordEntered(searchWord);
        const newFilter = movies.filter((value) => {
            return value.title.toLowerCase().includes(searchWord.toLowerCase());
        });
        if(searchWord === "") {
            setFilteredData([]);
        } else {
            setFilteredData(newFilter);
        }
    }

    const clearInput = () => {
        setFilteredData([]);
        setWordEntered("");
    }

    return (
        <div className="search">
            <div className="searchInputs">
                <input
                    type="text"
                    value={wordEntered}
                    placeholder="Enter a movie title..."
                    onChange={handleFilter}
                />
                <div className="searchIcon">
                    {wordEntered.length === 0 ?(
                        <GoSearch />
                        ) : ( <MdClose className="clearBtn" onClick={clearInput} />
                    )}
                </div>
            </div>
            { filteredData.length != 0 && (
            <div className="dataResult">
                {filteredData.slice(0, 15).map((movie, key) => {
                    return (
                    <a style={{textDecoration: "none"}} href={"movie" + movie.id} className="dataItem">
                        <div style={{maxWidth: "70px"}}>
                        <img src={movie.image} style={{marginLeft: "3px",width: "90%", objectFit: "cover"}}/>
                        </div>
                        <p>{movie.title}</p>
                    </a>
                    );
                })}
            </div>
            )}
        </div>
    );
}