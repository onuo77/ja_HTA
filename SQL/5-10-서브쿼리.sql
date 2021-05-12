-- 자신이 소속되어 있는 부서의 평균급여보다 급여를 많이 받는 사원들의 아이디, 이름, 급여 조회하기
select o.employee_id, o.first_name, o.salary
from employees o
where o.salary > (select avg(i.salary)
                    from employees i
                    where i.department_id = o.department_id);
                    
-- 사용자아이디, 이름, 급여, 자신이 소속된 부서의 평균급여를 조회하기
select o.employee_id, o.first_name, o.department_id, o.salary, 
        (select trunc(avg(i.salary))
        from employees i
        where i.department_id = o.department_id) avg_salary
from employees o;

-- 부서아이디, 부서명, 해당부서의 관리자 이름, 소속된 사원수를 조회하기
select o.department_id, o.department_name,
    (select i.first_name
    from employees i
    where i.employee_id = o.manager_id) manager_name,
    (select count(*)
    from employees I
    where I.department_id = o.department_id) as employee_cnt
from departments O;

-- 스칼라 서브쿼리
-- 서브쿼리의 실행결과가 단일행-단일컬럼인 경우(오직 값이 하나만 조회되는 서브쿼리)
-- 사원아이디, 이름, 급여, 사원전체평균급여를 조회하기
select O.employee_id, O.first_name, O.salary, 
        (select trunc(avg(I.salary)) from employees I) avg_salary
from employees O;

-- 스칼라 서브쿼리를 update문에서 사용하기
-- 사원들의 급여를 전체사원의 평균급여의 10%만큼 인상시키기
update employees
set
    salary = salary + (select trunc(avg(salary)*0.1) from employees);

-- 스칼라 서브쿼리를 insert문에서 사용하기
-- 새로운 사원정보를 추가하기
insert into employees
(employee_id, first_name, last_name, email, phone_number, 
hire_date, job_id, salary, commission_pct, manager_id, department_id)
values
(employees_seq.nextval, 'Gildong', 'Hong', 'GILDONG', '010.1111.2222',
sysdate, 'IT_PROG', (select min_salary from jobs where job_id = 'IT_PROG'), null, 103, 60);
