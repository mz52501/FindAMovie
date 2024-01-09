import './App.css';
import Admin from "./Admin";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Login from "./Login";
import Home from "./Home";
import Registration from "./Registration";
import Genres from "./Genres";
import AddMovie from "./AddMovie";
import Actors from "./Actors";
import MovieCard from "./MovieCard";
import CardSlider2 from "./CardSlider2";
import Actor from "./Actor";
import Movie from "./Movie";
import Reviews from "./Reviews";
import TheBestMovie from "./TheBestMovie";
import Search from "./Search";
import Director from "./Director";
import Error from "./Error";
import AddActor from "./AddActor";
import AddDirector from "./AddDirector";
import ConnectMovieActors from "./ConnectMovieActors";
import ConnectMovieDirector from "./ConnectMovieDirector";
import ConnectMovieGenres from "./ConnectMovieGenres";
import {UserContext} from "./UserContext";
import {useEffect, useMemo, useState} from "react";
import Directors from "./Directors";
import RateComment from "./RateComment";

function App() {

    const[user, setUser] = useState(null);
    const value = useMemo( () => ({user, setUser}), [user, setUser])

    useEffect(() => {
        document.title = "FindAMovie"
    }, [])

  return (
      <Router>
          <UserContext.Provider value={value}>
        <Routes>
            <Route path="/rateComment" element={<RateComment />} />
            <Route path="/connectMovieGenre" element={<ConnectMovieGenres />} />
            <Route path="/connectMovieDirector" element={<ConnectMovieDirector />} />
            <Route path="/connectMovieActor" element={<ConnectMovieActors />} />
            <Route path="/director:id" element={<Director />} />
            <Route path="allDirectors" element={<Directors />} />
            <Route path="/search" element={<Search />} />
            <Route path="/addActor" element={<AddActor />} />
            <Route path="/addDirector" element={<AddDirector />} />
            <Route path="/thebest" element={<TheBestMovie />} />
            <Route path="/reviews:id" element={<Reviews />} />
            <Route path="/actor:id" element={<Actor />} />
            <Route path="/frontpage" element={<CardSlider2 />} />
            <Route path="/card" element={<MovieCard />} />
            <Route path="/allActors" element={<Actors />} />
            <Route path="/addMovie" element={<AddMovie />} />
            <Route path="/admin" element={<Admin user={user} />} />
            <Route path="/genres" element={<Genres />} />
            <Route path="/movie:id" element={<Movie />} />
            <Route path="/login" element={<Login />} />
            <Route path="/registration" element={<Registration />} />
            <Route path="/" element={<Home />} />
            <Route path="*" element={<Error />} />
        </Routes>
      </UserContext.Provider>
      </Router>
  );
}

export default App;
