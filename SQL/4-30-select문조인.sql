-- 비등가조인 (주로 어떤 등급을 판정할 때 사용함)
-- 모든 사원들의 아이디,이름,급여,급여등급을 조회하기
-- 아이디, 이름, 급여 - employees
-- 급여등급 = salary_grade
-- 사원의 급여가 급여등급정보의 최소급여 이상이고, 최대급여 이하인 행과 조인
select e.employee_id, e.first_name, e.salary, s.grade
from employees E, salary_grade S
where e.salary >= s.min_salary and e.salary <= s.max_salary
order by e.employee_id;

-- 자체조인(셀프조인) : 똑같은 테이블을 조인하는 것
-- 하나의 테이블을 역할을 나눠서 두개처럼 다루는 것
-- 60번 부서에 소속된 사원들의 사원아이디, 사원이름, 상사의 아이디, 상사의 이름 조회하기
-- 사원아이디, 사원이름 - employees
-- 상사의 아이디, 상사의 이름 - employees
select e.employee_id emp_id, e.first_name emp_name, 
       m.employee_id mgr_id, m.first_name mgr_name
from employees E, employees M
where E.department_id = 60
and e.manager_id = m.employee_id;

-- 60번 부서에 소속된 사원들의 사원아이디, 사원이름, 사원의 소속부서명,
--                          상사의 아이디, 상사의 이름, 상사의 소속부서명 조회하기
-- 사원아이디, 사원이름 - employees
-- 상사의 아이디, 상사의 이름 - employees
-- 사원의 소속부서명 - departments
-- 상사의 소속부서명 - departments
select e.employee_id    emp_id,     e.first_name       emp_name, 
       ed.department_id edept_id,   ed.department_name edept_name,
       m.employee_id    mgr_id,     m.first_name       mgr_name, 
       md.department_id mdept_id,   md.department_name mdept_name
from employees E, employees M, departments ED, departments MD
where e.department_id = 60 
and e.manager_id = m.employee_id 
and e.department_id = ed.department_id
and m.department_id = md.department_id
order by emp_id asc; 
-- order by에서는 별칭 사용가능 (where 조건절에서는 별칭 사용불가)
-- select된 것에서 정렬하기 때문에 가능 (order by가 가장 마지막에 실행되기 때문에)
-- order by 컬럼순서(숫자) asc; 이런식으로 컬럼에 따른 정렬도 가능함
-- ex) order by 1 asc;/ order by 2 asc;/ order by 8 asc; ...
-- 위의 경우 1~8까지 가능 (1:emp_id, 2:emp_name, 3:edept_id ... 8:mdept_name)