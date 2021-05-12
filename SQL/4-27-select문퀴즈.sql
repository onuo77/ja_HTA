-- 모든 부서정보를 조회하기
select *
from departments;

-- 모든 부서소재지정보를 조회하기
select *
from locations;

-- 모든 직종정보를 조회하기
select *
from jobs;

-- 직종정보 테이블에서 직종아이디, 최소급여, 최대급여를 조회하기
select job_id, min_salary, max_salary
from jobs;

-- 부서정보 테이블에서 부서아이디, 부서명을 조회하기
select department_id, department_name
from departments;

-- 사원정보 테이블에서 사원아이디, 이름, 해당직종 시작일, 직종아이디, 급여를 조회하기
select employee_id, first_name, hire_date, job_id, salary
from employees;

-- 사원정보 테이블에서 사원아이디가 100번인 사원의 이름, 전화번호, 이메일, 입사일, 급여를 조회하기
select first_name, phone_number, email, hire_date, salary
from employees
where employee_id = 100;

-- 사원정보 테이블에서 100번 사원을 상사로 두고 있는 사원들의 이름, 입사일, 직종아이디를 조회하기
select employee_id, hire_date, job_id 
from employees
where manager_id = 100;

-- 직종테이블에서 직종 최소급여가 10000 이상인 직종의 아이디, 제목, 최소급여, 최대급여를 조회하기
select job_id, job_title, min_salary, max_salary
from jobs
where min_salary >= 10000;

-- 사원테이블에서 커미션을 받는 사원 중에서 급여를 5000이하 받는 사원의 아이디, 이름, 직종, 급여를 조회하기
select employee_id, first_name, job_id, salary
from employees
where commission_pct is not null
and salary <= 5000;

-- 사원테이블에서 직종이 'MAN'이나 'MGR'로 끝나는 사원중에서 급여를 10000이하 받는 사원의 아이디, 
-- 이름, 직종아이디, 급여를 조회하기
select employee_id, first_name, job_id, salary
from employees
where (job_id like '%MAN' or job_id like '%MGR')
and salary <= 10000;

-- 사원정보테이블에서 커미션을 받는 사원중에서 147번 사원을 상사를 두고 있으면서, 급여를 10000이상 받는
-- 사원의 아이디, 사원이름, 급여, 커미션을 조회하기
select employee_id, first_name, salary, commission_pct
from employees
where commission_pct is not null
and manager_id = 147
and salary >= 10000;

-- 사원정보테이블에서 101번과 102번이 상사로 정해진 사원들의 아이디, 이름, 상사아이디를 조회하기
select employee_id, first_name, manager_id
from employees
where manager_id in (101,102);

-- 사원정보테이블에서 소속부서가 결정되지 않은 사원의 아이디, 이름, 직종아이디를 조회하기
select employee_id, first_name, job_id
from employees
where department_id is null;

-- 사원정보테이블에서 60번 부서에 소속된 사원들의 아이디, 이름, 월급, 연봉을 조회하기
-- (salary는 월급이다. 연봉은 별칭을 부여한다.)
select employee_id, first_name, salary, salary*12 as annual_salary
from employees
where department_id = 60;