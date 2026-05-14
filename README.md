<img width="853" height="497" alt="image" src="https://github.com/user-attachments/assets/45e4511d-4e3b-4f2b-9f93-8ec421ded074" />

**🚀 Spring MVC Framework Architecture**
Spring Web MVC 프레임워크의 핵심 동작 원리에 따라 설계된 프로젝트입니다.

클라이언트 요청부터 DB 연동, 최종 View 렌더링까지의 전 과정을 효율적으로 관리합니다.

**🏗 System Architecture (Execution Flow)**
프로젝트의 전체적인 동작 흐름은 Front Controller 패턴을 준수합니다.

1️⃣ Request Lifecycle
Client Request

클라이언트로부터 *.do 형식의 요청 진입 (예: login.do, list.do)

DispatcherServlet

모든 요청을 중앙에서 집중 제어하는 Front Controller 역할 수행

HandlerMapping

@RequestMapping 등 어노테이션을 기반으로 최적의 컨트롤러 탐색

Controller & Service

Controller: 요청 수신 및 로직 호출 후 ModelAndView 반환

Service (@Service): 실제 비즈니스 로직 처리 계층

Repository (@Repository): DB와 상호작용하는 DAO 계층

ViewResolver

InternalResourceViewResolver 설정(prefix, suffix)에 따라 물리적 경로 탐색

Response

가공된 Model 데이터를 View에 전달하여 최종 화면 렌더링

**⚙️ Initialization Process (Server Startup)**
서버 구동 시 안정적인 서비스 제공을 위해 아래와 같은 초기화 과정을 거칩니다.

1. web.xml 로딩

Listener, Filter, Servlet 생성 및 인프라 설정 수행

2. Spring Bean 설정

설정 문서 로딩을 통한 WebApplicationContext 생성

3. Component Scan

<context:component-scan>으로 관리 대상 객체(@Bean) 자동 등록

4. DI (Dependency Injection)

객체 간 의존성 주입 완료 및 시스템 사전 초기화

📂 Project Layered Structure
Presentation Layer: Controller — 사용자 요청 접수 및 응답 제어

Business Layer: Service — 핵심 비즈니스 로직 수행

Persistence Layer: Repository — 데이터베이스 접근 (DAO)

Database: DB — 데이터 영구 저장소
