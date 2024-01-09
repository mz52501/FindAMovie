import {Link} from "react-router-dom"
import {Button} from "reactstrap"
import {useContext} from "react";
import {UserContext} from "./UserContext";

function NavBarForAdmin() {

    const{user, setUser} = useContext(UserContext);

    return(
        <nav className="navbar navbar-expand-lg navbar-light" style={{height: "46px", backgroundColor: "#FFD700"}}>
            <div className="container-fluid">
                <Link className="navbar-brand" to="/frontpage">Movies</Link>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <Link className="navbar-brand" to="/allActors">Actors</Link>
                    <Link className="navbar-brand" to="/allDirectors">Directors</Link>
                    <Link className="navbar-brand" to="/thebest">TheBest</Link>
                    <Link className="navbar-brand" to="/admin">Admin</Link>
                    <Button onClick={() => {
                        setUser(null);
                    }} style={{border: "none", backgroundColor: "#FFD700"}}><Link className="navbar-brand" to="/">Log out</Link></Button>
                </div>
            </div>
        </nav>
    )
}
export default NavBarForAdmin;