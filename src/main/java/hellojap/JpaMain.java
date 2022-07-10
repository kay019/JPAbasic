package hellojap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        //DB 연결
        //persistence의 unit name
        //lodaing 시점에 단 하나만 생성

        //connection 을 얻어 종료 시 무조건 한개씩 필수
//서버에 하나. 어플리케이션 전체 서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
// session 당 하나, 사용 후 close 해야 하는 것
//        쓰레드간의 공유 X
        EntityManager em = emf.createEntityManager();

        //jpa ** 트랜젝션 단위 안에서 실행되어야 함
        // data 변경은 트랜젝션 안에서 이뤄져야 함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 정석적인 코드
        try{
//            crete member
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            //save
//            em.persist(member);

//            find
            Member findMember = em.find(Member.class, 1L);
//            System.out.println("find member id = " + findMember.getId());
//            System.out.println("find member name = " + findMember.getName());

//            remove
//            em.remove(findMember);


//            update
//            findMember.setName("HelloJPA");

//            select where
            // 테이블 대상이 아닌 객체 대상으로 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            //pagenation 1 ~ 10 번 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();


            for(Member member : result){
                System.out.println("member name : " + member.getName());
            }

            //transaction start
            // commit 시점에서 data check -> update
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
