package application;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public final class Group extends Identificator {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Mountain mountain;

    @ManyToMany
    @JoinTable(name = "climbers_go", joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_climber"))
    private List<Climber> climbersList = new ArrayList<>();

    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private LocalDate startDate;

    private int durationDays;

    @Column(nullable = false)
    private LocalDate stopDate;

    public Group() { }

    public Group(Mountain mountain, boolean isOpen, LocalDate startDate, int durationDays) {
        this.mountain = mountain;
        setOpen(isOpen);
        setStartDate(startDate);
        setDuration(durationDays);
        setStopDate(startDate, durationDays);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        if (!isOpen) this.isOpen = open;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Восхождение не может начаться раньше, чем текущая дата!");
        this.startDate = startDate;
    }

    public int getDuration() {
        return durationDays;
    }

    public void setDuration(int duration) { this.durationDays = durationDays; }

    public void setStopDate(LocalDate startDate, int durationDays) {
        this.stopDate = startDate.plusDays(durationDays);
    }

    public LocalDate getStopDate() { return stopDate; }

    public Mountain getMountain() { return mountain; }

    public List<Climber> getClimbersList() { return climbersList; }

    /*
    public void setClimbersList(Climber name) {
        if (isOpen()) {
            if (getStopDate().isAfter(climbersList.)) {
                climbersList.add(name);
            }
            else {
                System.out.println("в это время вы еще в походе.");}
        }
    }
    */

}
