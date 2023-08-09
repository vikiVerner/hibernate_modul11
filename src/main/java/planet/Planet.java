package planet;

import jakarta.persistence.*;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;



    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
