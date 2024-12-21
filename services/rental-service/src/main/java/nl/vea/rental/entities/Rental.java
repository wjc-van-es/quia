package nl.vea.rental.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@MongoEntity(collection = "Rentals")
public class Rental extends PanacheMongoEntity {
    //private final Long id;
    private String userId;
    private Long reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    public Rental() {}

    public Rental(String userId, Long reservationId,
                  LocalDate startDate) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.startDate = startDate;
        active = true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static Optional<Rental> findByUserAndReservationIdsOptional(String userId, Long reservationId){
        return find("userId =?1 and reservationId =?2", userId, reservationId).firstResultOptional();
    }

    public static List<Rental> listActive(){
        return list("active", true);
    }

    private boolean calculateActive(){
        var now = LocalDate.now();
        return !startDate.isAfter(now) && (Objects.isNull(endDate) || endDate.isAfter(now));
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(id, rental.id) && Objects.equals(userId, rental.userId)
                && Objects.equals(reservationId, rental.reservationId) && Objects.equals(startDate, rental.startDate)
                && Objects.equals(endDate, rental.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, reservationId, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "\n\tid=" + id +
                ",\n\tuserId='" + userId + '\'' +
                ",\n\treservationId=" + reservationId +
                ",\n\tstartDate=" + startDate +
                ",\n\tendDate=" + endDate +
                ",\n\tactive=" + active +
                "\n}";
    }
}
