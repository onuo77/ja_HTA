-- 사원아이디, 이름, 소속부서 아이디, 소속부서명을 조회하기
select e.employee_id, e.first_name, e.department_id, d.department_name
from employees e, departments d
where e.department_id = d.department_id;

-- 60번부서에 소속된 사원들의 사원아이디, 이름, 급여, 직종아이디, 직종제목, 직종최저급여, 직종최고급여를 조회하기
select e.employee_id, e.first_name, e.salary, e.job_id, j.job_title, j.min_salary, j.max_salary
from employees e, jobs j
where e.department_id = 60
and e.job_id = j.job_id;

-- 모든 사원들의 사원아이디, 이름, 급여, 급여등급을 조회하기
select e.employee_id, e.first_name, e.salary, s.grade
from employees e, salary_grade s
where e.salary >= s.min_salary
and e.salary <= s.max_salary
order by e.employee_id asc;

-- 60번부서에 소속된 사원들의 사원아이디, 이름, 상사의 이름을 조회하기
select emp.employee_id, emp.first_name, mgr.first_name
from employees emp, employees mgr
where emp.department_id = 60
and emp.manager_id = mgr.employee_id;

-- 부서관리자가 있는 부서의 부서아이디, 부서명, 부서관리자 아이디, 부서관리자이름을 조회하기
select d.department_id, d.department_name, d.manager_id, e.first_name
from departments d, employees e
where d.manager_id = e.employee_id
order by d.department_id asc;

-- 부서관리자가 있는 부서의 부서소재지 도시명을 중복없이 조회하기
select DISTINCT l.city
from departments d, locations l
where d.manager_id is not null;

-- 소속부서명이 'Sales'이고, 급여등급이 'A'나 'B'에 해당하는 사원들의 아이디, 이름, 급여, 급여등급을 조회하기
select e.employee_id, e.first_name, e.salary, s.grade
from employees e, salary_grade s, departments d 
where d.department_name = 'Sales'
and e.salary >= s.min_salary
and e.salary <= s.max_salary
and s.grade in ('A','B');

-- 60번 부서에 소속된 사원들의 평균급여를 조회하기
select avg(salary)
from employees
where department_id = 60;

-- 직종아이디별 사원수를 조회하기
select job_id, count(*)
from employees
group by job_id;

-- 급여 등급별 사원수를 조회하기
select s.grade, count(e.employee_id)
from salary_grade s, employees e
where e.salary >= s.min_salary
and e.salary <= s.max_salary
group by s.grade;

-- 2007년 입사한 사원들의 월별 입사자 수를 조회하기
select to_char(hire_date, 'mm') hire_month, count(*)
from employees
where hire_date >= to_date('2007/01/01') and hire_date < ('2008/01/01')
group by to_char(hire_date, 'mm')
order by hire_month;