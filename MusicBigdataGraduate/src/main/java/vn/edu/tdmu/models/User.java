package vn.edu.tdmu.models;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import vn.edu.tdmu.annotations.PhoneNumber;
import vn.edu.tdmu.enums.State;;

/**
 * Created by nguye on 1/26/2018.
 *
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private static final int MAX_LENGTH_USER = 64;
    private static final int MAX_LENTH_PASSWORD = 100;
    private static final int MAX_LENTH_EMAIL = 100;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Size(min = 6)
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Size(min = 6, max = MAX_LENGTH_USER)
    @Column(name = "username", unique = true, nullable = false, length = MAX_LENGTH_USER)
    private String username;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Size(min = 6, max = MAX_LENTH_PASSWORD)
    @Column(name = "password", nullable = false, length = MAX_LENTH_PASSWORD)
    private String password;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Email
    @Size(min = 6, max = MAX_LENTH_EMAIL)
    @Column(name = "email", unique = true, nullable = false, length = MAX_LENTH_EMAIL)
    private String email;

    @JsonView(Views.ExtendedPublic.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_dt")
    private Date birthDate;

    @JsonView(Views.ExtendedPublic.class)
    @PhoneNumber
    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "sex")
    private String sex;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "image_url")
    private String imageUrl;

    @JsonView(Views.ExtendedPublic.class)
    @Column(name = "state", nullable = false)
    private String state = State.ACTIVE.getState();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_playlist_mappings", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_playlist_mappings", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "playlist_id")})
    private Set<Playlist> playlists = new HashSet<>();

    public User() {
    }

    public User(String fullname, String username, String password, String email, Date birthDate, String phoneNumber, String sex, String imageUrl, String state, Set<Role> roles, Set<Playlist> playlists) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.imageUrl = imageUrl;
        this.state = state;
        this.roles = roles;
        this.playlists = playlists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void update(String newFullname, String newEmail, Date newBirthDate, String newPhoneNumber, String newSex, String newState, Set<Role> newRoles){
        this.fullname = newFullname;
        this.email = newEmail;
        this.birthDate = newBirthDate;
        this.phoneNumber = newPhoneNumber;
        this.sex = newSex;
        this.state = newState;
        this.roles = newRoles;
    }

    public boolean addPlaylist(Playlist newPlaylist){
        if (this.playlists.contains(newPlaylist)){
            return false;
        }
        this.playlists.add(newPlaylist);
        return true;
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

        User other = (User) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
