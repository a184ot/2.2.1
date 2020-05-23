package hiber.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(generator = "userKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
            name = "userKeyGenerator",
            strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "property", value = "user"
            )
    )
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "series")
    @NonNull
    private int series;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private User user;

    public Car() {
        name = "";
        series = 0;
    }

    public Car(User user) {
        this.user = user;
    }

    public Car(User user, String name, int series) {
        this.user = user;
        this.name = name;
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public Long getId() {
        return id;
    }
}
