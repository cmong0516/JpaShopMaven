import domain.Member;
import domain.Team;

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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.setTeamId(team.getId());
//            member.setTeam(team);

//            public void changeTeam(Team team) {
//                this.team = team;
//                team.getMembers().add(this);
//            }
            member.changeTeam(team);

            em.persist(member);

            team.getMembers().add(member);
            // em.flush();
            // em.clear();
            // 를 안해주고 members 를 반복문 출력 하면 DB 에서 값을 가져와서 출력하는게 아닌 1차캐시의 값을 출력한다.
            // 또 객체지향적으로 보면 양방향에 처리해주는게 맞다.

            // 객체를 테이블에 맞추어 데이터 중심의 설계를 하면 협력관계를 만들수 없다.
            // 아래처럼 각각의 Table 에서 값을 조회하여 여러번 찾아야함.
            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);
//            String teamName = findTeam.getName();
            Team findTeam = findMember.getTeam();
            // @OneToMany 로 양방향 연관관계 설정을 해주었더니 이게 가능하다.
            List<Member> members = findMember.getTeam().getMembers();

            System.out.println("findTeam = " + findTeam.getName());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
