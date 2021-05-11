-- 뷰(가상의 테이블)
-- 부서 상세정보를 제공하는 가상의 테이블 생성하기
-- 부서 상세정보 - 부서아이디d, 부서명d, 관리자 아이디d-e, 관리자이름e,
-- 부서에 소속된 사원수e, 부서의 소새지l, 주소l
create or replace view departments_detail_view
as
select d.department_id, d.department_name, d.manager_id, e.first_name, e.last_name,
        l.city, l.street_address, 
        (select count(*) from employees I where I.department_id = d.department_id) emp_cnt
from departments D, employees E, locations L
where d.manager_id = e.employee_id(+)
and d.location_id = l.location_id;

-- 60번 부서의 상세정보 조회하기
-- View 사용하기 전
select d.department_id, d.department_name, d.manager_id, e.first_name, e.last_name,
        l.city, l.street_address, 
        (select count(*) from employees I where I.department_id = d.department_id) emp_cnt
from departments D, employees E, locations L
where d.manager_id = e.employee_id(+)
and d.location_id = l.location_id
and d.department_id = 60;
--View 사용한 후
select department_id, department_name, manager_id, first_name, last_name, 
        city, street_address, emp_cnt
from departments_detail_view
where department_id = 60;

-- 사원의 상세정보를 제공하는 뷰 생성하기
-- 사원아이디, 이름, 이메일, 전화번호, 입사일, 근무개월수, 근무년수, 급여, 커미션, 급여등급,
-- 연봉, 상사의 아이디, 상사이름, 직종아이디, 직종명, 부서아이디, 부서명, 부서관리자이름
create or replace view employees_detail_view
as
select E.employee_id as emp_id,
        E.first_name || ', ' || E.last_name as full_name,
        E.email, 
        E.phone_number, 
        E.hire_date, 
        trunc(months_between(sysdate, E.hire_date)) work_months,
        trunc(months_between(sysdate, E.hire_date)/12) working_year,
        E.salary, 
        E.commission_pct, 
        G.grade, 
        E.salary*12 + E.salary*12*nvl(E.commission_pct, 0) annual_salary,
        M.employee_id as manager_id,
        M.first_name || ', ' || M.last_name as manager_name,
        E.job_id,
        J.job_title,
        E.department_id,
        D.department_name,
        DM.employee_id dept_manager_id,
        DM.first_name || ', ' || DM.last_name as dept_manager_name        
from employees E, salary_grade G, employees M, jobs J, departments D, employees DM
where E.salary >= G.min_salary and E.salary <= G.max_salary
and E.manager_id = M.employee_id(+)
and E.job_id = J.job_id
and E.department_id = D.department_id
and D.manager_id = DM.employee_id(+);

-- 200번 사원의 아이디, 이름, 급여, 급여등급, 연봉, 직종아이디, 소속부서명 조회
select emp_id, full_name, salary, grade, annual_salary, job_id, department_name
from employees_detail_view
where emp_id = 200;

-- 급여등급별 사원수를 조회하기
select grade, count(*)
from employees_detail_view
group by grade
order by grade;

-- 입사일 기준 근무년수가 15년 이상인 사원의 아이디, 이름, 입사일, 근무년수를 조회하기
select emp_id, full_name, hire_date, working_year
from employees_detail_view
where working_year >= 15
order by working_year desc;

-- 연봉이 20만불 이상인 사원의 아이디, 이름, 급여, 급여등급, 연봉, 직종을 조회하기
select emp_id, full_name, salary, grade, annual_salary, job_id
from employees_detail_view
where annual_salary >= 200000;

-- 인라인 뷰
-- 직종별 사원수를 조회하기
-- 직종아이디, 직종제목, 직종최소급여, 직종최대급여, 사원수
-- select job_id, count(*)
-- from employees
-- group job_id
select A.job_id, B.job_title, B.min_salary, B.max_salary, A.cnt
from (select job_id, count(*) cnt
       from employees
       group by job_id) A, jobs B
where A.job_id = B.job_id;

-- Top-N 분석
-- 지정된 컬럼의 값을 기준으로 행을 정렬하고, 상위 N개의 열을 조회하는 것

-- 연봉에 대한 내림차순으로 정렬했을 때 상위 3명의 아이디, 이름, 연봉을 조회하기
select rownum, emp_id, full_name, annual_salary
from (select emp_id, full_name, annual_salary
        from employees_detail_view
        order by annual_salary desc)
where rownum <= 3;

-- 직종별 인원수를 조회했을 때, 인원수가 가장 많은 직종 3개를 조회하기
select rownum, job_id, cnt
from (select job_id, count(*) cnt
        from employees
        group by job_id
        order by cnt desc)
where rownum <= 3;

-- 직종별 인원수를 조회했을 때, 인원수를 기준으로 내림차순 정렬하고, 
-- 4번째~6번째에 해당하는 행을 조회하기
select ranking, job_id, cnt
from (select rownum ranking, job_id, cnt
      from(select job_id, count(*) cnt
            from employees
            group by job_id
            order by cnt desc))
where ranking >= 6 and ranking <=10;