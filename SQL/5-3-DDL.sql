create table sample_books(
    book_no number(4),
    book_title varchar2(200),
    book_writer varchar2(100),
    book_price number(8),
    book_discount_price number(8),
    book_stock number(4),
    book_created_date date default sysdate
);

-- 테이블에 새로운 행 추가하기

-- 행의 모든 컬럼의 값을 지정해서 추가하기
insert into sample_books
(book_no, book_title, book_writer, book_price, book_discount_price, book_stock, book_created_date)
values
(101, '자바의 정석', '남궁성', 35000, 29000, 1000, sysdate);

-- 행의 특정 컬럼에만 값을 지정해서 추가하기, 생략된 컬럼에는 null값이 저장된다.
insert into sample_books
(book_no, book_title, book_price, book_created_date)
values
(102, '이것이 자바다', 28000, sysdate);

-- 행을 추가할 때 행의 모든 컬럼에 값을 추가하고, 컬럼의 순서 그대로 값을 추가할 때는 
-- 컬럼명을 생략할 수 있다.
insert into sample_books
--(book_no, book_title, book_writer, book_price, book_discount_price, book_stock, book_created_date)
values (103, '이것이 데이터분석이다.', '윤기태', 28000, 26000, 100, sysdate);

insert into sample_books
(book_no, book_title, book_writer, book_price, book_discount_price, book_stock)
values
(104, '스프링 인 액션', '미상', 30000, 27000, 20); 

-- 테이블에 저장된 데이터 삭제하기

-- 테이블에 저장된 모든 데이터 삭제
delete from sample_books; 
-- 테이블에 저장된 데이터 중에서 특정 행을 삭제하기
delete from sample_books
where book_title like '%자바%';

delete from sample_books
where book_no = 103;

delete from sample_books
where book_no = 104;

select * from sample_books;

-- 테이블에 저장된 데이터 변경하기

-- 테이블의 모든 행에 대해서 book_price 컬럼의 값을 10000으로 변경하기
update sample_books
set
    book_price = 10000;
    
-- 테이블의 특정 행에 대해서 book_price 컬럼의 값을 32000으로 변경
update sample_books
set
    book_price = 32000
where
    book_no = 101;
    
update sample_books
set
    book_writer = '신용권',
    book_price = 35000,
    book_discount_price = 31500,
    book_stock = 50
where
    book_no = 102;

-- sample-books 테이블에 새로운 책 정보 추가하기 (3개 이상, 책 번호는 겹치지 않도록 한다.)
insert into sample_books
values
(105, '오라클 SQL과 PL/SQL', '서진수, 김균도', 32000, 30000, 50,sysdate);

insert into sample_books
(book_no, book_title, book_writer, book_price, book_discount_price, book_stock)
values
(106, '자바가 보이는 그림책', 'ANK Co.,Ltd', 15000, 12500, 100);

insert into sample_books
(book_no, book_title, book_writer, book_price, book_discount_price, book_stock)
values
(107, '혼자 공부하는 자바', '홍길동', 25000, 22500, 100);

-- 책번호가 104번인 책 삭제하기
delete from sample_books
where book_no = 104;

-- 책번호가 103번인 책 삭제하기
delete from sample_books
where book_no = 103;

-- 모든 책 정보 삭제하기
delete from sample_books;