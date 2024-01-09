import {Link} from "react-router-dom";

function NavigatingBar() {
    return(
        <nav className="navbar navbar-expand-lg navbar-light" style={{height: "46px", backgroundColor: "#FFD700"}}>
            <div className="container-fluid">
                <Link className="navbar-brand" to="/frontpage">Movies</Link>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <Link className="navbar-brand" to="/allActors">Actors</Link>
                    <Link className="navbar-brand" to="/allDirectors">Directors</Link>
                    <Link className="navbar-brand" to="/thebest">TheBest</Link>
                    <Link className="navbar-brand" to="/registration">Sign in</Link>
                    <Link className="navbar-brand" to="/login">Log in</Link>
                </div>
            </div>
        </nav>
    )
}
export default NavigatingBar;