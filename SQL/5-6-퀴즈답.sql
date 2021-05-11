-- 사원아이디e, 이름e, 소속부서 아이디e/d, 소속부서명d을 조회하기
select e.employee_id, e.first_name, d.department_id, d.department_name
from employees e, departments d
where e.department_id = d.department_id;

-- 60번부서e에 소속된 사원들의 사원아이디e, 이름e, 급여e, 직종아이디e, 직종제목j, 직종최저급여j, 직종최고급여j를 조회하기
select E.employee_id, e.first_name, e.salary, j.job_id, j.job_title, j.min_salary, j.max_salary
from employees e, jobs j
where e.department_id = 60
and e.job_id = j.job_id;

-- 모든 사원들의 사원아이디e, 이름e, 급여e, 급여등급g을 조회하기
select e.employee_id, e.first_name, e.salary, g.grade
from employees E, salary_grade g
where e.salary >= g.min_salary
and e.salary <= g.max_salary
order by e.employee_id;

-- 60번부서에 소속된 사원들의 사원아이디e, 이름e, 상사의 이름e을 조회하기
select e.employee_id, e.first_name employee_name, m.first_name manager_name
from employees E, employees M
where e.department_id = 60
and e.manager_id = m.employee_id;

-- 부서관리자가 있는 부서의 부서아이디d, 부서명d, 부서관리자 아이디d, 부서관리자이름e을 조회하기
select d.department_id, d.department_name, d.manager_id, e.first_name
from departments D, employees E
where d.manager_id is not null
and d.manager_id = e.employee_id;

-- 부서관리자d가 있는 부서의 부서소재지 도시명l을 중복없이 조회하기
select DISTINCT l.city
from departments d, locations l
where d.manager_id is not null
and d.location_id = l.location_id;

-- 소속부서명d이 'Sales'이고, 급여등급g이 'A'나 'B'에 해당하는 사원들의 아이디e, 이름e, 급여e, 급여등급g을 조회하기
select e.employee_id, e.first_name, e.salary, g.grade
from departments d, salary_grade g, employees e
where d.department_name = 'Sales'               -- 소속부서명이 'Sales'인 행
and d.department_id = e.department_id           -- 부서정보의 부서아이디와 같은 부서아이디를 가진 사원정보 조인
and e.salary >= g.min_salary and e.salary <= g.max_salary -- 사원의 급여와 급여등급정보를 조인
and g.grade in ('A', 'B', 'C', 'D');                                -- 급여등급이 'A'나 'B'인 경우

-- 60번 부서에 소속된 사원들의 평균급여를 조회하기
select avg(salary)
from employees
where department_id = 60;

-- 직종아이디e,j별 사원수e를 조회하기
select job_id, count(*) cnt
from employees
group by job_id;

-- 급여 등급별g 사원e수를 조회하기
select g.grade, count(*) cnt
from employees E, salary_grade G
where E.salary >= g.min_salary and e.salary <= g.max_salary
group by g.grade
order by g.grade asc;

-- 2007년 입사e한 사원들의 월별e 입사자 수e를 조회하기
select to_char(hire_date, 'mm') hire_month, count(*) cnt
from employees
where hire_date >= to_date('2007/01/01') and hire_date < to_date('2008/01/01')
group by to_char(hire_date, 'mm')
order by hire_month;