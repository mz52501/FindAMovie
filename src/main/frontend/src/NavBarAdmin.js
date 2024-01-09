function NavBarAdmin() {
    return(
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <a className="navbar-brand" href="/addMovie">Add movie</a>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <a className="navbar-brand" href="/addActor">Add actor</a>
                    <a className="navbar-brand" href="/addDirector">Add director</a>
                    <a className="navbar-brand" href="/connectMovieActor">Set movie actor</a>
                    <a className="navbar-brand" href="/connectMovieDirector">Set movie director</a>
                    <a className="navbar-brand" href="/connectMovieGenre">Set movie genres</a>
                </div>
            </div>
        </nav>
    )
}
export default NavBarAdmin;