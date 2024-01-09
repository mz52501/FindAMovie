import {Button, Card, CardBody, CardImg, CardSubtitle, CardTitle} from "reactstrap";
import {AiFillStar} from "react-icons/ai";
import {BsInfoSquare, BsInfoCircle} from "react-icons/bs";
import {Link} from "react-router-dom"

function MovieCard(props) {
    return(
    <Card style={{width:300, height: 620}}>
        <CardImg
            alt="Movie img"
            src={props.image}
            top
            style={{height: "75%", objectFit: "cover"}}
        />
        <CardBody >
            <div style={{display: "flex", alignItems: "center"}}>
            <AiFillStar size={24} style={{color: "#efcc00"}}/> {props.avgRating === 0 ? <h5 style={{paddingTop: "6px", paddingLeft: "5px"}}>Not rated</h5> : <h5 style={{paddingTop: "6px", paddingLeft: "5px"}}>{props.avgRating}</h5> }
            </div>
            <div style={{height: "40%"}}>
            <CardTitle tag="h5">
                {props.title}
            </CardTitle>
            </div>
            <Link to={props.linkToMovie}>
            <div style={{display: "flex", alignItems: "flex-end", justifyContent: "center", backgroundColor: "#D3D3D3", border: "solid #D3D3D3 1px", borderRadius: "5px"}}>
            <BsInfoCircle size={30} style={{color: "#4169E1"}}/>
            </div>
            </Link>
        </CardBody>
    </Card>
    )
}
export default MovieCard;