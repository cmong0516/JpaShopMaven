package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    private String city;
    private String street;
    private String zipcode;
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    // fetch LAZY 해주면 프록시 객체를 조회.
    // member 를 조회할때 team 이 필요하지 않으면 member 만 조회할수 있다.
    // 불필요한 조회를 안해서 성능업.
    // @ManyToOne(fetch = FetchType.EAGER)
    // EAGER 를 하면 member , team 둘다 초기화.
    // ** 실무에선 가급적 지연 로딩(LAZY)만 사용
    // 즉시로딩 적용시(EAGER) 예상치 못한 SQL 발생 , JPQL 에서 N + 1 문제 발생.
    // @ManyToOne, @OneToOne 은 기본이 즉시로딩 >> LAZY 로 다 바꿔
    // LAZY 이지만 한번에 가져오고 싶으면 JPQL join fetch 사용
    @JoinColumn(name = "TEAM_ID")
    private Team team;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
//
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }
}
