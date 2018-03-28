package vn.edu.tdmu.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdmu.enums.Country;
import vn.edu.tdmu.enums.PlaylistType;
import vn.edu.tdmu.enums.RoleType;
import vn.edu.tdmu.models.*;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenLinh on 2/17/2018.
 *
 */
@Transactional
@Service
public class InitDbService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDbService.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private GenreService genreService;

    @PostConstruct
    public void init() throws ParseException {
        if (roleService.findByType(RoleType.ADMIN.getRoleType()) == null) {
            LOGGER.info("Create a new Admin");

            Role role = new Role();
            role.setType(RoleType.ADMIN.getRoleType());
            Role adminRole = roleService.create(role);

            Role role2 = new Role();
            role2.setType(RoleType.DBA.getRoleType());
            Role dbaRole = roleService.create(role2);

            Role role3 = new Role();
            role3.setType(RoleType.USER.getRoleType());
            Role userRole = roleService.create(role3);

            User user = new User();
            user.setUsername("gemusic");
            user.setPassword("gemusic");
            user.setFullname("Nguyen Linh");
            user.setSex("Male");
            user.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/1995"));
            user.setPhoneNumber("0968270836");
            user.setEmail("nguyenlinh.it.1095@gmail.com");
            user.setImageUrl("/static/img/user/nguyenlinh.jpg");
            user.getRoles().add(adminRole);
            user.getRoles().add(dbaRole);
            user.getRoles().add(userRole);

            userService.create(user);

            //Create X Album
            create();

            //Create some Genre
            Genre genre = new Genre();
            genre.setName("Pop");

            Genre genre2 = new Genre();
            genre2.setName("Rock");

            Genre genre3 = new Genre();
            genre3.setName("Rap");

            Genre genre4 = new Genre();
            genre4.setName("R&B");

            Genre genre5 = new Genre();
            genre5.setName("Country");

            Genre genre6 = new Genre();
            genre6.setName("Dance");

            Genre popG = genreService.create(genre);
            Genre rockG = genreService.create(genre2);
            Genre rapG = genreService.create(genre3);
            Genre rnbG = genreService.create(genre4);
            Genre country = genreService.create(genre5);
            Genre dance = genreService.create(genre6);

            //Create 20 Artsit
            Artist a = new Artist();
            a.setName("Generateed Artsit");
            a.setSex("Male");
            a.setCountry(Country.VN.getCountry());
            a.setImageUrl("/static/img/artist/edsheeran.jpg");
            Artist artist = artistService.create(a);

            //Create 100 Songs
            for (int i = 0; i < 100; i++) {
                Song s = new Song();
                s.setName("Generated song " + i);
                s.setUrl("/static/data/mp3/Runaway - Ed Sheeran.mp3");
                s.addArtist(artist);
                s.setIsPublished(true);
                s.setWeekViews(0);
                if (i < 50) {
                    s.setGenre(popG);
                } else if (i < 60) {
                    s.setGenre(rockG);
                } else if (i < 70) {
                    s.setGenre(rapG);
                } else if (i < 80) {
                    s.setGenre(country);
                } else if (i < 90) {
                    s.setGenre(rnbG);
                }
                songService.create(s);
            }
        }
    }

    private void create() {
        Artist a = new Artist();
        a.setName("Robin Thicke");
        Artist artist = artistService.create(a);

        Song s1 = new Song();
        s1.setName("I Can't Make You Love Me");
        s1.addArtist(artist);
        s1.setUrl("/static/data/mp3/Josh Kaufman - I Can't Make You Love Me - Studio Version - The Voice 2014.mp3");
        s1.setIsPublished(true);
        s1.setOnHome(true);

        Song s2 = new Song();
        s2.setName("Too Little Too Late");
        s2.addArtist(artist);
        s2.setUrl("/static/data/mp3/Too Little Too Late.mp3");
        s2.setIsPublished(true);
        s2.setOnHome(true);

        List<Song> lsSongs = new ArrayList<Song>();
        lsSongs.add(songService.create(s1));
        lsSongs.add(songService.create(s2));

        for (int i = 0; i < 100; i++) {
            Playlist pl = new Playlist();
            pl.setName("Generated Album " + i);
            pl.setArtist(artist);
            pl.setTotalViews(10);
            pl.setWeekViews(1);
            pl.setType(PlaylistType.COLLECTION.getPlaylistType());

            Playlist playlist = playlistService.create(pl);

            playlistService.addSong(playlist.getId(), lsSongs);
        }
    }
}
