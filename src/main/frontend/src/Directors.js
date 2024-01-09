import {useEffect, useState} from "react";
import axios from "axios";
import SwiperCore, { Navigation, Pagination, Scrollbar, A11y } from 'swiper';
import "swiper/swiper.scss"
import "swiper/components/navigation/navigation.scss"
import "swiper/components/pagination/pagination.scss"
import "swiper/components/scrollbar/scrollbar.scss"
import ActorCard from "./ActorCard";
import { Swiper, SwiperSlide } from 'swiper/react';
import SearchBarActor from "./SearchBarActor";
import style from "./Actors.module.css"
import SearchBarDirector from "./SearchBarDirector";
SwiperCore.use([Navigation, Pagination, Scrollbar, A11y]);

function Directors() {

    const[persons, setPersons] = useState([]);

    const fetchPersons = () => {
        axios.get("http://localhost:8080/directors").then(res => {
            setPersons(res.data);
        });
    };

    useEffect(() => {
        fetchPersons();
    }, []);

    return(
        <div style={{display: "flex", flexDirection: "column", backgroundColor: "#202020", maxHeight: "100vh"}}>
            <div style={{display: "flex", alignItems: "flex-start", justifyContent: "center"}}>
                <SearchBarDirector />
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
                    {persons.map((director, index) => (
                        <SwiperSlide key={index} className={style.outer}>
                            <ActorCard
                                title={director.name + " " + director.surname}
                                image={director.image}
                                linkToMovie={"/director" + director.id}
                            />
                        </SwiperSlide>
                    ))}
                </Swiper>
            </div>
        </div>
    )

}
export default Directors;