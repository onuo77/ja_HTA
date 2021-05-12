-- employees 테이블에서 관리자(상사)의 사원아이디를 중복없이 조회하기
select DISTINCT manager_id
from employees
where manager_id is not null;

-- employees 테이블에 등록된 사원들이 소속된 부서아이디를 중복없이 조회하기
-- (소속부서가 없는 사원은 제외시킨다.)
select DISTINCT department_id
from employees
where department_id is not null;

-- employees 테이블에서 사원아이디, 이름, 이메일을 조회하기(이메일은 소문자로 표시)
select employee_id, first_name, lower(email) email
from employees;

-- employees 테이블에서 이름이 A로 시작하는 사원의 아이디, 이름을 조회하기
select employee_id, first_name
from employees
where first_name like 'A%';

select employee_id, first_name
from employees
where substr(first_name,1,1) = 'A'; -- 이것도 되는데 위에꺼 쓰는게 좋음

-- employees 테이블에서 모든 사원들의 아이디, 이름, 급여, 연간 총 수입을 조회하기
-- 연간 총수입은 급여*12 + (급여*12*commission_pct)다.
select employee_id, first_name, salary, salary*12 + (salary*12*commission_pct) annual_salary
from employees;

select employee_id, first_name, salary, salary*12 + salary*12*nvl(commission_pct,0) annual_salary
from employees;

-- employees 테이블에서 80번 부서에서 근무중인 사원들의 사원아이디, 이름, 급여, 세금을 조회하기
-- 14000이상 45%, 10000이상 42%, 7500이상 35%, 5000이상 25%, 2000이상 9%, 그외는 0%다.
select employee_id, first_name, salary,
    case
        when salary >= 14000 then salary*0.45
        when salary >= 10000 then salary*0.42
        when salary >= 7500  then salary*0.35
        when salary >= 5000  then salary*0.25
        when salary >= 2000  then salary*0.09   
        else 0
    end tax
from employees;

-- employees 테이블에서 50번 부서에서 근무중이 사원들의 급여를 15% 인상했을 때,
-- 사원아이디, 이름, 인상전 급여, 인상된 급여, 인상된 급여와 인상전 급여의 차이를 조회하기
select employee_id, first_name, salary,
        salary + (salary*0.15) as Increased_salary,
        (salary + (salary*0.15)) - salary as salary_difference
from employees
where department_id = 50;

-- employees 테이블에서 사원의 이름이 'A'또는 'J' 또는 'M'으로 시작하는 사원의 아이디, 이름을 조회하기
select employee_id, first_name
from employees
where first_name like 'A%'
    or first_name like 'J%'
    or first_name like 'M%';

select employee_id, first_name
from employees
where substr(first_name,1,1) in ('A','J','M');

-- employees 테이블에서 근무를 시작한 날로부터 오늘까지 근무한 개월수를 계산해서 조회하기
-- 사원아이디, 이름, 입사일, 근무한 개월수(개월수는 정수로 반올림한다.)만 조회한다.
select employee_id, first_name, hire_date, 
    round(months_between(sysdate,hire_date)) worked_month
from employees;

-- employees 테이블에서 사원들의 급여를 *의 갯수로 표시하려고 한다.
-- *하나가 1000달러를 나타낸다고 했을 때, 사원의 아이디, 이름, 급여, *을 표시하기
-- 100 홍길동 3400  ***
-- 101 김유신 15000 ***************
-- 102 강감찬 7300  *******
select employee_id, first_name, salary, 
    rpad('*', trunc(salary/1000), '*') secret_salary
from employees;
