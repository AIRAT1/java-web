package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    @Size(max = 3000)
    @Column(name = "body")
    private String body;
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "users_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
}
