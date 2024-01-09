package FindAMovie.controllers;

import FindAMovie.DTO.*;
import FindAMovie.models.*;
import FindAMovie.reporsitories.*;
import FindAMovie.services.MovieService;
import FindAMovie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@RestController
public class FirstController {

    TypeRepository typeRepository;
    DirectRepository directRepository;
    ActRepository actRepository;
    UserService userService;
    MovieService movieService;
    MovieRepository movieRepository;
    UserRepository userRepository;
    GenreRepository genreRepository;
    PersonRepository personRepository;
    ReviewRepository reviewRepository;
    ActorRepository actorRepository;
    DirectorRepository directorRepository;

    @Autowired
    public FirstController(TypeRepository typeRepository, DirectRepository directRepository, ActRepository actRepository, DirectorRepository directorRepository, ActorRepository actorRepository, UserService userService, ReviewRepository reviewRepository, PersonRepository personRepository, MovieService  movieService, MovieRepository movieRepository, UserRepository userRepository, GenreRepository genreRepository) {
        this.typeRepository = typeRepository;
        this.directRepository = directRepository;
        this.actRepository = actRepository;
        this.directorRepository = directorRepository;
        this.actorRepository = actorRepository;
        this.userService = userService;
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String showString() {return "Hello Unknown intruder";}

    @GetMapping("/thebest")
    public List<MovieDTO> showTheBestMovies() {
        return movieService.getTopMovies();
    }

    @GetMapping("/reviews{id}")
    public ReviewMovieDTO showReviewsForMovie(@PathVariable Integer id) {
        return movieService.getReviews(id);
    }

    @GetMapping("/movies")
    public List<MovieDTO> showMovies() {
        return movieService.getMovieDTO();
    }

    @GetMapping("/genres")
    public List<Genre> showGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/actors")
    public List<PersonDTO> showActors() {
        return movieService.getActors();
    }

    @GetMapping("/directors")
    public List<PersonDTO> showDirectors() { return movieService.getDirectors(); }

    @PostMapping("/registration")
    public void addUser(@RequestBody RegistrationDTO userDTO) {
        if(userRepository.existsByEmailOrUsername(userDTO.getEmail(), userDTO.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if(!userDTO.getPassword().equals(userDTO.getRepeatedPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole("ROLE_USER");
        userRepository.save(user);

    }

    @PostMapping("/addActor")
    public void addActor(@RequestBody AddActorDTO actor) {
        Person person = new Person();
        String text  = actor.getDescription();
        String cleanText = "";
        if(text.contains("'")) {
            cleanText = text.replaceAll("'", "''");
        }
        person.setName(actor.getName());
        person.setSurname(actor.getSurname());
        person.setDescription(cleanText);
        person.setImage(actor.getImage());
        personRepository.save(person);
        Person person1 = personRepository.getByImage(actor.getName().toLowerCase(Locale.ROOT) + "_" + actor.getSurname().toLowerCase(Locale.ROOT) + ".jpg");
        Actor actorToConnect = new Actor();
        actorToConnect.setPerson(person);
        actorRepository.save(actorToConnect);

    }

    @PostMapping("/addDirector")
    public void addDirector(@RequestBody AddActorDTO director) {
        Person person = new Person();
        String text  = director.getDescription();
        String cleanText = "";
        if(text.contains("'")) {
            cleanText = text.replaceAll("'", "''");
        }
        person.setName(director.getName());
        person.setSurname(director.getSurname());
        person.setDescription(cleanText);
        person.setImage(director.getImage());
        personRepository.save(person);
        //Person person1 = personRepository.getByImage(director.getName().toLowerCase(Locale.ROOT) + "_" + director.getSurname().toLowerCase(Locale.ROOT) + ".jpg");
        Director directorToConnect = new Director();
        directorToConnect.setPerson(person);
        directorRepository.save(directorToConnect);

    }

    @GetMapping("/actor{id}")
    public ActorMovieDTO getActorMovies(@PathVariable Integer id) {
        return movieService.getActor(id);
    }

    @GetMapping("/movie{id}")
    public MovieActorDTO getMovieActors(@PathVariable Integer id) {
        return movieService.getMovie(id);
    }

    @GetMapping("/director{id}")
    public ActorMovieDTO getDirectorMovies(@PathVariable Integer id) { return movieService.getDirector(id); }

    @GetMapping("/connectMovieActor")
    public ConnectMovieActorDTO getMovieAndActor() {
        return movieService.connectMovieActor();
    }

    @PostMapping("/connectMovieActor")
    public void connectMovieAndActor(@RequestBody ConnnectMovieActorFormDTO movAct) {
        Movie movie = movieRepository.getById(movAct.getMovieid());
        Actor actor = actorRepository.getById(movAct.getPersonid());
        Act actingPair = new Act();
        actingPair.setMovieid(movie);
        actingPair.setPersonid(actor);
        actingPair.setRole(movAct.getRole());
        ActId actId = new ActId();
        actId.setMovieid(movAct.getMovieid());
        actId.setPersonid(movAct.getPersonid());
        actingPair.setId(actId);
        actRepository.save(actingPair);
    }

    @GetMapping("/connectMovieDirector")
    public ConnectMovieDirectorDTO getMovieAndDirector() {
        return movieService.getMovieDirector();
    }

    @PostMapping("/connectMovieDirector")
    public void connectMovieAndDirector(@RequestBody ConnectMovieDirectorFormDTO movieDirector) {
        Movie movie = movieRepository.getById(movieDirector.getMovieid());
        Director director = directorRepository.getById(movieDirector.getPersonid());
        Direct directingPair = new Direct();
        directingPair.setMovieid(movie);
        directingPair.setPersonid(director);
        DirectId dirId = new DirectId();
        dirId.setMovieid(movieDirector.getMovieid());
        dirId.setPersonid(movieDirector.getPersonid());
        directingPair.setId(dirId);
        directRepository.save(directingPair);
    }

    @GetMapping("/connectMovieGenre")
    public ConnectMovieGenreDTO connectMovieAndGenres() {
        return movieService.getMovieGenres();
    }

    @PostMapping("/connectMovieGenre")
    public void connectMovieToGenres(@RequestBody ConnectMovieGenreFormDTO movieAndGenres) {
        Movie movie = movieRepository.getById(movieAndGenres.getMovieid());
        for(Integer id : movieAndGenres.getGenresid()) {
            Genre genre = genreRepository.getById(id);
            Type movieGenre = new Type();
            movieGenre.setGenreid(genre);
            movieGenre.setMovieid(movie);
            TypeId typeId = new TypeId();
            typeId.setGenreid(id);
            typeId.setMovieid(movieAndGenres.getMovieid());
            movieGenre.setId(typeId);
            typeRepository.save(movieGenre);
        }
    }

    @PostMapping("/addMovie")
    public void addMovie(@RequestBody AddMovieDTO movie) {
        for(Movie m : movieRepository.findAll()) {
            if(movie.getTitle().equals(m.getTitle())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        Movie m = new Movie();
        m.setTitle(movie.getTitle());
        m.setDescription(movie.getDescription());
        m.setImage(movie.getImage());
        m.setDuration(movie.getDuration());
        m.setReleasedate(movie.getRelDate());
        movieRepository.save(m);
    }

    @GetMapping("/home")
    public MovieActorDTO homePage() {
        return movieService.oneOfManyToBeReleased();
    }

    @PostMapping("/rateComment")
    public void rateForm(@RequestBody ReviewFormDTO review) {
        Review review1 = new Review();
        ReviewId rId = new ReviewId();
        rId.setMovieid(review.getMovieid());
        rId.setUserid(review.getUserid());
        review1.setId(rId);
        review1.setComment(review.getComment());
        review1.setRating(review.getRating());
        Movie movie = movieRepository.getById(review.getMovieid());
        User user = userRepository.getById(review.getUserid());
        review1.setMovie(movie);
        review1.setUser(user);
        reviewRepository.save(review1);
    }

}
