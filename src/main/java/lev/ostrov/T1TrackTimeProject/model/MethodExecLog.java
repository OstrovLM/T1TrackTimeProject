package lev.ostrov.T1TrackTimeProject.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class MethodExecLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String methodName;
    private Date executionTime;
    private Long executionDuration;
    private Boolean asyncSave;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MethodExecLog that = (MethodExecLog) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
