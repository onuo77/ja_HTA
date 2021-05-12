-- 모든 사원들의 사원아이디, 이름, 소속부서아이디, 소속부서명을 조회하기
-- department_id가 null값인 사원은 조회불가
select e.employee_id, e.first_name, e.department_id, d.department_name
from employees e, departments d
where e.department_id = d.department_id
order by e.employee_id asc;

-- 모든 사원들의 사원아이디, 이름, 직종아이디, 직종제목을 조회하기
select e.employee_id, e.first_name, j.job_id, j.job_title
from employees e, jobs j
where e.job_id = j.job_id
order by e.employee_id asc;

-- 사원들 중에서 소속부서가 정해지지 않는 사원의 아이디, 이름, 급여, 직종, 직종최소급여, 직종최대급여를 조회하기
select e.employee_id, e.first_name, e.salary, j.min_salary, j.max_salary
from employees e, jobs j
where e.department_id is null
and e.job_id = j.job_id;

-- 2007년에 입사한 사원들의 사원아이디, 이름, 소속부서아이디, 소속부서명을 조회하기
select e.employee_id, e.first_name, e.department_id, d.department_name
from employees e, departments d
where to_char(e.hire_date, 'yyyy') = 2007
and e.department_id = d.department_id
order by e.employee_id asc;

-- 커미션을 받은 사원들의 사원아이디, 이름, 직종아이디, 직종제목, 소속부서아이디, 소속부서명을 조회하기
select e.employee_id, e.first_name, e.job_id, j.job_title, d.department_id, d.department_name
from employees e, jobs j, departments d
where e.commission_pct is not null
and e.job_id = j.job_id
and e.department_id = d.department_id
order by e.employee_id asc;

-- 직종최소급여가 10000달러 이상인 직종에 종사하고 있는 사원들의 아이디, 이름, 직종아이디, 급여를 조회하기
select e.employee_id, e.first_name, j.job_id, e.salary
from jobs j, employees e
where j.min_salary >= 10000
and j.job_id = e.job_id
order by e.employee_id asc;

-- 모든 사원들의 사원아이디, 이름, 소속부서아이디, 소속부서명, 해당부서의 소재지 도시 및 주소를 조회하기
select e.employee_id, e.first_name, 
        d.department_id, d.department_name, 
        L.city, L.street_address
from employees e, departments d, locations L
where e.department_id = d.department_id 
and d.location_id = l.location_id;

-- 모든 사원들의 사원아이디, 이름, 직종아이디, 직종제목, 소속부서아이디, 소속부서명, 해당부서의 소재지 도시 및 주소
select e.employee_id, e.first_name, 
        j.job_id, j.job_title,
        d.department_id, d.department_name,
        L.city, L.street_address
from employees e, jobs j, departments d, locations L
where e.department_id = d.department_id
and e.job_id = j.job_id
and d.location_id = L.location_id;

-- 부서 소재지 국가가 미국에 위치하고 있는 부서의 부서아이디, 부서명, 도시명을 조회하기
select d.department_id, d.department_name, L.city
from departments d, locations L
where d.location_id = L.location_id
and l.country_id = 'US';

-- 'Seattle'에서 근무하고 있는 사원들의 사원아이디, 이름, 부서아이디, 부서명을 조회하기
select e.employee_id, e.first_name, e.department_id, d.department_name
from employees e, departments d, locations L
where e.department_id = d.department_id
and d.location_id = L.location_id
and L.city = 'Seattle'; 

-- 2006년에 job이 변경된 기록이 있는 사원들의 사원아이디, 이름, 직종아이디를 조회하기
select e.employee_id, e.first_name, e.job_id
from employees e, job_history jh
where jh.start_date >= to_date('2006-01-01', 'yyyy-mm-dd') 
and jh.end_date < to_date('2007-01-01', 'yyyy-mm-dd')
and e.employee_id = jh.employee_id;

-- 이전에 50번 부서에서 근무한 적이 있는 사원들의 사원아이디, 이름, 근무시작일, 근무종료일, 근무당시 직종아이디를 조회하기
select e.employee_id, e.first_name, jh.start_date, jh.end_date, jh.employee_id
from employees e, job_history jh
where jh.department_id = 50
and e.employee_id = jh.employee_id;

-- 사원들 중에서 자신이 근무하고 있는 직종의 최소급여와 최대급여의 평균값보다 적은 급여를 받는 
-- 사원의 아이디, 이름, 직종아이디, 급여, 최소급여, 최대급여, 최소/최대급여 평균값을 조회하기
-- (평균값은 (최소급여+최대급여)/2로 구한다)
 select e.employee_id, e.first_name, e.job_id, e.salary,
        j.min_salary, j.max_salary, (j.min_salary + j.max_salary)/2 avg
 from jobs j, employees e
 where e.salary < (j.min_salary + j.max_salary)/2
 and e.job_id = j.job_id
 order by e.employee_id asc;