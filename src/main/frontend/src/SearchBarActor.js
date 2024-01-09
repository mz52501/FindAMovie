import "./SearchBar.css"
import { GoSearch } from "react-icons/go";
import { MdClose } from "react-icons/md";
import React, {useEffect, useState} from "react";
import axios from "axios";

export default function SearchBarActor() {

    const[actors,setActors] = useState([]);
    const[filteredData, setFilteredData] = useState([]);
    const[wordEntered, setWordEntered] = useState("");

    const fetchActors = () => {
        axios.get("http://localhost:8080/actors").then(res => {
            setActors(res.data);
        });
    };

    useEffect(() => {
        fetchActors();
    }, []);

    const handleFilter = (event) => {
        const searchWord = event.target.value;
        setWordEntered(searchWord);
        const newFilter = actors.filter((value) => {
            return (value.name.toLowerCase() + " " + value.surname.toLowerCase()).includes(searchWord.toLowerCase());
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
                    placeholder="Enter an actor..."
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
                    {filteredData.slice(0, 15).map((actor, key) => {
                        return (
                            <a style={{textDecoration: "none"}} href={"actor" + actor.id} className="dataItem">
                                <div style={{maxWidth: "70px"}}>
                                    <img src={actor.image} style={{marginLeft: "5px",width: "65px", height: "80px", objectFit: "cover"}}/>
                                </div>
                                <p>{actor.name} {actor.surname}</p>
                            </a>
                        );
                    })}
                </div>
            )}
        </div>
    );
}