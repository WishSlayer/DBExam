package application;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public final class GroupUp extends Identificator {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Mountain mountain;

    @ManyToMany
    @JoinTable(name = "climb_events", joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_climber"))
    private List<Climber> climbersList = new ArrayList<>();

    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private int durationDays;

    @Column(nullable = false)
    private LocalDate stopDate;

    public GroupUp(Mountain mountain, boolean isOpen, LocalDate startDate, int durationDays) {
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

    private boolean checkDates(Climber climber){
        for (GroupUp group : climber.getClimberGroups()){
            if (this.startDate.isAfter(group.startDate)
                    && this.startDate.isBefore(group.stopDate)){
                return false;
            }
        }
        return true;
    }

    public void addClimber(Climber climber){
        if (startDate.isAfter(LocalDate.now())) {
            if (isOpen && checkDates(Objects.requireNonNull(climber))) {
                climbersList.add(climber);
                climber.getClimberGroups().add(this);
                if (climbersList.size() >= 7) isOpen = false;
            } else System.out.println("Приема в группу нет, добавить альпиниста " + climber.getClimberName() + " не возможно");
        } else {
            System.out.println("Группа уже закрыта!");
            isOpen = false;
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "mountain=" + mountain +
                ", climbersList=" + climbersList +
                ", isOpen=" + isOpen +
                ", startDate=" + startDate +
                ", durationDays=" + durationDays +
                ", stopDate=" + stopDate +
                '}';
    }

}
