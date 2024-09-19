package com.example.OnePieceBackend.pirate;


public class Pirate {
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

    
}
