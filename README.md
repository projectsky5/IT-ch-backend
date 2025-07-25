**IT-ch: Backend-система для сопровождения учебного процесса**

---

  Данный проект представляет собой дипломную работу, выполненную в рамках обучения в НИУ ВШЭ. Он предназначен для автоматизации и сопровождения учебного процесса, обеспечивая надежную backend-инфраструктуру для приложения IT-ch.

---

**Архитектура**

  Проект построен по микросервисной архитектуре и включает два основных сервиса:

	•	IT-ch-backend — основной микросервис, реализующий бизнес-логику и работу с данными;
 
	•	IT-ch-auth — микросервис авторизации и аутентификации.

---

**Основной функционал**

**IT-ch-backend**

	•	Полная реализация CRUD-операций;
 
	•	Проверка валидности JWT-токена на каждом запросе;
 
	•	Конфигурация Spring Security для разграничения доступа;
 
	•	Взаимодействие с микросервисом авторизации через REST API (RestTemplate).

**IT-ch-auth** - https://github.com/projectsky5/IT-ch-Auth

	•	Генерация и выдача JWT и Refresh-токенов;
 
	•	Обновление Refresh-токенов;
 
	•	Обработка ошибок авторизации;
 
	•	Разграничение доступа по ролям;
 
	•	Отправка кода подтверждения на почту и его валидация;
 
	•	Интеграция с основным микросервисом по REST.

---

**Технологии**

  •	Java 21;
  
  •	Apache Maven;
  
  •	Spring Boot 3 (Web, Spring Data JPA, Security, Validation, Mail, jjwt, lombok);
  
  •	REST API;
  
  •	RestTemplate;
  
  •	PostgreSQL;
  
  •	JWT (Json Web Token);
  
  •	SMTP.

---

**Демонстрация**



https://github.com/user-attachments/assets/e93a96ef-c632-4aa9-836b-d2b84bec0761

