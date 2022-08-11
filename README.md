# JpaShop
domain 내의 Entity 들은 서로의 ID 만 가지고 있다.
따라서 Order 에서 Member Entity 를 조회하려면 order 에서 memberId 를 찾고
Member Table 에서 위에서 찾은 memberId 로 값을 찾아야한다.

현재 방식은 객체 설계를 테이블 설계에 맞춘 방식.
객체 그래프 탐색이 불가능하다.
