-- 부서아이디, 부서명, 부서관리자아이디, 부서관리자 이름 조회하기
-- 부서아이디, 부서명, 부서관리자아이디 - departments
-- 부서관리자 이름 - employees
select d.department_id, d.department_name, d.manager_id, e.first_name
from departments D, employees E
where D.manager_id = e.employee_id
order by d.department_id asc;

-- 포괄 조인을 활용하면, employees테이블과 매칭되지 않는 departments의 부서정보도 조회할 수 있다.
select d.department_id, d.department_name, d.manager_id, e.first_name
from departments D, employees E
where D.manager_id = e.employee_id(+)
order by d.department_id asc;

-- 커미션을 받는 사원들의 사원아이디, 이름, 소속부서아이디, 소속부서명 조회하기
select E.employee_id, E.first_name, e.department_id, d.department_name
from employees E, departments D
where E.department_id = D.department_id(+)
and e.commission_pct is not null
order by e.employee_id;

-- 90번 부서에서 소속된 사원들의 사원아이디, 이름, 해당 사원의 상사의 이름을 조회하기
select emp.employee_id, emp.first_name emp_name, mgr.first_name mgr_name
from employees emp, employees mgr
where emp.department_id = 90
and emp.manager_id = mgr.employee_id(+);

-- 오라클조인과 ANSI-조인
-- 90번 부서에 소속된 사원들의 아이디, 이름, 직종제목 조회하기
-- 오라클조인
select E.employee_id, E.first_name, J.job_id, J.job_title
from employees E, jobs J
where E.department_id = 90
and E.job_id = J.job_id;

-- ANSI-조인
select E.employee_id, E.first_name, J.job_id, J.job_title
from employees E join jobs J on E.job_id = J.job_id
where E.department_id = 90;

-- 90번 부서에 소속된 사원의 아이디, 이름, 직종아이디, 직종제목, 소속부서 아이디, 소속부서명 조회하기
-- 사원의 아이디, 이름, 직종아이디, 소속부서아이디 - employees
-- 직종아이디, 직종제목 - jobs
-- 소속부서아이디, 소속부서명 - departments
-- 오라클조인
select e.employee_id, e.first_name, j.job_id, j.job_title, d.department_id, d.department_name
from employees E, jobs J, departments D
where e.department_id = 90
and E.job_id = J.job_id
and E.department_id = d.department_id;

-- ANSI-조인
select e.employee_id, e.first_name, j.job_id, j.job_title,
       d.department_id, d.department_name
from employees E join jobs J        on e.job_id = j.job_id
                 join departments D on E.department_id = d.department_id
where e.department_id = 90;

-- 90번 부서에 소속된 사원의 아이디, 이름, 급여, 급여등급을 조회하기
-- 오라클조인
select e.employee_id, e.first_name, e.salary, s.grade
from employees e, salary_grade S
where e.department_id = 90
and e.salary >= s.min_salary and e.salary <= s.max_salary;

-- ANSI-조인
select e.employee_id, e.first_name, e.salary, s.grade
from employees E join salary_grade S on e.salary >= s.min_salary and e.salary <= s.max_salary
where e.department_id = 90;

-- 부서아이디, 부서명, 부서담당자 아이디, 부서담당자 이름 조회하기
-- 부서담당자가 결정되어 있지 않은 부서도 조회한다.
select D.department_id, d.department_name, d.manager_id, e.first_name
from departments d, employees E
where d.manager_id = e.employee_id(+);

select d.department_id, d.department_name, d.manager_id, e.first_name
from departments D left outer join employees e on d.manager_id = e.employee_id;
-- left outer join : 선행테이블의 모든 행이 조회되도록 한다.

select d.department_id, d.department_name, d.manager_id, e.first_name
from employees e right outer join departments D on e.employee_id = d.manager_id;
-- right outer join : 후행테이블의 모든 행이 조회되도록 한다.