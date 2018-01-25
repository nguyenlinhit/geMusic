package vn.edu.tdmu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author NguyenLinh
 */

 @Entity
 @Table(name = "songs")
public final class Song extends BaseEntity{
    public static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_ID", unique = true, nullable = false)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.Summary.class)
    @Column(name = "url", nullable = true)
    private String url;

    @JsonView(Views.Summary.class)
    @Column(name = "total_views")
    private Integer weekViews = 0;

    @JsonView(Views.Summary.class)
    @Column(name = "country")
    private String country;

    @JsonView(Views.Summary.class)
    @Column(name = "image_url")
    private String imageUrl = "";

    @JsonView(Views.Summary.class)
    @Column(name = "description")
    private String description;

    @JsonView(Views.Summary.class)
    @Column(name = "show_on_home")
    private Boolean onHome;

    @JsonView(Views.Summary.class)
    @Column(name = "is_published")
    private Boolean isPublished = false;

    //@JsonView(Views.ExtendedPublic.class)
    //@ManyToOne
    //@JoinColumn(name = "genre_id")
    //private

    @JsonView(Views.Summary.class)
    @Column(name = "type")
    private String type;
}