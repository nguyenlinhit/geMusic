package vn.edu.tdmu.models;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

/**
 * Created by nguye on 1/26/2018.
 *
 */

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity{
    private static final long serialVersionUID = 1L;

	@JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable =  false, unique = true)
    private String name;

    @JsonView(Views.Summary.class)
    @Column(name = "real_name")
    private String realName;

    @JsonView(Views.Summary.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_dt")
    private Date birthDate;

    @JsonView(Views.Summary.class)
    @Column(name = "sex")
    private String sex;

    @JsonView(Views.Summary.class)
    @Column(name = "country")
    private String country;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "image_url")
    private String imageUrl;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "career")
    private String career;

    @ManyToMany(mappedBy = "artists")
    private Set<Song> songs = new HashSet<>();

    @OneToMany(mappedBy = "artist")
    private List<Playlist> playlists = new ArrayList<>();

    public Artist() {
    }

    public Artist(String name, String realName, Date birthDate, String sex, String country, String imageUrl, String coverImageUrl, String career, Set<Song> songs, List<Playlist> playlists) {
        this.name = name;
        this.realName = realName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.country = country;
        this.imageUrl = imageUrl;
        this.coverImageUrl = coverImageUrl;
        this.career = career;
        this.songs = songs;
        this.playlists = playlists;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public void update(String newName, String newRealName, Date newBirthDate, String newSex, String newCountry, String newCareer){
        this.name = newName;
        this.realName = newRealName;
        this.birthDate = newBirthDate;
        this.sex = newSex;
        this.country = newCountry;
        this.career = newCareer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Genre)){
            return false;
        }

        Artist other = (Artist) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
