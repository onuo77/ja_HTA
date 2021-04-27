-- 급여가 10000이상 15000이하인 사원들의 아이디, 이름, 급여를 조회
select employee_id, first_name, salary
from employees
where salary >= 10000 and salary <= 15000 ;

-- 소속부서 아이디가 50번이고, 급여를 3000이상 받는 사원의 아이디, 이름, 급여를 조회하기
select employee_id, first_name, salary
from employees
where department_id = 50 and salary >= 3000;

-- 직종 아이디가 SH_CLERK이고, 급여를 4000이상 받는 사원의 아이디, 이름, 급여를 조회하기
select employee_id, first_name, salary
from employees
where job_id = 'SH_CLERK' and salary >= 4000; 


-- or연산자는 특별한 경우 아닌이상 자주 사용하지 않음
-- 자신의 상사가 101번이거나 102번인 사원의 아이디, 이름, 직종 아이디, 상사의 아이디를 조회하기
select employee_id, first_name, job_id, manager_id
from employees
where manager_id = 101 or manager_id = 102;

-- 자신의 상사가 101번이거나 102번인 사원의 아이디, 이름, 직종 아이디, 상사의 아이디를 조회하기
select employee_id, first_name, job_id, manager_id
from employees
where manager_id in (101,102); -- or연산자를 사용하지말고 in 사용하기

-- 소속부서 아이디가 30번이거나 60번이면서 급여를 5000이상 받는 사원의 아이디,
-- 이름, 급여, 소속부서아이디를 조회하기
select employee_id, first_name, salary, department_id
from employees
where department_id in (30,60)
and salary >= 5000;

select employee_id, first_name, salary, department_id
from employees
where (department_id = 30 or department_id = 60) -- or가 있는 성분만 괄호로 묶기
and salary >= 5000;

select employee_id, first_name, salary, department_id
from employees
where department_id = 30 or department_id = 60
and salary >= 5000; -- or에 괄호가 없으면 검색조건과 맞지 않는 데이터도 조회가 된다.

-- is null, in not null을 이용해서 컬럼의 값이 null인 것과 null이 아닌것을 구분해서 조회하기
-- departments 테이블에서 등록된 부서 중에서 부서관리자가 지정되지 않은 부서의 아이디,
-- 부서명, 관리자 아이디를 조회하기
select department_id, department_name, manager_id
from departments
where manager_id is null;

-- employees테이블에 등록된 사원들 중에서 커미션을 받는 사원의 아이디, 이름, 급여, 커미션 조회하기
select employee_id, first_name, salary, commission_pct
from employees
where commission_pct is not null; 

-- employees테이블에 등록된 사원들 중에서 커미션을 받고, 급여를 10000이상 받는 사원의 아이디,
-- 이름, 급여, 커미션을 조회하기
select employee_id, first_name, salary, commission_pct
from employees
where commission_pct is not null
and salary >= 10000;

-- employees테이블에 등록된 사원들 중에서 커미션을 받고, 급여를 10000이상 받는 사원의 아이디,
-- 이름, 급여, 커미션을 조회하기(커미션에 대한 오름차순으로 정렬)
select employee_id, first_name, salary, commission_pct
from employees
where commission_pct is not null
    and salary >= 10000
order by commission_pct asc;

-- employee테이블에서 사원번호가 110번 이상 120번 이하인 사원의 사원아이디, 이름을 조회하기
select employee_id, first_name
from employees
where employee_id >= 110 and employee_id <= 120;

select employee_id, first_name
from employees
where employee_id between 110 and 120;

-- employees 테이블에서 직종 아이디에 CLERK를 포함하고 있는 사원의 아이디, 이름, 직종아이디 조회하기
select employee_id, first_name, job_id
from employees
where job_id like '%CLERK';

-- employees 테이블에서 대문자 'S'를 이름에 포함하고 있는 모든 사원의 이름을 조회하기
select first_name
from employees
where first_name like '%S%';

-- employees 테이블에 등록된 사원들 중에서 소속부서아이디가 30,50,60,80이 아닌 사원의
-- 아이디, 이름, 부서아이디를 조회하기
select employee_id, first_name, department_id
from employees
where department_id not in (30,50,60,80)
order by department_id asc;

-- 문자함수 연습하기
-- lower(컬럼명 혹은 표현식) - 소문자로 변환
-- upper(컬럼명 혹은 표현식) - 대문자로 변환
-- substr(컬럼명 혹은 표현식, 시작위치, 갯수) - 시작위치에서 갯수만큼 잘라서 반환
-- length(컬럼명 혹은 표현식) - 문자열의 길이 반환
-- instr(컬럼명 혹은 표현식, '문자') - 지정된 문자의 위치를 반환(인덱스는 1부터 시작)
-- instr(컬럼명 혹은 표현식, '문자', 시작위치, n번째) - 지정된 시작위치부터 문자를 검색했을 때 n번째로 등장하는 위치를 반환
-- lpad(컬럼명 혹은 표현식, 길이, '문자') - 지정된 길이보다 짧으면 부족한 길이만큼 지정된 문자를 왼쪽에 채운다.
-- rpad(컬럼명 혹은 표현식, 길이, '문자') - 지정된 길이보다 짧으면 부족한 길이만큼 지정된 문자를 오른쪽에 채운다.
-- trim(컬럼명 혹은 표현식) - 불필요한 좌우 공백을 제거한다.
-- replace(컬럼 혹은 표현식, '찾는문자', '대체할 문자') - 문자를 찾아서 지정된 문자로 대체한다.
select lower('Hello World'), 
       upper('Hello World'),
       substr('Hello World',3), --갯수를 지정하지 않으면 끝까지 가져옴
       substr('Hello World',1,1), --시작점, 길이
       substr('Hello World',3,4),
       length('Hello World'),  -- 11글자
       lengthb('Hello World'), -- 11byte
       length('안녕하세요'),     -- 5글자
       lengthb('안녕하세요'),    -- 15byte
       instr('010-1111-1111','-'), -- 4
       instr('02)1234-5678', ')'), -- 3
       instr('700)1234-5677', ')'), -- 4
       lpad('hi', 10, '#'),
       lpad('hello', 10, '#'),
       lpad('100', 10, '0'),        -- 0000000100
       lpad('12345', 10, '0'),      -- 0000012345
       replace('hello', 'l', '*')   -- he**o
from dual; --oracle에서 지원하는 1행 1열짜리 테이블

-- employees 테이블에서 이름에 'tay'가 포함되는 사원을 조회하기
select first_name
from employees
where lower(first_name) like '%tay%';

-- employees 테이블에서 'MAN'이나 'MGR'로 직종을 가진 사원의 이름 직종아이디 조회하기
select first_name, job_id
from employees
where job_id like '%MAN' or job_id like '%MRG';

select first_name, job_id
from employees
where substr(job_id, 4) in ('MAN','MGR'); -- substr('SA_MAN', 4) -> 'MAN'

-- employees 테이블에서 사용자 아이디, 이름, 원래전화번호, 전화번호 조회하기
-- (미국식 전화번호인 경우 마지막 숫자 4개, 영국식 전화번호인 경우 마지막 13자리 조회)
-- (전화번호의 2번째 구분점부터 조회)
select employee_id, first_name, phone_number, substr(phone_number, instr(phone_number, '.', 1, 2) + 1) short_phone_number
from employees;

-- employees 테이블에서 사원아이디, 이름, 이메일을 조회하기
-- (단, 이메일의 세번째부터 2글자는 **로 바꾸기)
select employee_id, first_name, email,
    lower(replace(email, substr(email, 3, 2), '**')) secret_email
from employees;

select replace('aaabbaabb', substr('aaabbaabb', 3, 2), '**') duplicated_text,
    replace(substr('aaabbaabb',1,4), substr('aaabbaabb',3,2), '**') || substr('aaabbaabb',5) duplicated_text2
from dual;