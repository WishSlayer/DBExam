package application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public final class Climber extends Identificator {
    @Column(nullable = false, length = 30)
    private String climberName;
    @Column(nullable = false, length = 100)
    private String climberAddress;
    @Column(nullable = false, length = 3)
    private int climberAge;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "climbersList")
    private List<GroupUp> groupsList;

    public Climber(String climberName, String climberAddress, int climberAge) {
        setClimberName(climberName);
        setClimberAddress(climberAddress);
        setClimberAge(climberAge);
    }

    public String getClimberName() {
        return climberName;
    }

    public void setClimberName(String climberName) {
        if (climberName == null || climberName.trim().length() < 3 || climberName.trim().length() > 30)
            throw new IllegalArgumentException("Имя альпиниста не должно быть короче 3 или длиннее 30 символов!");
        this.climberName = climberName;
    }

    public String getClimberAddress() {
        return climberAddress;
    }

    public void setClimberAddress(String climberAddress) {
        if (climberAddress == null || climberAddress.trim().length() < 5 || climberAddress.trim().length() > 100)
            throw new IllegalArgumentException("Адрес проживания альпиниста не должен быть короче 5 или длиннее 30 символов!");
        this.climberAddress = climberAddress;
    }

    public int getClimberAge() {
        return climberAge;
    }

    public void setClimberAge(int climberAge) {
        if (climberAge < 18 || climberAge > 150)
            throw new IllegalArgumentException("Возраст альпиниста не должен быть меньше 18 и больше 150 лет!");
        this.climberAge = climberAge;
    }

    public List<GroupUp> getClimberGroups() {
        return groupsList;
    }

    @Override
    public String toString() {
        return "Альпинист " + climberName + " (г. " + climberAddress + ", " + climberAge + " лет/года)";
    }
}