-- 모든 사원들의 사원아이디, 이름, 소속부서아이디, 소속부서명을 조회하기
-- employees - 사원아이디, 이름, 소속부서아이디
-- departments - 소속부서명
-- 사원이 소속되어 있는 부서의 이름 : 사원의 소속부서아이디 = 부서의 아이디
-- department_id가 null값인 사원은 조회불가
select e.employee_id, e.first_name, e.department_id, d.department_name
from employees e, departments d
where e.department_id = d.department_id
order by e.employee_id asc;

-- 모든 사원들의 사원아이디, 이름, 직종아이디, 직종제목을 조회하기
-- 사원아이디, 이름, 직종아이디 -> employees
-- 직종아이디, 직종제목 -> jobs
-- 사원이 수행하는 직종의 구체적인 제목 : 사원의 직종아이디 = 직종의 직종아이디
select e.employee_id, e.first_name, j.job_id, j.job_title
from employees e, jobs j
where e.job_id = j.job_id
order by e.employee_id asc;

-- 사원들 중에서 소속부서가 정해지지 않는 사원의 아이디, 이름, 급여, 직종아이디, 직종최소급여, 직종최대급여를 조회하기
-- 검색조건 : 소속부서가 없는 사원
-- 사원아이디, 이름, 급여, 직종아이디 - employees
-- 직종아이디 - 최소급여, 최대급여 - jobs
-- 사원이 수행하는 직종의 최소급여, 최대급여 : 사원의 직종아이디 = 직종의 직종아이디
select e.employee_id, e.first_name, e.salary, j.job_id, j.min_salary, j.max_salary
from employees e, jobs j
where e.department_id is null
and e.job_id = j.job_id;

-- 2007년에 입사한 사원들의 사원아이디, 이름, 소속부서아이디, 소속부서명을 조회하기
-- 검색조건 : 2007년 입사자
-- 사원아이디, 이름, 소속부서아이디 - employees
-- 소속부서아이디, 소속부서명 - departments
-- 2007년 입사자들의 소속부서명 : 입사자들의 소속부서아이디 = 부서의 부서아이디
select e.employee_id, e.first_name, e.department_id, d.department_name
from employees e, departments d
where to_char(e.hire_date, 'yyyy') = '2007'
and e.department_id = d.department_id
order by e.employee_id asc;
-- ↑위처럼 컬럼값을 가공하는 방법은 별로 좋지않은 방법
-- ↓컬럼값을 가공하지 않고 그대로 사용하는 아래의 방법이 더 좋은 방법
select e.employee_id, e.first_name, e.department_id, d.department_name 
from employees E, departments D
where E.hire_date >= to_date('2007/01/01')
and E.hire_date < to_date('2008/01/01')
and e.department_id = d.department_id
order by e.employee_id asc; 

-- 커미션을 받은 사원들의 사원아이디, 이름, 직종아이디, 직종제목, 소속부서아이디, 소속부서명을 조회하기
-- 검색조건 : 커미션을 받는 사원
-- 사원아이디, 이름, 직종아이디, 소속부서아이디 - employees
-- 직종아이디, 직종제목 - jobs
-- 부서아이디, 부서명 - departments
-- 사원의 직종아이디에 대한 구체적인 직종제목 조회 - 사원이 수행중인 직종아이디 = 직종의 직종아이디 
-- 사원의 소속부서아이디에 대한 부서정보 조회 - 사원이 소속된 부서아이디 = 부서의 부서아이디
select e.employee_id, e.first_name, e.job_id, j.job_title, d.department_id, d.department_name
from employees e, jobs j, departments d
where e.commission_pct is not null
and e.job_id = j.job_id
and e.department_id = d.department_id
order by e.employee_id asc;

-- 직종최소급여가 10000달러 이상인 직종에 종사하고 있는 사원들의 아이디, 이름, 직종아이디, 급여를 조회하기
-- 검색조건 : 직종최소급여가 10000달러 이상인 직종 - jobs
-- 사원아이디, 이름, 직종아이디, 급여 - employees
-- 직종최소급여가 10000달러 이상인 직종의 아이디와 같은 직종아이디를 가진 사원들을 조인
-- (최소급여가 10000달러 이하인 직종은 조인조건 위배, 조회대상에서 제외됨)
select e.employee_id, e.first_name, e.job_id, e.salary
from jobs j, employees e
where j.min_salary >= 10000
and j.job_id = e.job_id
order by e.employee_id asc;

-- 모든 사원들의 사원아이디, 이름, 소속부서아이디, 소속부서명, 해당부서의 소재지 도시 및 주소를 조회하기
-- 사원아이디, 이름, 소속부서아이디 - employees
-- 부서이이디, 소속부서명 - departments
-- 도시, 주소 - locations
-- 사원의 소속부서아이디와 같은 부서아이디를 가진 부서정보 조인
-- 조인된 부서정보의 소재지 아이디와 같은 소재지 아이디를 가진 소재지 정보 조인
select e.employee_id, e.first_name, 
        d.department_id, d.department_name, 
        L.city, L.street_address
from employees e, departments d, locations L
where e.department_id = d.department_id 
and d.location_id = l.location_id;

-- 모든 사원들의 사원아이디, 이름, 직종아이디, 직종제목, 소속부서아이디, 소속부서명, 해당부서의 소재지 도시 및 주소
-- 사원아이디, 이름, 직종아이디, 소속부서아이디 - employees
-- 직종아이디, 직종제목 - jobs
-- 소속부서아이디, 소속부서명 - departments
-- 부서 소재지 도시, 주소 - locations
-- 사원이 수행중인 직종아이디와 같은 직종아이디를 가진 직종정보조인
-- 사원이 소속된 부서아이디와 같은 부서아이디를 가진 부서정보 조인
-- 조인된 부서정보의 소재지 아이디와 같은 소재지 아이디를 가진 소재지정보 조인
select e.employee_id, e.first_name, e.job_id, j.job_title,
        d.department_id, d.department_name,
        L.city, L.street_address
from employees e, jobs j, departments d, locations L
where e.department_id = d.department_id
and e.job_id = j.job_id
and d.location_id = L.location_id;

-- 부서 소재지 국가가 미국에 위치하고 있는 부서의 부서아이디, 부서명, 도시명을 조회하기
-- 검색조건 : 부서 소재지 국가가 미국인 경우(locations)
-- 부서아이디, 부서명 - departments
-- 부서소재가 미국인 소재지 정보의 소재지 아이디와 동일한 소재지아이디를 가진 부서 조인
select d.department_id, d.department_name, L.city
from departments d, locations L
where d.location_id = L.location_id
and l.country_id = 'US';

-- 'Seattle'에서 근무하고 있는 사원들의 사원아이디, 이름, 부서아이디, 부서명을 조회하기
-- 검색조건 : 소속부서 소재지 도시가 Seattle인 경우(locations)
-- 사원아이디, 이름, 소속부서아이디 - employees
-- 부서아이디, 부서명 - departments
-- 부서소재지가 'Seattle'인 소재지의 소재지 아이디와 동일한 소재지 아이디를 가진 부서정보 조인
-- 조인된 부서정보의 부서아이디와 동일한 부서아이디를 가진 사원정보 조인
select e.employee_id, e.first_name, e.department_id, d.department_name
from employees e, departments d, locations L
where L.city = 'Seattle'
and d.location_id = L.location_id
and e.department_id = d.department_id; 

-- 2006년에 job이 변경된 기록이 있는 사원들의 사원아이디, 이름, 직종아이디를 조회하기
-- 검색조건 : 2006년에 job이 변경된 사원(job_history)
-- 사원아이디, 이름, 직종아이디 - employees
-- 직종이 2006년에 변경된 기록이 있는 사원의 아이디와 같은 사원아이디를 가진 사원정보 조인
select e.employee_id, e.first_name, e.job_id
from job_history H, employees E
where H.start_date >= '2006/01/01' and H.start_date < '2007/01/01'
and h.employee_id = e.employee_id;

select e.employee_id, e.first_name, e.job_id
from job_history H, employees E
where ((H.start_date >= '2006/01/01' and H.start_date < '2007/01/01')
       or (H.end_date >= '2006/01/01' and H.end_date < '2007/01/01')) 
       -- or는 사용할때 꼭 앞뒤로 ()해줘야함 and랑 겹침
and h.employee_id = e.employee_id;

-- 이전에 50번 부서에서 근무한 적이 있는 사원들의 사원아이디, 이름, 근무시작일, 근무종료일, 근무당시 직종아이디를 조회하기
-- 검색조건 : 50번부서에 근무했던(job_history)
-- 사원아이디, 이름 - employees
-- 근무시작일, 근무종료일, 근무당시 직종아이디 - job_history
-- 50번 부서에서 근무한 적이 있는 사원의 아이디와 같은 사원아이디를 가진 사원정보 조인
select e.employee_id, e.first_name, jh.start_date, jh.end_date, jh.job_id
from employees e, job_history jh
where jh.department_id = 50
and e.employee_id = jh.employee_id;

-- 사원들 중에서 자신이 근무하고 있는 직종의 최소급여와 최대급여의 평균값보다 적은 급여를 받는 
-- 사원의 아이디, 이름, 직종아이디, 급여, 최소급여, 최대급여, 최소/최대급여 평균값을 조회하기
-- (평균값은 (최소급여+최대급여)/2로 구한다)
-- 검색조건 : 최소/최대급여의 평균값보다 급여가 적은 사원(jobs, employees)
-- 사원의 아이디, 이름, 직종아이디, 급여 -- employees
-- 최소급여, 최대급여, 최소/최대급여 평균값 - jobs
-- 사원이 수행하고 있는 직종의 직종아이디와 같은 직종아이디를 가진 직종정보 조인
 select e.employee_id, e.first_name, e.job_id, e.salary,
        j.min_salary, j.max_salary, (j.min_salary + j.max_salary)/2 avg
 from jobs j, employees e
 where e.salary < (j.min_salary + j.max_salary)/2
 and e.job_id = j.job_id
 order by e.employee_id asc;