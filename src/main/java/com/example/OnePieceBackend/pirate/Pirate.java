package com.example.OnePieceBackend.pirate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Pirate {
    @Id
    @SequenceGenerator(
        name = "pirate_sequence",
        sequenceName = "pirate_sequence",
        allocationSize = 1 
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "pirate_sequence"
    )
    private Long id;
    private String name;
    private String epithet;
    private Integer bounty;
    private String role;
    private Integer age;
    private String devilFruit;
    private Long crewId;
    
    public Pirate() {
    }

    public Pirate(Long id, String name, String epithet, Integer bounty, String role, Integer age, String devilFruit,
            Long crewId) {
        this.id = id;
        this.name = name;
        this.epithet = epithet;
        this.bounty = bounty;
        this.role = role;
        this.age = age;
        this.devilFruit = devilFruit;
        this.crewId = crewId;
    }

    public Pirate(String name, String epithet, Integer bounty, String role, Integer age, String devilFruit,
            Long crewId) {
        this.name = name;
        this.epithet = epithet;
        this.bounty = bounty;
        this.role = role;
        this.age = age;
        this.devilFruit = devilFruit;
        this.crewId = crewId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEpithet() {
        return epithet;
    }

    public void setEpithet(String epithet) {
        this.epithet = epithet;
    }

    public Integer getBounty() {
        return bounty;
    }

    public void setBounty(Integer bounty) {
        this.bounty = bounty;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDevilFruit() {
        return devilFruit;
    }

    public void setDevilFruit(String devilFruit) {
        this.devilFruit = devilFruit;
    }

    public Long getCrewId() {
        return crewId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }

    @Override
    public String toString() {
        return "Pirate [id=" + id + ", name=" + name + ", epithet=" + epithet + ", bounty=" + bounty + ", role=" + role
                + ", age=" + age + ", devilFruit=" + devilFruit + ", crewId=" + crewId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((epithet == null) ? 0 : epithet.hashCode());
        result = prime * result + ((bounty == null) ? 0 : bounty.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((devilFruit == null) ? 0 : devilFruit.hashCode());
        result = prime * result + ((crewId == null) ? 0 : crewId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pirate other = (Pirate) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (epithet == null) {
            if (other.epithet != null)
                return false;
        } else if (!epithet.equals(other.epithet))
            return false;
        if (bounty == null) {
            if (other.bounty != null)
                return false;
        } else if (!bounty.equals(other.bounty))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (devilFruit == null) {
            if (other.devilFruit != null)
                return false;
        } else if (!devilFruit.equals(other.devilFruit))
            return false;
        if (crewId == null) {
            if (other.crewId != null)
                return false;
        } else if (!crewId.equals(other.crewId))
            return false;
        return true;
    }

    
}
