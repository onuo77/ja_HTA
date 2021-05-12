-- 테이블 조회하기
-- 사원아이디, 사원명, 소속부서아이디, 소속부서명
-- employees 테이블 -> 사원아이디, 사원명, 소속부서아이디
-- departments 테이블 -> 소속부서명 
select employees.employee_id, employees.first_name, employees.department_id,
        departments.department_name
from employees, departments
where employees.department_id = departments.department_id;

select A.employee_id, A.first_name, B.department_id, B.department_name
from employees A, departments B
where A.department_id = B.department_id;

-- 테이블 조인하기
-- 사원아이디, 이름, 직종아이디, 급여, 직종제목, 직종 최소 급여, 직종 최대 급여 조회하기
-- employees 테이블 -> 사원아이디, 이름, 직종아이디, 급여
-- jobs 테이블 -> 직종아이디, 직종제목, 직종 최저급여, 직종 최고급여
select a.employee_id, a.first_name, a.salary, a.job_id, b.min_salary, b.max_salary
from employees A, jobs B
where a.job_id = b.job_id;

-- 테이블 조인하기
-- 부서아이디, 부서명, 소재지 아이디, 소재지 주소, 소재지 도시명 조회하기
-- departments -> 부서아이디, 부서명, 소재지아이디
-- locations -> 소재지 주소, 소재지 도시명
select a.department_id, a.department_name, a.location_id,
       b.street_address, b.city
from departments a, locations b
where a.location_id = b.location_id;

-- 테이블 조인하기
-- 부서관리자가 지정되어 있는 부서의 부서아이디, 부서명, 부서관리자 아이디, 부서관리자 이름 조회하기
-- departments -> 부서아이디, 부서명, 부서관리자아이디
-- employees -> 부서관리자이름(사원이름)
-- departments의 manager_id는 부서관리자로 지정된 사원의 사원아이디다.
-- departments의 manager_id와 동일한 사원아이디를 가진 사원정보가 조인되어야한다.
select a.department_id, a.department_name, a.manager_id,
        b.first_name
from departments a, employees b
where a.manager_id = b.employee_id
and a.manager_id is not null;

-- 3개 이상의 테이블 조인하기
-- 사원아이디, 사원이름, 직종아이디, 급여, 직종최저급여, 직종최고급여, 소속부서 아이디, 부서명
-- employees -> 사원아이디, 사원이름, 직종아이디, 급여, 소속부서아이디
-- jobs -> 직종최저급여, 직종최고급여
-- departments -> 부서명
select e.employee_id, e.first_name,
        e.salary, j.min_salary, j.max_salary,
        e.department_id, d.department_name
from employees E, jobs J, departments D
where e.job_id = j.job_id
and e.department_id = d.department_id
order by e.employee_id asc;

-- 100번 사원의 이름, 전화번호, 이메일, 
-- 소속부서아이디, 소속부서명, 소속부서 도시명, 소속부서 소재지주소 조회하기
select e.first_name, e.phone_number, e.email,
        d.department_id, d.department_name,
        l.city, l.street_address
from employees E, departments D, locations L
where E.employee_id = 100 -- 검색조건
and E.department_id = D.department_id
and D.location_id = L.location_id;

-- 50번 부서에 소속된 사원들의 사원아이디, 이름, 직종아이디, 직종제목 조회하기
-- 사원아이디, 이름, 직종아이디 --> employees
-- 직종제목 --> jobs
select e.employee_id, e.first_name, e.job_id, j.job_title
from employees E, jobs J
where e.job_id = j.job_id 
and e.department_id = 50;

-- 급여를 15000이상 받은 사원들의 이름, 직종아이디, 급여, 부서명을 조회하기
-- 급여를 15000이상, 급여, 이름, 직종아이디 -> employees
-- 부서명 --> departments
select e.first_name, e.job_id, e.salary, d.department_name
from employees E, departments D
where e.department_id = d.department_id
and e.salary >= 15000;

-- 100 직원이 관리하는 사원들의 이름, 직종아이디, 직종제목, 부서아이디, 부서명 조회하기
-- 100번 직원, 이름, 직종아이디, 부서아이디 --> employees
-- 직종제목 --> jobs
-- 부서명 --> departments
select e.first_name, e.job_id, j.job_title, 
        d.department_id, d.department_name
from employees E, jobs J, departments D
where e.manager_id = 100
and e.department_id = d.department_id
and e.job_id = j.job_id;