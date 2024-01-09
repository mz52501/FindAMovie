package FindAMovie.services;

import FindAMovie.DTO.*;
import FindAMovie.models.*;
import FindAMovie.reporsitories.*;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    MovieRepository movieRepository;
    ActorRepository actorRepository;
    PersonRepository personRepository;
    ActRepository actRepository;
    ReviewRepository reviewRepository;
    UserRepository userRepository;
    DirectRepository directRepository;
    TypeRepository typeRepository;
    GenreRepository genreRepository;
    DirectorRepository directorRepository;

    @Autowired
    public MovieService(DirectorRepository directorRepository, GenreRepository genreRepository, TypeRepository typeRepository, DirectRepository directRepository, UserRepository userRepository, ReviewRepository reviewRepository, ActRepository actRepository, MovieRepository movieRepository, ActorRepository actorRepository, PersonRepository personRepository) {
        this.directRepository = directRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.actRepository = actRepository;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.personRepository = personRepository;
        this.typeRepository = typeRepository;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public ReviewMovieDTO getReviews(Integer id) {
        Movie movie = movieRepository.getById(id);
        List<Review> reviews = reviewRepository.findAll();
        ReviewMovieDTO rmdto = new ReviewMovieDTO();
        rmdto.setTitle(movie.getTitle());
        Set<ReviewDTO> reviewsdto = new HashSet<>();
        for(Review review : reviews) {
            if(review.getMovie().getId() == id) {
                ReviewDTO rdto = new ReviewDTO();
                User user = userRepository.getById(review.getUser().getId());
                rdto.setUsername(user.getUsername());
                rdto.setUserid(user.getId());
                rdto.setMovieid(id);
                rdto.setRating(review.getRating());
                rdto.setComment(review.getComment());
                reviewsdto.add(rdto);
            }
        }

        ReviewDTO[] array_help = new ReviewDTO[reviewsdto.size()];
        reviewsdto.toArray(array_help);
        rmdto.setArray(array_help);
        return rmdto;
    }

    public List<MovieDTO> moviesToBeReleased() {
        List<Movie> movies = movieRepository.findAllNewMovies();
        List<MovieDTO> movieDTOS = new LinkedList<>();
        for(Movie m : movies) {
            MovieDTO mdto = new MovieDTO();
            mdto.setId(m.getId());
            mdto.setTitle(m.getTitle());
            mdto.setImage(m.getImage());
            mdto.setDuration(m.getDuration());
            mdto.setDescription(m.getDescription());
            mdto.setRelDate(m.getReleasedate());
            movieDTOS.add(mdto);
        }
        return movieDTOS;
    }

    public MovieActorDTO oneOfManyToBeReleased() {
        MovieActorDTO madto = new MovieActorDTO();
        List<Integer> personsId = new LinkedList<>();
        List<MovieDTO> list = new LinkedList<>(moviesToBeReleased());
        Random rand = new Random();
        MovieDTO mdto = list.get(rand.nextInt(list.size()));
        madto.setId(mdto.getId());
        madto.setImage(mdto.getImage());
        madto.setDescription(mdto.getDescription());
        madto.setRelDate(mdto.getRelDate());
        madto.setDuration(mdto.getDuration());
        madto.setTitle(mdto.getTitle());
        List<Act> listAct = actRepository.findAll();
        List<Direct> listDirect = directRepository.findAll();
        List<Type> listType = typeRepository.findAll();
        List<Person> listPerson = personRepository.findAll();
        List<Genre> listGenres = genreRepository.findAll();
        Set<PersonDTO> personsdto = new HashSet<>();
        Set<PersonDTO> personsdto2 = new HashSet<>();
        Set<PersonDTO> directors = new HashSet<>();
        Set<PersonDTO> directors2 = new HashSet<>();
        Set<GenreDTO> genres = new HashSet<>();
        Set<GenreDTO> genres2 = new HashSet<>();
        for(Act actingPair : listAct) {
            if(actingPair.getMovieid().getId() == mdto.getId()) {
                personsId.add(actingPair.getPersonid().getId());
                PersonDTO perdto = new PersonDTO();
                perdto.setId(actingPair.getPersonid().getId());
                perdto.setRole(actingPair.getRole());
                personsdto.add(perdto);
            }
        }
        for(PersonDTO perdto : personsdto) {
            for(Person person : listPerson) {
                if(person.getId() == perdto.getId()) {
                    perdto.setName(person.getName());
                    perdto.setSurname(person.getSurname());
                    perdto.setDescription(person.getDescription());
                    perdto.setImage(person.getImage());
                    personsdto2.add(perdto);
                    break;
                }
            }
        }
        for(Direct directingPair : listDirect) {
            if(directingPair.getMovieid().getId() == mdto.getId()) {
                PersonDTO perdto = new PersonDTO();
                perdto.setId(directingPair.getPersonid().getId());
                directors.add(perdto);
            }
        }
        for(PersonDTO perdto : directors) {
            for(Person person : listPerson) {
                if(person.getId() == perdto.getId()) {
                    perdto.setName(person.getName());
                    perdto.setSurname(person.getSurname());
                    perdto.setDescription(person.getDescription());
                    perdto.setImage(person.getImage());
                    directors2.add(perdto);
                    break;
                }
            }
        }

        for(Type genreMoviePair : listType) {
            if(genreMoviePair.getMovieid().getId() == mdto.getId()) {
                GenreDTO genredto = new GenreDTO();
                genredto.setId(genreMoviePair.getGenreid().getId());
                genres.add(genredto);
            }
        }
        for(GenreDTO genredto : genres) {
            for(Genre genre : listGenres) {
                if(genre.getId() == genredto.getId()) {
                    genredto.setGenreName(genre.getGenrename());
                    genres2.add(genredto);
                    break;
                }
            }
        }

        GenreDTO[] array_help3 = new GenreDTO[genres2.size()];
        genres2.toArray(array_help3);
        madto.setGenres(array_help3);

        PersonDTO[] array_help2 = new PersonDTO[directors2.size()];
        directors2.toArray(array_help2);
        madto.setDirectors(array_help2);

        PersonDTO[] array_help = new PersonDTO[personsdto2.size()];
        personsdto2.toArray(array_help);
        madto.setActors(array_help);
        return madto;

    }

    public List<MovieDTO> getMovieDTO() {
        List<MovieDTO> list = new LinkedList<>();
        List<Review> reviewsList = reviewRepository.findAll();
        for(Movie mov : getMovies()) {
            double rating = 0;
            Set<ReviewDTO> revdtos = new HashSet<>();
            MovieDTO movie = new MovieDTO();
            movie.setId(mov.getId());
            movie.setTitle(mov.getTitle());
            movie.setDescription(mov.getDescription());
            movie.setDuration(mov.getDuration());
            movie.setRelDate(mov.getReleasedate());
            movie.setImage(mov.getImage());
            for(Review review : reviewsList) {
                if(review.getMovie().getId() == mov.getId()) {
                    ReviewDTO rdto = new ReviewDTO();
                    rdto.setMovieid(review.getMovie().getId());
                    rdto.setUserid(review.getUser().getId());
                    rdto.setComment(review.getComment());
                    rdto.setRating(review.getRating());
                    revdtos.add(rdto);
                    rating += review.getRating();
                }
            }
            double avgRate = 0;
            if(rating != 0) {
                double rate = rating / revdtos.size();
                avgRate = DoubleRounder.round(rate, 1);
            }
            movie.setAvgRating(avgRate);
            ReviewDTO[] array_help = new ReviewDTO[revdtos.size()];
            revdtos.toArray(array_help);
            movie.setArray(array_help);
            list.add(movie);
        }
        return list;
    }

    public List<PersonDTO> getActors() {
        List<PersonDTO> list = new LinkedList<>();
        List<Person> persons = personRepository.findAll();
        for(Person person : persons) {
            if((actorRepository.findById(person.getId())).isPresent()) {
                PersonDTO persondto = new PersonDTO();
                persondto.setId(person.getId());
                persondto.setName(person.getName());
                persondto.setSurname(person.getSurname());
                persondto.setDescription(person.getDescription());
                persondto.setImage(person.getImage());
                list.add(persondto);
            }
        }
        return  list;
    }

    public List<PersonDTO> getDirectors() {
        List<PersonDTO> list = new LinkedList<>();
        List<Person> persons = personRepository.findAll();
        for(Person person : persons) {
            if((directorRepository.findById(person.getId())).isPresent()) {
                PersonDTO persondto = new PersonDTO();
                persondto.setId(person.getId());
                persondto.setName(person.getName());
                persondto.setSurname(person.getSurname());
                persondto.setDescription(person.getDescription());
                persondto.setImage(person.getImage());
                list.add(persondto);
            }
        }
        return  list;
    }

    public ConnectMovieDirectorDTO getMovieDirector() {
        ConnectMovieDirectorDTO movieDirector = new ConnectMovieDirectorDTO();
        List<PersonDTO> directors = getDirectors();
        PersonDTO[] array = new PersonDTO[directors.size()];
        directors.toArray(array);
        movieDirector.setDirectors(array);
        List<MovieDTO> movies = getMovieDTO();
        MovieDTO[] help_array = new MovieDTO[movies.size()];
        movies.toArray(help_array);
        movieDirector.setMovies(help_array);
        return movieDirector;
    }

    public ActorMovieDTO getActor(Integer id) {
        List<Integer> moviesId = new LinkedList<>();
        if(actorRepository.findById(id).isPresent()) {
            Person person = personRepository.getById(id);
            ActorMovieDTO actorAndMovies = new ActorMovieDTO();
            actorAndMovies.setId(person.getId());
            actorAndMovies.setName(person.getName());
            actorAndMovies.setSurname(person.getSurname());
            actorAndMovies.setImage(person.getImage());
            actorAndMovies.setDescription(person.getDescription());
            List<Act> listAct = actRepository.findAll();
            List<Movie> listMovie = movieRepository.findAll();
            for (Act actingPair : listAct) {
                if (actingPair.getPersonid().getId() == id) {
                    moviesId.add(actingPair.getMovieid().getId());
                }
            }
            Set<MovieDTO> moviesdto = new HashSet<>();
            for (Integer idMov : moviesId) {
                for (Movie mov : listMovie) {
                    if (mov.getId() == idMov) {
                        MovieDTO movie = new MovieDTO();
                        movie.setId(mov.getId());
                        movie.setTitle(mov.getTitle());
                        movie.setDescription(mov.getDescription());
                        movie.setDuration(mov.getDuration());
                        movie.setRelDate(mov.getReleasedate());
                        movie.setImage(mov.getImage());
                        moviesdto.add(movie);
                        break;
                    }
                }
            }
            MovieDTO[] array_help = new MovieDTO[moviesdto.size()];
            moviesdto.toArray(array_help);
            actorAndMovies.setArray(array_help);
            return actorAndMovies;
        } else
            return null;

    }

    public ActorMovieDTO getDirector(Integer id) {
        List<Integer> moviesId = new LinkedList<>();
        if(directorRepository.findById(id).isPresent()) {
            Person person = personRepository.getById(id);
            ActorMovieDTO actorAndMovies = new ActorMovieDTO();
            actorAndMovies.setId(person.getId());
            actorAndMovies.setName(person.getName());
            actorAndMovies.setSurname(person.getSurname());
            actorAndMovies.setImage(person.getImage());
            actorAndMovies.setDescription(person.getDescription());
            List<Direct> listDirect = directRepository.findAll();
            List<Movie> listMovie = movieRepository.findAll();
            for (Direct directingPair : listDirect) {
                if (directingPair.getPersonid().getId() == id) {
                    moviesId.add(directingPair.getMovieid().getId());
                }
            }
            Set<MovieDTO> moviesdto = new HashSet<>();
            for (Integer idMov : moviesId) {
                for (Movie mov : listMovie) {
                    if (mov.getId() == idMov) {
                        MovieDTO movie = new MovieDTO();
                        movie.setId(mov.getId());
                        movie.setTitle(mov.getTitle());
                        movie.setDescription(mov.getDescription());
                        movie.setDuration(mov.getDuration());
                        movie.setRelDate(mov.getReleasedate());
                        movie.setImage(mov.getImage());
                        moviesdto.add(movie);
                        break;
                    }
                }
            }
            MovieDTO[] array_help = new MovieDTO[moviesdto.size()];
            moviesdto.toArray(array_help);
            actorAndMovies.setArray(array_help);
            return actorAndMovies;
        } else
            return null;

    }

    public MovieActorDTO getMovie(Integer id) {
        List<Integer> personsId = new LinkedList<>();
        Movie movie = movieRepository.getById(id);
        MovieActorDTO madto = new MovieActorDTO();
        madto.setId(movie.getId());
        madto.setTitle(movie.getTitle());
        madto.setDuration(movie.getDuration());
        if(movie.getDuration() != null) {
            int hours = movie.getDuration().getHour();
            int minutes = movie.getDuration().getMinute();
            madto.setHours(hours);
            madto.setMinutes(minutes);
        }
        madto.setDescription(movie.getDescription());
        madto.setRelDate(movie.getReleasedate());
        madto.setImage(movie.getImage());
        List<Act> listAct = actRepository.findAll();
        List<Direct> listDirect = directRepository.findAll();
        List<Type> listType = typeRepository.findAll();
        List<Person> listPerson = personRepository.findAll();
        List<Genre> listGenres = genreRepository.findAll();
        Set<PersonDTO> personsdto = new HashSet<>();
        Set<PersonDTO> personsdto2 = new HashSet<>();
        Set<PersonDTO> directors = new HashSet<>();
        Set<PersonDTO> directors2 = new HashSet<>();
        Set<GenreDTO> genres = new HashSet<>();
        Set<GenreDTO> genres2 = new HashSet<>();
        List<Review> reviews = reviewRepository.findAll();
        Set<ReviewDTO> reviewsdto = new HashSet<>();
        for(Review review : reviews) {
            if(review.getMovie().getId() == id) {
                ReviewDTO rdto = new ReviewDTO();
                User user = userRepository.getById(review.getUser().getId());
                rdto.setUsername(user.getUsername());
                rdto.setUserid(user.getId());
                rdto.setMovieid(id);
                rdto.setRating(review.getRating());
                rdto.setComment(review.getComment());
                reviewsdto.add(rdto);
            }
        }

        ReviewDTO[] array_help4 = new ReviewDTO[reviewsdto.size()];
        reviewsdto.toArray(array_help4);
        madto.setReviews(array_help4);
        for(Act actingPair : listAct) {
            if(actingPair.getMovieid().getId() == id) {
                personsId.add(actingPair.getPersonid().getId());
                PersonDTO perdto = new PersonDTO();
                perdto.setId(actingPair.getPersonid().getId());
                perdto.setRole(actingPair.getRole());
                personsdto.add(perdto);
            }
        }
        for(PersonDTO perdto : personsdto) {
            for(Person person : listPerson) {
                if(person.getId() == perdto.getId()) {
                    perdto.setName(person.getName());
                    perdto.setSurname(person.getSurname());
                    perdto.setDescription(person.getDescription());
                    perdto.setImage(person.getImage());
                    personsdto2.add(perdto);
                    break;
                }
            }
        }
        for(Direct directingPair : listDirect) {
            if(directingPair.getMovieid().getId() == id) {
                PersonDTO perdto = new PersonDTO();
                perdto.setId(directingPair.getPersonid().getId());
                directors.add(perdto);
            }
        }
        for(PersonDTO perdto : directors) {
            for(Person person : listPerson) {
                if(person.getId() == perdto.getId()) {
                    perdto.setName(person.getName());
                    perdto.setSurname(person.getSurname());
                    perdto.setDescription(person.getDescription());
                    perdto.setImage(person.getImage());
                    directors2.add(perdto);
                    break;
                }
            }
        }

        for(Type genreMoviePair : listType) {
            if(genreMoviePair.getMovieid().getId() == id) {
                GenreDTO genredto = new GenreDTO();
                genredto.setId(genreMoviePair.getGenreid().getId());
                genres.add(genredto);
            }
        }
        for(GenreDTO genredto : genres) {
            for(Genre genre : listGenres) {
                if(genre.getId() == genredto.getId()) {
                    genredto.setGenreName(genre.getGenrename());
                    genres2.add(genredto);
                    break;
                }
            }
        }

        GenreDTO[] array_help3 = new GenreDTO[genres2.size()];
        genres2.toArray(array_help3);
        madto.setGenres(array_help3);

        PersonDTO[] array_help2 = new PersonDTO[directors2.size()];
        directors2.toArray(array_help2);
        madto.setDirectors(array_help2);

        PersonDTO[] array_help = new PersonDTO[personsdto2.size()];
        personsdto2.toArray(array_help);
        madto.setActors(array_help);
        return madto;

    }

    public List<MovieDTO> getTopMovies() {
        Set<MovieDTO> list = new TreeSet<>();
        List<Review> reviewsList = reviewRepository.findAll();
        for(Movie mov : getMovies()) {
            double rating = 0;
            Set<ReviewDTO> revdtos = new HashSet<>();
            MovieDTO movie = new MovieDTO();
            movie.setId(mov.getId());
            movie.setTitle(mov.getTitle());
            movie.setDescription(mov.getDescription());
            movie.setDuration(mov.getDuration());
            movie.setRelDate(mov.getReleasedate());
            movie.setImage(mov.getImage());
            for(Review review : reviewsList) {
                if(review.getMovie().getId() == mov.getId()) {
                    ReviewDTO rdto = new ReviewDTO();
                    rdto.setMovieid(review.getMovie().getId());
                    rdto.setUserid(review.getUser().getId());
                    rdto.setComment(review.getComment());
                    rdto.setRating(review.getRating());
                    revdtos.add(rdto);
                    rating += review.getRating();
                }
            }
            double rate = rating / revdtos.size();
            double avgRate = DoubleRounder.round(rate, 1);
            movie.setAvgRating(avgRate);
            ReviewDTO[] array_help = new ReviewDTO[revdtos.size()];
            revdtos.toArray(array_help);
            movie.setArray(array_help);
            list.add(movie);
        }
        List<MovieDTO> top3Movies = new LinkedList<>();
        MovieDTO[] array = new MovieDTO[list.size()];
        list.toArray(array);
        for(int i = list.size() - 1; i > list.size() - 6; i--) {
            top3Movies.add(array[i]);
        }
        return top3Movies;
    }

    public ConnectMovieActorDTO connectMovieActor() {
        ConnectMovieActorDTO movAct = new ConnectMovieActorDTO();
        List<PersonDTO> actors = getActors();
        PersonDTO[] array = new PersonDTO[actors.size()];
        actors.toArray(array);
        movAct.setActors(array);
        List<MovieDTO> movies = getMovieDTO();
        MovieDTO[] help_array = new MovieDTO[movies.size()];
        movies.toArray(help_array);
        movAct.setMovies(help_array);
        return movAct;
    }

    public List<GenreDTO> getGenres() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDTO> genreDTOS = new LinkedList<>();
        for(Genre g : genres) {
            GenreDTO gdto = new GenreDTO();
            gdto.setId(g.getId());
            gdto.setGenreName(g.getGenrename());
            genreDTOS.add(gdto);
        }

        return genreDTOS;
    }

    public ConnectMovieGenreDTO getMovieGenres() {
        ConnectMovieGenreDTO movieGenre = new ConnectMovieGenreDTO();
        List<GenreDTO> genreDTOS = getGenres();
        GenreDTO[] array = new GenreDTO[genreDTOS.size()];
        genreDTOS.toArray(array);
        movieGenre.setGenres(array);
        List<MovieDTO> movies = getMovieDTO();
        MovieDTO[] help_array = new MovieDTO[movies.size()];
        movies.toArray(help_array);
        movieGenre.setMovies(help_array);
        return movieGenre;
    }


}
