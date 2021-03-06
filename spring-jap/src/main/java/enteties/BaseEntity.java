package enteties;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "modifyDate", columnDefinition = "DATETIME", nullable = false)
    protected LocalDateTime modifyDate;
    @Column(name = "creationDate", columnDefinition = "DATETIME", nullable = false)
    protected LocalDateTime creationDate;

    @PrePersist
    public void setCreationDatetime(){
        //  log.debug("Pre Persist");
        LocalDateTime localDateTime = getNowLocalDateTime();
        this.setModifyDate(localDateTime);
        this.setCreationDate(localDateTime);
    }

    @PreUpdate
    public void setLastModified(){
        // log.debug("Pre Update");
        LocalDateTime localDateTime = getNowLocalDateTime();
        this.setModifyDate(localDateTime);
        if (this.getCreationDate()==null){
            this.setCreationDate(localDateTime);
        }
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    private LocalDateTime getNowLocalDateTime() {
        ZonedDateTime utcNow = ZonedDateTime.now( ZoneOffset.UTC );
        return utcNow.toLocalDateTime();
    }
}
