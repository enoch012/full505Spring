package com.bitc.data.repository;

import com.bitc.data.entity.ProductEntity;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/* JpaRepository<매핑하려는 테이블 클래스, Pk 값의 데이터 타입>
* JpaRepository 를 상속 받았기 때문에 JpaRepository 가 제공하는 기본 메소드와 쿼리 메소드를 생성할 수 있음 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
  /* 쿼리 메소드 : JpaRepository 에서 제공하는 기본 메소드로는 원하는 데이터를 모두 컨트롤하기 힘들기 때문에
  * JpaRepository 에서 제공하주는 주제 키워드를 조합하여 원하는 메소드를 생성하여 사용할 수 있음
  * @Query : 복잡한 쿼리문은 쿼리 메소드로 만들기 힘들기 때문에 @Query 라는 어노테이션을 제공함
  *
  * 쿼리 메소드 생성방법
  * 리턴 타입 + {주제 키워드 + 서술어}를 조합하여 메소드 생성 ({} 부분은 반복 사용 가능)
  * 예시 ) List<Person> findByLastnameAndEmail(String lastName, String email);
  *
  * 쿼리 메소드의 주제 키워드
  * find ... By : select 명령을 수행하는 키워드, read ... By, get ... By, query ... By, search ... By, stream ... By 추가로 존재함
  * exists ... By : 특정 데이터가 존재하는지 확인하는 키워드
  * count ... By : select 명령을 수행한 후 쿼리 결과로 나온 레코드의 수를 출력하는 키워드
  * delete ... By, remove ... By : delete 명령을 수행하는 키워드, delete By 는 리턴이 없음, remove By는 삭제 수를 반환함
  * First<number>... , Top<number>... , : select 명령을 수행한 후 조회된 결과를 제한하는 키워드
  * 주제 키워드와 By 사이에 입력함
  * */


  // find ... By 예시
  Optional<ProductEntity> findByNumber(long number); // entity를 이미 지정했기 때문에 생략 가능
  Optional<ProductEntity> findProductEntityByNumber(long number); // 기본방식
  List<ProductEntity> findAllByName(String name); // 이름 기준으로 모든 데이터 가져오기
  ProductEntity queryByName(String name); // 다른 키워드로 데이터 가져오기

  // exists ... By 예시, 특정 데이터가 존재하는지 확인 (true/false)
  boolean existsByNumber(Long number);

  // count ... By 예시
  int countByName(String name); // 지정한 이름을 가지고 있는 데이터의 수

  // delete ... By, remove ... By 예시
  void deleteByNumber(Long number); // 지정한 번호를 기준으로 데이터 삭제, 반환값 없음
  long removeByName(String name); // 지정한 이름으로 데이터 삭제, 삭제된 데이터 수 반환

  // First<number> ... 예시, 주제 키워드와 By 사이에 입력
  List<ProductEntity> findFirst5ByName(String name); // 조회된 결과 중 처음 5개만 출력
  List<ProductEntity> findTop5ByNumber(Long number); // 조회된 결과 중 숫자가 큰 것 5개 출력

  // Is : 조건자 키워드, 값의 일치 여부를 조건으로 사용, Equals 와 동일한 기능을 제공
  ProductEntity findByNumberIs(Long number);
  ProductEntity findByNumberEquals(Long number); // 지정한 번호와 같은 데이터를 조화
  // (Is) Not : 값의 불일치 조건을 사용하는 자체 조건자 키워드, is를 생략할 수 있음
  ProductEntity findByNumberIsNot(Long number); // 지정한 번호와 같ㅈㅣ 않은 데이터를 조화

  // (Is) Null, (Is)NotNull : 값이 null 인지 확인하는 조건자 키워드
  /*List<ProductEntity> findByUpdateAtNull(); // 수정된 데이터가 null인 것만 조회
  List<ProductEntity> findByUpdatedAtIsNull();
  List<ProductEntity> findByUpdatedAtNotNull(); // 수정된 데이터가 Null이 아닌것만 조회

  List<ProductEntity> findByUpdatedAtIsNotNull();*/

  // (Is)True, (Is)False : Boolean 타입으로 지정된 컬럼값을 확인하는 키워드
  // List<ProductEntity> findByIsActiveTrue();
  // List<ProductEntity> findByisActiveIsTrue();
  //  List<ProductEntity> findByisActiveIsFalse();

  // And Or : 조건을 추가하는 키워드
  ProductEntity findByNumberAndName(Long number, String name); // and 명령으로 조회 조건을 추가, 지정한 번호와 지정한 이름이 같은 데이터를 조회
  ProductEntity findByNumberOrName(Long number, String name); // or 명령으로 조회 조건을 추가, 지정한 번호나 지정한 이름을 가지고 있는 데이터를 조회

  // (Is)Greaterthan, (Is)Lessthan, (Is)Between : 숫자나 datetime 컬럼을 대상으로 비교 연산에 사용할 수 있는 조건자 키워드
  // greaterthan, Lessthan 은 비교 대상에 대한 초과 및 미만을 비교 연산으로 수행.
  // 경계값을 포함할 경우 Equal 키워드를 추가
  List<ProductEntity> findByPriceIsGreaterThan(Long price); // 지정한 가격 초과 데이터 조회
  List<ProductEntity> findByPriceGreaterThan(Long price);
  List<ProductEntity>  findByPriceGreaterThanEqual(Long price); // 지정한 가격 이상의 데이터 조회
  List<ProductEntity> findByPriceIsLessThan(Long price);
  List<ProductEntity> findByPriceLessThan(Long price); // 지정한 가격 이하의 데이터 조회
  // 지정한 낮은 데이터부터 높은 데이터까지의 데이터를 조회
  List<ProductEntity>  findByPriceIsBetween(Long lowPrice, Long highPrice);
  List<ProductEntity>  findByPriceBetween(Long lowPrice, Long highPrice);

  // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith : 데이터가 일부 일치 여부를 확인하는 조건자 키워드
  // Like : 키워드가 포함되어 있을 경우
  List<ProductEntity> findByNameLike(String name); // 지정한 문자열이 포함되어 있는 이름 조회
  List<ProductEntity> findByNameIsLike(String name);

  /*// Containing : 키워드가 문자열의 양 끝에 있을 경우
  List<ProductEntity> findByContains(String name); // 지정한 문자열이 양끝에 포함되어 있는 이름을 조회
  List<ProductEntity> findByNameContaining(String name);*/

  // StartingWith : 키워드가 문자열의 시작 부분에 있을 경우
  List<ProductEntity> findByNameStartsWith(String name); // 지정한 문자열로 시작하는 이름 조회
  List<ProductEntity> findByNameStartingWith(String name);
  List<ProductEntity> findByNameIsStartingWith(String name);

  // EndingWith : 키워드가 끝 문자열일 경우
  List<ProductEntity> findByNameEndsWith(String name); // 지정한 문자열로 끝나는 이름 조회
  List<ProductEntity> findByNameEndingWith(String name);
  List<ProductEntity> findByNameIsEndingWith(String name);

  // OrderBy ... Asc|Desc ... Asc|Desc : 쿼리문의 결과를 지정한 컬럼으로 정렬하는 키워드(여러개 정렬 가능)
  List<ProductEntity> findByNameOrderByNumberAsc(String name); // 지정한 이름으로 데이터를 조회 후 번호 기준으로 오름차순 정렬
  List<ProductEntity> findByNameOrderByNumberDesc(String name); // 지정한 이름으로 데이터를 조회 후 번호를 기준으로 내림차순 정렬
  List<ProductEntity> findByNameOrderByPriceAscStockDesc(String name); // 지정한 이름으로 데이터를 조회 후 가격을 기준으로 오름차순 정렬을 먼저 하고, 재고 수량을 기준으로 내림차순 정렬


  // 정렬 사용 시 Sort 객체를 사용하여 정렬하는 것이 가능함
  // 쿼리 메소드 생성 후 매개변수에 Sort 객체를 추가하여 정렬
  // List<ProductEntity> findByName(String name); 와 같이 쿼리 메소드를 생성 후 실행 시에 Sort 객체를 추가하여 실행
  // productRepository.findByName('볼펜', Sort.by(Order.asc('price')));
  // productRepository.findByName('볼펜', Sort.by(Order.ac('price'), Order.desc('stock')));

  /*
  * @Query : 복잡한 쿼리문은 쿼리 메소드로 만들기 때문에 @Query 라는 어노테이션을 제공함
  * JPQL(JPA SQL) 문법을 사용하여 SQL 쿼리를 생성하여 복잡한 쿼리문을 실핼할 수 있도록 도와줌
  * 검색 조건에 '?순번' 형태로 데이터를 사용함
  * 사용법 :
  * @Query("select entity객체명 from entity클래스명 WHERE 검색 조건1, 검색조건2, ... ")
  * 반환 타입 메소드명 (매개변수1, 매개변수2 ...)
  * */

  // @Query("SELECT p FROM ProductEntity AS p")
  //List<ProductEntity> selectAll()
  // List<ProductEntity> querySelectAll();

  @Query("SELECT p FROM ProductEntity AS p ") // 다 고를 경우 별 대신에 객체명을 넣어주면 된다
  List<ProductEntity> querySelectAll();

  // FROM 다음에 entity 클래스 명을 사용
  // AS 명령 사용하여 클래스의 별명을 입력
  // SELEC 절에 클래스의 별명, 컬럼명로 출력하고자 하는 컬러만 입력
// WHERE절에선 클래스 별명, 컬럼명 형태로 적용함
  @Query("SELECT p FROM ProductEntity AS p WHERE p.name = '만년필' ")
  ProductEntity querySelectName();

  // 메소드의 매개변수를 쿼리문에 적용할 경우 ?순번 형식으로 입력하여 데이터를 매칭함
  // 순번은 1번부터 시작하고, 매개변수의 순서대로 매칭이 됨

  @Query("SELECT p FROM ProductEntity AS p WHERE p.name = ?1 ")
  ProductEntity querySelectName(String name);

  // 메소드의 매개변수를 쿼리문에 적용할 경우 :매개변수명 형식으로 입력하는 방법
  // :매개변수 방식은 메소드의 매개변수 앞에 @Param("컬럼명") 을 반드시 사용해야함
  // :매개변수 방식은 메소드의 매개변수 순서와 상관이 없음
  @Query("SELECT p FROM ProductEntity AS p WHERE p.name = :name AND p.price = :price ")
  ProductEntity querySelectNamePrice(@Param("name") String name, @Param("price") int price);

}
