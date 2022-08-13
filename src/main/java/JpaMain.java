import domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
////            member.setTeamId(team.getId());
////            member.setTeam(team);
//
////            public void changeTeam(Team team) {
////                this.team = team;
////                team.getMembers().add(this);
////            }
//            member.changeTeam(team);
//
//            em.persist(member);
//
//            team.getMembers().add(member);
//            // em.flush();
//            // em.clear();
//            // 를 안해주고 members 를 반복문 출력 하면 DB 에서 값을 가져와서 출력하는게 아닌 1차캐시의 값을 출력한다.
//            // 또 객체지향적으로 보면 양방향에 처리해주는게 맞다.
//
//            // 객체를 테이블에 맞추어 데이터 중심의 설계를 하면 협력관계를 만들수 없다.
//            // 아래처럼 각각의 Table 에서 값을 조회하여 여러번 찾아야함.
//            Member findMember = em.find(Member.class, member.getId());
////            Long findTeamId = findMember.getTeamId();
////            Team findTeam = em.find(Team.class, findTeamId);
////            String teamName = findTeam.getName();
//            Team findTeam = findMember.getTeam();
//            // @OneToMany 로 양방향 연관관계 설정을 해주었더니 이게 가능하다.
//            List<Member> members = findMember.getTeam().getMembers();
//
//            System.out.println("findTeam = " + findTeam.getName());
//            1.
//            Order order = new Order();
//            order.addOrderItem(new OrderItem());

//            2.
//            Order order = new Order();
//            em.persist(order);
//
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            em.persist(orderItem);
//            // * 단방향 설계가 가장 중요하고 양방향 설계는 필요에 의해 작성한다.

//            Book book = new Book();
//            book.setName("JPA");
//            book.setAuthor("김영한");
//
//            em.persist(book);

            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());
            // 멤버만 출력할때 팀을 조회할 필요가 있을까 ?
            Member findMember = em.getReference(Member.class, member.getId());

            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getUsername() = " + findMember.getUsername());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

    private static void printMember(Member member) {
        System.out.println("member.getUsername() = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team);
    }
}


// 테이블 : 외래키 하나로 양쪽 조인이 가능하기에 방향의 의미가 없다.
// 객체 : 참조용 필드가 있는 쪽으로만 참조 가능. 한쪽만 : 단방향 , 양쪽서로 : 양방향.
// JPA 는 데이터 중심이 아니라 객체 중심이으로 쿼리를 보내기 때문에 이런 차이가 발생.
// 다대일 단방향 에서 FK 는 N 쪽에 .
// FK(외래키) 가 있는곳이 양방향 연관관계의 주인.