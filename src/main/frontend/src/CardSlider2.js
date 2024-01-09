import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Scrollbar, A11y } from 'swiper';
import "swiper/swiper.scss"
import "swiper/components/navigation/navigation.scss"
import "swiper/components/pagination/pagination.scss"
import "swiper/components/scrollbar/scrollbar.scss"
import {useEffect, useState} from "react";
import axios from "axios";
import MovieCard from "./MovieCard";
import style from "./CardSlider2.module.css"
import SearchBar from "./SearchBar";
import {Link} from "react-router-dom"
SwiperCore.use([Navigation, Pagination, Scrollbar, A11y]);
export default function CardSlider2() {

    const[movies,setMovies] = useState([]);

    const fetchMovies = () => {
            axios.get("http://localhost:8080/movies").then(res => {
                setMovies(res.data);
            });
    };

    useEffect(() => {
        fetchMovies();
    }, []);

    return (
        <div style={{display: "flex", flexDirection: "column", backgroundColor: "#202020", maxHeight: "100vh"}}>
            <div style={{display: "flex", alignItems: "flex-start", justifyContent: "center"}}>
            <SearchBar />
            </div>
        <div className={style.sec}>
        <Swiper
            spaceBetween={20}
            slidesPerView={4}
            navigation
            pagination={{ clickable: true }}
            /*scrollbar={{ draggable: true }}*/
            onSlideChange={() => console.log('slide change')}
        >
            {movies.map((movie, index) => (
                <SwiperSlide key={index} className={style.outer}>
                    <MovieCard
                        title={movie.title}
                        image={movie.image}
                        avgRating={movie.avgRating}
                        linkToMovie={"/movie" + movie.id}
                    />
                </SwiperSlide>
            ))}
        </Swiper>
        </div>
        </div>
    );

}