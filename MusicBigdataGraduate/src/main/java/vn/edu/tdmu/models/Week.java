package vn.edu.tdmu.models;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nguye on 1/26/2018.
 *
 */
@Entity
@Table(name = "weeks")
public class Week extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotNull
    @Column(name = "no", nullable = false)
    private Integer no;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.Summary.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "startDate")
    private Date startDate;

    @JsonView(Views.Summary.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "endDate")
    private Date endDate;

    @JsonView(Views.Summary.class)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "week")
    private List<Playlist> playlists = new ArrayList<>();

    public Week() {
    }

    public Week(Integer no, String name, Date startDate, Date endDate, String description, List<Playlist> playlists) {
        this.no = no;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.playlists = playlists;
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

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void update(Integer newNo, String newName, Date newStartDate, Date newEndDate, String newDescription) {
        this.no = newNo;
        this.name = newName;
        this.startDate = newStartDate;
        this.endDate = newEndDate;
        this.description = newDescription;
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

        Week other = (Week) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
